package gui;

import java.time.LocalDate;
import java.time.LocalDateTime;

import application.Hotel;
import application.HotelTillaeg;
import application.Konference;
import application.Person;
import application.Service;
import application.Tilmelding;
import application.Udflugt;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        // TEST
        @SuppressWarnings("unused")
        Konference _tk0 = new Konference("TestKonference", "TestVej", LocalDateTime.now(), LocalDateTime.now(),
                "Det er bare en test drenge.");
        @SuppressWarnings("unused")
        Konference _tk1 = new Konference("Baconferencen", "Ham Road 22", LocalDateTime.now().plusDays(3),
                LocalDateTime.now().plusDays(5), "Det handler om bacon, drenge.");

        HotelTillaeg ht = new HotelTillaeg("Wifi", 50);
        Hotel h = new Hotel("Testotel", "Whatever", 100, 200);
        h.addHotelTillaeg(ht);
        // Udflugt u1 = new Udflugt("U1", "bla", LocalDateTime.now(),
        // LocalDateTime.now(), 120, true);
        // Udflugt u2 = new Udflugt("U2", "bllla", LocalDateTime.now(),
        // LocalDateTime.now(), 202, false);
        //
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

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("KAS");
        GridPane pane = new GridPane();
        initContent(pane);

        viewConference = new ViewConferenceWindow("Konferencevisning", stage);
        createConference = new CreateConferenceWindow("Opret Konference", stage);
        joinconference = new JoinConferenceWindow("Tilmeld Konference", stage);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private final Controller controller = new Controller();
    private ViewConferenceWindow viewConference;
    private CreateConferenceWindow createConference;
    private JoinConferenceWindow joinconference;
    private ComboBox<Konference> cbKonferencer;

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblTitle = new Label("Konference Administrations System");
        pane.add(lblTitle, 0, 0);

        cbKonferencer = new ComboBox<>();
        cbKonferencer.setMinWidth(308);
        pane.add(cbKonferencer, 0, 1, 2, 1);
        controller.updateKonferencerListe();

        Button btnApply = new Button("Tilmeld Konference");
        pane.add(btnApply, 3, 1);
        btnApply.setOnAction(event -> controller.btnApplyAction());

        Button btnView = new Button("Se Konferencedetaljer");
        pane.add(btnView, 0, 3);
        btnView.setOnAction(event -> controller.btnViewAction());

        Button btnCreate = new Button("Opret Konference");
        pane.add(btnCreate, 1, 3);
        btnCreate.setOnAction(event -> controller.btnCreateAction());

        Label lblCopyright = new Label("© Berg, Valentin & Priestyard 2018");
        pane.add(lblCopyright, 0, 4);
    }

    private class Controller {
        public void btnCreateAction() {
            createConference.showAndWait();
            if (createConference.konference != null) {
                System.out.println("Konference oprettet!");
                updateKonferencerListe();
            }
        }

        public void btnViewAction() {
            viewConference.setStageTitle(cbKonferencer.getSelectionModel().getSelectedItem().getNavn());
            viewConference.showAndWait();
        }

        public void btnApplyAction() {
            joinconference.showAndWait();
        }

        private void updateKonferencerListe() {
            cbKonferencer.getItems().setAll(Service.getKonferencer());
        }
    }

}
