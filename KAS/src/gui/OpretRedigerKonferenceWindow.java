package gui;

import java.time.LocalDate;

import application.Hotel;
import application.Service;
import application.Udflugt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretRedigerKonferenceWindow extends Stage {

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
    private TextField txfNavn, txfAdresse, txfBeskrivelse, txfAfgift;
    private DatePicker dpFraDato, dpTilDato;
    private ComboBox<Hotel> cboxHotels;
    private ListView<Hotel> lvwHotels;
    private ListView<Udflugt> lvwUdflugts;

    // Content Initialization
    private void initContent(GridPane pane) {
        // Column #0
        txfNavn = new TextField("Navn");
        pane.add(txfNavn, 0, 0);

        txfAdresse = new TextField("Adresse");
        pane.add(txfAdresse, 0, 1);

        dpFraDato = new DatePicker(LocalDate.now());
        pane.add(dpFraDato, 0, 2);

        dpTilDato = new DatePicker(LocalDate.now());
        pane.add(dpTilDato, 0, 3);

        txfBeskrivelse = new TextField("Beskrivelse");
        pane.add(txfBeskrivelse, 0, 4);

        txfAfgift = new TextField("Afgift");
        pane.add(txfAfgift, 0, 5);

        Button btnLuk = new Button("Luk");
        btnLuk.setOnAction(event -> hide());
        pane.add(btnLuk, 0, 9);

        // Column #1
        cboxHotels = new ComboBox<>();
        cboxHotels.setOnAction(event -> cboxHotelsAction());
        pane.add(cboxHotels, 1, 0);

        lvwHotels = new ListView<>();
        pane.add(lvwHotels, 1, 1, 1, 7);

        Button btnSletHotel = new Button("Slet");
        btnSletHotel.setOnAction(event -> btnSletHotelAction());
        pane.add(btnSletHotel, 1, 8);

        updateCboxHotels();
    }

    // Node Actions
    private void updateCboxHotels() {
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

    // External
    public void updateHotelList() {
        updateCboxHotels();
    }

}
