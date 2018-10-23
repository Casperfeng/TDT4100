package objectstructures;
/**
 * 17/2/18
 * @author casperfeng
 *
 */
public class Tweet {
	
	private TwitterAccount owner; //eier av tweeten
	private String text; //teksten til en tweet
	private Tweet originalTweet; //original-tweeten
	private int retweetCount = 0; //antall ganger retweetet
	/**
	 * Kontruktør for ny original tweet
	 * @param acc er twitterbrukeren til tweeten
	 * @param text teksten til tweeten
	 */
	public Tweet(TwitterAccount acc, String text) {
		this.owner = acc;
		this.text = text;	
		this.originalTweet = null;
		 
	}
	/**
	 * Konstruktør for en retweet
	 * @param acc er twitterbrukeren som retweeter
	 * @param originalTweet er tweeten som blir retweetet
	 */
	public Tweet(TwitterAccount acc, Tweet originalTweet) {
		if (acc.equals(originalTweet.getOwner())){ //kan ikke retweete egne tweets
			throw new IllegalArgumentException("You can't retweet your own tweet!");
		}
		this.owner = acc;
		this.text = originalTweet.getText();
		this.originalTweet = originalTweet;
		if (originalTweet.getOriginalTweet() == null) {
			this.originalTweet = originalTweet;
		}
		else {
			this.originalTweet = originalTweet.getOriginalTweet();
		}
		originalTweet.retweet(); //teller en retweet
	}
	
	/**
	 * returnerer teksten til en tweet
	 * @return tweeten som tekstform
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * returnerer twitterbrukeren til en tweet
	 * @return twitterbrukeren til tweeten
	 */
	public TwitterAccount getOwner() {
		return this.owner;
	}
	
	/**
	 * returnerer originaltweeten 
	 * @return originaltweeten
	 */
	public Tweet getOriginalTweet() {
		return this.originalTweet;
	}
	
	/**
	 * returnerer retweetcounten 
	 * @return antall retweets
	 */
	public int getRetweetCount() {
		return this.retweetCount;
	}
	
	/**
	 * øker retweetcounten med en
	 */
	
	public void retweet() {
		this.retweetCount++;
	}
	
	/**
	 * Returnerer en tweet på en fin måte :) 
	 */
	public String toString() {
		return this.getOwner().getUserName() + ": " + this.getText();
	}
	
	
}