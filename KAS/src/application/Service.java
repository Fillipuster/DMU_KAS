package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Service {

	private static ArrayList<Konference> konferencer = new ArrayList<>();
	private static ArrayList<Hotel> hoteller = new ArrayList<>();
	private static ArrayList<Person> personer = new ArrayList<>();

	public static void addKonference(Konference konference) {
		konferencer.add(konference);
	}

	public static ArrayList<Konference> getKonferencer() {
		return new ArrayList<>(konferencer);
	}

	public static void addHotel(Hotel hotel) {
		hoteller.add(hotel);
	}

	public static ArrayList<Hotel> getHotels() {
		return new ArrayList<>(hoteller);
	}

	public static void addPerson(Person person) {
		personer.add(person);
	}

	public static ArrayList<Person> getPersons() {
		return new ArrayList<>(personer);
	}

	public static void createTilmelding(Konference konference, LocalDate ankomstDato, LocalDate afrejseDato,
			Person deltager, Person ledsager) {
		Tilmelding t = new Tilmelding(konference, ankomstDato, afrejseDato, deltager, ledsager);
	}

	public static Konference createKonference(String navn, String adresse, LocalDateTime fraDato, LocalDateTime tilDato,
			String beskrivelse) {
		Konference k = new Konference(navn, adresse, fraDato, tilDato, beskrivelse);
		addKonference(k);
		return k;

	}

	public static void createPerson(String fornavn, String efternavn, String adresse, String telefonNr) {
		Person p = new Person(fornavn, efternavn, adresse, telefonNr);
		addPerson(p);
	}

	public static Udflugt createUdflugt(Konference konference, String navn, String beskrivelse, LocalDateTime fraTid,
			LocalDateTime tilTid, double pris, boolean frokost) {
		Udflugt u = new Udflugt(navn, beskrivelse, fraTid, tilTid, pris, frokost);
		konference.addUdflugt(u);
		return u;
	}
}
