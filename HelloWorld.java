import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class HelloWorld {
    public static void main(String[] args) {
        String strJson = JsonParser.getJSONFromFile("C:/Doks/Teamprojekt/bp(with codes 42K).json");
        ArrayList<String> urls = new ArrayList<>();        
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(strJson);
            JSONArray jsonarray = (JSONArray)obj;
            /*************** First Name ****************/

            for (int i = 0; i < jsonarray.size(); i++) {
                System.out.println("jsonarray get("+i+") = "+jsonarray.get(i));
                JSONObject jsonObject = (JSONObject)jsonarray.get(i);
                urls.add((String) jsonObject.get("webside"));
            }
            System.out.println(urls);
        }
        catch(Exception ex) {
            System.out.println("not works");
            ex.printStackTrace();
        }
        crawl(1,urls,new ArrayList<String>());
    }

    private static void crawl (int level, ArrayList<String> urls, ArrayList<String> visited){
        for(int i = 0; i<urls.size();i++){
            Document doc = request(urls.get(i), visited);
        }
    }

    private static Document request(String url, ArrayList<String> v) {
        try{
            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if(con.response().statusCode() == 200) {
                System.out.println("Link: " + url);
            }
            return null;
        }
        catch(IOException e) {
            System.out.println("Not available: " + url)
            return null;
    }
}
}