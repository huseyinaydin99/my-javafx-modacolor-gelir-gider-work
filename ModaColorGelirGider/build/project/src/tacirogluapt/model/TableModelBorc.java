package tacirogluapt.model;

import javafx.beans.property.SimpleStringProperty;

public class TableModelBorc {
	private SimpleStringProperty daireNo;
	private SimpleStringProperty ad;
	private SimpleStringProperty soyad;
	private SimpleStringProperty yatan;
	private SimpleStringProperty borc;
	
	public TableModelBorc(String daireNo,String ad,String soyad,String yatan,String borc) {
		this.daireNo = new SimpleStringProperty(daireNo);
		this.ad = new SimpleStringProperty(ad);
		this.soyad = new SimpleStringProperty(soyad);
		this.yatan = new SimpleStringProperty(yatan);
		this.borc = new SimpleStringProperty(borc);
	}

	public void setDaireNo(String daireNo) {
		this.daireNo.set(daireNo);
	}

	public void setAd(String ad) {
		this.ad.set(ad);
	}

	public void setSoyad(String soyad) {
		this.soyad.set(soyad);
	}

	public String getDaireNo() {
		return daireNo.get();
	}

	public String getAd() {
		return ad.get();
	}

	public String getSoyad() {
		return soyad.get();
	}

	public String getYatan() {
		return yatan.get();
	}

	public void setYatan(String yatan) {
		this.yatan.set(yatan);
	}

	public String getBorc() {
		return borc.get();
	}

	public void setBorc(String borc) {
		this.borc.set(borc);
	}

}
