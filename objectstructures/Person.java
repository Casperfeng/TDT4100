package objectstructures;

import java.util.ArrayList;

/**
 * created 2/3/18
 * @author casperfeng
 * Lagd i sammenheng med Casper og Johannes kodelørdag
 */
public class Person implements PersonInterface{
	
	private final String name; //navnet til en person
	private final char gender; //kjønnet til en person enten 'F' eller 'M'
	private Person mother; //moren til en person
	private Person father; //faren til en person
	private ArrayList<Person> children = new ArrayList<>(); //oppbevarer barna til person
	
	/**
	 * Konstruktør som lager et nytt person-objekt med navn og kjønn
	 * @param name er navnet til personen
	 * @param gender er kjønnet til personen
	 */
	
	public Person(String name, char gender) { 
		this.name = name;
		if (gender != 'F' && gender != 'M') {
			throw new IllegalArgumentException("Not a defined gender!"); //sikkert ikke helt politisk korrekt men samma det
		}
		this.gender = gender;
	}
	
	/**
	 * returnerer navnet til person-objektet
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/**
	 * returnerer kjønnet til person-objektet
	 */
	@Override
	public char getGender() {
		// TODO Auto-generated method stub
		return this.gender;
	}

	/**
	 * returnerer moren til person-objektet
	 */
	@Override
	public Person getMother() {
		// TODO Auto-generated method stub
		return this.mother;
	}
	
	/**
	 * returnerer faren til person-objektet
	 */
	@Override
	public Person getFather() {
		// TODO Auto-generated method stub
		return this.father;
	}
	
	/**
	 * returnerer antall barn til person-objektet
	 */
	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return this.children.size();
	}
	
	/**
	 * returnerer barn nr. n (kun hvis barn nr. n er definert - ellers utløses IllegalArgumentException)
	 * @param n er barn nr. n (du er barn 0 hvis du er eldst i søskenflokken osv)
	 * @return barnet til person-objektet (som også er et person-objekt)
	 */
	@Override
	public Person getChild(int n) { 
		// TODO Auto-generated method stub
		if (n < 0 || n - 1 > this.children.size()) {
			throw new IllegalArgumentException("Undefined child nr. n"); //fant ikke noe bedre setning
		}
		return this.children.get(n);
	}
	
	/**
	 * legger til et barn i children-lista
	 * person-objektet skal opprette en kobling til barn
	 * hvis person-objektet er kvinne - personen settes som mor
	 * hvis person-objektet er mann - personen settes som far
	 */
	@Override
	public void addChild(Person person) {
		// TODO Auto-generated method stub
		if (person == this.getMother() || person == this.getFather()) { //sjekker om personen er moren eller faren til this
			throw new IllegalArgumentException("You can't be the parent of your own parents");
		}
		if (!this.children.contains(person)) { //sjekker om children lista inneholder person
			this.children.add(person); //legger til person
			if (this.getGender() == 'M' && person.getFather() != this) { //this er mann og this ikke allerede er satt som far
				person.setFather(this);
			}
			else if (this.getGender() == 'F' && person.getMother() != this) { //this er kvinne og this ikke allerede er satt som mor
				person.setMother(this);
			}
		}
		
	}
	/**
	 * fjerner et barn fra children-lista
	 * person-objektet fjerner en kobling til et barn
	 * hvis this-objektet som kaller er moren - fjernes koblingen
	 * hvis this-objektet som kaller er faren - fjernes koblingen
	 * 
	 */
	@Override
	public void removeChild(Person person) {
		// TODO Auto-generated method stub
		if (this.children.contains(person)) { //sjekker om children lista inneholder person
			this.children.remove(person); //fjerner person
			if (this.getGender() == 'M' && person.getFather() != this) { //hvis personen er gutt og person ikke allerede er satt som faren
				person.setFather(null); //fjerner koblingen
			}
			else if (this.getGender() == 'F' && person.getMother() != this) { //hvis personen er kvinne og person ikke allerede er satt som moren
				person.setMother(null); //fjerner koblingen
			}
		
		}
	}
	
	/**
	 * setter et person-objekt som mor til et annet person objekt
	 * skal kun kalles for kvinner
	 */
	@Override
	public void setMother(Person mother) {
		if (mother.gender == 'M' || mother == this) { //om personen ikke er kvinne og ikke sin egen mor
			throw new IllegalArgumentException("Not a valid mother"); //igjen, sikkert ikke helt politisk korrekt
		}
		if (this.getMother() != null) { //hvis en mor allerede eksisterer (adopsjon)
			this.getMother().removeChild(this); //moren mister barnet sitt (adopterer det bort)
		}
		this.mother = mother; //ny mor blir satt
 		mother.addChild(this); //barnet blir lagt til i children lista til this
		
	}
	/**
	 * setter et person-objekt som far til et annet person objekt
	 * skal kun kalles for menn
	 */
	@Override
	public void setFather(Person father) { 
		// TODO Auto-generated method stub
		if ( father.gender == 'F' || this == father) { //sjekker om father er kvinne eller om man forsøker å være sin egen far
			throw new IllegalArgumentException("Not a valid father"); //trenger jeg å si det?
		}
		if (this.getFather() != null) { //om ikke en far allerede er satt
			this.getFather().removeChild(this); //fjerner barnet fra children lista til den originale faren
		}
		this.father = father; //ny far blir satt
		father.addChild(this); //barnet blir lagt i children lista til this
	}
	/**
	 * printer ut person-objektet fint
	 * @return en streng-representasjon av person
	 */
	public String toString() {
		return this.name;		
	}
	
}
