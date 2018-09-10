package gui;

import java.time.temporal.ChronoUnit;

import application.Hotel;
import application.HotelTillaeg;
import application.Konference;
import application.Person;
import application.Service;
import application.Tilmelding;
import application.Udflugt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TilmeldKonferenceWindow extends Stage {

    private Konference konference = null;

    public void setKonference(Konference konference) {
        this.konference = konference;
        cboxHotels.getItems().removeAll(cboxHotels.getItems());
        cboxHotels.getItems().addAll(konference.getHoteller());
        dpFraDato.setValue(konference.getFraDato());
        dpTilDato.setValue(konference.getTilDato());
    }

    public Konference getKonference() {
        return konference;
    }

    public TilmeldKonferenceWindow(String title, Stage owner) {
        initOwner(owner);
        initStyle(StageStyle.DECORATED);
        initModality(Modality.APPLICATION_MODAL);
        setMinHeight(100);
        setMinWidth(200);
        setResizable(false);

        buildWindows(owner);

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

    // Window Fields
    private ConfirmPopup confirm;

    // External Window Creation
    private void buildWindows(Stage owner) {
        confirm = new ConfirmPopup("Accepter Tilmelding", owner,
                "Er du sikker på at du vil\ntilmelde dig konferencen?");
    }

    // Node Fields
    private TextField txfFornavn, txfEfternavn, txfAdresse, txfTelefon, txfLedsagerFornavn, txfLedsagerEfternavn,
            txfLedsagerAdresse, txfLedsagerTelefon, txfFirmaNavn, txfFirmaTlf;
    private CheckBox cbLedsager, cbSpeaker, cbFirma;
    private ComboBox<Hotel> cboxHotels;
    private ComboBox<HotelTillaeg> cboxTillaeg;
    private ListView<HotelTillaeg> lvwTillaeg;
    private ComboBox<Udflugt> cboxUdflugter;
    private ListView<Udflugt> lvwUdflugter;
    private Label lblTotalPrice;
    private DatePicker dpFraDato, dpTilDato;

    private void initContent(GridPane pane) {
        // Column #0
        txfFornavn = new TextField("Fornavn");
        pane.add(txfFornavn, 0, 0);

        txfEfternavn = new TextField("Efternavn");
        pane.add(txfEfternavn, 0, 1);

        txfAdresse = new TextField("Adresse");
        pane.add(txfAdresse, 0, 2);

        txfTelefon = new TextField("Telefon Nr.");
        pane.add(txfTelefon, 0, 3);

        cbFirma = new CheckBox("Representerer firma?");
        cbFirma.setOnAction(event -> cbFirmaAction());
        pane.add(cbFirma, 0, 4);

        txfFirmaNavn = new TextField("Firmanavn");
        txfFirmaNavn.setDisable(true);
        pane.add(txfFirmaNavn, 0, 5);

        txfFirmaTlf = new TextField("Firma tlf.");
        txfFirmaTlf.setDisable(true);
        pane.add(txfFirmaTlf, 0, 6);

        Service.label(pane, "Start dato:", 0, 7);
        dpFraDato = new DatePicker();
        dpFraDato.setOnAction(event -> updateTotalPrice());
        pane.add(dpFraDato, 0, 8);

        Service.label(pane, "Slut dato:", 0, 9);
        dpTilDato = new DatePicker();
        dpTilDato.setOnAction(event -> updateTotalPrice());
        pane.add(dpTilDato, 0, 10);

        Service.label(pane, "Vælg hotel:", 0, 11);
        cboxHotels = new ComboBox<>();
        cboxHotels.setOnAction(event -> cboxHotelsAction());
        pane.add(cboxHotels, 0, 12);

        cbSpeaker = new CheckBox("Foredragsholder?");
        cbSpeaker.setOnAction(evnet -> updateTotalPrice());
        pane.add(cbSpeaker, 0, 13);

        cbLedsager = new CheckBox("Medbringer ledsager?");
        cbLedsager.setOnAction(event -> cbLedsagerAction());
        pane.add(cbLedsager, 0, 14);

        Button btnCancel = new Button("Luk");
        pane.add(btnCancel, 0, 16);
        btnCancel.setOnAction(event -> hide());

        // Column #1
        txfLedsagerFornavn = new TextField("Ledsager Fornavn");
        txfLedsagerFornavn.setDisable(true);
        pane.add(txfLedsagerFornavn, 1, 0);

        txfLedsagerEfternavn = new TextField("Ledsager Efternavn");
        txfLedsagerEfternavn.setDisable(true);
        pane.add(txfLedsagerEfternavn, 1, 1);

        txfLedsagerAdresse = new TextField("Ledsager Adresse");
        txfLedsagerAdresse.setDisable(true);
        pane.add(txfLedsagerAdresse, 1, 2);

        txfLedsagerTelefon = new TextField("Ledsager Telefon Nr.");
        txfLedsagerTelefon.setDisable(true);
        pane.add(txfLedsagerTelefon, 1, 3);

        Button btnAccept = new Button("Tilmeld");
        pane.add(btnAccept, 1, 16);
        btnAccept.setOnAction(event -> btnAcceptAction());

        // Column #2
        Service.label(pane, "Vælg hotel-tillæg:", 2, 0);

        cboxTillaeg = new ComboBox<>();
        cboxTillaeg.setDisable(true);
        cboxTillaeg.setOnAction(event -> cboxTillaegAction());
        pane.add(cboxTillaeg, 2, 1);

        lvwTillaeg = new ListView<>();
        lvwTillaeg.setDisable(true);
        lvwTillaeg.setMaxHeight(200);
        pane.add(lvwTillaeg, 2, 2, 1, 8);

        Button btnRemoveTilaeg = new Button("Slet");
        btnRemoveTilaeg.setOnAction(event -> btnRemoveTilaegAction());
        pane.add(btnRemoveTilaeg, 2, 10);

        // Column #3
        Service.label(pane, "Vælge udflugter:", 3, 0);

        cboxUdflugter = new ComboBox<>();
        cboxUdflugter.setDisable(true);
        cboxUdflugter.setOnAction(event -> cboxUdflugterAction());
        pane.add(cboxUdflugter, 3, 1);

        lvwUdflugter = new ListView<>();
        lvwUdflugter = new ListView<>();
        lvwUdflugter.setDisable(true);
        lvwUdflugter.setMaxHeight(200);
        pane.add(lvwUdflugter, 3, 2, 1, 8);

        Button btnRemoveUdflugt = new Button("Slet");
        btnRemoveUdflugt.setOnAction(event -> btnRemoveUdflugtAction());
        pane.add(btnRemoveUdflugt, 3, 10);

        lblTotalPrice = new Label("TOTAL: 0.0");
        pane.add(lblTotalPrice, 3, 13);
    }

    private void btnRemoveTilaegAction() {
        lvwTillaeg.getItems().remove(lvwTillaeg.getSelectionModel().getSelectedItem());

        updateTotalPrice();
    }

    private void btnRemoveUdflugtAction() {
        lvwUdflugter.getItems().remove(lvwUdflugter.getSelectionModel().getSelectedItem());

        updateTotalPrice();
    }

    private void cboxHotelsAction() {
        Hotel selected = cboxHotels.getSelectionModel().getSelectedItem();

        if (selected != null) {
            cboxTillaeg.getItems().removeAll(cboxTillaeg.getItems());
            cboxTillaeg.getItems().addAll(selected.getHotelTillaeg());
            cboxTillaeg.setDisable(false);

            updateTotalPrice();
        }
    }

    private void cbLedsagerAction() {
        cboxUdflugter.getItems().addAll(konference.getUdflugter());

        if (cbLedsager.isSelected()) {
            lvwUdflugter.setDisable(false);
            cboxUdflugter.setDisable(false);
            txfLedsagerFornavn.setDisable(false);
            txfLedsagerEfternavn.setDisable(false);
            txfLedsagerAdresse.setDisable(false);
            txfLedsagerTelefon.setDisable(false);
        } else {
            lvwUdflugter.setDisable(true);
            cboxUdflugter.setDisable(true);
            txfLedsagerFornavn.setDisable(true);
            txfLedsagerEfternavn.setDisable(true);
            txfLedsagerAdresse.setDisable(true);
            txfLedsagerTelefon.setDisable(true);
        }

        updateTotalPrice();
    }

    private void btnAcceptAction() {
        updateTotalPrice();

        confirm.showAndWait();
        Person deltager = null;
        if (confirm.confirmed) {
            deltager = Service.createPerson(txfFornavn.getText(), txfEfternavn.getText(), txfAdresse.getText(),
                    txfTelefon.getText());
            Person ledsager = null;
            if (cbLedsager.isSelected()) {
                ledsager = Service.createPerson(txfLedsagerFornavn.getText(), txfLedsagerEfternavn.getText(),
                        txfLedsagerAdresse.getText(), txfLedsagerTelefon.getText());
            }

            Tilmelding t = Service.createTilmelding(konference, dpFraDato.getValue(), dpTilDato.getValue(), deltager,
                    ledsager, cbSpeaker.isSelected());

            Hotel selectedHotel = cboxHotels.getSelectionModel().getSelectedItem();
            if (selectedHotel != null) {
                t.setHotel(selectedHotel);
            }
            for (HotelTillaeg ht : lvwTillaeg.getItems()) {
                t.addTillaeg(ht);
            }
            for (Udflugt u : lvwUdflugter.getItems()) {
                t.addUdflugt(u);
            }

            hide();
        } else {
            confirm.hide();
        }
    }

    private void cboxTillaegAction() {
        lvwTillaeg.setDisable(false);
        if (!lvwTillaeg.getItems().contains(cboxTillaeg.getSelectionModel().getSelectedItem())) {
            lvwTillaeg.getItems().add(cboxTillaeg.getSelectionModel().getSelectedItem());
        }

        updateTotalPrice();
    }

    private void cboxUdflugterAction() {
        lvwUdflugter.setDisable(false);
        if (!lvwUdflugter.getItems().contains(cboxUdflugter.getSelectionModel().getSelectedItem())) {
            lvwUdflugter.getItems().add(cboxUdflugter.getSelectionModel().getSelectedItem());
        }

        updateTotalPrice();
    }

    private void cbFirmaAction() {
        if (cbFirma.isSelected()) {
            txfFirmaNavn.setDisable(false);
            txfFirmaTlf.setDisable(false);
        } else {
            txfFirmaNavn.setDisable(true);
            txfFirmaTlf.setDisable(true);
        }

        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double total = 0;

        if (cboxHotels.getSelectionModel().getSelectedItem() != null) {
            if (cbLedsager.isSelected()) {
                total += cboxHotels.getSelectionModel().getSelectedItem().getPrisDobbelt();
            } else {
                total += cboxHotels.getSelectionModel().getSelectedItem().getPrisEnkelt();
            }
            for (HotelTillaeg ht : lvwTillaeg.getItems()) {
                total += ht.getPris();
            }
        }
        for (Udflugt u : lvwUdflugter.getItems()) {
            total += u.getPris();
        }

        if (!cbSpeaker.isSelected() && !cbFirma.isSelected()) {
            total += (ChronoUnit.DAYS.between(dpFraDato.getValue(), dpTilDato.getValue()) + 1) * konference.getAfgift();
        }

        lblTotalPrice.setText("TOTAL: " + total);
    }

}
