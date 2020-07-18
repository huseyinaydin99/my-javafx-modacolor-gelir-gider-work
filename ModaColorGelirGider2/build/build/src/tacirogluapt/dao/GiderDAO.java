package tacirogluapt.dao;

import java.util.List;

import tacirogluapt.model.Gider;

public interface GiderDAO {
	public void giderKaydet(Gider gider);
	public List<Gider> giderListele();
	public boolean giderGuncelle(Gider gider);
	public void giderSil(Gider gider);
}
