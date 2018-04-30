package gui;

import application.Hotel;
import application.Konference;
import application.Person;
import application.Service;
import application.Tilmelding;
import application.Udflugt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TilmeldingslisteWindow extends Stage {

    private Konference konference = null;

    public void setKonference(Konference konference) {
        this.konference = konference;

        lvwKonferenceTilmeldings.getItems().removeAll(lvwKonferenceTilmeldings.getItems());
        lvwKonferenceTilmeldings.getItems().addAll(this.konference.getTilmeldte());

        cboxHotels.getItems().removeAll(cboxHotels.getItems());
        cboxHotels.getItems().addAll(this.konference.getHoteller());

        cboxUdflugts.getItems().removeAll(cboxUdflugts.getItems());
        cboxUdflugts.getItems().addAll(this.konference.getUdflugter());

        lvwHotelTilmeldings.getItems().removeAll(lvwHotelTilmeldings.getItems());
        lvwUdflugtPersoner.getItems().removeAll(lvwUdflugtPersoner.getItems());
    }

    public Konference getKonference() {
        return this.konference;
    }

    public TilmeldingslisteWindow(String title, Stage owner) {
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
    private ListView<Tilmelding> lvwKonferenceTilmeldings, lvwHotelTilmeldings;
    private ListView<Person> lvwUdflugtPersoner;
    private ComboBox<Hotel> cboxHotels;
    private ComboBox<Udflugt> cboxUdflugts;

    // Content Initialization
    private void initContent(GridPane pane) {
        // Column #0
        Service.label(pane, "Konferencens tilmeldinger:", 0, 0);
        lvwKonferenceTilmeldings = new ListView<>();
        pane.add(lvwKonferenceTilmeldings, 0, 2);

        // Column #1
        Service.label(pane, "Vælg hotel for tilmeldingsliste:", 1, 0);
        cboxHotels = new ComboBox<>();
        cboxHotels.setOnAction(event -> cboxHotelsAction());
        pane.add(cboxHotels, 1, 1);

        lvwHotelTilmeldings = new ListView<>();
        pane.add(lvwHotelTilmeldings, 1, 2);

        // Column #2
        Service.label(pane, "Vælg udflugt for tilmeldingsliste:", 2, 0);
        cboxUdflugts = new ComboBox<>();
        cboxUdflugts.setOnAction(event -> cboxUdflugtsAction());
        pane.add(cboxUdflugts, 2, 1);

        lvwUdflugtPersoner = new ListView<>();
        pane.add(lvwUdflugtPersoner, 2, 2);
    }

    // Node Actions
    private void cboxHotelsAction() {
        Hotel selected = cboxHotels.getSelectionModel().getSelectedItem();

        if (selected != null) {
            lvwHotelTilmeldings.getItems().removeAll(lvwHotelTilmeldings.getItems());

            for (Tilmelding t : konference.getTilmeldte()) {
                if (t.getHotel() == selected) {
                    lvwHotelTilmeldings.getItems().add(t);
                }
            }
        }
    }

    private void cboxUdflugtsAction() {
        Udflugt selected = cboxUdflugts.getSelectionModel().getSelectedItem();

        if (selected != null) {
            lvwUdflugtPersoner.getItems().removeAll(lvwUdflugtPersoner.getItems());
            for (Tilmelding t : konference.getTilmeldte()) {
                if (t.getUdflugter().contains(selected)) {
                    lvwUdflugtPersoner.getItems().add(t.getLedsager());
                }
            }
        }
    }
}
