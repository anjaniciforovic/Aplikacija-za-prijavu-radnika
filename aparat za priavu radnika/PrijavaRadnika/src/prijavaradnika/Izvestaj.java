
package prijavaradnika;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Izvestaj {
   
    public void mesecniIzvestaj()throws Exception{
    LocalDate danasnji = LocalDate.now();

        try{
        boolean poslednji= danasnji.equals(danasnji.with(TemporalAdjusters.lastDayOfMonth()));


        if (poslednji) {
           String naziv1="tekuci.json";
           String naziv2="prosli.json";



            JSONArray jsonNiz=(JSONArray) new JSONParser().parse(new FileReader(naziv1));
            FileWriter prosli=new FileWriter(naziv2);
            jsonNiz.writeJSONString(prosli);
            prosli.close();
            for(Object object : jsonNiz){
            JSONObject o=(JSONObject) object;

            o.put("kasnjenje",0);
            o.put("prekovremeno",0);
            o.put("bonus",0);

            }
        }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
    public void dnevniIzvestaj()throws Exception{
        LocalTime krajDana = LocalTime.of(23, 55);


        LocalTime trenutnoVreme = LocalTime.now();

        try{
        if (trenutnoVreme.isAfter(krajDana)) {
            String naziv="tekuci.json";
            JSONArray jsonNiz=(JSONArray) new JSONParser().parse(new FileReader(naziv));
            for(Object object : jsonNiz){
            JSONObject o=(JSONObject) object;
            o.put("vremeDolaska","00:00:00");
            o.put("vremeOdlaska","00:00:00");


            }
        }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}