package encapsulation;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CardDeckController {
	
	private CardDeck cardDeck;
	
	@FXML TextField suitSizeTextField;
	@FXML TextArea toStringTextArea;
	@FXML Button shuffleButton;
	
	@FXML
	private void initialize() {
		update();
	}
	
	private void update() {
		toStringTextArea.setDisable(cardDeck == null);
		shuffleButton.setDisable(cardDeck == null);
		
		if(cardDeck != null) {
			toStringTextArea.setText(cardDeck.toString());			
		}
	}
	
	@FXML
	private void handleNewDeck() {
		int suitSize = Integer.parseInt(suitSizeTextField.getText());
		cardDeck = new CardDeck(suitSize);
		update();
	}
	
	@FXML
	private void handleShuffle() {
		cardDeck.shufflePerfectly();
		update();
	}

}
