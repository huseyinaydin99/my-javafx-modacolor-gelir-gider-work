package tacirogluapt.model;

import java.util.Date;

public class Gider {
	private long giderId;
	private String giderAdi;
	private String giderAciklamasi;
	private long giderOdeme;
	private Date giderTarihi;
	
	public Gider() {
	}
	public Gider(long giderId, String giderAdi, String giderAciklamasi, long giderOdeme, Date giderTarihi) {
		super();
		this.giderId = giderId;
		this.giderAdi = giderAdi;
		this.giderAciklamasi = giderAciklamasi;
		this.giderOdeme = giderOdeme;
		this.giderTarihi = giderTarihi;
	}
	public long getGiderId() {
		return giderId;
	}
	public void setGiderId(long giderId) {
		this.giderId = giderId;
	}
	public String getGiderAdi() {
		return giderAdi;
	}
	public void setGiderAdi(String giderAdi) {
		this.giderAdi = giderAdi;
	}
	public String getGiderAciklamasi() {
		return giderAciklamasi;
	}
	public void setGiderAciklamasi(String giderAciklamasi) {
		this.giderAciklamasi = giderAciklamasi;
	}
	public long getGiderOdeme() {
		return giderOdeme;
	}
	public void setGiderOdeme(long giderOdeme) {
		this.giderOdeme = giderOdeme;
	}
	public Date getGiderTarihi() {
		return giderTarihi;
	}
	public void setGiderTarihi(Date giderTarihi) {
		this.giderTarihi = giderTarihi;
	}
	
}
