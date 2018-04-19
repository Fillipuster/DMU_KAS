package gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import java.util.HashMap;
//import java.util.Map;

import application.Hotel;
import application.HotelTillaeg;
import application.Konference;
import application.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JoinConferenceWindow extends Stage {

    protected Konference konference;
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public JoinConferenceWindow(String title, Stage owner) {
        initOwner(owner);
        initStyle(StageStyle.DECORATED);
        initModality(Modality.APPLICATION_MODAL);
        setMinHeight(100);
        setMinWidth(200);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    private TextField[] txfDeltager = new TextField[4];
    private TextField[] txfLedsager = new TextField[4];
    private String[] txtTxfPerson = { "John", "Doe", "Lupinvej 24", "12345678" };
    private Label[] lblPerson = new Label[4];
    private String[] txtLblPerson = { "Fornavn:", "Efternavn:", "Adresse:", "Telefon:" };
    private CheckBox cbLedsager;
    private ComboBox<Hotel> cboxHotels;
    private ComboBox<HotelTillaeg> cboxTillaeg;

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblRubrikDeltager = new Label("Indtast informationer (deltager):");
        pane.add(lblRubrikDeltager, 0, 0);

        int o = 1;
        for (int i = 0; i < 4; i++) {
            lblPerson[i] = new Label(txtLblPerson[i]);
            pane.add(lblPerson[i], 0, o);
            txfDeltager[i] = new TextField(txtTxfPerson[i]);
            pane.add(txfDeltager[i], 0, o + 1);
            o += 2;
        }

        cbLedsager = new CheckBox("Medbringer ledsager?");
        cbLedsager.setOnAction(event -> cbLedsagerAction());
        pane.add(cbLedsager, 0, 10);

        Label lblRubrikLedsager = new Label("Indtast informationer (ledsager):");
        pane.add(lblRubrikLedsager, 1, 0);

        int p = 1;
        for (int j = 0; j < 4; j++) {
            lblPerson[j] = new Label(txtLblPerson[j]);
            pane.add(lblPerson[j], 1, p);
            txfLedsager[j] = new TextField(txtTxfPerson[j]);
            pane.add(txfLedsager[j], 1, p + 1);
            txfLedsager[j].setDisable(true);
            p += 2;
        }

        Label lblRubrikHotel = new Label("Vælg hotel og evt. tillæg:");
        pane.add(lblRubrikHotel, 2, 0);

        cboxHotels = new ComboBox<>();
        cboxHotels.getItems().addAll(Service.getHotels());
        cboxHotels.setOnAction(event -> cboxHotelsAction());
        pane.add(cboxHotels, 2, 2);

        cboxTillaeg = new ComboBox<>();
        cboxTillaeg.setDisable(true);
        pane.add(cboxTillaeg, 2, 4);

        /*
         * Label lblIntro = new Label("Indtast informationer:"); pane.add(lblIntro, 0,
         * 0);
         *
         * Label lblFornavn = new Label("Fornavn:"); pane.add(lblFornavn, 0, 1);
         *
         * txfNavn = new TextField("John"); pane.add(txfNavn, 0, 2);
         *
         * Label lblEfternavn = new Label("Efternavn:"); pane.add(lblEfternavn, 0, 3);
         *
         * t
         *
         * Label lblAdresse = new Label("Adresse:"); pane.add(lblAdresse, 0, 3);
         *
         * txfAdresse = new TextField("T. Ulipsvej 69, 1337 Baconistan");
         * pane.add(txfAdresse, 0, 4);
         *
         * Label lblFraDato = new Label("Fra Dato:"); pane.add(lblFraDato, 0, 5);
         *
         * txfFraDato = new TextField("<dd-MM-yyyy HH:mm>"); pane.add(txfFraDato, 0, 6);
         *
         * Label lblTilDato = new Label("Til dato:"); pane.add(lblTilDato, 0, 7);
         *
         * txfTilDato = new TextField("17-02-2019 15:30"); pane.add(txfTilDato, 0, 8);
         *
         * Label lblBeskrivelse = new Label("Beskrivelse:"); pane.add(lblBeskrivelse, 0,
         * 9);
         *
         * txfBeskrivelse = new TextField("En konference omkring bacon.");
         * pane.add(txfBeskrivelse, 0, 10);
         *
         * Button btnCancel = new Button("Luk"); pane.add(btnCancel, 0, 11);
         * btnCancel.setOnAction(event -> btnCancelAction());
         *
         * Button btnAccept = new Button("Tilmeld"); pane.add(btnAccept, 1, 11);
         * btnAccept.setOnAction(event -> btnAcceptAction());
         */
    }

    private void cboxHotelsAction() {
        cboxTillaeg.getItems().addAll(cboxHotels.getSelectionModel().getSelectedItem().getHotelTillaeg());
        cboxTillaeg.setDisable(false);
    }

    private void cbLedsagerAction() {
        if (cbLedsager.isSelected()) {
            for (TextField tf : txfLedsager) {
                tf.setDisable(false);
            }
        } else {
            for (TextField tf : txfLedsager) {
                tf.setDisable(true);
            }
        }
    }

    private void btnCancelAction() {
        hide();
    }

    private void btnAcceptAction() {
        /*
         * try { konference = new Konference(txfNavn.getText(), txfAdresse.getText(),
         * LocalDateTime.parse(txfFraDato.getText(), timeFormat),
         * LocalDateTime.parse(txfTilDato.getText(), timeFormat),
         * txfBeskrivelse.getText()); } catch (DateTimeParseException e) {
         * System.out.println("LocalDateTime parse failed. Using current time.");
         * konference = new Konference(txfNavn.getText(), txfAdresse.getText(),
         * LocalDateTime.now(), LocalDateTime.now(), txfBeskrivelse.getText()); }
         */

        hide();
    }

}
