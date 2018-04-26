package application;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private String adresse;
    private LocalDate fraDato;
    private LocalDate tilDato;
    private String beskrivelse;
    private double afgift;
    private ArrayList<Tilmelding> tilmeldte = new ArrayList<>();
    private ArrayList<Udflugt> udflugter = new ArrayList<>();

    public Konference(String navn, String adresse, LocalDate fraDato, LocalDate tilDato, String beskrivelse,
            double afgift) {
        this.navn = navn;
        this.adresse = adresse;
        this.fraDato = fraDato;
        this.tilDato = tilDato;
        this.beskrivelse = beskrivelse;
        setAfgift(afgift);
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

    public LocalDate getFraDato() {
        return fraDato;
    }

    public void setFraDato(LocalDate fraDato) {
        this.fraDato = fraDato;
    }

    public LocalDate getTilDato() {
        return tilDato;
    }

    public void setTilDato(LocalDate tilDato) {
        this.tilDato = tilDato;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public double getAfgift() {
        return afgift;
    }

    public void setAfgift(double afgift) {
        this.afgift = afgift;
    }

    public void addTilmelding(Tilmelding tilmelding) {
        this.tilmeldte.add(tilmelding);
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public void removeUdflugt(Udflugt udflugt) {
        udflugter.remove(udflugt);
    }

    @Override
    public String toString() {
        return navn;
    }

}