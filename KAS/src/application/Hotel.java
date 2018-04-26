package application;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private double prisEnkelt;
    private double prisDobbelt;
    private ArrayList<HotelTillaeg> hotelTillaeg = new ArrayList<>();

    public Hotel(String navn, String adresse, double prisEnkelt, double prisDobbelt) {
        this.navn = navn;
        this.adresse = adresse;
        this.prisEnkelt = prisEnkelt;
        this.prisDobbelt = prisDobbelt;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrisEnkelt() {
        return prisEnkelt;
    }

    public void setPrisEnkelt(double prisEnkelt) {
        this.prisEnkelt = prisEnkelt;
    }

    public double getPrisDobbelt() {
        return prisDobbelt;
    }

    public void setPrisDobbelt(double prisDobbelt) {
        this.prisDobbelt = prisDobbelt;
    }

    public void addHotelTillaeg(HotelTillaeg tillaeg) {
        hotelTillaeg.add(tillaeg);
    }

    public void removeHotelTillaeg(HotelTillaeg tillaeg) {
        hotelTillaeg.remove(tillaeg);
    }

    public ArrayList<HotelTillaeg> getHotelTillaeg() {
        return new ArrayList<>(hotelTillaeg);
    }

    @Override
    public String toString() {
        return navn + " (" + prisEnkelt + "/" + prisDobbelt + ")";
    }

}
