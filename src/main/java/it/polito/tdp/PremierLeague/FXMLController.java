/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Avversari;
import it.polito.tdp.PremierLeague.model.Model;
import it.polito.tdp.PremierLeague.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnTopPlayer"
    private Button btnTopPlayer; // Value injected by FXMLLoader

    @FXML // fx:id="btnDreamTeam"
    private Button btnDreamTeam; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="txtGoals"
    private TextField txtGoals; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	
    	Double goal = 0.0;
    
    	try {
    		goal = Double.valueOf(txtGoals.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Inserire valore numerico!");
    	}
    	
    	this.model.creaGrafo(goal);
    	if(this.model.getVerticiSize() == 0 || this.model.getArchiSize() == 0) {
    		txtResult.appendText("Nessun grafo possibile per il valore selezionato");
    		return;
    	}
    	
    	txtResult.appendText("GRAFO CREATO : \n");
    	txtResult.appendText("#"+this.model.getVerticiSize()+" vertici \n");
    	txtResult.appendText("#"+this.model.getArchiSize()+" archi \n");
    	
    	
    }

    @FXML
    void doDreamTeam(ActionEvent event) {
    	txtResult.clear();
    	Integer k = 0;
    	try {
    		k = Integer.parseInt(txtK.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Inserire valore numerico!");
    	}
    	
    	if(model.getGraph() == null) {
    		txtResult.appendText("Creare prima grafo!");
    		return;
    	}
    	
    	for(Player p : this.model.getDreamTeam(k)) {
    		txtResult.appendText(p+ "\n");
    	}
    	
    	txtResult.appendText("GRADO DREAM TEAM "+this.model.getBestDegree());
    	

    }

    @FXML
    void doTopPlayer(ActionEvent event) {
    	
    	txtResult.clear();
    	if(this.model.getGraph() == null) {
    		txtResult.appendText("Creare prima il grafo!");
    		return;
    	}
    	
    	txtResult.appendText("TOP PLAYER: "+this.model.topPlayer());
    	txtResult.appendText("\n Lista avversari battuti: \n");
    	for(Avversari a : this.model.getListBattuti(this.model.topPlayer())) {
    		txtResult.appendText(a.toString()+ "\n");
    	}
    	
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTopPlayer != null : "fx:id=\"btnTopPlayer\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtGoals != null : "fx:id=\"txtGoals\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
