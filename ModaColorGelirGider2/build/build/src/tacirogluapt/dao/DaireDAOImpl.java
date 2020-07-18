package tacirogluapt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import tacirogluapt.model.Daire;
@Repository
public class DaireDAOImpl implements DaireDAO {

	@Autowired
	@Qualifier("hibernate5AnnotatedSessionFactory")
	private SessionFactory sessionFactory;
	private Session session;
	
	@Override
	public void kaydetDaire(Daire daire) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.save(daire);
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
	public List<Daire> daireListele() {
		this.session = this.sessionFactory.getCurrentSession();
		Query query = this.session.createQuery("FROM Daire as d");
		return query.list();
	}
	@Override
	public boolean daireGuncelle(Daire daire) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.update(daire);
		return true;
	}
	@Override
	public boolean daireSil(Daire daire) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.delete(daire);
		return false;
	}
	@Override
	public List<Daire> daireAra(long daireNo) {
		this.session = this.sessionFactory.getCurrentSession();
		Criteria criteria = this.session.createCriteria(Daire.class);
		criteria.add(Restrictions.eq("daireNo", daireNo));
		
		return criteria.list();
	}
	@Override
	public List<Daire> borcListele() {
		
		return null;
	}

}
