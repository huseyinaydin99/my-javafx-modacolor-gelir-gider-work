package com.modacolor.dao;

import java.util.List;

import com.modacolor.model.Gelir;

public interface GelirDAO {
	public void gelirKaydet(Gelir gelir);
	public List<Gelir> gelirCek();
	public Gelir gelirCek(Long id);
	public void gelirGuncelle(Gelir gelir);
	public void gelirSil(Gelir gelir);
}
