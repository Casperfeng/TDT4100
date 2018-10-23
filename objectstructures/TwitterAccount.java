package objectstructures;

import java.util.ArrayList;
/**
 * 17/2/18
 * @author casperfeng
 *
 */
public class TwitterAccount {
	private String username; //brukernavnet til twitterbrukeren
	private ArrayList <TwitterAccount> following; //brukeren følger
	private ArrayList <TwitterAccount> followedBy; //brukeren blir fulgt av
	private ArrayList <Tweet> tweets; //tweetsene til brukeren
	private int retweetCounter = 0; //antall retweets av brukeren
	
	/**
	 * konstruktøren til en helt ny twitterbruker
	 * @param username er brukernavnet til twitterbrukeren
	 */
	public TwitterAccount(String username) {
		this.username = username;
		this.following = new ArrayList<>(); //følger ingen 
		this.followedBy = new ArrayList<>(); //har ingen følgere
		this.tweets = new ArrayList<>(); //tweetsene til twitterbrukeren
	}
	/**
	 * returnerer brukernavnet til twitterbrukeren
	 * @return brukernavnet i streng
	 */
	public String getUserName() {
		return this.username;
	}
	
	/**
	 * følger en bruker
	 * @param account er brukeren som skal følges
	 */
	public void follow(TwitterAccount account) {
		if (account != this && !following.contains(account)){
			this.following.add(account);
			account.followedBy.add(this);
		}
	}
	
	/**
	 * avfølger en bruker
	 * @param account er brukeren som avfølges
	 */
	public void unfollow(TwitterAccount account) {
		this.following.remove(account);
		account.followedBy.remove(this);
	}
	
	/**
	 * sjekker om twitterbrukeren følger account
	 * @param account er brukeren som skal sjekkes opp
	 * @return true om brukeren følger account, false ellers
	 */
	
	public boolean isFollowing(TwitterAccount account) {
		if (this.following.contains(account)) {
			return true;
		}
		return false;
	}
	
	/**
	 * sjekker om twitterbrukeren blir fulgt av account
	 * @param account er brukeren som skal sjekkes opp
	 * @return true om twitterbrukeren blir fulgt av account, false ellers
	 */
	
	public boolean isFollowedBy(TwitterAccount account) {
		if (account.isFollowing(this)) {
			return true;
		}
		return false;
	}
	
	/**
	 * lager en ny tweet i twitterbrukeren
	 * @param text er teksten i tweeten
	 */
	
	public void tweet(String text) {
		Tweet newTweet = new Tweet(this, text);
		this.tweets.add(0, newTweet);
	}
		
	/**
	 * retweeter en tweet
	 * @param tweet er tweeten som blir retweetet
	 */
	
	public void retweet(Tweet tweet) {
		tweets.add(0, new Tweet(this, tweet));
		tweet.retweet();
		
		if (tweet.getOriginalTweet() != null) {
			tweet.getOriginalTweet().getOwner().increaseRetweetCounter();
		} else {
			tweet.getOwner().increaseRetweetCounter();
		}
	}
	
	/**
	 * returnerer en tweet avhengig av hvilket tall som oppgis
	 * @param i hvilken tweet som skal returneres
	 * @return tweet nr i
	 */
	public Tweet getTweet(int i) {
		int index = i-1;
		if (index < 0 || index >= this.tweets.size()) {
			throw new IllegalArgumentException("Invalid index");
			
		}
		return this.tweets.get(index);	
	}
	
	/**
	 * returnerer antall tweets
	 * @return antall tweets fra denne brukeren
	 */
	public int getTweetCount() {
		return this.tweets.size();
	}
	
	/**
	 * returnerer antall retweets
	 * @return antall retweets fra denne brukeren
	 */
	
	public int getRetweetCount() {
		return this.retweetCounter;
	}
	
	/**
	 * øker retweetcounteren med en
	 */
	
	public void increaseRetweetCounter() {
		this.retweetCounter++;
	}
	
	public String toString() {
		return this.username;
	}
	

}
