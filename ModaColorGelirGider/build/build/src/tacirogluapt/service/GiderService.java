package tacirogluapt.service;

import java.util.List;

import tacirogluapt.model.Gider;

public interface GiderService {
	public void kaydetGider(Gider daire);
	public List<Gider> giderListele();
	public boolean giderGuncelle(Gider gider);
	public void giderSil(Gider gider);
}
