package encapsulation;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VehicleController {
	
	private Vehicle vehicle;
	
	@FXML private TextField getVehicleTextField;
	@FXML private TextField getFuelTextField;
	@FXML private TextField getRNTextField;
	
	@FXML private TextField setVehicleTextField;
	@FXML private TextField setFuelTextField;
	@FXML private TextField setRNTextField;
	
	@FXML private TextArea exceptionTextArea;
	
	@FXML
	private void initialize() {
		
		getVehicleTextField.setDisable(true);
		getFuelTextField.setDisable(true);
		getRNTextField.setDisable(true);
		
	}
	
	private void update() {
		getVehicleTextField.setText(Character.toString(vehicle.getVehicleType()));
		getFuelTextField.setText(Character.toString(vehicle.getFuelType()));
		getRNTextField.setText(vehicle.getRegistrationNumber());
		
	}
	
	private void showException(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.write(e.getMessage() != null ? e.getMessage() : "No message");
		pw.write("\n\n");
		e.printStackTrace(pw);
		exceptionTextArea.setText(sw.toString());
	}
	
	private void clearException() {
		exceptionTextArea.setText("");
	}
	
	private void tryvehicleManipulation(Consumer<Vehicle> vehicleConsumer) {
		try {
			vehicleConsumer.accept(vehicle);
		}
		catch (Exception e) {
			showException(e);
			throw e;
		}
		finally {
			update();
		}
		
		clearException();	
	}
	
	@FXML
	private void create() {
		char type = setVehicleTextField.getText().charAt(0);
		char fuel = setFuelTextField.getText().charAt(0);
		String rn = setRNTextField.getText();
		try {
			vehicle = new Vehicle(type, fuel, rn); 
		}
		catch(Exception e) {
			showException(e); 
			throw e; 
		}
		finally {
			update(); 
		}
		clearException(); 
		
	}
	

}