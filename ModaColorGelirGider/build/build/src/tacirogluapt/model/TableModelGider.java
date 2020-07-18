package tacirogluapt.model;

import javafx.beans.property.SimpleStringProperty;

public class TableModelGider {
	private SimpleStringProperty gelirId;
	private SimpleStringProperty giderAdi;
	private SimpleStringProperty giderAciklamasi;
	private SimpleStringProperty giderOdeme;
	private SimpleStringProperty giderTarihi;
	public TableModelGider(String gelirId, String giderAdi,String giderAciklamasi, String giderOdeme, String giderTarihi) {
		this.gelirId = new SimpleStringProperty(gelirId);
		this.giderAdi = new SimpleStringProperty(giderAdi);
		this.giderAciklamasi = new SimpleStringProperty(giderAciklamasi);
		this.giderOdeme = new SimpleStringProperty(giderOdeme);
		this.giderTarihi = new SimpleStringProperty(giderTarihi);
	}
	public String getGelirId() {
		return gelirId.get();
	}
	public void setGelirId(String gelirId) {
		this.gelirId.set(gelirId);
	}
	public String getGiderAdi() {
		return giderAdi.get();
	}
	public void setGiderAdi(String giderAdi) {
		this.giderAdi.set(giderAdi);
	}
	public String getGiderAciklamasi() {
		return giderAciklamasi.get();
	}
	public void setGiderAciklamasi(String giderAciklamasi) {
		this.giderAciklamasi.set(giderAciklamasi);
	}
	public String getGiderOdeme() {
		return giderOdeme.get();
	}
	public void setGiderOdeme(String giderOdeme) {
		this.giderOdeme.set(giderOdeme);
	}
	public String getGiderTarihi() {
		return giderTarihi.get();
	}
	public void setGiderTarihi(String giderTarihi) {
		this.giderTarihi.set(giderTarihi);
	}
	
	
}
