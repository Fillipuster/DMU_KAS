package application;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import storage.Storage;

public class Service {

    // Konferencer
    public static ArrayList<Konference> getKonferencer() {
        return Storage.getKonferencer();
    }

    public static void addKonference(Konference konference) {
        Storage.addKonference(konference);
    }

    public static void removeKonference(Konference konference) {
        Storage.removeKonference(konference);
    }

    // Hoteller
    public static ArrayList<Hotel> getHotels() {
        return Storage.getHotels();
    }

    public static void addHotel(Hotel hotel) {
        Storage.addHotel(hotel);
    }

    public static void removeHotel(Hotel hotel) {
        Storage.removeHotel(hotel);
    }

    // HotelTillaeg
    public static void removeHotelTillaeg(Hotel hotel, HotelTillaeg hotelTillaeg) {
        hotel.removeHotelTillaeg(hotelTillaeg);
    }

    // Creation
    public static Hotel createHotel(String navn, String adresse, double prisEnkelt, double prisDobbelt) {
        Hotel h = new Hotel(navn, adresse, prisEnkelt, prisDobbelt);
        addHotel(h);

        return h;
    }

    public static HotelTillaeg createHotelTillaeg(Hotel hotel, String navn, double pris) {
        HotelTillaeg ht = new HotelTillaeg(navn, pris);
        hotel.addHotelTillaeg(ht);

        return ht;
    }

    public static Konference createKonference(String navn, String adresse, LocalDate fraDato, LocalDate tilDato,
            String beskrivelse, double afgift) {
        Konference k = new Konference(navn, adresse, fraDato, tilDato, beskrivelse, afgift);
        addKonference(k);

        return k;
    }

    public static Person createPerson(String fornavn, String efternavn, String adresse, String telefonNr) {
        return new Person(fornavn, efternavn, adresse, telefonNr);
    }

    public static Tilmelding createTilmelding(Konference konference, LocalDate ankomstDato, LocalDate afrejseDato,
            Person deltager, Person ledsager, boolean speaker) {
        Tilmelding t = new Tilmelding(konference, ankomstDato, afrejseDato, deltager, ledsager, speaker);
        konference.addTilmelding(t);
        return t;
    }

    public static Udflugt createUdflugt(Konference konference, String navn, String beskrivelse, LocalDate dato,
            double pris, boolean frokost) {
        Udflugt u = new Udflugt(navn, beskrivelse, dato, pris, frokost);
        konference.addUdflugt(u);

        return u;
    }

    // Updating
    public static void updateHotel(Hotel hotel, String navn, String adresse, double prisEnkelt, double prisDobbelt) {
        hotel.setNavn(navn);
        hotel.setAdresse(adresse);
        hotel.setPrisEnkelt(prisEnkelt);
        hotel.setPrisDobbelt(prisDobbelt);
    }

    public static void updateHotelTillaeg(HotelTillaeg hotelTillaeg, String navn, double pris) {
        hotelTillaeg.setNavn(navn);
        hotelTillaeg.setPris(pris);
    }

    public static void updateKonference(Konference konference, String navn, String adresse, LocalDate fraDato,
            LocalDate tilDato, String beskrivelse, double afgift) {
        konference.setNavn(navn);
        konference.setAdresse(adresse);
        konference.setFraDato(fraDato);
        konference.setTilDato(tilDato);
        konference.setBeskrivelse(beskrivelse);
        konference.setAfgift(afgift);
    }

    // GUI Utils
    public static Label label(GridPane pane, String label, int x, int y) {
        Label l = new Label(label);
        pane.add(l, x, y);
        return l;
    }
}
