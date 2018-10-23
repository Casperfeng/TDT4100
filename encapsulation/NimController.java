package encapsulation;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class NimController {
	
	private Nim nim;
	
	@FXML private Text pile1;
	@FXML private Text pile2;
	@FXML private Text pile3;
	@FXML private Text gameState;
	
	@FXML private TextField pileSizeField;
	@FXML private TextField numberField;
	
	@FXML private Text feedbackText;
	@FXML private ChoiceBox<String> targetPileChoice; 
	
	@FXML
	public void initialize() {
		nim = new Nim();
		update();
	}
	
	@FXML
	public void newGame() {
		if (pileSizeField.getText() != null && (! pileSizeField.getText().trim().isEmpty())) {
			int pileSize = Integer.valueOf(pileSizeField.getText());
			nim = new Nim(pileSize);
		}
		else {
			nim = new Nim();
		}
		update();
	}
	
	@FXML
	public void removePieces(){
		if (numberField.getText() == null || numberField.getText().isEmpty()) {
			return;
		}
		int number = Integer.valueOf(numberField.getText());
		int targetPile = Integer.valueOf(targetPileChoice.getValue()) - 1;
		
		if (nim.isValidMove(number, targetPile)) {
			nim.removePieces(number, targetPile);
			feedbackText.setText(String.format("Removed %d pieces.", number));
			update();
		} else {
			feedbackText.setText("Illegal move!");
		}
	}
	
	@FXML
	private void update() {
		pile1.setText(Integer.toString(nim.getPile(0)));
		pile2.setText(Integer.toString(nim.getPile(1)));
		pile3.setText(Integer.toString(nim.getPile(2)));
		
		gameState.setText("Game " + (nim.isGameOver() ? "over!" : "active"));
		gameState.setFill(nim.isGameOver() ? Color.RED : Color.WHITE);
	}
	
	
}
