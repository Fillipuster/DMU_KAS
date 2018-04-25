package gui;

import java.time.LocalDate;

import application.Konference;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewConferenceWindow extends Stage {

    private Konference konference;

    public ViewConferenceWindow(String title, Stage owner) {
        initOwner(owner);
        initStyle(StageStyle.DECORATED);
        initModality(Modality.APPLICATION_MODAL);
        setMinHeight(600);
        setMinWidth(1200);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    public void setKonference(Konference konference) {
        this.konference = konference;
        updateContentKT();
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(0));
        pane.setHgap(10);
        pane.setVgap(10);

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        tabPane.setMinWidth(1200);
        tabPane.setPadding(new Insets(0));

        Tab konferenceTab = new Tab("Konference", buildKonferencePane());
        tabPane.getTabs().add(konferenceTab);

        Tab tilmeldingerTab = new Tab("Tilmeldinger", new Button("Tilmeldinger"));
        tabPane.getTabs().add(tilmeldingerTab);

        Tab hotellerTab = new Tab("Hoteller", new Button("Hoteller"));
        tabPane.getTabs().add(hotellerTab);

        Tab udflugterTab = new Tab("Udflugter", new Button("Udflugter"));
        tabPane.getTabs().add(udflugterTab);

        pane.add(tabPane, 0, 0);
    }

    private TextField txfNavnKT, txfAdresseKT, txfFraDatoKT, txfTilDatoKT, txfBeskrivelseKT;
    private DatePicker dpFraDatoKT;

    private GridPane buildKonferencePane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblIntro = new Label("Indtast konference-informationer:");
        pane.add(lblIntro, 0, 0);

        Label lblNavn = new Label("Navn:");
        pane.add(lblNavn, 0, 1);

        txfNavnKT = new TextField("<navn>");
        pane.add(txfNavnKT, 0, 2);

        Label lblAdresse = new Label("Adresse:");
        pane.add(lblAdresse, 0, 3);

        txfAdresseKT = new TextField("<adresse>");
        pane.add(txfAdresseKT, 0, 4);

        Label lblFraDato = new Label("Fra Dato:");
        pane.add(lblFraDato, 0, 5);

        txfFraDatoKT = new TextField("<fra_dato>");
        pane.add(txfFraDatoKT, 0, 6);

        Label lblTilDato = new Label("Til dato:");
        pane.add(lblTilDato, 0, 7);

        txfTilDatoKT = new TextField("<til_date>");
        pane.add(txfTilDatoKT, 0, 8);

        Label lblBeskrivelse = new Label("Beskrivelse:");
        pane.add(lblBeskrivelse, 1, 1);

        dpFraDatoKT = new DatePicker(LocalDate.now());
        pane.add(dpFraDatoKT, 1, 2);

        txfBeskrivelseKT = new TextField("<beskrivelse>");
        pane.add(txfBeskrivelseKT, 1, 2, 2, 3);
        txfBeskrivelseKT.setMinHeight(100);
        txfBeskrivelseKT.setMinWidth(200);
        txfBeskrivelseKT.setAlignment(Pos.TOP_LEFT);

        Button btnUpdateKonference = new Button("Opdater Konference");
        pane.add(btnUpdateKonference, 0, 11);
        btnUpdateKonference.setOnAction(event -> btnUpdateKonferenceActionKT());

        return pane;
    }

    private void updateContentKT() {
        setTitle(konference.getNavn());

        txfNavnKT.setText(konference.getNavn());
        txfAdresseKT.setText(konference.getAdresse());
        txfFraDatoKT.setText(konference.getFraDato().toString());
        txfTilDatoKT.setText(konference.getTilDato().toString());
        txfBeskrivelseKT.setText(konference.getBeskrivelse());
    }

    private void btnUpdateKonferenceActionKT() {

    }

}
