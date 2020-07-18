package tacirogluapt.service;

import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tacirogluapt.dao.DaireDAO;
import tacirogluapt.model.Daire;
@Service
public class DaireServiceImpl implements DaireService {

	@Autowired
	@Qualifier("daireDAO")
	private DaireDAO daireDAO;
	
	@Transactional
	@Override
	public void kaydetDaire(Daire daire) {
		this.daireDAO.kaydetDaire(daire);
	}
	
	public DaireDAO getDaireDAO() {
		return daireDAO;
	}
	public void setDaireDAO(DaireDAO daireDAO) {
		this.daireDAO = daireDAO;
	}

	@Transactional
	@Override
	public List<Daire> daireListele() {
		return this.daireDAO.daireListele();
	}

	@Transactional
	@Override
	public boolean daireGuncelle(Daire daire) {
		return this.daireDAO.daireGuncelle(daire);
	}

	@Transactional
	@Override
	public boolean daireSil(Daire daire) {
		return this.daireDAO.daireSil(daire);
	}

	@Transactional
	@Override
	public List<Daire> daireAra(long daireNo) {
		return this.daireDAO.daireAra(daireNo);
	}
	
}
