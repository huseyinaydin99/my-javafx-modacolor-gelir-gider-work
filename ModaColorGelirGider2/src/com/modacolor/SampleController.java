package com.modacolor;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.modacolor.model.Gelir;
import com.modacolor.model.Gider;
import com.modacolor.model.TableModelGelir;
import com.modacolor.model.TableModelGider;
import com.modacolor.service.GelirService;
import com.modacolor.service.GelirServiceImpl;
import com.modacolor.service.GiderService;
import com.modacolor.service.GiderServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController implements Initializable {
	private static ApplicationContext applicationContext;
	private static GelirService gelirService;

	private static GiderService giderService;
	private boolean ilkUyari = false;

	@FXML
	private TextField gelirIDText;
	@FXML
	private TextField giderIDText;
	@FXML
	private TextField gelirMiktarText, giderMiktarText;
	@FXML
	private TextField gelirAciklamaText, giderAciklamaText;
	@FXML
	private Label gelirTarihLabel, giderTarihLabel, gunTopGelir, gunTopGider;
	@FXML
	private Button gelirTarih;
	@FXML
	private Button gelirGuncelle;
	@FXML
	private TextField araId, araId2, gelirToplam, giderToplam;
	@FXML
	private TableView<TableModelGelir> gelirTableView;
	@FXML
	private TableView<TableModelGider> giderTableView;
	@FXML
	private ComboBox<String> aylar, yillar;

	private ObservableList<TableModelGelir> tabloDeposu;
	private ObservableList<TableModelGider> tabloDeposu2;
	private ObservableList<String> comboDeposu, comboDeposu2;

	private Long kayitIndex = 1L, kayitIndex2 = 1L;

	static {
		System.out.println("static");
		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext("springConfig.xml");
			System.out.println("spring");
		}
		if (gelirService == null)
			gelirService = new GelirServiceImpl();
		if (giderService == null)
			giderService = new GiderServiceImpl();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialize");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		gelirTarihLabel.setText(df.format(new Date()) + " Bugun");
		giderTarihLabel.setText(df.format(new Date()) + " Bugun");
		gelirCek();
		gelirCekTextlere();

		giderCek();
		giderCekTextlere();
		this.gelirTableView.setSelectionModel(null);
		this.comboDeposu = FXCollections.observableArrayList();
		this.comboDeposu2 = FXCollections.observableArrayList();
		byte i = 1;
		while (i < 13) {
			if (i < 10)
				this.comboDeposu.add("0" + String.valueOf(i));
			else if (i >= 10)
				this.comboDeposu.add(String.valueOf(i));
			i++;
		}
		int j = 2019;
		while (j < 2500) {
			this.comboDeposu2.add(String.valueOf(j));
			j++;
		}
		if (aylar == null)
			System.out.println("aylar null");
		if (this.comboDeposu == null)
			System.out.println("combo deposu null");
		this.aylar.setItems(this.comboDeposu);
		this.yillar.setItems(this.comboDeposu2);
		this.gunlukGelirTopla();
		this.gunlukGiderTopla();
		this.ilkUyari = true;
	}

	public void aylikHesapla() {
		String seciliAy = this.aylar.getValue().toString();
		List<Gelir> gelirListesi = this.gelirService.gelirCek();
		Double gelirToplam = 0.0;
		for (int i = 0; i < gelirListesi.size(); i++) {
			Gelir gelir = gelirListesi.get(i);
			String ay = "";
			String yil = "";
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String gelirTarih = df.format(gelir.getGelirTarih());
			ay += String.valueOf(gelirTarih.charAt(3)) + gelirTarih.charAt(4);
			yil += String.valueOf(gelirTarih.charAt(6)) + gelirTarih.charAt(7) + gelirTarih.charAt(8)
					+ gelirTarih.charAt(9);
			if (ay.equals(this.aylar.getValue().toString()) && yil.equals(this.yillar.getValue().toString())) {
				gelirToplam += gelir.getGelirMiktar();
			}
			System.out.println("nani " + ay);
		}
		List<Gider> giderListesi = this.giderService.giderCek();
		Double giderToplam = 0.0;
		for (int i = 0; i < giderListesi.size(); i++) {
			Gider gider = giderListesi.get(i);
			String ay = "";
			String yil = "";
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String giderTarih = df.format(gider.getGiderTarih());
			ay += String.valueOf(giderTarih.charAt(3)) + giderTarih.charAt(4);
			yil += String.valueOf(giderTarih.charAt(6)) + giderTarih.charAt(7) + giderTarih.charAt(8)
					+ giderTarih.charAt(9);
			if (ay.equals(this.aylar.getValue().toString()) && yil.equals(this.yillar.getValue().toString())) {
				giderToplam += gider.getGiderMiktar();
			}
			System.out.println("mani " + ay);
		}
		this.gelirToplam.setText(gelirToplam.toString());
		this.giderToplam.setText(giderToplam.toString());
	}

	private void gelirCek() {
		List<Gelir> gelirDeposu = this.gelirService.gelirCek();
		tabloDeposu = FXCollections.observableArrayList();
		for (int i = 0; i < gelirDeposu.size(); i++) {
			Gelir gelir = gelirDeposu.get(i);
			String gelirId = gelir.getGelirID().toString();
			String gelirMiktar = gelir.getGelirMiktar().toString();
			String gelirAciklama = gelir.getGelirAciklama();
			Date tarih = gelir.getGelirTarih();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String gelirTarih = df.format(tarih);
			tabloDeposu.add(new TableModelGelir(gelirId, gelirMiktar, gelirAciklama, gelirTarih));
		}
		TableColumn tableColumn1 = new TableColumn<>("Gelir ID");
		tableColumn1.setCellValueFactory(new PropertyValueFactory<TableModelGelir, String>("gelirID"));
		tableColumn1.setMinWidth(175);
		TableColumn tableColumn2 = new TableColumn<>("Gelir Miktari");
		tableColumn2.setCellValueFactory(new PropertyValueFactory<TableModelGelir, String>("gelirMiktar"));
		tableColumn2.setMinWidth(175);
		TableColumn tableColumn3 = new TableColumn<>("Gelir Aciklamasi");
		tableColumn3.setCellValueFactory(new PropertyValueFactory<TableModelGelir, String>("gelirAciklama"));
		tableColumn3.setMinWidth(175);
		TableColumn tableColumn4 = new TableColumn<>("Gelir Tarihi");
		tableColumn4.setCellValueFactory(new PropertyValueFactory<TableModelGelir, String>("gelirTarih"));
		tableColumn4.setMinWidth(175);
		gelirTableView.getColumns().clear();
		gelirTableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4);
		gelirTableView.setItems(tabloDeposu);
	}

	private void giderCek() {
		List<Gider> giderDeposu = this.giderService.giderCek();
		tabloDeposu2 = FXCollections.observableArrayList();
		for (int i = 0; i < giderDeposu.size(); i++) {
			Gider gider = giderDeposu.get(i);
			String giderId = gider.getGiderID().toString();
			String giderMiktar = gider.getGiderMiktar().toString();
			String giderAciklama = gider.getGiderAciklama();
			Date tarih = gider.getGiderTarih();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String giderTarih = df.format(tarih);
			tabloDeposu2.add(new TableModelGider(giderId, giderMiktar, giderAciklama, giderTarih));
		}
		TableColumn tableColumn1 = new TableColumn<>("Gider ID");
		tableColumn1.setCellValueFactory(new PropertyValueFactory<TableModelGelir, String>("giderID"));
		tableColumn1.setMinWidth(175);
		TableColumn tableColumn2 = new TableColumn<>("Gider Miktari");
		tableColumn2.setCellValueFactory(new PropertyValueFactory<TableModelGelir, String>("giderMiktar"));
		tableColumn2.setMinWidth(175);
		TableColumn tableColumn3 = new TableColumn<>("Gider Aciklamasi");
		tableColumn3.setCellValueFactory(new PropertyValueFactory<TableModelGelir, String>("GiderAciklama"));
		tableColumn3.setMinWidth(175);
		TableColumn tableColumn4 = new TableColumn<>("Gider Tarihi");
		tableColumn4.setCellValueFactory(new PropertyValueFactory<TableModelGelir, String>("giderTarih"));
		tableColumn4.setMinWidth(175);
		giderTableView.getColumns().clear();
		giderTableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4);
		giderTableView.setItems(tabloDeposu2);
	}

	private void gelirCekTextlere() {
		System.out.println("burda " + this.kayitIndex);
		Gelir gelir = null;
		try {
			gelir = this.gelirService.gelirCek().get(Integer.parseInt(String.valueOf(this.kayitIndex - 1)));
		} catch (IndexOutOfBoundsException e) {
			this.alaniTemizle();
			if (this.ilkUyari) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Kayit Hakkinda");
				alert.setHeaderText("Uyari");
				alert.setContentText(this.kayitIndex + ". nolu kayit silinmis bir sonrakine gecin!");
				alert.showAndWait();
			}
			System.out.println("1 uyarıyı verdim ----------------");
			return;
		}
		this.gelirIDText.setText(gelir.getGelirID().toString());
		this.gelirMiktarText.setText(gelir.getGelirMiktar().toString());
		this.gelirAciklamaText.setText(gelir.getGelirAciklama());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.gelirTarihLabel.setText(df.format(gelir.getGelirTarih()));
	}

	private void gelirCekTextlere2(Long id) {
		System.out.println("burda " + this.kayitIndex);
		Gelir gelir = null;
		try {
			gelir = this.gelirService.gelirCek(id);
		} catch (IndexOutOfBoundsException e) {
			this.alaniTemizle();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Kayit Hakkinda");
			alert.setHeaderText("Uyarı");
			alert.setContentText(this.kayitIndex + ". nolu kayit silinmis bir sonrakine gecin!");
			alert.showAndWait();
			System.out.println("2 uyarıyı verdim ----------------");
			return;
		}
		this.gelirIDText.setText(gelir.getGelirID().toString());
		this.gelirMiktarText.setText(gelir.getGelirMiktar().toString());
		this.gelirAciklamaText.setText(gelir.getGelirAciklama());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.gelirTarihLabel.setText(df.format(gelir.getGelirTarih()));
	}

	private void giderCekTextlere() {
		System.out.println("burda " + (this.kayitIndex2 - 1));
		Gider gider = null;
		try {
			gider = this.giderService.giderCek().get(Integer.parseInt(String.valueOf(this.kayitIndex2 - 1)));
		} catch (IndexOutOfBoundsException e) {
			this.alaniTemizle2();
			if (this.ilkUyari) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Kayit Hakkinda");
				alert.setHeaderText("Uyari");
				alert.setContentText(this.kayitIndex2 + ". nolu kayit silinmiş bir sonrakine gecin!");
				alert.showAndWait();
			}
			System.out.println("3 uyarıyı verdim ----------------");
			return;
		}
		this.giderIDText.setText(gider.getGiderID().toString());
		this.giderMiktarText.setText(gider.getGiderMiktar().toString());
		this.giderAciklamaText.setText(gider.getGiderAciklama());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.giderTarihLabel.setText(df.format(gider.getGiderTarih()));
	}

	private void giderCekTextlere2(Long id) {
		System.out.println("burda " + (this.kayitIndex2 - 1));
		Gider gider = null;
		try {
			gider = this.giderService.giderCek(id);
		} catch (IndexOutOfBoundsException e) {
			this.alaniTemizle2();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Kayit Hakkinda");
			alert.setHeaderText("Uyari");
			alert.setContentText(this.kayitIndex2 + ". nolu kayit silinmis bir sonrakine gecin!");
			alert.showAndWait();
			System.out.println("4 uyarıyı verdim ----------------");
			return;
		}
		this.giderIDText.setText(gider.getGiderID().toString());
		this.giderMiktarText.setText(gider.getGiderMiktar().toString());
		this.giderAciklamaText.setText(gider.getGiderAciklama());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.giderTarihLabel.setText(df.format(gider.getGiderTarih()));
	}

	public void gelirKaydet() {
		Gelir gelir = new Gelir();
		try {
			gelir.setGelirMiktar(Double.parseDouble(gelirMiktarText.getText()));
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Uyari");
			alert.setHeaderText("Hatali giris!");
			alert.setContentText("Hatali giris yaptiniz lutfen duzeltip tekrar girin!");
			alert.showAndWait();
			return;
		}
		gelir.setGelirAciklama(gelirAciklamaText.getText());
		gelir.setGelirTarih(new Date());
		// if (gelirService != null)
		this.gelirService.gelirKaydet(gelir);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Kayit Bilgisi");
		alert.setHeaderText("Basarili (:");
		alert.setContentText("Girdiginiz gelir kaydi basari ile yapildi!");
		alert.showAndWait();
		System.out.println("kayıt");
		gelirCek();
		this.kayitIndex = (long) this.gelirService.gelirCek().size();
		gelirCekTextlere();
		this.gunlukGelirTopla();
	}

	public void giderKaydet() {
		Gider gider = new Gider();
		try {
			gider.setGiderMiktar(Double.parseDouble(giderMiktarText.getText()));
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Uyari");
			alert.setHeaderText("Hatali giris!");
			alert.setContentText("Hatali giris yaptiniz lutfen duzeltip tekrar girin!");
			alert.showAndWait();
			return;
		}
		gider.setGiderAciklama(giderAciklamaText.getText());
		gider.setGiderTarih(new Date());
		// if (gelirService != null)
		this.giderService.giderKaydet(gider);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Kayit Bilgisi");
		alert.setHeaderText("Basarili (:");
		alert.setContentText("Girdiginiz gelir kaydi basari ile yapildi!");
		alert.showAndWait();
		System.out.println("kayıt");
		this.kayitIndex2 = (long) this.giderService.giderCek().size();
		giderCek();
		giderCekTextlere();
		this.gunlukGiderTopla();
	}

	public void gelirGuncelle() {
		Gelir gelir = this.gelirService.gelirCek(Long.parseLong(this.gelirIDText.getText()));
		gelir.setGelirID(Long.parseLong(this.gelirIDText.getText()));
		gelir.setGelirAciklama(this.gelirAciklamaText.getText());
		String deger = this.gelirTarihLabel.getText();
		String bir = String.valueOf(deger.charAt(0)) + deger.charAt(1);
		String iki = String.valueOf(deger.charAt(3)) + deger.charAt(4);
		String uc = String.valueOf(deger.charAt(6)) + deger.charAt(7) + deger.charAt(8) + deger.charAt(9);

		// gelir.setGelirTarih(new Date(Integer.parseInt(bir), Integer.parseInt(iki),
		// Integer.parseInt(uc)));
		gelir.setGelirMiktar(Double.parseDouble(this.gelirMiktarText.getText()));
		this.gelirService.gelirGuncelle(gelir);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Guncelleme Bilgisi");
		alert.setHeaderText("Basarili (:");
		alert.setContentText("Girdiginiz guncelleme basari ile yapildi!");
		alert.showAndWait();
		gelirCek();
		gelirCekTextlere();
		this.gunlukGelirTopla();
	}

	public void giderGuncelle() {
		Gider gider = this.giderService.giderCek(Long.parseLong(this.giderIDText.getText()));
		gider.setGiderID(Long.parseLong(this.giderIDText.getText()));
		gider.setGiderAciklama(this.giderAciklamaText.getText());
		String deger = this.giderTarihLabel.getText();
		String bir = String.valueOf(deger.charAt(0)) + deger.charAt(1);
		String iki = String.valueOf(deger.charAt(3)) + deger.charAt(4);
		String uc = String.valueOf(deger.charAt(6)) + deger.charAt(7) + deger.charAt(8) + deger.charAt(9);

		// gelir.setGelirTarih(new Date(Integer.parseInt(bir), Integer.parseInt(iki),
		// Integer.parseInt(uc)));
		gider.setGiderMiktar(Double.parseDouble(this.giderMiktarText.getText()));
		this.giderService.giderGuncelle(gider);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Guncelleme Bilgisi");
		alert.setHeaderText("Basarili (:");
		alert.setContentText("Girdiginiz guncelleme basari ile yapildi!");
		alert.showAndWait();
		giderCek();
		giderCekTextlere();
		this.gunlukGiderTopla();
	}

	public void ilkeGit() {
		this.kayitIndex = 1L;
		gelirCek();
		gelirCekTextlere();
	}

	public void ilkeGit2() {
		this.kayitIndex2 = 1L;
		giderCek();
		giderCekTextlere();
	}

	public void sonaGit() {
		this.kayitIndex = (long) this.gelirService.gelirCek().size();
		gelirCek();
		gelirCekTextlere();
	}

	public void sonaGit2() {
		this.kayitIndex2 = (long) this.giderService.giderCek().size();
		giderCek();
		giderCekTextlere();
	}

	public void geriGit() {
		if (this.kayitIndex < 0) {
			this.kayitIndex++;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uyari");
			alert.setHeaderText("Uyari mesaji!");
			alert.setContentText("Ilk kayda geldiniz");
			alert.showAndWait();
		}
		if (this.kayitIndex > 1)
			this.kayitIndex--;
		if (this.kayitIndex == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uyari");
			alert.setHeaderText("Uyari mesaji!");
			alert.setContentText("Ilk kayda geldiniz");
			alert.showAndWait();
			this.kayitIndex = 1L;
		}
		if (this.kayitIndex == 1) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uyari");
			alert.setHeaderText("Uyari mesaji!");
			alert.setContentText("Ilk kayda geldiniz");
			alert.showAndWait();
			this.kayitIndex = 1L;
		}
		gelirCek();
		gelirCekTextlere();
	}

	public void geriGit2() {
		if (this.kayitIndex2 < 0) {
			this.kayitIndex2++;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uyari");
			alert.setHeaderText("Uyari mesaji!");
			alert.setContentText("Ilk kayda geldiniz");
			alert.showAndWait();
		}
		if (this.kayitIndex2 > 1)
			this.kayitIndex2--;
		if (this.kayitIndex2 == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uyari");
			alert.setHeaderText("Uyari mesaji!");
			alert.setContentText("Ilk kayda geldiniz");
			alert.showAndWait();
			this.kayitIndex2 = 1L;
		}
		if (this.kayitIndex2 == 1) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uyari");
			alert.setHeaderText("Uyari mesaji!");
			alert.setContentText("Ilk kayda geldiniz");
			alert.showAndWait();
			this.kayitIndex2 = 1L;
		}
		giderCek();
		giderCekTextlere();
	}

	public void ileriGit() {
		Long max = (long) this.gelirService.gelirCek().size();
		if (this.kayitIndex > 0)
			this.kayitIndex++;
		if (this.kayitIndex > max) {
			this.kayitIndex--;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uyari");
			alert.setHeaderText("Uyari mesaj,!");
			alert.setContentText("Son kayda geldiniz");
			alert.showAndWait();
		}
		gelirCek();
		gelirCekTextlere();
	}

	public void ileriGit2() {
		Long max = (long) this.giderService.giderCek().size();
		if (this.kayitIndex2 > 0)
			this.kayitIndex2++;
		if (this.kayitIndex2 > max) {
			this.kayitIndex2--;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uyari");
			alert.setHeaderText("Uyari mesaji!");
			alert.setContentText("Son kayda geldiniz");
			alert.showAndWait();
		}
		giderCek();
		giderCekTextlere();
	}

	public void alaniTemizle() {
		this.gelirIDText.clear();
		this.gelirAciklamaText.clear();
		this.gelirMiktarText.clear();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		gelirTarihLabel.setText(df.format(new Date()) + " Bugun");
	}

	public void alaniTemizle2() {
		this.giderIDText.clear();
		this.giderAciklamaText.clear();
		this.giderMiktarText.clear();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		giderTarihLabel.setText(df.format(new Date()) + " Bugun");
	}

	public void gelirSil() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Uyari");
		alert.setHeaderText("Uyari mesajı!");
		alert.setContentText("Silmek istediginize emin misiniz?");
		ButtonType buttonTypeOne = new ButtonType("Evet");
		ButtonType buttonTypeTwo = new ButtonType("Hayir");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			Long max = (long) this.gelirService.gelirCek().size();
			System.out.println("mami " + this.kayitIndex);
			Gelir gelir = this.gelirService.gelirCek(Long.parseLong(this.gelirIDText.getText()));
			this.gelirService.gelirSil(gelir);
			gelirCek();
			gelirCekTextlere();
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Uyari");
			alert2.setHeaderText("Uyari mesaji!");
			alert2.setContentText("Silme islemi başarili!");
			alert2.showAndWait();
			this.kayitIndex--;
			this.alaniTemizle();
		}
		this.gunlukGelirTopla();
	}

	public void giderSil() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Uyari");
		alert.setHeaderText("Uyari mesaji!");
		alert.setContentText("Silmek istediginize emin misiniz?");
		ButtonType buttonTypeOne = new ButtonType("Evet");
		ButtonType buttonTypeTwo = new ButtonType("Hayir");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			Long max = (long) this.giderService.giderCek().size();
			System.out.println("mami " + this.kayitIndex2);
			Gider gider = this.giderService.giderCek(Long.parseLong(this.giderIDText.getText()));
			this.giderService.giderSil(gider);
			giderCek();
			giderCekTextlere();
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Uyari");
			alert2.setHeaderText("Uyari mesaji!");
			alert2.setContentText("Silme islemi başarili!");
			alert2.showAndWait();
			this.kayitIndex2--;
			this.alaniTemizle2();
		}
		this.gunlukGiderTopla();
	}

	public void gelirAra() {
		this.kayitIndex = Long.parseLong(this.araId.getText());
		gelirCekTextlere2(Long.parseLong(this.araId.getText()));
	}

	public void gelirAra2() {
		this.kayitIndex = Long.parseLong(this.araId2.getText());
		giderCekTextlere2(Long.parseLong(this.araId2.getText()));
	}

	private void gunlukGelirTopla() {
		List<Gelir> gelirListesi = this.gelirService.gelirCek();
		List<Gelir> bugunGelirListesi = new ArrayList<Gelir>();
		for (int i = 0; i < gelirListesi.size(); i++) {
			Gelir gelir = gelirListesi.get(i);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String date1 = df.format(gelir.getGelirTarih());
			String bugunDate = df.format(new Date());
			if (date1.equals(bugunDate)) {
				bugunGelirListesi.add(gelir);
			}
		}
		Double gelirGunlukToplam = 0.0;
		for (int i = 0; i < bugunGelirListesi.size(); i++) {
			gelirGunlukToplam += bugunGelirListesi.get(i).getGelirMiktar();
		}
		this.gunTopGelir.setText(String.valueOf(gelirGunlukToplam) + " TL");
	}

	private void gunlukGiderTopla() {
		List<Gider> giderListesi = this.giderService.giderCek();
		List<Gider> bugununGiderListesi = new ArrayList<>();
		for (int i = 0; i < giderListesi.size(); i++) {
			Gider gider = giderListesi.get(i);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String date1 = df.format(gider.getGiderTarih());
			String bugunDate = df.format(new Date());
			if (date1.equals(bugunDate)) {
				bugununGiderListesi.add(gider);
			}
		}
		Double giderGunlukToplam = 0.0;
		for (int i = 0; i < bugununGiderListesi.size(); i++) {
			giderGunlukToplam += bugununGiderListesi.get(i).getGiderMiktar();
		}
		this.gunTopGider.setText(String.valueOf(giderGunlukToplam) + " TL");
	}

	public GelirService getGelirService() {
		return gelirService;
	}

	public void setGelirService(GelirService gelirService) {
		this.gelirService = gelirService;
	}

	public GiderService getGiderService() {
		return giderService;
	}

	public void setGiderService(GiderService giderService) {
		this.giderService = giderService;
	}

	public TextField getGiderIDText() {
		return giderIDText;
	}

	public void setGiderIDText(TextField giderIDText) {
		this.giderIDText = giderIDText;
	}

	public TextField getGiderMiktarText() {
		return giderMiktarText;
	}

	public void setGiderMiktarText(TextField giderMiktarText) {
		this.giderMiktarText = giderMiktarText;
	}

	public TextField getGiderAciklamaText() {
		return giderAciklamaText;
	}

	public void setGiderAciklamaText(TextField giderAciklamaText) {
		this.giderAciklamaText = giderAciklamaText;
	}

	public Label getGiderTarihLabel() {
		return giderTarihLabel;
	}

	public void setGiderTarihLabel(Label giderTarihLabel) {
		this.giderTarihLabel = giderTarihLabel;
	}
}
