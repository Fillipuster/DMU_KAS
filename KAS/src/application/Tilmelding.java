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

    public Tilmelding(Konference konference, LocalDate ankomstDato, LocalDate afrejseDato, Person deltager,
            Person ledsager) {
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.deltager = deltager;
        this.ledsager = ledsager;
        konference.addTilmelding(this);
    }

    public Tilmelding(Konference konference, LocalDate ankomstDato, LocalDate afrejseDato, Person deltager) {
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.deltager = deltager;
        konference.addTilmelding(this);
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

    public void addTillaeg(HotelTillaeg tillaeg) {
        this.hotelTillaeg.add(tillaeg);
    }

    public void removeTillaeg(HotelTillaeg tillaeg) {
        this.hotelTillaeg.remove(tillaeg);
    }

    public void addUdflugt(Udflugt udflugt) {
        this.udflugter.add(udflugt);
    }

    public void removeUdflugt(Udflugt udflugt) {
        this.udflugter.remove(udflugt);
    }

    public double totalPrice() {
        double total = 0;
        if (hotel != null) {
            if (ledsager != null) {
                total += hotel.getPrisDobbelt();
            } else {
                total += hotel.getPrisEnkelt();
            }
            for (HotelTillaeg ht : hotelTillaeg) {
                total += ht.getPris();
            }
        }
        for (Udflugt u : udflugter) {
            total += u.getPris();
        }
        return total;
    }

}
