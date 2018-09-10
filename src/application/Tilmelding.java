package application;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    private Person deltager;
    private Person ledsager;
    private Firma firma;
    private Hotel hotel;
    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private ArrayList<HotelTillaeg> hotelTillaeg = new ArrayList<>();
    private boolean speaker;

    public Tilmelding(Konference konference, LocalDate ankomstDato, LocalDate afrejseDato, Person deltager,
            Person ledsager, boolean speaker) {
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.deltager = deltager;
        this.ledsager = ledsager;
        this.speaker = speaker;
    }

    public LocalDate getAnkomstDato() {
        return ankomstDato;
    }

    public void setAnkomstDato(LocalDate ankomstDato) {
        this.ankomstDato = ankomstDato;
    }

    public LocalDate getAfrejseDato() {
        return afrejseDato;
    }

    public void setAfrejseDato(LocalDate afrejseDato) {
        this.afrejseDato = afrejseDato;
    }

    public Person getDeltager() {
        return deltager;
    }

    public void setDeltager(Person deltager) {
        this.deltager = deltager;
    }

    public Person getLedsager() {
        return ledsager;
    }

    public void setLedsager(Person ledsager) {
        this.ledsager = ledsager;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isSpeaker() {
        return speaker;
    }

    public void setSpeaker(boolean speaker) {
        this.speaker = speaker;
    }

    public void addTillaeg(HotelTillaeg tillaeg) {
        this.hotelTillaeg.add(tillaeg);
    }

    public void removeTillaeg(HotelTillaeg tillaeg) {
        this.hotelTillaeg.remove(tillaeg);
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public void addUdflugt(Udflugt udflugt) {
        this.udflugter.add(udflugt);
    }

    public void removeUdflugt(Udflugt udflugt) {
        this.udflugter.remove(udflugt);
    }

    @Override
    public String toString() {
        String str = "";
        str += deltager.getFornavn() + " " + deltager.getEfternavn() + "\n";
        if (ledsager != null) {
            str += ledsager.getFornavn() + " " + ledsager.getEfternavn();
        }

        return str;
    }

}
