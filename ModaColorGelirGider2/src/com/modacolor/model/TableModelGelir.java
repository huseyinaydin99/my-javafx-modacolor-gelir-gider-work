package com.modacolor.model;

import javafx.beans.property.SimpleStringProperty;

public class TableModelGelir {
	private SimpleStringProperty gelirID;
	private SimpleStringProperty gelirMiktar;
	private SimpleStringProperty gelirAciklama;
	private SimpleStringProperty gelirTarih;
	public TableModelGelir() {
		super();
	}
	public TableModelGelir(String gelirID, String gelirMiktar,
			String gelirAciklama, String gelirTarih) {
		super();
		this.gelirID = new SimpleStringProperty(gelirID);
		this.gelirMiktar = new SimpleStringProperty(gelirMiktar);
		this.gelirAciklama = new SimpleStringProperty(gelirAciklama);
		this.gelirTarih = new SimpleStringProperty(gelirTarih);
	}
	public String getGelirID() {
		return gelirID.get();
	}
	public void setGelirID(String gelirID) {
		this.gelirID.set(gelirID);
	}
	public String getGelirMiktar() {
		return gelirMiktar.get();
	}
	public void setGelirMiktar(String gelirMiktar) {
		this.gelirMiktar.set(gelirMiktar);
	}
	public String getGelirAciklama() {
		return gelirAciklama.get();
	}
	public void setGelirAciklama(String gelirAciklama) {
		this.gelirAciklama.set(gelirAciklama);
	}
	public String getGelirTarih() {
		return gelirTarih.get();
	}
	public void setGelirTarih(String gelirTarih) {
		this.gelirTarih.set(gelirTarih);
	}
	
}
