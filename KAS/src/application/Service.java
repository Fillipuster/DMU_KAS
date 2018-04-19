package application;

import java.util.ArrayList;

public class Service {

    private static ArrayList<Konference> konferencer = new ArrayList<>();
    private static ArrayList<Hotel> hoteller = new ArrayList<>();

    public static void addKonference(Konference konference) {
        konferencer.add(konference);
    }

    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public static void addHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    public static ArrayList<Hotel> getHotels() {
        return new ArrayList<>(hoteller);
    }

}
