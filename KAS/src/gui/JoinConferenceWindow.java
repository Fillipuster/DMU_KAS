package gui;

import java.time.format.DateTimeFormatter;

import application.Hotel;
import application.HotelTillaeg;
import application.Konference;
import application.Service;
import application.Person;
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

        o = 1;
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

        Label lblRubrikHotel = new Label("Vælg hotel og evt. tillæg:");
        pane.add(lblRubrikHotel, 2, 0);

        cboxHotels = new ComboBox<>();
        cboxHotels.getItems().addAll(Service.getHotels());
        cboxHotels.setOnAction(event -> cboxHotelsAction());
        pane.add(cboxHotels, 2, 2);

        cboxTillaeg = new ComboBox<>();
        cboxTillaeg.setDisable(true);
        pane.add(cboxTillaeg, 2, 4);

        Button btnCancel = new Button("Luk");
        pane.add(btnCancel, 0, 11);
        btnCancel.setOnAction(event -> btnCancelAction());

        Button btnAccept = new Button("Tilmeld");
        pane.add(btnAccept, 1, 11);
        btnAccept.setOnAction(event -> btnAcceptAction());

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

    private void btnAcceptAction() {
        Person deltager = new Person(txfDeltager[0].getText(), txfDeltager[1].getText(), txfDeltager[2].getText(),
                txfDeltager[3].getText());
        Person ledsager = null;
        if (cbLedsager.isSelected()) {
            ledsager = new Person(txfLedsager[0].getText(), txfLedsager[1].getText(), txfLedsager[2].getText(),
                    txfLedsager[3].getText());
        }
        hide();
    }

    private void btnCancelAction() {
        hide();
    }

}
