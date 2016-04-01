import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by madavis on 3/25/16.
 */
public class NOAAData {

    public static void main(String[] args) {

        //create properties file
        Properties prop = new Properties();
        String propFileName = "noaa.properties";
        ChartMaker chart;

        InputStream readFile = NOAAData.class.getResourceAsStream(propFileName);

        //try to read the file
        if(readFile != null) {
            try {
                prop.load(readFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        String token = prop.getProperty("token");
        NOAARestClient restClient = new NOAARestClient(token);
        chart = new ChartMaker("Snowfall in Longmont", restClient.noaaGetRequest());



    }

}
