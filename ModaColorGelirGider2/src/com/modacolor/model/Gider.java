package com.modacolor.model;

import java.util.Date;

public class Gider {
	private Long giderID;
	private Double giderMiktar;
	private String giderAciklama;
	private Date giderTarih;
	public Gider() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Gider(Long giderID, Double giderMiktar, String giderAciklama, Date giderTarih) {
		super();
		this.giderID = giderID;
		this.giderMiktar = giderMiktar;
		this.giderAciklama = giderAciklama;
		this.giderTarih = giderTarih;
	}
	public Long getGiderID() {
		return giderID;
	}
	public void setGiderID(Long giderID) {
		this.giderID = giderID;
	}
	public Double getGiderMiktar() {
		return giderMiktar;
	}
	public void setGiderMiktar(Double giderMiktar) {
		this.giderMiktar = giderMiktar;
	}
	public String getGiderAciklama() {
		return giderAciklama;
	}
	public void setGiderAciklama(String giderAciklama) {
		this.giderAciklama = giderAciklama;
	}
	public Date getGiderTarih() {
		return giderTarih;
	}
	public void setGiderTarih(Date giderTarih) {
		this.giderTarih = giderTarih;
	}
	
}
