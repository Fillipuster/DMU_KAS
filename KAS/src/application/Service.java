package application;

import java.util.ArrayList;

public class Service {

    private static ArrayList<Konference> konferencer = new ArrayList<>();

    public static void addKonference(Konference konference) {
        konferencer.add(konference);
    }

    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

}
