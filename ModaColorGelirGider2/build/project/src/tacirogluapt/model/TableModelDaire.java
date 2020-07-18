package tacirogluapt.model;

import javafx.beans.property.SimpleStringProperty;

public class TableModelDaire {
	private SimpleStringProperty daireId;
	private SimpleStringProperty daireNo;
	private SimpleStringProperty tcNo;
	private SimpleStringProperty ad;
	private SimpleStringProperty soyad;
	private SimpleStringProperty odenenMiktar;
	private SimpleStringProperty odemeTarihi;
	
	public TableModelDaire(String daireId,String daireNo,String tcNo,String ad,String soyad,String odenenMiktar,String odemeTarihi) {
		this.daireId = new SimpleStringProperty(daireId);
		this.daireNo = new SimpleStringProperty(daireNo);
		this.tcNo = new SimpleStringProperty(tcNo);
		this.ad = new SimpleStringProperty(ad);
		this.soyad = new SimpleStringProperty(soyad);
		this.odenenMiktar = new SimpleStringProperty(odenenMiktar);
		this.odemeTarihi = new SimpleStringProperty(odemeTarihi);
	}

	public void setDaireId(String daireId) {
		this.daireId.set(daireId);
	}

	public void setDaireNo(String daireNo) {
		this.daireNo.set(daireNo);
	}

	public void setTcNo(String tcNo) {
		this.tcNo.set(tcNo);
	}

	public void setAd(String ad) {
		this.ad.set(ad);
	}

	public void setSoyad(String soyad) {
		this.soyad.set(soyad);
	}

	public void setOdenenMiktar(String odenenMiktar) {
		this.odenenMiktar.set(odenenMiktar);
	}

	public void setOdemeTarihi(String odemeTarihi) {
		this.odemeTarihi.set(odemeTarihi);
	}

	public String getDaireId() {
		return daireId.get();
	}

	public String getDaireNo() {
		return daireNo.get();
	}

	public String getTcNo() {
		return tcNo.get();
	}

	public String getAd() {
		return ad.get();
	}

	public String getSoyad() {
		return soyad.get();
	}

	public String getOdenenMiktar() {
		return odenenMiktar.get();
	}

	public String getOdemeTarihi() {
		return odemeTarihi.get();
	}
	
	
}
