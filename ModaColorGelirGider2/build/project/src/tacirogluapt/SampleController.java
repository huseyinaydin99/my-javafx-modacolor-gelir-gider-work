package tacirogluapt;

import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aquafx_project.AquaFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tacirogluapt.model.Daire;
import tacirogluapt.model.Gider;
import tacirogluapt.model.TableModelBorc;
import tacirogluapt.model.TableModelDaire;
import tacirogluapt.model.TableModelGider;
import tacirogluapt.service.DaireService;
import tacirogluapt.service.DaireServiceImpl;
import tacirogluapt.service.GiderService;
import tacirogluapt.service.GiderServiceImpl;

public class SampleController implements Initializable {
	private static ApplicationContext applicationContext;
	@Autowired
	@Qualifier("daireService")
	private static DaireService daireService;
	@Autowired
	@Qualifier("giderService")
	private static GiderService giderService;
	static {
		if (applicationContext == null)
			applicationContext = new ClassPathXmlApplicationContext("springConfig.xml");
		if (daireService == null)
			daireService = new DaireServiceImpl();
		if (giderService == null)
			giderService = new GiderServiceImpl();
	}

	@FXML
	private TextField ggDaireNo;
	@FXML
	private TextField ggTCNo;
	@FXML
	private TextField ggAdi;
	@FXML
	private TextField ggSoyadi;
	@FXML
	private TextField ggOdemeMiktari;
	@FXML
	private DatePicker ggOdemeTarihi;
	@FXML
	private Button ggKaydet;
	@FXML
	private Button ggIlk;
	@FXML
	private Button ggIleri;
	@FXML
	private Button ggGeri;
	@FXML
	private Button ggSon;
	@FXML
	private Button ggGuncelle;
	@FXML
	private Button ggSil;
	@FXML
	private TextField ggAraT;
	@FXML
	private Button ggAraB;
	@FXML
	private Button ggAraIptal;
	@FXML
	private TableView<TableModelBorc> bbTableView;
	@FXML
	private ComboBox<String> bbCombo1;
	@FXML
	private Button bbHesapla;
	@FXML
	private LineChart<String, Integer> bbLineChart;
	@FXML
	private TextField giAdi;
	@FXML
	private TextField giMiktar;
	@FXML
	private DatePicker giTar;
	@FXML
	private TextArea giAciklama;
	@FXML
	private Button giKaydet;
	@FXML
	private TextField giToplamGelir;
	@FXML
	private TableView<TableModelGider> giTableView;

	private Integer yedekKayitIndex = 0;
	@FXML
	private TableView<TableModelDaire> ggTableView;
	@FXML
	private Button giGuncelle;
	@FXML
	private Button giSil;
	@FXML
	private Button gIlk;
	@FXML
	private Button gSon;
	@FXML
	private Button giGeri;
	@FXML
	private Button giIleri;
	@FXML
	private TextField bbMiktar;
	private ObservableList<TableModelDaire> tabloDeposu;
	private ObservableList<TableModelBorc> tabloDeposu2;
	private ObservableList<TableModelGider> tabloDeposu3;
	private Integer kayitIndex = 0;
	private Integer kayitIndex2 = 0;

	@FXML
	private ImageView javaimage, javafximage, springimage, hibernateimage;

