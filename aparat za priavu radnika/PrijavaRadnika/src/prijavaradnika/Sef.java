
package prijavaradnika;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Sef extends Zaposleni {
    
     public static void dodajRadnika(String fajl) throws Exception{
        Random random = new Random();

        // 
        int donja = 1000;
        int gornja = 9999;
        
        FileWriter fw=null;
        JSONArray jsonNiz =(JSONArray) new JSONParser().parse(new FileReader(fajl));
        JSONArray jsonZaposleni=new JSONArray();
        Zaposleni zaposleni=new Zaposleni();
        Scanner scanner=new Scanner(System.in);
        for(Object obj: jsonNiz){
            JSONObject jsonObj=(JSONObject) obj;
            jsonZaposleni.add(jsonObj);
            }
        JSONObject novi_zaposleni=new JSONObject();
        int id=random.nextInt(gornja-donja+1)+donja;
        novi_zaposleni.put("ID",id);
        System.out.println("Unesite ime:");
        String ime=scanner.nextLine();
        novi_zaposleni.put("ime",ime);
        System.out.println("Unesite prezime: ");
        String prezime=scanner.nextLine();
        novi_zaposleni.put("prezime",prezime);
        novi_zaposleni.put("zvanje","radnik");
        int sifra=random.nextInt(gornja-donja+1)+donja;
        novi_zaposleni.put("sifra",sifra);
        novi_zaposleni.put("vremeDolaska", "00:00:00");
        novi_zaposleni.put("vremeOdlaska", "00:00:00");
        novi_zaposleni.put("prekovremeno", 0);
        novi_zaposleni.put("kasnjenje", 0);
        novi_zaposleni.put("bonus", 0);
         System.out.println("Unesi pocetnu platu radnika: ");
         int plata=scanner.nextInt();
        novi_zaposleni.put("plata", plata);
        
        scanner.close();
        
        jsonZaposleni.add(novi_zaposleni);
        try {
            fw=new FileWriter("tekuci.json");
            fw.write(jsonZaposleni.toString());
            
        } catch(IOException e){
                System.out.println(e.getMessage());
            }
        finally{
            if(fw != null)
                fw.close();
        }
    }
     public static void procitajRadnike(String fajl) throws Exception{
        
        JSONArray jsonNiz =(JSONArray) new JSONParser().parse(new FileReader(fajl));
        
        for(Object obj: jsonNiz){
            JSONObject jsonObj = (JSONObject) obj;

                    System.out.println("Ime: "+jsonObj.get("ime"));
                    System.out.println("Prezime: "+jsonObj.get("prezime"));
                    System.out.println("ID: "+jsonObj.get("ID"));
                    System.out.println("Sifra: "+jsonObj.get("sifra"));
                    System.out.println("Kasnjenja: "+jsonObj.get("kasnjenja"));
                    System.out.println("Plata: "+jsonObj.get("plata"));
                    System.out.println("Prekovremeno: "+jsonObj.get("prekovremeno"));
                    System.out.println("Bonusi: "+jsonObj.get("bonus"));
                    System.out.println("\n");

        }
     }
     
     public static void isplataPlate(String fajl) throws Exception{
         
         JSONArray jsonNiz =(JSONArray) new JSONParser().parse(new FileReader(fajl));
         
         for(Object obj: jsonNiz){
             JSONObject jsonObj = (JSONObject) obj;
                 int bonus=Integer.parseInt(jsonObj.get("bonus").toString());
                 int prekovremeno=Integer.parseInt(jsonObj.get("prekovremeno").toString());
                 int kasnjenja=Integer.parseInt(jsonObj.get("kasnjenja").toString());
                 int plata=Integer.parseInt(jsonObj.get("plata").toString());
                 int isplata=plata+bonus-kasnjenja*7+prekovremeno*10;
                 
                 System.out.println("Radnik: "+jsonObj.get("ime")+" "+jsonObj.get("prezime")+" je primio isplatu u vrednosti od " +isplata+"$");
                 System.out.println("\n");
         }
         
     
     }
    
}
