package application;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private String adresse;
    private LocalDateTime fraDato;
    private LocalDateTime tilDato;
    private String beskrivelse;
    private ArrayList<Tilmelding> tilmeldte = new ArrayList<>();

    public Konference(String navn, String adresse, LocalDateTime fraDato, LocalDateTime tilDato, String beskrivelse) {
        this.navn = navn;
        this.adresse = adresse;
        this.fraDato = fraDato;
        this.tilDato = tilDato;
        this.beskrivelse = beskrivelse;

        Service.addKonference(this);
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

    public LocalDateTime getFraDato() {
        return fraDato;
    }

    public void setFraDato(LocalDateTime fraDato) {
        this.fraDato = fraDato;
    }

    public LocalDateTime getTilDato() {
        return tilDato;
    }

    public void setTilDato(LocalDateTime tilDato) {
        this.tilDato = tilDato;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public void addTilmelding(Tilmelding tilmelding) {
        this.tilmeldte.add(tilmelding);
    }

    @Override
    public String toString() {
        return navn;
    }

}