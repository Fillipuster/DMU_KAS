package application;

import java.time.LocalDateTime;

public class Udflugt {
    private String navn;
    private String beskrivelse;
    private LocalDateTime fraTid;
    private LocalDateTime tilTid;
    private double pris;
    private boolean frokost;

    public Udflugt(String navn, String beskrivelse, LocalDateTime fraTid, LocalDateTime tilTid, double pris,
            boolean frokost) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.fraTid = fraTid;
        this.tilTid = tilTid;
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

    public LocalDateTime getFraTid() {
        return fraTid;
    }

    public void setFraTid(LocalDateTime fraTid) {
        this.fraTid = fraTid;
    }

    public LocalDateTime getTilTid() {
        return tilTid;
    }

    public void setTilTid(LocalDateTime tilTid) {
        this.tilTid = tilTid;
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
