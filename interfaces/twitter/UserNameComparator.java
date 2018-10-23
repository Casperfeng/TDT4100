package interfaces.twitter;

import java.util.Comparator;

public class UserNameComparator implements Comparator<TwitterAccount>{

	@Override
	public int compare(TwitterAccount TwitterAccount1, TwitterAccount TwitterAccount2) {
		// TODO Auto-generated method stub
		String usn1 = TwitterAccount1.getUserName(), usn2 = TwitterAccount2.getUserName();
		if (usn1.compareTo(usn2) > 0) {
			return 1;
		}
		else if (usn1.compareTo(usn2) < 0) {
			return -1;
		}
		return 0;
	}
	
}