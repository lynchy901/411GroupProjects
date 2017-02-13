/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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
    
    public String convertKelvin(String x) {
        System.out.println("converting " + x + " to F");
        
        double temp;
        temp = (9/5)*(Double.parseDouble(x) - 273.15) + 32;
        return Double.toString(temp);
    }
    
    public String convertCelcius(String x)
    {
        double temp;
        temp = (Double.parseDouble(x)* 9/5.0) +32;
        return Double.toString(temp);
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
        map.put("mode", "xml");
        
        urlString = APIRequestHelper.makeURLString("http://api.openweathermap.org/data/2.5/weather?", map);
        String results = APIRequestHelper.makeGetRequest(urlString);
        updateLabels(results);
        //System.out.println(results);
        
    }
    
    public void setAPICall(String lat, String lng)
    {
        Map<String, String> map = new HashMap<String, String>();
        String urlString = "";

        map.put("lat", lat);
        map.put("lon", lng);
        map.put("APPID", API_KEY);
        map.put("mode", "xml");
        
        urlString = APIRequestHelper.makeURLString("http://api.openweathermap.org/data/2.5/weather?", map);
        String results = APIRequestHelper.makeGetRequest(urlString);
       
        //System.out.println(results);

        
    }
    
    public void updateLabels(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(
                    xml)));
           NodeList current = document.getElementsByTagName("current");
           NodeList children = current.item(0).getChildNodes();
           
           for (int i = 0; i < children.getLength(); i++) {
               
               System.out.println(children.item(i).getNodeName());
               if (children.item(i).getNodeName().equals("humidity")) {
                   humidity.setText(children.item(i).getAttributes().getNamedItem("value").getNodeValue() + " %");
               }
               if (children.item(i).getNodeName().equals("city")) {
                   cityId.setText(children.item(i).getAttributes().getNamedItem("id").getNodeValue());
                   cityName.setText(children.item(i).getAttributes().getNamedItem("name").getNodeValue());
                   Node currNode = children.item(i);
                   if (currNode.getChildNodes().getLength() > 0) {
                       NodeList childList = currNode.getChildNodes();
                       for (int y = 0; y < childList.getLength(); y++) {
//                           if (childList.item(y).getNodeName().equals("coord")) {
//                               cityLat.setText(childList.item(y).getAttributes().getNamedItem("lat").getNodeValue();
//                             cityLong.setText(childList.item(y).getAttributes().getNamedItem("lon").getNodeValue());
//                           }
                       } 
                   }
               }
               if (children.item(i).getNodeName().equals("temperature")) {
                   tempCurrent.setText(convertKelvin(children.item(i).getAttributes().getNamedItem("value").getNodeValue()) + " °F");
                   tempMin.setText(convertKelvin(children.item(i).getAttributes().getNamedItem("min").getNodeValue()) + " °F");
                   tempMax.setText(convertKelvin(children.item(i).getAttributes().getNamedItem("max").getNodeValue()) + " °F");
               }
               if (children.item(i).getNodeName().equals("precipitation")) {
                   precip.setText(children.item(i).getAttributes().getNamedItem("mode").getNodeValue());
               }
               if (children.item(i).getNodeName().equals("wind")) {
                   Node currNode = children.item(i);
                   if (currNode.getChildNodes().getLength() > 0) {
                       NodeList childList = currNode.getChildNodes();
                       for (int y = 0; y < childList.getLength(); y++) {
                           if (childList.item(y).getNodeName().equals("speed")) {
                               windSpeed.setText(childList.item(y).getAttributes().getNamedItem("value").getNodeValue() + " mph");

                           }
                           if (childList.item(y).getNodeName().equals("direction")) {
                               windDir.setText(childList.item(y).getAttributes().getNamedItem("value").getNodeValue());

                           }
                       }
                   }
               }
           }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
