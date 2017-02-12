import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author nathanlynch
 */
public class APIRequestHelper {
    
    public static String makeURLString (String baseURL, Map params){
        String urlString = baseURL;
        int counter = 0;
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (counter == 0) {
                urlString += pair.getKey() + "=" + pair.getValue().toString().replace(" ", "%20");
            } else {
                urlString += "&" + pair.getKey() + "=" + pair.getValue().toString().replace(" ", "%20");
            }
            it.remove(); 
            counter++;
        }
        System.out.println(urlString);
        return urlString;
    }
    
    public static String makeGetRequest (String urlString) {
        String results = "";
        try {
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new CityNotFoundException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
                if ((output = br.readLine()) != null) {
                    results = output;
                }

		conn.disconnect();

	  } catch (MalformedURLException e) {
                System.out.println("malformed");
		e.printStackTrace();

	  } catch (IOException e) {
                System.out.println("IOException");
		e.printStackTrace();

	  } catch (CityNotFoundException e) {
              e.printStackTrace();
          }
        
        return results;

	}
}

class CityNotFoundException extends Exception
    {
      //Parameterless Constructor
      public CityNotFoundException() {}

      //Constructor that accepts a message
      public CityNotFoundException(String message)
      {
         super(message);
      }
    }
    
