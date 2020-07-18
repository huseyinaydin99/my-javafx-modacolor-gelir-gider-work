package tacirogluapt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tacirogluapt.dao.DaireDAO;
import tacirogluapt.dao.GiderDAO;
import tacirogluapt.model.Daire;
import tacirogluapt.model.Gider;
@Service
public class GiderServiceImpl implements GiderService {

	@Autowired
	@Qualifier("giderDAO")
	private GiderDAO giderDAO;
	
	@Transactional
	@Override
	public void kaydetGider(Gider gider) {
		this.giderDAO.giderKaydet(gider);
	}
	public GiderDAO getGiderDAO() {
		return giderDAO;
	}
	public void setGiderDAO(GiderDAO giderDAO) {
		this.giderDAO = giderDAO;
	}
	@Transactional
	@Override
	public List<Gider> giderListele() {
		return this.giderDAO.giderListele();
	}
	@Transactional
	@Override
	public boolean giderGuncelle(Gider gider) {
		return this.giderDAO.giderGuncelle(gider);
	}
	@Transactional
	@Override
	public void giderSil(Gider gider) {
		this.giderDAO.giderSil(gider);
	}
	
	
}
