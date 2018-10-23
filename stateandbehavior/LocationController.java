package stateandbehavior;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LocationController {
 
    Location location;
 
    @FXML
    Text locationOutput;
 
    @FXML
    void initialize() {
        location = new Location();
    }
 
    @FXML
    void handleUpAction() {
    		location.up();
        locationOutput.setText("Current position: " + location.getX() + ", " + location.getY());
    }
    @FXML
    void handleDownAction() {
        location.down();
        locationOutput.setText("Current position: " + location.getX() + ", " + location.getY());
    }
    @FXML
    void handleRightAction() {
    		location.right();
        locationOutput.setText("Current position: " + location.getX() + ", " + location.getY());
    }
    @FXML
    void handleLeftAction() {
    		location.left();
        locationOutput.setText("Current position: " + location.getX() + ", " + location.getY());
    }
   
}