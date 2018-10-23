package interfaces.twitter;

import java.util.Comparator;

public class TweetsCountComparator implements Comparator<TwitterAccount> {

	@Override
	public int compare(TwitterAccount arg0, TwitterAccount arg1) {
		int tweet1 = arg0.getTweetCount(), tweet2 = arg1.getTweetCount();
		if (tweet1 - tweet2 > 0) {
			return -1;
		}
		else if (tweet1 - tweet2 < 0) {
			return 1;
		}
		return 0;
	}
	

}
