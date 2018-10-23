package game;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PersistentGameController {


	@FXML TextArea console;

	@FXML TextField inputField;
	@FXML TextField fileName;


	//Her maa du deklarerere spillet ditt dersom det heter noe annet enn TicTacToe
	TicTacToe game;

	public void initialize(){
		//Her maa du opprette et objekt av spillet ditt med de argumentene som behoves for det - resten av koden vil gaa ut ifra at du har kalt den game
		game = new TicTacToe() ;
		console.setText(game.toString());
	}

	public void update() {
		console.setText(game.toString());
	}


	@FXML
	public void sendInput(){
		String in = inputField.getText();
		System.out.println(in);
		//Denne metoden kan hete hva som helst, men husk aa endre det her
		game.getInput(in);
		update();

	}

	@FXML
	public void undo() {
		System.out.println("UNDO");
		game.undo();
		update();
	}

	@FXML
	public void redo() {
		System.out.println("REDO");
		game.redo();
		update();
	}

	@FXML
	public void save() throws IOException {
		game.save(fileName.getText());
		update();
	}

	@FXML
	public void load() throws IOException {
		game.load(fileName.getText());
		update();
	}
}
