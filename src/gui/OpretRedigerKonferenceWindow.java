package gui;

import java.time.LocalDate;

import application.Hotel;
import application.Konference;
import application.Service;
import application.Udflugt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretRedigerKonferenceWindow extends Stage {

    private Konference konference = null;

    public void setKonference(Konference konference) {
        this.konference = konference;
        lvwUdflugts.setDisable(konference == null);

        txfUdflugtNavn.setDisable(konference == null);
        txfUdflugtBeskrivelse.setDisable(konference == null);
        txfUdflugtPris.setDisable(konference == null);

        btnUdflugtOpretRediger.setDisable(konference == null);
        btnUdflugtSlet.setDisable(konference == null);

        dpUdflugtDato.setDisable(konference == null);

        cbUdflugtFrokost.setDisable(konference == null);

        btnGem.setDisable(konference != null);
        btnOpdater.setDisable(konference == null);

        if (konference != null) {
            // cboxHotels

            lvwUdflugts.getItems().removeAll(lvwUdflugts.getItems());
            lvwUdflugts.getItems().addAll(konference.getUdflugter());

            lvwHotels.getItems().removeAll(lvwHotels.getItems());
            System.out.println(konference.getHoteller());
            lvwHotels.getItems().addAll(konference.getHoteller());

            txfNavn.setText(konference.getNavn());
            txfAdresse.setText(konference.getAdresse());
            txfBeskrivelse.setText(konference.getBeskrivelse());
            txfAfgift.setText("" + konference.getAfgift());

            dpFraDato.setValue(konference.getFraDato());
            dpTilDato.setValue(konference.getTilDato());
        }
    }

    public Konference getKonference() {
        return konference;
    }

    public OpretRedigerKonferenceWindow(String title, Stage owner) {
        initOwner(owner);
        initStyle(StageStyle.DECORATED);
        initModality(Modality.APPLICATION_MODAL);
        setMinHeight(100);
        setMinWidth(200);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    // Node Fields
    private Button btnUdflugtOpretRediger, btnUdflugtSlet, btnGem, btnOpdater;
    private TextField txfNavn, txfAdresse, txfBeskrivelse, txfAfgift;
    private TextField txfUdflugtNavn, txfUdflugtBeskrivelse, txfUdflugtPris;
    private CheckBox cbUdflugtFrokost;
    private DatePicker dpFraDato, dpTilDato;
    private DatePicker dpUdflugtDato;
    private ComboBox<Hotel> cboxHotels;
    private ListView<Hotel> lvwHotels;
    private ListView<Udflugt> lvwUdflugts;

    // Content Initialization
    private void initContent(GridPane pane) {
        // Column #0
        txfNavn = new TextField("Navn");
        pane.add(txfNavn, 0, 1);

        txfAdresse = new TextField("Adresse");
        pane.add(txfAdresse, 0, 2);

        dpFraDato = new DatePicker(LocalDate.now());
        pane.add(dpFraDato, 0, 3);

        dpTilDato = new DatePicker(LocalDate.now());
        pane.add(dpTilDato, 0, 4);

        txfBeskrivelse = new TextField("Beskrivelse");
        pane.add(txfBeskrivelse, 0, 5);

        txfAfgift = new TextField("Afgift");
        pane.add(txfAfgift, 0, 6);

        btnGem = new Button("Gem");
        btnGem.setOnAction(event -> btnGemAction());
        pane.add(btnGem, 0, 10);

        // Column #1
        cboxHotels = new ComboBox<>();
        cboxHotels.setOnAction(event -> cboxHotelsAction());
        pane.add(cboxHotels, 1, 0);

        lvwHotels = new ListView<>();
        pane.add(lvwHotels, 1, 1, 1, 13);

        Button btnSletHotel = new Button("Slet");
        btnSletHotel.setOnAction(event -> btnSletHotelAction());
        pane.add(btnSletHotel, 1, 14);

        // Column #2
        lvwUdflugts = new ListView<>();
        lvwUdflugts.setOnMouseClicked(event -> lvwUdflugtsOnMouse());
        pane.add(lvwUdflugts, 2, 1, 1, 13);

        // Column #3
        txfUdflugtNavn = new TextField("Navn");
        pane.add(txfUdflugtNavn, 3, 1);

        txfUdflugtBeskrivelse = new TextField("Beskrivelse");
        pane.add(txfUdflugtBeskrivelse, 3, 2);

        txfUdflugtPris = new TextField("Pris");
        pane.add(txfUdflugtPris, 3, 3);

        dpUdflugtDato = new DatePicker(LocalDate.now());
        pane.add(dpUdflugtDato, 3, 4);

        cbUdflugtFrokost = new CheckBox("Frokost");
        pane.add(cbUdflugtFrokost, 3, 5);

        btnUdflugtOpretRediger = new Button("Opret/Rediger");
        btnUdflugtOpretRediger.setOnAction(event -> btnUdflugtOpretRedigerAction());
        pane.add(btnUdflugtOpretRediger, 3, 6);

        btnUdflugtSlet = new Button("Slet");
        btnUdflugtSlet.setOnAction(evnet -> btnUdflugtSletAction());
        pane.add(btnUdflugtSlet, 3, 7);

        btnOpdater = new Button("Opdater");
        btnOpdater.setOnAction(event -> btnOpdaterAction());
        pane.add(btnOpdater, 3, 10);

        Button btnLuk = new Button("Luk");
        btnLuk.setOnAction(event -> hide());
        pane.add(btnLuk, 3, 11);

        cboxHotelsUpdate();
    }

    // Node Actions
    private void cboxHotelsUpdate() {
        cboxHotels.getItems().removeAll(cboxHotels.getItems());
        cboxHotels.getItems().addAll(Service.getHotels());
    }

    private void cboxHotelsAction() {
        Hotel selected = cboxHotels.getSelectionModel().getSelectedItem();

        if (!lvwHotels.getItems().contains(selected)) {
            lvwHotels.getItems().add(selected);
        }
    }

    private void btnSletHotelAction() {
        Hotel selected = lvwHotels.getSelectionModel().getSelectedItem();

        if (selected != null) {
            lvwHotels.getItems().remove(selected);
        }
    }

    private void lvwUdflugtsOnMouse() {
        Udflugt selected = lvwUdflugts.getSelectionModel().getSelectedItem();

        if (selected != null) {
            txfUdflugtNavn.setText(selected.getNavn());
            txfUdflugtBeskrivelse.setText(selected.getBeskrivelse());
            txfUdflugtPris.setText("" + selected.getPris());
        }
    }

    private void btnUdflugtOpretRedigerAction() {
        Udflugt selected = lvwUdflugts.getSelectionModel().getSelectedItem();

        if (selected != null) {
            selected.setNavn(txfUdflugtNavn.getText());
            selected.setBeskrivelse(txfUdflugtBeskrivelse.getText());
            selected.setPris(Double.parseDouble(txfUdflugtPris.getText()));
            selected.setDato(dpUdflugtDato.getValue());
            selected.setFrokost(cbUdflugtFrokost.isSelected());
            lvwUdflugts.getItems().remove(selected);
            lvwUdflugts.getItems().add(selected);
        } else {
            Udflugt u = Service.createUdflugt(konference, txfUdflugtNavn.getText(), txfUdflugtBeskrivelse.getText(),
                    dpUdflugtDato.getValue(), Double.parseDouble(txfUdflugtPris.getText()),
                    cbUdflugtFrokost.isSelected());
            lvwUdflugts.getItems().add(u);
        }
    }

    private void btnUdflugtSletAction() {
        Udflugt selected = lvwUdflugts.getSelectionModel().getSelectedItem();

        if (selected != null) {
            lvwUdflugts.getItems().remove(selected);
        }
    }

    private void btnGemAction() {
        if (konference == null) {
            setKonference(Service.createKonference(txfNavn.getText(), txfAdresse.getText(), dpFraDato.getValue(),
                    dpTilDato.getValue(), txfBeskrivelse.getText(), Double.parseDouble(txfAfgift.getText())));
        }
    }

    private void btnOpdaterAction() {
        if (konference != null) {
            konference.setNavn(txfNavn.getText());
            konference.setAdresse(txfAdresse.getText());
            konference.setFraDato(dpFraDato.getValue());
            konference.setTilDato(dpTilDato.getValue());
            konference.setBeskrivelse(txfBeskrivelse.getText());
            konference.setAfgift(Double.parseDouble(txfAfgift.getText()));
            for (Hotel h : lvwHotels.getItems()) {
                konference.addHotel(h);
            }
            hide();
        }
    }

    // External
    public void updateHotelList() {
        cboxHotelsUpdate();
    }
}
