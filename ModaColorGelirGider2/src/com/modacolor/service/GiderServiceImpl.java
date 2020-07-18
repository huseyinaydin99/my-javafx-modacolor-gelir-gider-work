package com.modacolor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modacolor.dao.GiderDAO;
import com.modacolor.model.Gider;

@Service
public class GiderServiceImpl implements GiderService {

	@Autowired
	@Qualifier("giderDAO")
	private GiderDAO giderDAO;
	
	@Transactional
	@Override
	public void giderKaydet(Gider gider) {
		this.giderDAO.giderKaydet(gider);
	}

	@Transactional
	@Override
	public List<Gider> giderCek() {
		// TODO Auto-generated method stub
		return this.giderDAO.giderCek();
	}

	@Transactional
	@Override
	public Gider giderCek(Long id) {
		// TODO Auto-generated method stub
		return this.giderDAO.giderCek(id);
	}
	
	@Transactional
	@Override
	public void giderGuncelle(Gider gider) {
		this.giderDAO.giderGuncelle(gider);
	}

	@Transactional
	@Override
	public void giderSil(Gider gider) {
		this.giderDAO.giderSil(gider);
	}

	public GiderDAO getGiderDAO() {
		return giderDAO;
	}

	public void setGiderDAO(GiderDAO giderDAO) {
		this.giderDAO = giderDAO;
	}

}
