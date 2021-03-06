package gui;

import java.time.LocalDate;

import application.Hotel;
import application.Konference;
import application.Person;
import application.Service;
import application.Tilmelding;
import application.Udflugt;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage ownerStage;

    private static void createTestData() {
        Hotel h = Service.createHotel("Testotel", "Ham Road", 60, 100);
        Konference k = Service.createKonference("Baconferencen", "Ham Road", LocalDate.now(), LocalDate.now(),
                "Det handler om Bacon!", 120);
        k.addHotel(h);
        Service.createHotelTillaeg(h, "Wifi", 40);
        Udflugt u = Service.createUdflugt(k, "Baconspisning", "Mmmmm....", LocalDate.now(), 100, true);

        Person d = Service.createPerson("Jens", "Vejmand", "Lupinvej 23", "82739182");
        Person l = Service.createPerson("Lone", "Vejmand", "Lupinvej 24", "98371927");

        Tilmelding t = Service.createTilmelding(k, LocalDate.now(), LocalDate.now(), d, l, false);
        t.setHotel(h);
        t.addUdflugt(u);
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

        ownerStage = stage;

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
        cboxKonferencer.setOnAction(event -> controller.cboxKonferencerAction());
        cboxKonferencer.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                cboxKonferencer.getSelectionModel().select(null);
                btnJoin.setDisable(true);
                btnListe.setDisable(true);
            }
        });

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
        public void cboxKonferencerAction() {
            btnJoin.setDisable(false);
            btnListe.setDisable(false);
        }

        public void btnCreateAction() {
            // NOTE: Konferencen der skal redigeres skal vælges fra combo boxen hver gang
            // før redigering. Eller vil OpretRedigerKonferenceWindow ikke æde konferencen
            // fra cbox'en.
            opretRedigerKonferenceWindow.updateHotelList();
            Konference selected = cboxKonferencer.getSelectionModel().getSelectedItem();
            if (selected != null) {
                opretRedigerKonferenceWindow.setKonference(selected);
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
            Konference selected = cboxKonferencer.getSelectionModel().getSelectedItem();
            tilmeldKonferenceWindow = new TilmeldKonferenceWindow(selected.getNavn() + " Tilmelding", ownerStage);
            tilmeldKonferenceWindow.setKonference(selected);
            tilmeldKonferenceWindow.showAndWait();
        }

        private void updateKonferencerListe() {
            cboxKonferencer.getItems().setAll(Service.getKonferencer());
        }

        private void btnListeAction() {
            tilmeldingslisteWindow.setKonference(cboxKonferencer.getSelectionModel().getSelectedItem());
            tilmeldingslisteWindow.showAndWait();
        }
    }

}
