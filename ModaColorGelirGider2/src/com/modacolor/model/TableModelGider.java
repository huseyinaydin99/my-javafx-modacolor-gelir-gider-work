package com.modacolor.model;

import javafx.beans.property.SimpleStringProperty;

public class TableModelGider {
	private SimpleStringProperty giderID;
	private SimpleStringProperty giderMiktar;
	private SimpleStringProperty giderAciklama;
	private SimpleStringProperty giderTarih;
	public TableModelGider() {
		super();
	}
	public TableModelGider(String giderID, String giderMiktar,
			String giderAciklama, String giderTarih) {
		super();
		this.giderID = new SimpleStringProperty(giderID);
		this.giderMiktar = new SimpleStringProperty(giderMiktar);
		this.giderAciklama = new SimpleStringProperty(giderAciklama);
		this.giderTarih = new SimpleStringProperty(giderTarih);
	}
	public String getGiderID() {
		return giderID.get();
	}
	public void setGiderID(String giderID) {
		this.giderID.set(giderID);
	}
	public String getGiderMiktar() {
		return giderMiktar.get();
	}
	public void setGiderMiktar(String giderMiktar) {
		this.giderMiktar.set(giderMiktar);
	}
	public String getGiderAciklama() {
		return giderAciklama.get();
	}
	public void setGiderAciklama(String giderAciklama) {
		this.giderAciklama.set(giderAciklama);
	}
	public String getGiderTarih() {
		return giderTarih.get();
	}
	public void setGiderTarih(String giderTarih) {
		this.giderTarih.set(giderTarih);
	}
	
}
