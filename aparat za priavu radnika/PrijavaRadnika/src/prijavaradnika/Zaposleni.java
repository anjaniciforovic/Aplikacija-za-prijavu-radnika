
package prijavaradnika;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Zaposleni extends Kartica implements Masina{
    private String ime;
    private String prezime;
    private String zvanje;
    private Integer sifra;
    private LocalTime vremeDolaska;
    private LocalTime vremeOdlaska;
    private Integer prekovremeno;
    private Integer kasnjenja;
    private Integer bonus;
    private Integer plata;

    public Zaposleni(String ime, String prezime, String zvanje, Integer sifra, LocalTime vremeDolaska, LocalTime vremeOdlaska, Integer prekovremeno, Integer kasnjenja, Integer bonus, Integer plata, Integer ID) {
        super(ID);
        this.ime = ime;
        this.prezime = prezime;
        this.zvanje = zvanje;
        this.sifra = sifra;
        this.vremeDolaska = vremeDolaska;
        this.vremeOdlaska = vremeOdlaska;
        this.prekovremeno = prekovremeno;
        this.kasnjenja = kasnjenja;
        this.bonus = bonus;
        this.plata = plata;
    }

    public Zaposleni() {
         super();
        ime = "";
        prezime = "";
        zvanje = "";
        sifra = 0;
        vremeDolaska = LocalTime.of(0,0);
        vremeOdlaska =LocalTime.of(0,0) ;
        prekovremeno = 0;
        kasnjenja = 0;
        bonus = 0;
        plata = 0;
        
    }

    @Override
    public void poruke(String ispis){
        System.out.println(ispis);
    };
    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public Integer getSifra() {
        return sifra;
    }

    public void setSifra(Integer sifra) {
        this.sifra = sifra;
    }

    public LocalTime getVremeDolaska() {
        return vremeDolaska;
    }

    public void setVremeDolaska(LocalTime vremeDolaska) {
        this.vremeDolaska = vremeDolaska;
    }

    public LocalTime getVremeOdlaska() {
        return vremeOdlaska;
    }

    public void setVremeOdlaska(LocalTime vremeOdlaska) {
        this.vremeOdlaska = vremeOdlaska;
    }

    public Integer getPrekovremeno() {
        return prekovremeno;
    }

    public void setPrekovremeno(Integer prekovremeno) {
        this.prekovremeno = prekovremeno;
    }

    public Integer getKasnjenja() {
        return kasnjenja;
    }

    public void setKasnjenja(Integer kasnjenja) {
        this.kasnjenja = kasnjenja;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getPlata() {
        return plata;
    }

    public void setPlata(Integer plata) {
        this.plata = plata;
    }

    @Override
    public String toString() {
        return "Zaposleni{" + "ime=" + ime + ", prezime=" + prezime + ", zvanje=" + zvanje + ", sifra=" + sifra + ", vremeDolaska=" + vremeDolaska + ", vremeOdlaska=" + vremeOdlaska + ", prekovremeno=" + prekovremeno + ", kasnjenja=" + kasnjenja + ", bonus=" + bonus + ", plata=" + plata + '}';
    }
    
     public boolean proveriId(String id,String fajl) throws Exception {
        
        JSONArray jsonNiz =(JSONArray) new JSONParser().parse(new FileReader(fajl));
        

        for(int i = 0; i < jsonNiz.size(); i++){
            JSONObject obj = (JSONObject) jsonNiz.get(i);
            String jsonId =obj.get("ID").toString();
            if (jsonId.equals(id) && jsonId != null ) {
                return true;
            }
        }

        return false;
    }
    
      public void dolazakNaPosao(String id,String fajl) throws Exception{
        vremeDolaska = LocalTime.now();
        FileWriter fw;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
       
        String vreme = vremeDolaska.format(formatter);

        JSONArray jsonNiz =(JSONArray) new JSONParser().parse(new FileReader(fajl));
        
        JSONArray jsonZaposleni=new JSONArray();
        
         for (Object obj:jsonNiz) {
            JSONObject jsonObj = (JSONObject) obj;
            String jsonId=jsonObj.get("ID").toString();
            
            if(jsonId.equals(id) && unesiSifru(fajl,id)){
            int kasnjenje = Integer.parseInt(jsonObj.get("kasnjenja").toString());
            String[] splittedTime = vreme.split(":");

            if (Integer.parseInt(splittedTime[0]) > 8 || (Integer.parseInt(splittedTime[0]) == 8 && Integer.parseInt(splittedTime[1]) > 15)) {
                kasnjenje += 1;
            }
            jsonObj.put("dolazak",vreme);
            System.out.println("Zaposleni " + jsonObj.get("ime") + " " + jsonObj.get("prezime") + " je dosao/la na posao u " + jsonObj.get("dolazak"));
            
            jsonObj.put("dolazak", vreme);
            jsonObj.put("kasnjenja", kasnjenje);
            
            poruke("Dobro dosao/la!\n"); 
            }
            jsonZaposleni.add(jsonObj);}
            try{
               fw=new FileWriter("tekuci.json");
               fw.write(jsonZaposleni.toJSONString());
               fw.close();
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
    }
    
      public void odlazakSaPosla(String id,String fajl) throws Exception{
        vremeOdlaska = LocalTime.now();
        FileWriter fw;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String vreme = vremeOdlaska.format(formatter);
        
        JSONArray jsonNiz =(JSONArray) new JSONParser().parse(new FileReader(fajl));
        JSONArray jsonZaposleni=new JSONArray();

         for (Object obj: jsonNiz) {
                JSONObject jsonObj = (JSONObject) obj;
                String jsonId=jsonObj.get("ID").toString();
                if (jsonId.equals(id)){
                    String[] splittedTime = vreme.split(":");
                    String dolazak=jsonObj.get("dolazak").toString();
                    String[] dolazakPod=dolazak.split(":");
                    
                    
            if (Integer.parseInt(splittedTime[0])-Integer.parseInt(dolazakPod[0]) >= 8) {
                if(unesiSifru(id,fajl)){
                    int prekovremeniRad = Integer.parseInt(jsonObj.get("prekovremeno").toString());
                    int dodatno=0;
                    
                    dodatno=Integer.parseInt(splittedTime[0])-Integer.parseInt(dolazakPod[0]);
                    prekovremeniRad+=dodatno;
                    jsonObj.put("prekovremeno",prekovremeniRad);
                    
                jsonObj.put("odlazak", vreme);
                System.out.println("Zaposleni " + jsonObj.get("ime") + " " + jsonObj.get("prezime") + " je otisao sa posla u " + jsonObj.get("odlazak"));
                poruke("Dovidjenja "+jsonObj.get("ime")+"!"); 
                
                
                }
            }
            else{
                System.out.println("Niste radili dovoljan broj sati, vratite se na posao, inace ce Vam biti obrisan bonus i dodata dva kasnjenja vise!");
                System.out.println("Ako na svoju odgovornost zelite izaci sa posla, ukucajte 13:");
                Scanner scanner=new Scanner(System.in);
                int odluka=scanner.nextInt();
                if(odluka==13){
                jsonObj.put("bonus",0);
                int kasnjenje = Integer.parseInt(jsonObj.get("kasnjenja").toString());
                    System.out.println(kasnjenje);
                kasnjenje+=2;
                jsonObj.put("kasnjenja",kasnjenje);
                jsonObj.put("odlazak", vreme);
                System.out.println("Zaposleni " + jsonObj.get("ime") + " " + jsonObj.get("prezime") + " je otisao sa posla u " + jsonObj.get("odlazak"));
                poruke("Dovidjenja "+jsonObj.get("ime")+"!"); 
                 
                
            }
            } 
            }
            jsonZaposleni.add(jsonObj);
         }
          try{
               fw=new FileWriter("tekuci.json");
               fw.write(jsonZaposleni.toJSONString());
               fw.close();
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
 }
      public void teren(String id,String fajl)throws Exception{
        FileWriter fw;  
        JSONArray jsonZaposleni=new JSONArray();
        JSONArray jsonNiz =(JSONArray) new JSONParser().parse(new FileReader(fajl));
         
        for(Object obj : jsonNiz){
            JSONObject jsonObj=(JSONObject) obj;
            String jsonId=jsonObj.get("ID").toString();
            if(jsonId.equals(id)){
                int bonus=Integer.parseInt(jsonObj.get("bonus").toString());
                bonus+=40;
                jsonObj.put("bonus", bonus);
                }
            jsonZaposleni.add(jsonObj);
            
            }
        try{
               fw=new FileWriter("tekuci.json");
               fw.write(jsonZaposleni.toJSONString());
               fw.close();
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        
        }
      
      
      
     public boolean unesiSifru(String fajl,String id) throws Exception{
        System.out.println("Molim vas unesite sifru");
       
            Scanner scanner = new Scanner(System.in);
            String unesenaSifra=scanner.nextLine();
            scanner.close();
            while(unesenaSifra.length()!=4 && !unesenaSifra.equals("13")){
                System.out.println("Sifra mora imati tacno 4 broja!");
                return false;
            }
            
              
                JSONArray jsonNiz =(JSONArray) new JSONParser().parse(new FileReader(fajl));
                for(int i = 0; i < jsonNiz.size(); i++){
                    JSONObject obj = (JSONObject) jsonNiz.get(i);
                    String jsonId=obj.get("ID").toString();
                    String jsonSifra=obj.get("sifra").toString();
                    if (jsonId.equals(id) && jsonSifra.equals(unesenaSifra)) {
                        return true;
                    }
                    
                }
                System.out.println("Uneli ste pogresnu sifru!");
                return false;
        
                
     }
     
}