	public void kayitEt() {
		try {
			Long daireNo = Long.parseLong(this.ggDaireNo.getText());
			Long tcNo = Long.parseLong(this.ggTCNo.getText());
			String ad = this.ggAdi.getText();
			String soyad = this.ggSoyadi.getText();
			Long odemeMiktari = Long.parseLong(this.ggOdemeMiktari.getText());
			LocalDate localDate = this.ggOdemeTarihi.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);

			if ((daireNo != 0 || daireNo != null) && (tcNo != 0 || tcNo != null)
					&& (ad != null || !ad.equals("") || !ad.isEmpty())
					&& (soyad != null || !soyad.equals("") || !soyad.isEmpty())
					&& (odemeMiktari != 0 || odemeMiktari != null) && (date != null)) {
				Daire daire = new Daire();
				daire.setDaireNo(daireNo);
				daire.setTcNo(tcNo);
				daire.setAd(ad);
				daire.setSoyad(soyad);
				daire.setOdenenMiktar(odemeMiktari);
				daire.setOdemeTarihi(date);

				if (this.daireService != null) {
					this.daireService.kaydetDaire(daire);
					giToplamGelir.setText(String.valueOf(geliriTopla()));
					List<Daire> daires = this.daireService.daireListele();
					gelirCek();
					kayitIndex = tabloDeposu.size() - 1;
					editleriYenile();
					System.out.println("Çalýþtý kayýt et!");
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Kayýt Bilgisi");
					alert.setHeaderText("Baþarýlý (:");
					alert.setContentText("Girdiðiniz gelir kaydý baþarýlý ile yapýldý!");
					alert.showAndWait();
				} else
					System.out.println("Kayýt baþarýsýz daire boþ");
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Uyarý!");
				alert.setHeaderText("Boþ geçme hatasý!");
				alert.setContentText("Lütfen gerekli alanlarý boþ geçmeyiniz!");
				alert.showAndWait();
			}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Uyarý!");
			alert.setHeaderText("Alan doldurma hatasý!");
			alert.setContentText("Lütfen gerekli alanlara olmasý gerektiði þekilde doldurunuz!");
			alert.showAndWait();
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Uyarý!");
			alert.setHeaderText("Alan doldurma hatasý!");
			alert.setContentText("Lütfen gerekli alanlara olmasý gerektiði þekilde doldurunuz!");
			alert.showAndWait();
		}

	}

	public void giKayitEt() {
		Date date2 = null;
		try {
			LocalDate localDate2 = this.giTar.getValue();
			Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
			date2 = Date.from(instant2);
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Uyarý!");
			alert.setHeaderText("Alan doldurma hatasý!");
			alert.setContentText("Lütfen gerekli alanlara olmasý gerektiði þekilde doldurunuz!");
			alert.showAndWait();
			return;
		}
		if (!giAdi.getText().equals("") && !giMiktar.getText().equals("") && date2 != null
				&& !giAciklama.getText().equals("")) {
			long miktar = Long.parseLong(giMiktar.getText());
			long toplamGelir = Long.parseLong(giToplamGelir.getText());
			if ((miktar <= toplamGelir) && (miktar != 0)) {
				Gider gider = new Gider();
				gider.setGiderAdi(giAdi.getText());
				gider.setGiderOdeme(Long.parseLong(giMiktar.getText()));
				LocalDate localDate = this.giTar.getValue();
				Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
				Date date = Date.from(instant);
				gider.setGiderTarihi(date);
				gider.setGiderAciklamasi(giAciklama.getText());
				this.giderService.kaydetGider(gider);
				giderCek();
				kayitIndex2 = tabloDeposu3.size() - 1;
				giEditleriYenile();
				giToplamGelir.setText(String.valueOf(geliriTopla()));
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Kayýt Bilgisi");
				alert.setHeaderText("Baþarýlý (:");
				alert.setContentText("Girdiðiniz gider kaydý baþarýlý ile yapýldý!");
				alert.showAndWait();
			} else {
				if (miktar != 0) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Uyarýsý");
					alert.setHeaderText("Miktar Uyarýsý");
					alert.setContentText("Girdiðiniz miktar toplam gelirden büyük olamaz!");
					alert.showAndWait();
				} else if (miktar == 0) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Uyarýsý");
					alert.setHeaderText("Miktar Uyarýsý");
					alert.setContentText("Girdiðiniz miktar sýfýr olamaz!");
					alert.showAndWait();
				}
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Uyarý!");
			alert.setHeaderText("Alan doldurma hatasý!");
			alert.setContentText("Lütfen gerekli alanlara olmasý gerektiði þekilde doldurunuz!");
			alert.showAndWait();
		}
	}

	public long geliriTopla() {
		List<Daire> daires = this.daireService.daireListele();
		List<Gider> giders = this.giderService.giderListele();
		long gelirToplam = 0;
		for (int i = 0; i < daires.size(); i++) {
			gelirToplam += daires.get(i).getOdenenMiktar();
		}
		long giderToplam = 0;
		for (int i = 0; i < giders.size(); i++) {
			giderToplam += giders.get(i).getGiderOdeme();
		}
		return gelirToplam - giderToplam;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// AquaFx.style();

		InputStream javajpg = getClass().getResourceAsStream("/view/java.jpg");
		javaimage.setImage(new Image(javajpg));

		InputStream javafxjpg = getClass().getResourceAsStream("/view/javafx.jpg");
		javafximage.setImage(new Image(javafxjpg));

		InputStream springjpg = getClass().getResourceAsStream("/view/spring.jpg");
		springimage.setImage(new Image(springjpg));

		InputStream hibernatepng = getClass().getResourceAsStream("/view/hibernate.png");
		hibernateimage.setImage(new Image(hibernatepng));

		giToplamGelir.setText(String.valueOf(geliriTopla()));
		ObservableList<String> options = FXCollections.observableArrayList();
		for (int i = 2000; i < 2150; i++) {
			options.add(String.valueOf(i));
		}
		bbCombo1.setItems(options);
		this.kayitIndex = 0;
		this.kayitIndex2 = 0;
		editleriYenile2();
		ggTableView.setSelectionModel(null);
		gelirCek();
		giderCek();
		giEditleriYenile2();
		// borcCek();
	}

	private void editleriYenile() {
		List<Daire> daires = this.daireService.daireListele();
		try {
			Daire daire = daires.get(kayitIndex);
			ggDaireNo.setText(String.valueOf(daire.getDaireNo()));
			ggTCNo.setText(String.valueOf(daire.getTcNo()));
			ggAdi.setText(daire.getAd());
			ggSoyadi.setText(daire.getSoyad());
			ggOdemeMiktari.setText(String.valueOf(daire.getOdenenMiktar()));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			Date date = daire.getOdemeTarihi();
			Date date2 = new Date();
			date2.setDate(date.getDate());
			Instant instant = date2.toInstant();
			ggOdemeTarihi.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
		} catch (IndexOutOfBoundsException e) {
			if (kayitIndex <= 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Uyarý!");
				alert.setHeaderText("Ýlk kayýt!");
				alert.setContentText("Zaten ilk kayýtdasýnýz!");
				alert.showAndWait();
			} else {
				kayitIndex--;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Uyarý!");
				alert.setHeaderText("Son kayýt!");
				alert.setContentText("Zaten son kayýtdasýnýz!");
				alert.showAndWait();
			}
		}
	}

	public void editleriYenile2() {
		List<Daire> daires = this.daireService.daireListele();
		try {
			Daire daire = daires.get(kayitIndex);
			ggDaireNo.setText(String.valueOf(daire.getDaireNo()));
			ggTCNo.setText(String.valueOf(daire.getTcNo()));
			ggAdi.setText(daire.getAd());
			ggSoyadi.setText(daire.getSoyad());
			ggOdemeMiktari.setText(String.valueOf(daire.getOdenenMiktar()));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			Date date = daire.getOdemeTarihi();
			Date date2 = new Date();
			date2.setDate(date.getDate());
			Instant instant = date2.toInstant();
			ggOdemeTarihi.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
		} catch (IndexOutOfBoundsException e) {

		}
	}

	private void giEditleriYenile() {
		List<Gider> giders = this.giderService.giderListele();
		try {
			Gider gider = giders.get(kayitIndex2);
			giAdi.setText(gider.getGiderAdi());
			giMiktar.setText(String.valueOf(gider.getGiderOdeme()));
			LocalDate localDate = giTar.getValue();

			Date dates = gider.getGiderTarihi();
			Date date2 = new Date();
			date2.setDate(dates.getDate());
			Instant instant = date2.toInstant();
			giTar.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
			giAciklama.setText(gider.getGiderAciklamasi());
			giToplamGelir.setText(String.valueOf(geliriTopla()));
		} catch (IndexOutOfBoundsException e) {
			if (kayitIndex2 <= 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Uyarý!");
				alert.setHeaderText("Ýlk kayýt!");
				alert.setContentText("Zaten ilk kayýtdasýnýz!");
				alert.showAndWait();
			} else {
				kayitIndex2--;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Uyarý!");
				alert.setHeaderText("Son kayýt!");
				alert.setContentText("Zaten son kayýtdasýnýz!");
				alert.showAndWait();
			}
		}
	}

	private void giEditleriYenile2() {
		List<Gider> giders = this.giderService.giderListele();
		try {
			Gider gider = giders.get(kayitIndex2);
			giAdi.setText(gider.getGiderAdi());
			giMiktar.setText(String.valueOf(gider.getGiderOdeme()));
			LocalDate localDate = giTar.getValue();

			Date dates = gider.getGiderTarihi();
			Date date2 = new Date();
			date2.setDate(dates.getDate());
			Instant instant = date2.toInstant();
			giTar.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
			giAciklama.setText(gider.getGiderAciklamasi());
			giToplamGelir.setText(String.valueOf(geliriTopla()));
		} catch (IndexOutOfBoundsException e) {

		}
	}

	public void gelirCek() {
		List<Daire> daires = this.daireService.daireListele();
		if (tabloDeposu != null)
			tabloDeposu.clear();
		giToplamGelir.setText(String.valueOf(geliriTopla()));
		tabloDeposu = FXCollections.observableArrayList();
		for (int i = 0; i < daires.size(); i++) {
			Daire daire = daires.get(i);
			String daireId = String.valueOf(daire.getDaireId());
			System.out.println(daireId);
			String daireNo = String.valueOf(daire.getDaireNo());
			System.out.println(daireNo);
			String tcNo = String.valueOf(daire.getTcNo());
			System.out.println(tcNo);
			String ad = daire.getAd();
			System.out.println(ad);
			String soyad = daire.getSoyad();
			System.out.println(soyad);
			String odenenMiktar = String.valueOf(daire.getOdenenMiktar());
			System.out.println(odenenMiktar);
			Date date = daire.getOdemeTarihi();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String odemeTarihi = df.format(date);
			System.out.println(odemeTarihi);
			tabloDeposu.add(new TableModelDaire(daireId, daireNo, tcNo, ad, soyad, odenenMiktar, odemeTarihi));
		}
		TableColumn tableColumn1 = new TableColumn<>("Daire ID");
		tableColumn1.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("daireId"));
		tableColumn1.setMinWidth(100);
		TableColumn tableColumn2 = new TableColumn<>("Daire No");
		tableColumn2.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("daireNo"));
		tableColumn2.setMinWidth(100);
		TableColumn tableColumn3 = new TableColumn<>("TC No");
		tableColumn3.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("tcNo"));
		tableColumn3.setMinWidth(100);
		TableColumn tableColumn4 = new TableColumn<>("Ad");
		tableColumn4.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("ad"));
		tableColumn4.setMinWidth(100);
		TableColumn tableColumn5 = new TableColumn<>("Soyad");
		tableColumn5.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("soyad"));
		tableColumn5.setMinWidth(100);
		TableColumn tableColumn6 = new TableColumn<>("Ödenen Miktar");
		tableColumn6.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("odenenMiktar"));
		tableColumn6.setMinWidth(100);
		TableColumn tableColumn7 = new TableColumn<>("Ödeme Tarihi");
		tableColumn7.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("odemeTarihi"));
		tableColumn7.setMinWidth(100);
		ggTableView.getColumns().clear();
		ggTableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4, tableColumn5,
				tableColumn6, tableColumn7);

		ggTableView.setItems(tabloDeposu);
	}

	private void giderCek() {
		List<Gider> giders = this.giderService.giderListele();
		tabloDeposu3 = FXCollections.observableArrayList();
		if (tabloDeposu3 != null)
			tabloDeposu3.clear();
		for (int i = 0; i < giders.size(); i++) {
			Gider gider = giders.get(i);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			tabloDeposu3.add(new TableModelGider(String.valueOf(gider.getGiderId()),
					String.valueOf(gider.getGiderAdi()), String.valueOf(gider.getGiderAciklamasi()),
					String.valueOf(gider.getGiderOdeme()), df.format(gider.getGiderTarihi())));
		}
		TableColumn tableColumn1 = new TableColumn<>("Gider ID");
		tableColumn1.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("gelirId"));
		tableColumn1.setMinWidth(100);
		TableColumn tableColumn2 = new TableColumn<>("Gider Adý");
		tableColumn2.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("giderAdi"));
		tableColumn2.setMinWidth(100);
		TableColumn tableColumn3 = new TableColumn<>("Gider Açýklamasý");
		tableColumn3.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("giderAciklamasi"));
		tableColumn3.setMinWidth(100);
		TableColumn tableColumn4 = new TableColumn<>("Gider Ödeme Tutarý");
		tableColumn4.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("giderOdeme"));
		tableColumn4.setMinWidth(100);
		TableColumn tableColumn5 = new TableColumn<>("Gider Tarihi");
		tableColumn5.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("giderTarihi"));
		tableColumn5.setMinWidth(100);

		giTableView.getColumns().clear();
		giTableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4, tableColumn5);
		giTableView.setItems(tabloDeposu3);
	}

	private void borcCek() {
		Long yillikMiktar = null;
		try {
			yillikMiktar = Long.parseLong(bbMiktar.getText());
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Bilgilendirme");
			alert.setHeaderText("Sayý hatasý!");
			alert.setContentText("Girdiðiniz deðer sayý olmalý!");
			alert.showAndWait();
			return;
		}

		if (tabloDeposu2 != null)
			tabloDeposu2.clear();
		tabloDeposu2 = FXCollections.observableArrayList();
		String yil = bbCombo1.getValue();
		List<Daire> dairex = this.daireService.daireListele();
		List<Daire> daires = new ArrayList<>();
		XYChart.Series series = new XYChart.Series();
		series.getData().clear();
		series.setName(yil);
		for (int i = 0; i < dairex.size(); i++) {
			Daire daire = dairex.get(i);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date today = daire.getOdemeTarihi();
			String reportDate = df.format(today);
			String date = String.valueOf(reportDate.charAt(6)) + String.valueOf(reportDate.charAt(7))
					+ String.valueOf(reportDate.charAt(8)) + String.valueOf(reportDate.charAt(9));
			if (date.equals(yil)) {
				daires.add(daire);
			}
		}
		Map<Integer, Integer> toplaDepo = new HashMap<>();
		List<Integer> keys = new ArrayList<>();
		for (int i = 0; i < daires.size(); i++) {
			int toplam = 0;
			Integer daireNoi = (int) daires.get(i).getDaireNo();
			for (int j = 0; j < daires.size(); j++) {
				Daire daire = daires.get(j);

				/*
				 * DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); Date
				 * today = daire.getOdemeTarihi(); String reportDate =
				 * df.format(today); String date =
				 * String.valueOf(reportDate.charAt(6)) +
				 * String.valueOf(reportDate.charAt(7)) +
				 * String.valueOf(reportDate.charAt(8)) +
				 * String.valueOf(reportDate.charAt(9));
				 */

				if (daire.getDaireNo() == daireNoi) {
					toplam += daire.getOdenenMiktar();
				}
			}
			boolean durum = false;
			for (int j = 0; j < keys.size(); j++) {
				int integer = keys.get(j);
				if (integer == daireNoi) {
					durum = true;
				}
			}
			if (!durum) {
				toplaDepo.put(daireNoi, toplam);
				keys.add(daireNoi);
			}
			System.out.println("daire no : " + daireNoi + " / toplam : " + toplam);
		}

		List<TableModelBorc> borcs = new ArrayList<>();
		for (int i = 0; i < keys.size(); i++) {
			Integer toplam = toplaDepo.get(keys.get(i));
			String ad = "";
			String soyad = "";
			int daireNo = 0;
			for (int j = 0; j < daires.size(); j++) {
				Daire daire = daires.get(j);
				if (daire.getDaireNo() == keys.get(i)) {
					ad = daire.getAd();
					soyad = daire.getSoyad();
					daireNo = (int) daire.getDaireNo();
				}
			}
			series.getData().add(new XYChart.Data(String.valueOf(daireNo), toplam));
			String borc = "";
			if (yillikMiktar >= toplam) {
				borc = String.valueOf(yillikMiktar - toplam);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Bilgilendirme");
				alert.setHeaderText("Sayý hatasý!");
				alert.setContentText("Girdiðiniz miktar toplam ödenenden büyük olmalý!");
				alert.showAndWait();

			}
			borcs.add(new TableModelBorc(String.valueOf(daireNo), ad, soyad, String.valueOf(toplam), borc));
			tabloDeposu2.add(new TableModelBorc(String.valueOf(daireNo), ad, soyad, String.valueOf(toplam), borc));
		}

		TableColumn tableColumn1 = new TableColumn<>("Daire No");
		tableColumn1.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("daireNo"));
		tableColumn1.setMinWidth(100);
		TableColumn tableColumn2 = new TableColumn<>("Ad");
		tableColumn2.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("ad"));
		tableColumn2.setMinWidth(100);
		TableColumn tableColumn3 = new TableColumn<>("Soyad");
		tableColumn3.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("soyad"));
		tableColumn3.setMinWidth(100);
		TableColumn tableColumn4 = new TableColumn<>("Toplam Yatan");
		tableColumn4.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("yatan"));
		tableColumn4.setMinWidth(100);
		TableColumn tableColumn5 = new TableColumn<>("Yýllýk Borç");
		tableColumn5.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("borc"));
		tableColumn5.setMinWidth(100);
		bbTableView.getColumns().clear();
		bbTableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4, tableColumn5);
		bbLineChart.getData().clear();
		bbLineChart.getData().add(series);
		bbTableView.setItems(tabloDeposu2);
	}

	public void ilkeGit() {
		kayitIndex = 0;
		editleriYenile();
	}

	public void giIlkk() {
		kayitIndex2 = 0;
		giEditleriYenile();
	}

	public void giSonn() {
		kayitIndex2 = tabloDeposu3.size() - 1;
		giEditleriYenile();
	}

	public void giGerii() {
		kayitIndex2--;
		giEditleriYenile();
	}

	public void giIlerii() {
		kayitIndex2++;
		giEditleriYenile();
	}

	public void ileriGit() {
		kayitIndex++;
		editleriYenile();
	}

	public void geriGit() {
		kayitIndex--;
		editleriYenile();
	}

	public void sonaGit() {
		kayitIndex = tabloDeposu.size() - 1;
		System.out.println("hüseyin " + tabloDeposu.size());
		editleriYenile();
	}

	public void gGuncelle() {

		List<Daire> daires = this.daireService.daireListele();

		Long daireNo = Long.parseLong(this.ggDaireNo.getText());
		Long tcNo = Long.parseLong(this.ggTCNo.getText());
		String ad = this.ggAdi.getText();
		String soyad = this.ggSoyadi.getText();
		Long odemeMiktari = Long.parseLong(this.ggOdemeMiktari.getText());
		LocalDate localDate = this.ggOdemeTarihi.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);

		Daire daire = new Daire(daires.get(kayitIndex).getDaireId(), daireNo, tcNo, ad, soyad, odemeMiktari, date);

		boolean durum = this.daireService.daireGuncelle(daire);
		if (durum) {
			giToplamGelir.setText(String.valueOf(geliriTopla()));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Bilgilendirme");
			alert.setHeaderText("Güncelleme baþarýlý!");
			alert.setContentText("Yaptýðýnýz güncelleme baþarý ile tamamlandý!");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Bilgilendirme");
			alert.setHeaderText("Güncelleme baþarýsýz!");
			alert.setContentText("Yaptýðýnýz güncelleme baþarýsýz oldu!");
			alert.showAndWait();
		}
		gelirCek();
	}

	public void giGuncellee() {
		List<Gider> giders = this.giderService.giderListele();
		String adi = giAdi.getText();
		Long miktar = Long.parseLong(giMiktar.getText());
		LocalDate localDate = giTar.getValue();
		String aciklama = giAciklama.getText();
		Gider gider = giders.get(kayitIndex2);
		gider.setGiderAdi(adi);
		gider.setGiderOdeme(miktar);
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);
		gider.setGiderTarihi(date);
		gider.setGiderAciklamasi(aciklama);
		boolean durum = this.giderService.giderGuncelle(gider);
		if (durum) {
			giderCek();

			giToplamGelir.setText(String.valueOf(geliriTopla()));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Bilgilendirme");
			alert.setHeaderText("Güncelleme baþarýlý!");
			alert.setContentText("Yaptýðýnýz güncelleme baþarý ile tamamlandý!");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Bilgilendirme");
			alert.setHeaderText("Güncelleme baþarýsýz!");
			alert.setContentText("Yaptýðýnýz güncelleme baþarýsýz oldu!");
			alert.showAndWait();
		}
	}

	public void gAra() {
		tabloDeposu.clear();

		Daire daire = null;
		List<Daire> daires2 = this.daireService.daireAra(Integer.parseInt(ggAraT.getText()));
		yedekKayitIndex = kayitIndex;
		kayitIndex = 0;
		for (int i = 0; i < daires2.size(); i++) {
			daire = daires2.get(i);
			String daireId = String.valueOf(daire.getDaireId());
			String daireNo = String.valueOf(daire.getDaireNo());
			String tcNo = String.valueOf(daire.getTcNo());
			String ad = daire.getAd();
			String soyad = daire.getSoyad();
			String odenenMiktar = String.valueOf(daire.getOdenenMiktar());
			Date date = daire.getOdemeTarihi();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String odemeTarihi = df.format(date);

			ggDaireNo.setText(String.valueOf(daire.getDaireNo()));
			ggTCNo.setText(String.valueOf(daire.getTcNo()));
			ggAdi.setText(daire.getAd());
			ggSoyadi.setText(daire.getSoyad());
			ggOdemeMiktari.setText(String.valueOf(daire.getOdenenMiktar()));
			Date dates = daire.getOdemeTarihi();
			Date date2 = new Date();
			date2.setDate(dates.getDate());
			Instant instant = date2.toInstant();
			ggOdemeTarihi.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
			break;
		}

		for (int i = 0; i < daires2.size(); i++) {
			daire = daires2.get(i);
			String daireId = String.valueOf(daire.getDaireId());
			String daireNo = String.valueOf(daire.getDaireNo());
			String tcNo = String.valueOf(daire.getTcNo());
			String ad = daire.getAd();
			String soyad = daire.getSoyad();
			String odenenMiktar = String.valueOf(daire.getOdenenMiktar());
			Date date = daire.getOdemeTarihi();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String odemeTarihi = df.format(date);
			tabloDeposu.add(new TableModelDaire(daireId, daireNo, tcNo, ad, soyad, odenenMiktar, odemeTarihi));

		}
		TableColumn tableColumn1 = new TableColumn<>("Daire ID");
		tableColumn1.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("daireId"));
		tableColumn1.setMinWidth(100);
		TableColumn tableColumn2 = new TableColumn<>("Daire No");
		tableColumn2.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("daireNo"));
		tableColumn2.setMinWidth(100);
		TableColumn tableColumn3 = new TableColumn<>("TC No");
		tableColumn3.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("tcNo"));
		tableColumn3.setMinWidth(100);
		TableColumn tableColumn4 = new TableColumn<>("Ad");
		tableColumn4.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("ad"));
		tableColumn4.setMinWidth(100);
		TableColumn tableColumn5 = new TableColumn<>("Soyad");
		tableColumn5.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("soyad"));
		tableColumn5.setMinWidth(100);
		TableColumn tableColumn6 = new TableColumn<>("Ödenen Miktar");
		tableColumn6.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("odenenMiktar"));
		tableColumn6.setMinWidth(100);
		TableColumn tableColumn7 = new TableColumn<>("Ödeme Tarihi");
		tableColumn7.setCellValueFactory(new PropertyValueFactory<TableModelDaire, String>("odemeTarihi"));
		tableColumn7.setMinWidth(100);
		ggTableView.getColumns().clear();
		ggTableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4, tableColumn5,
				tableColumn6, tableColumn7);

		ggTableView.setItems(tabloDeposu);
	}

	public void gAraIptal() {
		kayitIndex = yedekKayitIndex;
		yedekKayitIndex = 0;
		editleriYenile();
		gelirCek();
	}

	public void gSil() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Bilgilendirme");
		alert.setHeaderText("Silme Ýþlemi");
		alert.setContentText("Bu kaydý silmek istediðinize eminmisiniz?");
		ButtonType buttonTypeOne = new ButtonType("Evet");
		ButtonType buttonTypeTwo = new ButtonType("Hayýr");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			List<Daire> daires = this.daireService.daireListele();
			Daire daire = daires.get(kayitIndex);
			this.daireService.daireSil(daire);
			giToplamGelir.setText(String.valueOf(geliriTopla()));
			if (kayitIndex > 0)
				kayitIndex--;
			editleriYenile();
			gelirCek();
		} else if (result.get() == buttonTypeTwo) {

		}
	}

	public void giSill() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Bilgilendirme");
		alert.setHeaderText("Silme Ýþlemi");
		alert.setContentText("Bu kaydý silmek istediðinize eminmisiniz?");
		ButtonType buttonTypeOne = new ButtonType("Evet");
		ButtonType buttonTypeTwo = new ButtonType("Hayýr");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			List<Gider> giders = this.giderService.giderListele();
			Gider gider = giders.get(kayitIndex2);
			this.giderService.giderSil(gider);
			giToplamGelir.setText(String.valueOf(geliriTopla()));
			if (kayitIndex2 > 0)
				kayitIndex2--;
			giEditleriYenile();
			giderCek();
		} else if (result.get() == buttonTypeTwo) {

		}
	}

	public void bHesapla() {
		borcCek();
	}

	public TextField getGgDaireNo() {
		return ggDaireNo;
	}

	public void setGgDaireNo(TextField ggDaireNo) {
		this.ggDaireNo = ggDaireNo;
	}

	public TextField getGgTCNo() {
		return ggTCNo;
	}

	public void setGgTCNo(TextField ggTCNo) {
		this.ggTCNo = ggTCNo;
	}

	public TextField getGgAdi() {
		return ggAdi;
	}

	public void setGgAdi(TextField ggAdi) {
		this.ggAdi = ggAdi;
	}

	public TextField getGgSoyadi() {
		return ggSoyadi;
	}

	public void setGgSoyadi(TextField ggSoyadi) {
		this.ggSoyadi = ggSoyadi;
	}

	public TextField getGgOdemeMiktari() {
		return ggOdemeMiktari;
	}

	public void setGgOdemeMiktari(TextField ggOdemeMiktari) {
		this.ggOdemeMiktari = ggOdemeMiktari;
	}

	public DatePicker getGgOdemeTarihi() {
		return ggOdemeTarihi;
	}

	public void setGgOdemeTarihi(DatePicker ggOdemeTarihi) {
		this.ggOdemeTarihi = ggOdemeTarihi;
	}

	public Button getGgKaydet() {
		return ggKaydet;
	}

	public void setGgKaydet(Button ggKaydet) {
		this.ggKaydet = ggKaydet;
	}

	public DaireService getDaireService() {
		return daireService;
	}

	public void setDaireService(DaireService daireService) {
		this.daireService = daireService;
	}

	public static GiderService getGiderService() {
		return giderService;
	}

	public void setGiderService(GiderService giderService) {
		this.giderService = giderService;
	}

}
