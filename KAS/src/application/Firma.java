package application;

public class Firma {
	private String navn;
	private String telefonNr;
	private boolean betalerForLedsager;

	public Firma(String navn, String telefonNr, boolean betalerForLedsager) {
		this.navn = navn;
		this.telefonNr = telefonNr;
		this.betalerForLedsager = betalerForLedsager;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getTelefonNr() {
		return telefonNr;
	}

	public void setTelefonNr(String telefonNr) {
		this.telefonNr = telefonNr;
	}

	public boolean betalerForLedsager() {
		return betalerForLedsager;
	}

}
