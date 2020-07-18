package com.modacolor.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.modacolor.comparator.GelirComparator;
import com.modacolor.comparator.GiderComparator;
import com.modacolor.model.Gelir;
import com.modacolor.model.Gider;
@Repository
public class GiderDAOImpl implements GiderDAO {
	@Autowired
	@Qualifier("hibernate5AnnotatedSessionFactory")
	private SessionFactory sessionFactory;
	private Session session;
	@Override
	public void giderKaydet(Gider gider) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.save(gider);
	}

	@Override
	public List<Gider> giderCek() {
		this.session = this.sessionFactory.getCurrentSession();
		Criteria criteria = this.session.createCriteria(Gider.class);
		List<Gider> giders = criteria.list();
		Collections.sort(giders, new GiderComparator());
		return criteria.list();
	}

	@Override
	public Gider giderCek(Long id) {
		this.session = this.sessionFactory.getCurrentSession();
		Criteria criteria = this.session.createCriteria(Gider.class);
		criteria.add(Restrictions.eq("giderID", id));
		List<Gider> giders = criteria.list();
		Gider gider = giders.get(0);
		return gider;
	}

	@Override
	public void giderGuncelle(Gider gider) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.update(gider);
	}

	@Override
	public void giderSil(Gider gider) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.delete(gider);
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

}
