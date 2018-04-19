package application;

public class Hotel {
    private String navn;
    private String adresse;
    private double prisEnkelt;
    private double prisDobbelt;

    public Hotel(String navn, String adresse, double prisEnkelt, double prisDobbelt) {
        this.navn = navn;
        this.adresse = adresse;
        this.prisEnkelt = prisEnkelt;
        this.prisDobbelt = prisDobbelt;

        Service.addHotel(this);
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

    @Override
    public String toString() {
        return navn + "(" + prisEnkelt + "/" + prisDobbelt + ")";
    }

}
