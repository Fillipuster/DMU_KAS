package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmPopup extends Stage {

    public boolean confirmed = false;

    private String label;

    public ConfirmPopup(String title, Stage owner, String label) {
        this.label = label;

        initOwner(owner);
        initStyle(StageStyle.UTILITY);
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

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lbl = new Label(label);
        pane.add(lbl, 0, 0);

        Button btnAccept = new Button("Accepter");
        pane.add(btnAccept, 0, 1);
        btnAccept.setOnAction(event -> btnAcceptAction());

        Button btnCancel = new Button("Fortryd");
        pane.add(btnCancel, 1, 1);
        btnCancel.setOnAction(event -> btnCancelAction());
    }

    private void btnCancelAction() {
        hide();
    }

    private void btnAcceptAction() {
        confirmed = true;
        hide();
    }

}
