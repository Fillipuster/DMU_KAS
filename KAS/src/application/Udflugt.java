package application;

import java.time.LocalDate;

public class Udflugt {
    private String navn;
    private String beskrivelse;
    private LocalDate dato;
    private double pris;
    private boolean frokost;

    public Udflugt(String navn, String beskrivelse, LocalDate dato, double pris, boolean frokost) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        setDato(dato);
        this.pris = pris;
        this.frokost = frokost;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public boolean isFrokost() {
        return frokost;
    }

    public void setFrokost(boolean frokost) {
        this.frokost = frokost;
    }

    @Override
    public String toString() {
        return navn + " (" + pris + ")";
    }

}
