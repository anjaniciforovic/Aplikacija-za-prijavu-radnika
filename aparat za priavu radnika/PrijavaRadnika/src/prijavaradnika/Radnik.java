
package prijavaradnika;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Radnik extends Zaposleni{

    public Radnik(String ime, String prezime, String zvanje, Integer sifra, LocalTime vremeDolaska, LocalTime vremeOdlaska, Integer prekovremeno, Integer kasnjenja, Integer bonus, Integer plata, Integer ID) {
        super(ime, prezime, zvanje, sifra, vremeDolaska, vremeOdlaska, prekovremeno, kasnjenja, bonus, plata, ID);
    }

    public static void stanje(String id)throws Exception{
        String fajl="tekuci.json";
        JSONArray jsonNiz =(JSONArray) new JSONParser().parse(new FileReader(fajl));
        int brejker=0;
        for(Object obj: jsonNiz){
            JSONObject jsonObj = (JSONObject) obj;
            String jsonId=jsonObj.get("ID").toString();
            if(brejker==0){
                if(jsonId.equals(id)) {
                    System.out.println("Ime: "+jsonObj.get("ime"));
                    System.out.println("Prezime: "+jsonObj.get("prezime"));
                    System.out.println("ID: "+jsonObj.get("ID"));
                    System.out.println("Sifra: "+jsonObj.get("sifra"));
                    System.out.println("Kasnjenja: "+jsonObj.get("kasnjenja"));
                    System.out.println("Plata: "+jsonObj.get("plata"));
                    System.out.println("Prekovremeno: "+jsonObj.get("prekovremeno"));
                    System.out.println("Bonusi: "+jsonObj.get("bonus"));
                    brejker=1;
                } 
                    
                    
            }
        }
            
    }
}
