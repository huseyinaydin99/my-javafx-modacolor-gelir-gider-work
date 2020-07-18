package com.modacolor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modacolor.dao.GelirDAO;
import com.modacolor.model.Gelir;

@Service
public class GelirServiceImpl implements GelirService {

	@Autowired
	@Qualifier("gelirDAO")
	private GelirDAO gelirDAO;
	
	@Transactional
	@Override
	public void gelirKaydet(Gelir gelir) {
		this.gelirDAO.gelirKaydet(gelir);
	}

	public GelirDAO getGelirDAO() {
		return gelirDAO;
	}

	public void setGelirDAO(GelirDAO gelirDAO) {
		this.gelirDAO = gelirDAO;
	}

	@Transactional
	@Override
	public List<Gelir> gelirCek() {
		return this.gelirDAO.gelirCek();
	}

	@Transactional
	@Override
	public Gelir gelirCek(Long id) {
		return this.gelirDAO.gelirCek(id);
	}

	@Transactional
	@Override
	public void gelirGuncelle(Gelir gelir) {
		this.gelirDAO.gelirGuncelle(gelir);
	}

	@Transactional
	@Override
	public void gelirSil(Gelir gelir) {
		this.gelirDAO.gelirSil(gelir);
	}

}
