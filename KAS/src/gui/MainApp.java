package gui;

import java.time.LocalDate;
import java.time.LocalDateTime;

import application.Konference;
import application.Service;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        // TEST
        Konference k1 = new Konference("TestKonference", "TestVej", LocalDateTime.now(), LocalDateTime.now(),
                "Det er bare en test drenge.");
        Konference k2 = new Konference("Baconference", "Ham Road 22", LocalDateTime.now(), LocalDateTime.now(),
                "Det handler om bacon, drenge.");
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("KAS");
        GridPane pane = new GridPane();
        initContent(pane);

        // popup = new PopupWindow("Person Information", stage);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private final Controller controller = new Controller();
    // private PopupWindow popup;
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
        cbKonferencer.getItems().addAll(Service.getKonferencer());

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
            // User presses the "create conference" button.
        }

        public void btnViewAction() {
            // User presses the "view conference" button.
        }

        public void btnApplyAction() {
            // User presses the "apply to conference" button.
        }
    }

}
