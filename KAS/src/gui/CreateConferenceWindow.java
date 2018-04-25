package gui;

import java.time.LocalDate;
import application.Konference;
import application.Service;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreateConferenceWindow extends Stage {

    protected Konference konference;

    public CreateConferenceWindow(String title, Stage owner) {
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

    private TextField txfNavn, txfAdresse, txfBeskrivelse;
    private DatePicker dpFraDato, dpTilDato;

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblIntro = new Label("Indtast konference-informationer:");
        pane.add(lblIntro, 0, 0);

        Label lblNavn = new Label("Navn:");
        pane.add(lblNavn, 0, 1);

        txfNavn = new TextField("Baconferencen");
        pane.add(txfNavn, 0, 2);

        Label lblAdresse = new Label("Adresse:");
        pane.add(lblAdresse, 0, 3);

        txfAdresse = new TextField("T. Ulipsvej 69, 1337 Baconistan");
        pane.add(txfAdresse, 0, 4);

        Label lblFraDato = new Label("Fra Dato:");
        pane.add(lblFraDato, 0, 5);

        dpFraDato = new DatePicker(LocalDate.now());
        pane.add(dpFraDato, 0, 6);

        Label lblTilDato = new Label("Til dato:");
        pane.add(lblTilDato, 0, 7);

        dpTilDato = new DatePicker(LocalDate.now());
        pane.add(dpTilDato, 0, 8);

        Label lblBeskrivelse = new Label("Beskrivelse:");
        pane.add(lblBeskrivelse, 1, 1);

        txfBeskrivelse = new TextField("En konference omkring bacon.");
        pane.add(txfBeskrivelse, 1, 2, 2, 3);
        txfBeskrivelse.setMinHeight(100);
        txfBeskrivelse.setMinWidth(200);
        txfBeskrivelse.setAlignment(Pos.TOP_LEFT);

        Button btnCancel = new Button("Luk");
        pane.add(btnCancel, 0, 11);
        btnCancel.setOnAction(event -> btnCancelAction());

        Button btnAccept = new Button("Opret");
        pane.add(btnAccept, 1, 11);
        btnAccept.setOnAction(event -> btnAcceptAction());
    }

    private void btnCancelAction() {
        hide();
    }

    private void btnAcceptAction() {
        konference = Service.createKonference(txfNavn.getText(), txfAdresse.getText(), dpFraDato.getValue(),
                dpTilDato.getValue(), txfBeskrivelse.getText());
        hide();
    }

}
