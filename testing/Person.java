package testing;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String name;

	private char gender;

	public final static char MALE = 'M';
	public final static char FEMALE = 'F';

	private Person mother;
	private Person father;

	private List<Person> children = new ArrayList<>();

	public Person(String name, char gender) {
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	private char getGender() {
		return gender;
	}

	public Person getMother() {
		return mother;
	}

	public Person getFather() {
		return father;
	}

	public int getChildCount() {
		return children.size();
	}

	public Person getChild(int n) {
		if (n >= getChildCount() || n < 0) {
			throw new IndexOutOfBoundsException("Child index out of range");
		}

		return children.get(n);
	}

	private void checkGender(Person person, char gender) {
		if (person != null && person.gender != gender) {
			throw new IllegalArgumentException("Wrong gender for parent");
		}
	}

	private void checkOwnParent(Person person, Person parent) {
		if (person == parent) {
			throw new IllegalArgumentException("A person cannot be its own parent");
		}
	}

	public void setMother(Person person) {
		checkGender(person, FEMALE);
		checkOwnParent(this, person);

		if (mother != null && mother.equals(person)) {
			return;
		}

		if (mother != null) {
			mother.removeChild(this);
		}

		mother = person;

		if (mother != null) {
			mother.addChild(this);
		}
	}

	public void setFather(Person person) {
		checkGender(person, MALE);
		checkOwnParent(this, person);

		if (father != null && father.equals(person)) {
			return;
		}

		if (father != null) {
			father.removeChild(this);
		}

		father = person;

		if (father != null) {
			father.addChild(this);
		}
	}

	public void addChild(Person person) {
		children.add(person);

		if (getGender() == MALE) {
			person.setFather(this);
		} else if (getGender() == FEMALE) {
			person.setMother(this);
		}
	}

	public void removeChild(Person person) {
		if (children.contains(person)) {
			children.remove(person);

			person.setFather(null);
			person.setMother(null);
		}
	}
}
