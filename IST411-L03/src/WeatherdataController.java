/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author kmarti
 */
public class WeatherdataController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String apiCall;
    private final String API_KEY = "1e01a85676af57dd55ad02d9f60f16bb";
    
    
    @FXML private Label cityName;
    @FXML private Label cityId;
    @FXML private Label tempCurrent;
    @FXML private Label tempMin;
    @FXML private Label tempMax;
    @FXML private Label precipType;
    @FXML private Label precip;
    @FXML private Label windSpeed;
    @FXML private Label windDir;
    @FXML private Label humidity;
    @FXML private Label cityLat;
    @FXML private Label cityLong;
    
    // In this controller you need to get the information from the api and edit the label fields ex:  cityName.setText(namevar);
    // None of the values have units, so you need to add those to the end of the string you are editing. ex: tempCurrent.setText(temp + " °F");
    // I belive the weather api uses metric units, so I made some unit conversion methods below for temp and windspeed
    
    public double convertCelcius(double x)
    {
        double temp;
        temp = (x* 9/5.0) +32;
        return temp;
    }
    
    public double convertmps(double m)
    {
        double mph;
        mph = (m * 3600)/1609.3;
        return mph;
    }
    
    public void setAPICall(String name)
    {
        Map<String, String> map = new HashMap<String, String>();
        String urlString = "";

        map.put("q", name);
        map.put("APPID", API_KEY);
        
        urlString = APIRequestHelper.makeURLString("http://api.openweathermap.org/data/2.5/weather?", map);
        String results = APIRequestHelper.makeGetRequest(urlString);
        
        
        System.out.println(results);
    }
    
    public void setAPICall(String lat, String lng)
    {
        Map<String, String> map = new HashMap<String, String>();
        String urlString = "";

        map.put("lat", lat);
        map.put("lon", lng);
        map.put("APPID", API_KEY);
        
        urlString = APIRequestHelper.makeURLString("http://api.openweathermap.org/data/2.5/weather?", map);
        String results = APIRequestHelper.makeGetRequest(urlString);
        
        
        System.out.println(results);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
