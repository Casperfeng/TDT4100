package objectstructures;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PartnerController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField p1Name;

    @FXML
    private TextField p4Name;

    @FXML
    private TextField p3Name;

    @FXML
    private TextField p2Name;

    @FXML
    private TextField p1Partner;

    @FXML
    private TextField p2Partner;

    @FXML
    private TextField p3Partner;

    @FXML
    private TextField p4Partner;

    @FXML
    private ComboBox<String> p1newPartner;

    @FXML
    private ComboBox<String> p2newPartner;

    @FXML
    private ComboBox<String> p3newPartner;

    @FXML
    private ComboBox<String> p4newPartner;

    private int partnerCount = 0 ; 
    private Partner[] partners = new Partner[4]; 
    
    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();
    @FXML
    public void initialize(){
    	partners = new Partner[4]; 
    }
    
    
    public void update(){
    	String partnerName; 
    	if(partnerCount>0){
    		partnerName = partners[0].getPartner() != null ? partners[0].getPartner().getName() : "";  
    		p1Name.setText(partners[0].getName());
    		p1Partner.setText(partnerName);
    		p1newPartner.setItems(comboBoxData);
    	}

    	if(partnerCount>1){
    		partnerName = partners[1].getPartner() != null ? partners[1].getPartner().getName() : "";  
    		p2Name.setText(partners[1].getName());
    		p2Partner.setText(partnerName);
    		p2newPartner.setItems(comboBoxData);
    	}

    	if(partnerCount>2){
    		partnerName = partners[2].getPartner() != null ? partners[2].getPartner().getName() : "";  
    		p3Name.setText(partners[2].getName());
    		p3Partner.setText(partnerName);
    		p3newPartner.setItems(comboBoxData);
    	}

    	if(partnerCount>3){
    		partnerName = partners[3].getPartner() != null ? partners[3].getPartner().getName() : "";  
    		p4Name.setText(partners[3].getName());
    		p4Partner.setText(partnerName);
    		p4newPartner.setItems(comboBoxData);
    	}
    }
    @FXML
    void createPartner() {
    	Partner partner = new Partner(nameField.getText()); 
    	partners[partnerCount] = partner ; 
    	partnerCount ++ ; 
    	comboBoxData.add(partner.getName()); 
    	update(); 
    }

    @FXML
    void p1setPartner() {
    	
    	partners[0].setPartner(partners[comboBoxData.indexOf(p1newPartner.getValue())]); 
    	update(); 
    }

    @FXML
    void p2setPartner() {
    	partners[1].setPartner(partners[comboBoxData.indexOf(p2newPartner.getValue())]); 
    	update(); 
    }
   

    @FXML
    void p3setPartner() {
    	partners[2].setPartner(partners[comboBoxData.indexOf(p3newPartner.getValue())]); 
    	update(); 
    }

    @FXML
    void p4setPartner() {
    	partners[3].setPartner(partners[comboBoxData.indexOf(p4newPartner.getValue())]); 
    	update(); 
    }

}