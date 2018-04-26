package storage;

import java.util.ArrayList;

import application.Hotel;
import application.Konference;

public class Storage {

    private static ArrayList<Konference> konferencer = new ArrayList<>();
    private static ArrayList<Hotel> hoteller = new ArrayList<>();

    // Konferencer
    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public static void addKonference(Konference konference) {
        konferencer.add(konference);
    }

    public static void removeKonference(Konference konference) {
        konferencer.remove(konference);
    }

    // Hoteller
    public static ArrayList<Hotel> getHotels() {
        return new ArrayList<>(hoteller);
    }

    public static void addHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    public static void removeHotel(Hotel hotel) {
        hoteller.remove(hotel);
    }

}
