package interfaces.twitter;

import java.util.Comparator;

public class FollowersCountComparator implements Comparator<TwitterAccount>{

	@Override
	public int compare(TwitterAccount arg0, TwitterAccount arg1) {
		// TODO Auto-generated method stub
		int followers1 = arg0.getFollowerCount(), followers2 = arg1.getFollowerCount();
		if (followers1 - followers2 > 0) {
			return -1;
		}
		else if (followers1 - followers2 < 0) {
			return 1;
		}
		return 0;
	}


}
