package gui;

import java.time.LocalDate;

import application.Hotel;
import application.HotelTillaeg;
import application.Konference;
import application.Service;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static void createTestData() {
        // Konference k = Service.createKonference("Baconferencen", "Ham Road 22",
        // LocalDate.now().plusDays(3),
        // LocalDate.now().plusDays(5), "Det handler om bacon, drenge.");
        //
        // Service.createUdflugt(k, "U1", "bla", LocalDate.now(), LocalDate.now(), 120,
        // true);
        // Service.createUdflugt(k, "U2", "bllla", LocalDate.now(), LocalDate.now(),
        // 202, false);
        //
        // HotelTillaeg ht1 = new HotelTillaeg("Wifi", 50);
        // HotelTillaeg ht2 = new HotelTillaeg("Morgenmad", 400);
        // Hotel h = new Hotel("Testotel", "Whatever", 100, 200);
        // h.addHotelTillaeg(ht1);
        // h.addHotelTillaeg(ht2);

        // Person p1 = new Person("Jonas", "Berg", "Whatever", "288713");
        // Person p2 = new Person("Daniel", "Præstegaard", "Gah", "923884");
        //
        // Tilmelding t = new Tilmelding(_tk1, LocalDate.now(), LocalDate.now(), p1);
        // t.setLedsager(p2);
        // t.addUdflugt(u2);
        // t.setHotel(h);
        // t.addTillaeg(ht);
        //
        // System.out.println(t.totalPrice());
        // System.out.println(200 + 9001 + 202);
    }

    public static void main(String[] args) {
        createTestData();
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Konference Administrations System");
        GridPane pane = new GridPane();
        initContent(pane);

        hoteladminWindow = new HoteladminWindow("Hoteladministration", stage);
        joinconference = new TilmeldKonferenceWindow("Tilmeld Konference", stage);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private final Controller controller = new Controller();

    private HoteladminWindow hoteladminWindow;
    private TilmeldKonferenceWindow joinconference;

    private ComboBox<Konference> cbKonferencer;
    private Button btnJoin, btnHoteladmin, btnCreate;

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblTitle = new Label("Vælg konference:");
        pane.add(lblTitle, 0, 0);

        cbKonferencer = new ComboBox<>();
        cbKonferencer.setMinWidth(308);
        pane.add(cbKonferencer, 0, 1, 2, 1);
        controller.updateKonferencerListe();
        cbKonferencer.setOnAction(event -> controller.cbKonferencerAction());

        btnJoin = new Button("Tilmeld Konference");
        pane.add(btnJoin, 3, 1);
        btnJoin.setOnAction(event -> controller.btnJoinAction());
        btnJoin.setDisable(true);

        btnHoteladmin = new Button("Hoteladministration");
        pane.add(btnHoteladmin, 0, 3);
        btnHoteladmin.setOnAction(event -> controller.btnHoteladminAction());

        btnCreate = new Button("Opret Konference");
        pane.add(btnCreate, 1, 3);
        // btnCreate.setOnAction(event -> controller.btnCreateAction());

        Label lblCopyright = new Label("© Berg, Valentin & Priestyard 2018");
        pane.add(lblCopyright, 0, 4);
    }

    private class Controller {
        // public void btnCreateAction() {
        // createConference.showAndWait();
        // if (createConference.konference != null) {
        // System.out.println("Konference oprettet!");
        // updateKonferencerListe();
        // }
        // }

        public void btnHoteladminAction() {

            hoteladminWindow.showAndWait();
        }

        public void btnJoinAction() {
            joinconference.konference = cbKonferencer.getSelectionModel().getSelectedItem();
            joinconference.showAndWait();
        }

        private void updateKonferencerListe() {
            cbKonferencer.getItems().setAll(Service.getKonferencer());
        }

        private void cbKonferencerAction() {
            btnJoin.setDisable(false);
        }
    }

}
