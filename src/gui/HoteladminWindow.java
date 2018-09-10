package gui;

import application.Hotel;
import application.HotelTillaeg;
import application.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HoteladminWindow extends Stage {

    public HoteladminWindow(String title, Stage owner) {
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
    private ListView<Hotel> lvwHotels;
    private TextField txfNavn, txfAdresse, txfPrisEnkelt, txfPrisDobbelt, txfTillaegNavn, txfTillaegPris;
    private ListView<HotelTillaeg> lvwHotelTillaegs;

    // Content Initialization
    private void initContent(GridPane pane) {

        // Column #0
        Service.label(pane, "Ændring af hoteller:", 0, 0);
        lvwHotels = new ListView<>();
        lvwHotels.setOnMouseClicked(event -> lvwHotelsOnClick());
        pane.add(lvwHotels, 0, 1, 1, 8);

        Button btnLuk = new Button("Luk");
        btnLuk.setOnAction(event -> hide());
        pane.add(btnLuk, 0, 10);

        // Column #1
        txfNavn = new TextField("Navn");
        pane.add(txfNavn, 1, 1);

        txfAdresse = new TextField("Adresse");
        pane.add(txfAdresse, 1, 2);

        txfPrisEnkelt = new TextField("Pris Enkelt");
        pane.add(txfPrisEnkelt, 1, 3);

        txfPrisDobbelt = new TextField("Pris Dobbelt");
        pane.add(txfPrisDobbelt, 1, 4);

        Button btnOpretRediger = new Button("Opret/Rediger");
        btnOpretRediger.setOnAction(event -> btnOpretRedigerAction());
        pane.add(btnOpretRediger, 1, 6);

        Button btnSlet = new Button("Slet");
        btnSlet.setOnAction(event -> btnSletAction());
        pane.add(btnSlet, 1, 7);

        // Column #2
        Service.label(pane, "Ændring af hotel-tillæg:", 2, 0);
        lvwHotelTillaegs = new ListView<>();
        lvwHotelTillaegs.setOnMouseClicked(event -> lvwHotelTillaegsOnClick());
        pane.add(lvwHotelTillaegs, 2, 1, 1, 8);

        // Colimn #3
        txfTillaegNavn = new TextField("Navn");
        pane.add(txfTillaegNavn, 3, 1);

        txfTillaegPris = new TextField("Pris");
        pane.add(txfTillaegPris, 3, 2);

        Button btnTillaegOpretRediger = new Button("Opret/Rediger");
        btnTillaegOpretRediger.setOnAction(event -> btnTillaegOpretRedigerAction());
        pane.add(btnTillaegOpretRediger, 3, 6);

        Button btnTillaegSlet = new Button("Slet");
        btnTillaegSlet.setOnAction(event -> btnTillaegSletAction());
        pane.add(btnTillaegSlet, 3, 7);

        lvwHotelsUpdate();

    }

    // Node Actions
    private void lvwHotelsUpdate() {
        lvwHotels.getItems().removeAll(lvwHotels.getItems());
        lvwHotels.getItems().addAll(Service.getHotels());
    }

    private void lvwHotelTillaegsUpdate() {
        lvwHotelTillaegs.getItems().removeAll(lvwHotelTillaegs.getItems());

        Hotel selected = lvwHotels.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lvwHotelTillaegs.getItems().setAll(selected.getHotelTillaeg());
        }
    }

    private void lvwHotelsOnClick() {
        lvwHotelTillaegsUpdate();
        Hotel selected = lvwHotels.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txfNavn.setText(selected.getNavn());
            txfAdresse.setText(selected.getAdresse());
            txfPrisEnkelt.setText("" + selected.getPrisEnkelt());
            txfPrisDobbelt.setText("" + selected.getPrisDobbelt());
        }
    }

    private void lvwHotelTillaegsOnClick() {
        HotelTillaeg selected = lvwHotelTillaegs.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txfTillaegNavn.setText(selected.getNavn());
            txfTillaegPris.setText("" + selected.getPris());
        }
    }

    private void btnOpretRedigerAction() {
        Hotel selected = lvwHotels.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Service.updateHotel(selected, txfNavn.getText(), txfAdresse.getText(),
                    Double.parseDouble(txfPrisEnkelt.getText()), Double.parseDouble(txfPrisDobbelt.getText()));
        } else {
            Service.createHotel(txfNavn.getText(), txfAdresse.getText(), Double.parseDouble(txfPrisEnkelt.getText()),
                    Double.parseDouble(txfPrisDobbelt.getText()));
        }

        lvwHotelsUpdate();
    }

    private void btnSletAction() {
        Hotel selected = lvwHotels.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Service.removeHotel(selected);
            lvwHotelsUpdate();
        }
    }

    private void btnTillaegOpretRedigerAction() {
        if (lvwHotels.getSelectionModel().getSelectedItem() != null) {
            HotelTillaeg selected = lvwHotelTillaegs.getSelectionModel().getSelectedItem();

            if (selected != null) {
                Service.updateHotelTillaeg(selected, txfTillaegNavn.getText(),
                        Double.parseDouble(txfTillaegPris.getText()));
            } else {
                Service.createHotelTillaeg(lvwHotels.getSelectionModel().getSelectedItem(), txfTillaegNavn.getText(),
                        Double.parseDouble(txfTillaegPris.getText()));
            }

            lvwHotelTillaegsUpdate();
        }
    }

    private void btnTillaegSletAction() {
        HotelTillaeg selected = lvwHotelTillaegs.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Service.removeHotelTillaeg(lvwHotels.getSelectionModel().getSelectedItem(), selected);
            lvwHotelTillaegsUpdate();
        }
    }
}
