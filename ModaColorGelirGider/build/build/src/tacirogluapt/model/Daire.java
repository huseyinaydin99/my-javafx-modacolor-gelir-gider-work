package tacirogluapt.model;

import java.util.Date;

public class Daire {
	private long daireId;
	private long daireNo;
	private long tcNo;
	private String ad;
	private String soyad;
	private long odenenMiktar;
	private Date odemeTarihi;
	private long borc;
	
	public Daire() {
	}
	
	public Daire(long daireId, long daireNo, long tcNo, String ad, String soyad, long odenenMiktar, Date odemeTarihi) {
		super();
		this.daireId = daireId;
		this.daireNo = daireNo;
		this.tcNo = tcNo;
		this.ad = ad;
		this.soyad = soyad;
		this.odenenMiktar = odenenMiktar;
		this.odemeTarihi = odemeTarihi;
	}
	public long getDaireId() {
		return daireId;
	}
	public void setDaireId(long daireId) {
		this.daireId = daireId;
	}
	public long getTcNo() {
		return tcNo;
	}
	public void setTcNo(long tcNo) {
		this.tcNo = tcNo;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public long getOdenenMiktar() {
		return odenenMiktar;
	}
	public void setOdenenMiktar(long odenenMiktar) {
		this.odenenMiktar = odenenMiktar;
	}
	public Date getOdemeTarihi() {
		return odemeTarihi;
	}
	public void setOdemeTarihi(Date odemeTarihi) {
		this.odemeTarihi = odemeTarihi;
	}
	public long getDaireNo() {
		return daireNo;
	}
	public void setDaireNo(long daireNo) {
		this.daireNo = daireNo;
	}

	public long getBorc() {
		return borc;
	}

	public void setBorc(long borc) {
		this.borc = borc;
	}
	
	
}
