package gui;

import application.Hotel;
import application.Konference;
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
    private ListView<Tilmelding> lvwKonferenceTilmeldings, lvwHotelTilmeldings, lvwUdflugtTilmeldings;
    private ComboBox<Hotel> cboxHotels;
    private ComboBox<Udflugt> cboxUdflugts;

    // Content Initialization
    private void initContent(GridPane pane) {
        // Column #0
        lvwKonferenceTilmeldings = new ListView<>();
        pane.add(lvwKonferenceTilmeldings, 0, 1);

        // Column #1
        cboxHotels = new ComboBox<>();
        cboxHotels.setOnAction(event -> cboxHotelsAction());
        pane.add(cboxHotels, 1, 0);

        lvwHotelTilmeldings = new ListView<>();
        pane.add(lvwHotelTilmeldings, 1, 1);

        // Column #2
        cboxUdflugts = new ComboBox<>();
        cboxUdflugts.setOnAction(event -> cboxUdflugtsAction());
        pane.add(cboxUdflugts, 2, 0);

        lvwUdflugtTilmeldings = new ListView<>();
        pane.add(lvwUdflugtTilmeldings, 2, 1);
    }

    // Node Actions
    private void cboxHotelsAction() {
        lvwHotelTilmeldings.getItems().removeAll(lvwHotelTilmeldings.getItems());
        for (Tilmelding t : konference.getTilmeldte()) {
            if (t.getHotel() == cboxHotels.getSelectionModel().getSelectedItem()) {
                lvwHotelTilmeldings.getItems().add(t);
            }
        }
    }

    private void cboxUdflugtsAction() {
        lvwUdflugtTilmeldings.getItems().removeAll(lvwUdflugtTilmeldings.getItems());
        for (Tilmelding t : konference.getTilmeldte()) {
            if (t.getUdflugter().contains(cboxUdflugts.getSelectionModel().getSelectedItem())) {
                lvwUdflugtTilmeldings.getItems().add(t);
            }
        }
    }
}
