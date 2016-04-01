import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 * Created by madavis on 3/25/16.
 */
public class NOAARestClient {


    String noaaURL = "http://www.ncdc.noaa.gov/cdo-web/api/v2/data";
    String token;
    String station = "GHCND:US1COBO0150";


    public NOAARestClient(String token) {
        this.token = token;

    }

    public JSONArray noaaGetRequest() {

        JSONArray results = null;

        //create REST request
        try {
            HttpResponse<JsonNode> response = Unirest.get(noaaURL)
                    .queryString("datasetid", "GHCND")
                    .queryString("stationid", station)
                    .queryString("startdate", "2016-01-01")
                    .queryString("enddate", "2016-01-31")
                    .queryString("datatypeid", "SNOW")
                    .queryString("limit", "100")
                    .header("token", token)
                    .asJson();
            //System.out.println(response.getBody()); //debug

            JSONObject responseObj = response.getBody().getObject();
            results = responseObj.getJSONArray("results");
            //System.out.println(results.get(0).toString()); //debug



        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return results;
    }


}
//http://www.ncdc.noaa.gov/cdo-web/api/v2/data?datasetid=GHCND&stationid=GHCND:US1COBO0150&startdate=2016-01-01&enddate=2016-01-31&datatypeid=SNOW