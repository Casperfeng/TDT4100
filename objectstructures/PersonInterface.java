package objectstructures;

public interface PersonInterface {
	
	String getName();
	char getGender();
	Person getMother();
	Person getFather();
	int getChildCount();
	Person getChild(int n);
	void addChild(Person person);
	void removeChild(Person person);
	void setMother(Person mother);
	void setFather(Person father);
}
