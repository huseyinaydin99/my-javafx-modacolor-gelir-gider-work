package tacirogluapt.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import tacirogluapt.model.Gider;
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
	public List<Gider> giderListele() {
		this.session = this.sessionFactory.getCurrentSession();
		Query query = this.session.createQuery("FROM Gider as g");
		return query.list();
	}

	@Override
	public boolean giderGuncelle(Gider gider) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.update(gider);
		return true;
	}

	@Override
	public void giderSil(Gider gider) {
		this.session = this.sessionFactory.getCurrentSession();
		this.session.delete(gider);
	}
	
}
