
package prijavaradnika;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PrijavaRadnika {

   
    public static void main(String[] args) {
        String tekuciM="tekuci.json";
        String prosliM="prosli.json";
        Izvestaj izvestaj=new Izvestaj();
        try {
            izvestaj.mesecniIzvestaj();
        } catch (Exception ex) {
            Logger.getLogger(PrijavaRadnika.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            izvestaj.dnevniIzvestaj();
        } catch (Exception ex) {
            Logger.getLogger(PrijavaRadnika.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        System.out.println("Dobro dosli u firmu! Prislonite svoju karticu(unesite ID) i ukucajte sifru ako je to potrebno!");

        Scanner scanner = new Scanner(System.in);

        String kartica = scanner.next();
        Zaposleni zaposleni = new Zaposleni();
        try{
        if (zaposleni.proveriId(kartica,tekuciM)){
            
            System.out.println("Izaberite opciju: ");
            System.out.println("1.Dolazak na posao \n2.Odlazak sa posla \n3. Dodavanje novog radnika \n4.Teren \n5.Evidencija \n6.Isplata plata za prosli mesec");
            try{
                int izbor=scanner.nextInt();

                if(izbor==1){
                    zaposleni.dolazakNaPosao(kartica,tekuciM);

                }
                else if(izbor==2){
                    zaposleni.odlazakSaPosla(kartica,tekuciM);

                }
                else if(izbor==3 && Integer.parseInt(kartica)/1000==1){
                    
                    Sef.dodajRadnika(tekuciM);
                    System.out.println("Uspesno ste dodali novog zaposlenog!");

                }else if(izbor==3 && Integer.parseInt(kartica)/1000!=1){
                    System.out.println("Vi niste sef i nemate pristup ovoj opciji!");

                }else if(izbor==4){
                    zaposleni.teren(kartica,tekuciM);

                } 
                 else if(izbor==5 && Integer.parseInt(kartica)/1000==1){
                    Sef.procitajRadnike(prosliM);
                }
    
                else if(izbor==5 && Integer.parseInt(kartica)/1000!=1){
                    Radnik.stanje(kartica);
                } 
                else if(izbor==6 && Integer.parseInt(kartica)/1000==1){
                    Sef.isplataPlate(prosliM);
                }
                scanner.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Greska prilikom ocitavanja kartice!");
        }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            
    }
    }
    
    
}
