package tacirogluapt.dao;

import java.util.List;

import tacirogluapt.model.Daire;

public interface DaireDAO {
	public void kaydetDaire(Daire daire);
	public List<Daire> daireListele();
	public boolean daireGuncelle(Daire daire);
	public boolean daireSil(Daire daire);
	public List<Daire> daireAra(long daireNo);
	public List<Daire> borcListele();
}
