package application;

public class Person {
	private String fornavn;
	private String efternavn;
	private String adresse;
	private String telefonNr;

	public Person(String fornavn, String efternavn, String adresse, String telefonNr) {
		this.fornavn = fornavn;
		this.efternavn = efternavn;
		this.adresse = adresse;
		this.telefonNr = telefonNr;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEfternavn() {
		return efternavn;
	}

	public void setEfternavn(String efternavn) {
		this.efternavn = efternavn;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelefonNr() {
		return telefonNr;
	}

	public void setTelefonNr(String telefonNr) {
		this.telefonNr = telefonNr;
	}

}
