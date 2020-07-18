package com.modacolor.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.modacolor.comparator.GelirComparator;
import com.modacolor.model.Gelir;
@Repository
public class GelirDAOImpl implements GelirDAO {
	@Autowired
	@Qualifier("hibernate5AnnotatedSessionFactory")
	private SessionFactory sessionFactory;
	private Session session;
	
	@Override
	public void gelirKaydet(Gelir gelir) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.save(gelir);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public List<Gelir> gelirCek() {
		this.session = this.sessionFactory.getCurrentSession();
		Criteria criteria = this.session.createCriteria(Gelir.class);
		List<Gelir> gelirs = criteria.list();
		Collections.sort(gelirs, new GelirComparator());
		return criteria.list();
	}

	@Override
	public Gelir gelirCek(Long id) {
		this.session = this.sessionFactory.getCurrentSession();
		Criteria criteria = this.session.createCriteria(Gelir.class);
		criteria.add(Restrictions.eq("gelirID", id));
		List<Gelir> gelirs = criteria.list();
		Gelir gelir = gelirs.get(0);
		return gelir;
	}

	@Override
	public void gelirGuncelle(Gelir gelir) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.update(gelir);
	}

	@Override
	public void gelirSil(Gelir gelir) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.delete(gelir);
	}

}
