package encapsulation;
import java.util.*;
public class Person {
	private String name;
	private String email;
	private Date date;
	private char gender;
	private final List<String> domains = Arrays.asList("ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw");
	
	public void setName(String name) throws IllegalArgumentException{
		if (!isValidName(name)) {
			throw new IllegalArgumentException("Invalid name!");
		}
		this.name = name;
	}
	
	private boolean isValidName(String name) {
		String[] fullName = name.split(" ");
		if (fullName.length != 2 || fullName[0].length() < 2 || fullName[1].length() < 2 || !fullName[0].matches("[a-zA-Z]+") || !fullName[1].matches("[a-zA-Z]+")) {
			return false;
		}
		return true;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setEmail(String email) throws IllegalArgumentException{
		System.out.println("lol xd");
		if ((!validateEmailName(email) || !validateDomain(email)) && email != null) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}
	
	private boolean validateEmailName(String email) {
		String[] fullName = email.split("@|\\.");
		String firstName = fullName[0];
		String lastName = fullName[1];
		if (firstName.length() < 2 || lastName.length() < 2) {
			return false;
		}
		if ((firstName + " " + lastName).equalsIgnoreCase(this.name)) {
			return true;
		}
		return false;
	}
	
	private boolean validateDomain(String email) {
		if (!email.contains("@")) {
			return false;
		}
		String[] domain = email.split("\\.");
		if (domain.length != 3) {
			return false;
		}
		for (String dom : domains) {
			if (domain[2].equalsIgnoreCase(dom)) {
				return true;
			}
		}
		return false;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setBirthday(Date date) throws IllegalArgumentException{
		Date today = new Date();
		if (date.after(today)) {
			throw new IllegalArgumentException("You cannot be from the future!");
		}
		this.date = date;
	}
	
	public Date getBirthday() {
		return this.date;
	}
	
	public void setGender(char gender) throws IllegalArgumentException{
		if (!validGender(gender)) {
			throw new IllegalArgumentException("Invalid gender");
		}
	}
	
	private boolean validGender(char gender) {
		if (gender == 'M' || gender == 'F' || gender == '\0') {
			return true;
		}
		return false;
	}
	
	public char getGender() {
		return this.gender;
	}
	
	
}

