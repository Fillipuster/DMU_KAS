package gui;

import java.time.LocalDate;

import application.Hotel;
import application.Konference;
import application.Service;
import application.Udflugt;
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
        Hotel h = Service.createHotel("Testotel", "Ham Road", 60, 100);
        Konference k = Service.createKonference("Baconferencen", "Ham Road", LocalDate.now(), LocalDate.now(),
                "Det handler om Bacon!", 120);
        k.addHotel(h);
        Service.createHotelTillaeg(h, "Wifi", 40);
        Service.createUdflugt(k, "Baconspisning", "Mmmmm....", LocalDate.now(), 100, true);
    }

    public static void main(String[] args) {
        createTestData();
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Konference Administrations System");
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        initContent(pane);

        hoteladminWindow = new HoteladminWindow("Hoteladministration", stage);
        tilmeldKonferenceWindow = new TilmeldKonferenceWindow("Tilmeld Konference", stage);
        opretRedigerKonferenceWindow = new OpretRedigerKonferenceWindow("Opret/Rediger Konference", stage);
        tilmeldingslisteWindow = new TilmeldingslisteWindow("Tilmeldingsliste", stage);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private final Controller controller = new Controller();

    private HoteladminWindow hoteladminWindow;
    private TilmeldKonferenceWindow tilmeldKonferenceWindow;
    private OpretRedigerKonferenceWindow opretRedigerKonferenceWindow;
    private TilmeldingslisteWindow tilmeldingslisteWindow;

    private ComboBox<Konference> cboxKonferencer;
    private Button btnJoin, btnHoteladmin, btnCreate, btnListe;

    private void initContent(GridPane pane) {
        Service.label(pane, "Vælg konference:", 0, 0);

        cboxKonferencer = new ComboBox<>();
        cboxKonferencer.setMinWidth(400);
        pane.add(cboxKonferencer, 0, 1, 3, 1);
        controller.updateKonferencerListe();
        cboxKonferencer.setOnAction(event -> controller.cbKonferencerAction());

        btnJoin = new Button("Tilmeld Konference");
        pane.add(btnJoin, 4, 1);
        btnJoin.setOnAction(event -> controller.btnJoinAction());
        btnJoin.setDisable(true);

        btnHoteladmin = new Button("Hoteladministration");
        pane.add(btnHoteladmin, 0, 2);
        btnHoteladmin.setOnAction(event -> controller.btnHoteladminAction());

        btnCreate = new Button("Opret/Rediger Konference");
        pane.add(btnCreate, 1, 2);
        btnCreate.setOnAction(event -> controller.btnCreateAction());

        btnListe = new Button("Tilmeldingsliste");
        pane.add(btnListe, 2, 2);
        btnListe.setOnAction(event -> controller.btnListeAction());

        Label lblCopy = new Label("© Berg, Valentin & Priestyard 2018");
        pane.add(lblCopy, 0, 3, 3, 1);
    }

    private class Controller {
        public void btnCreateAction() {
            opretRedigerKonferenceWindow.updateHotelList();
            if (cboxKonferencer.getSelectionModel().getSelectedItem() != null) {
                opretRedigerKonferenceWindow.setKonference(cboxKonferencer.getSelectionModel().getSelectedItem());
            }
            opretRedigerKonferenceWindow.showAndWait();
            if (opretRedigerKonferenceWindow.getKonference() != null) {
                updateKonferencerListe();
            }
        }

        public void btnHoteladminAction() {
            hoteladminWindow.showAndWait();
        }

        public void btnJoinAction() {
            tilmeldKonferenceWindow.setKonference(cboxKonferencer.getSelectionModel().getSelectedItem());
            tilmeldKonferenceWindow.showAndWait();
        }

        private void updateKonferencerListe() {
            cboxKonferencer.getItems().setAll(Service.getKonferencer());
        }

        private void cbKonferencerAction() {
            btnJoin.setDisable(false);
        }

        private void btnListeAction() {
            tilmeldingslisteWindow.setKonference(cboxKonferencer.getSelectionModel().getSelectedItem());
            tilmeldingslisteWindow.showAndWait();
        }
    }

}
