package com.modacolor.dao;

import java.util.List;

import com.modacolor.model.Gider;

public interface GiderDAO {
	public void giderKaydet(Gider gider);
	public List<Gider> giderCek();
	public Gider giderCek(Long id);
	public void giderGuncelle(Gider gider);
	public void giderSil(Gider gider);
}
