package com.modacolor.model;

import java.util.Date;

public class Gelir {
	private Long gelirID;
	private Double gelirMiktar;
	private String gelirAciklama;
	private Date gelirTarih;
	public Gelir() {
		super();
		this.gelirTarih = new Date();
	}
	public Long getGelirID() {
		return gelirID;
	}
	public void setGelirID(Long gelirID) {
		this.gelirID = gelirID;
	}
	public Double getGelirMiktar() {
		return gelirMiktar;
	}
	public void setGelirMiktar(Double gelirMiktar) {
		this.gelirMiktar = gelirMiktar;
	}
	public String getGelirAciklama() {
		return gelirAciklama;
	}
	public void setGelirAciklama(String gelirAciklama) {
		this.gelirAciklama = gelirAciklama;
	}
	public Date getGelirTarih() {
		return gelirTarih;
	}
	public void setGelirTarih(Date gelirTarih) {
		this.gelirTarih = gelirTarih;
	}
	
}
