/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author kmarti
 */
public class MainController implements Initializable {
    
    @FXML private TextField cityname;
    @FXML private TextField citylat;
    @FXML private TextField citylong;
    @FXML private AnchorPane weatherpane;
    
    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws IOException 
    {
        System.out.println("Load Weather Data");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Weatherdata.fxml"));
            AnchorPane pane = loader.load();
            WeatherdataController controller = loader.<WeatherdataController>getController();
            // edit the below
            if (!(citylat.getText().equals("") && citylong.getText().equals(""))) {
                controller.setAPICall(citylat.getText(), citylong.getText());
            } else if (!cityname.getText().equals("")) {
                controller.setAPICall(cityname.getText());
            }
            
            
            weatherpane.getChildren().setAll(pane);
    }
    
    @FXML
    private void handleResetButtonAction(ActionEvent event) 
    {
        cityname.setText("");
        citylat.setText("");
        citylong.setText("");
    }
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) 
    {
        Platform.exit();
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
