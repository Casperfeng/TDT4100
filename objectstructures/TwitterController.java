package objectstructures;


import java.util.Collection;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TwitterController {
	
	
    @FXML
    private TextField nameField;

    @FXML
    private Label account1Name;

    @FXML
    private Label account2Name;

    @FXML
    private Label account3Name;

    @FXML
    private Label acc1TweetCount;
    @FXML
    private Label acc2TweetCount;
    @FXML
    private Label acc3TweetCount;
    
    @FXML
    private Label acc1RetweetCount;
    @FXML
    private Label acc2RetweetCount;
    @FXML
    private Label acc3RetweetCount;


    @FXML
    private Label tweet1Title;
    @FXML
    private Label tweet2Title;
    @FXML
    private Label tweet3Title;
    @FXML
    private Label tweet4Title;

    @FXML
    private Label tweet1Text;
    @FXML
    private Label tweet2Text;
    @FXML
    private Label tweet3Text;
    @FXML
    private Label tweet4Text;
    

    @FXML
    private Label tweet1TweetedBy;
    @FXML
    private Label tweet2TweetedBy;
    @FXML
    private Label tweet3TweetedBy;
    @FXML
    private Label tweet4TweetedBy;
    
    
    @FXML
    private Label tweet1RetweetedBy;
    @FXML
    private Label tweet2RetweetedBy;
    @FXML
    private Label tweet3RetweetedBy;
    @FXML
    private Label tweet4RetweetedBy;
    
    @FXML
    private ComboBox<TwitterAccount> retweetAccountComboBox;

    @FXML
    private ComboBox<Tweet> retweetTweetComboBox;
    
    @FXML
    private TextArea tweetInput;

    @FXML
    private ComboBox<TwitterAccount> tweetInputComboBox;
    
    private ObservableList<TwitterAccount> accounts = FXCollections.observableArrayList();
    
    private ObservableList<Tweet> tweets = FXCollections.observableArrayList();
    
    @FXML
    public void initialize(){
    	tweetInputComboBox.setItems(accounts);
    	accounts.add(new TwitterAccount("Account1"));
    	accounts.add(new TwitterAccount("Account2"));
    	accounts.add(new TwitterAccount("Account3"));
    	retweetTweetComboBox.setItems(tweets);
    	retweetAccountComboBox.setItems(accounts);
    	update();
    }
    
    
    private void update(){
    	account1Name.setText(accounts.get(0).getUserName());
    	account2Name.setText(accounts.get(1).getUserName());
    	account3Name.setText(accounts.get(2).getUserName());
    	
    	// Needed to update text in ComboBoxes
    	tweetInputComboBox.setItems(accounts);
    	retweetTweetComboBox.setItems(tweets);
    	retweetAccountComboBox.setItems(accounts);
    	
    	updateTweetCounts();
    	updateTweetPanes();
    }
    
    private void updateTweetPanes() {
    	// Horrible, horrible way of doing this. Do not try this at home.

    	tweet1Title.setText("");
    	tweet2Title.setText("");
    	tweet3Title.setText("");
    	tweet4Title.setText("");
    	
    	if(tweets.size() > 0){  
    		tweet1Title.setText("Tweet");
    		tweet1Text.setText(tweets.get(0).getText());
    		tweet1TweetedBy.setText("Owner: " + tweets.get(0).getOwner().getUserName());
    		try {
        		tweet1RetweetedBy.setText("Original: " + tweets.get(0).getOriginalTweet().getOwner().getUserName());
    		} catch(Exception e){
    			tweet1RetweetedBy.setText("");
    		}
    		
    	}

    	if(tweets.size() > 1){
    		tweet2Title.setText("Tweet");
    		tweet2Text.setText(tweets.get(1).getText());
    		tweet2TweetedBy.setText("Owner: " + tweets.get(1).getOwner().getUserName());
    		try {
        		tweet2RetweetedBy.setText("Original: " + tweets.get(1).getOriginalTweet().getOwner().getUserName());
    		} catch(Exception e){
    			tweet2RetweetedBy.setText("");
    		}
    	}

    	if(tweets.size() > 2){
    		tweet3Title.setText("Tweet");
    		tweet3Text.setText(tweets.get(2).getText());
    		tweet3TweetedBy.setText("Owner: " + tweets.get(2).getOwner().getUserName());
    		try {
        		tweet3RetweetedBy.setText("Original: " + tweets.get(2).getOriginalTweet().getOwner().getUserName());
    		} catch(Exception e){
    			tweet3RetweetedBy.setText("");
    		}
    	}
    	
    	if(tweets.size() > 3){
    		tweet4Title.setText("Tweet");
    		tweet4Text.setText(tweets.get(3).getText());
    		tweet4TweetedBy.setText("Owner: " + tweets.get(3).getOwner().getUserName());
    		try {
        		tweet4RetweetedBy.setText("Original: " + tweets.get(3).getOriginalTweet().getOwner().getUserName());
    		} catch(Exception e){
    			tweet4RetweetedBy.setText("");
    		}
    	}
    	
    }
    
    private void updateTweetCounts(){
    	acc1TweetCount.setText(String.valueOf("TweetCount " + accounts.get(0).getTweetCount()));
    	acc2TweetCount.setText(String.valueOf("TweetCount " + accounts.get(1).getTweetCount()));
    	acc3TweetCount.setText(String.valueOf("TweetCount " + accounts.get(2).getTweetCount()));
    	acc1RetweetCount.setText(String.valueOf("RetweetCount " + accounts.get(0).getRetweetCount()));
    	acc2RetweetCount.setText(String.valueOf("RetweetCount " + accounts.get(1).getRetweetCount()));
    	acc3RetweetCount.setText(String.valueOf("RetweetCount " + accounts.get(2).getRetweetCount()));
    }
    
    private void updateTweetList(){
    	Collection<Tweet> newTweets = new HashSet<>();
    	
    	for (int i = 1; i <= accounts.get(0).getTweetCount(); i++){
    		newTweets.add(accounts.get(0).getTweet(i));
    	}
    	for (int i = 1; i <= accounts.get(1).getTweetCount(); i++){
    		newTweets.add(accounts.get(1).getTweet(i));
    	}
    	for (int i = 1; i <= accounts.get(2).getTweetCount(); i++){
    		newTweets.add(accounts.get(2).getTweet(i));
    	}
    	
    	tweets.setAll(newTweets);
    }
    
    
    public void createTweet(){
    	tweetInputComboBox.getValue().tweet(tweetInput.getText());
    	tweetInput.clear();
    	updateTweetList();
    	update();
    }
    
    public void createRetweet(){
    	retweetAccountComboBox.getValue().retweet(retweetTweetComboBox.getValue());
    	updateTweetList();
    	update();
    }
}