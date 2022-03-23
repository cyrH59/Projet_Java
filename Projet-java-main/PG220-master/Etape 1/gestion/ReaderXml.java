package gestion;

import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;



class ReaderXml{

    public ReaderXml(){
        
    }

    public ReaderXml initReaderXml(){
        return new ReaderXml();
    }

    public String[] reader_xml(String arg)
    {   int type = -1;
        String date;
        int number;
        Double price;
        int id;
        int idplanche;
        int length;
        int width;
        List<String> tab = new ArrayList<String>();
        //List<ClientOrder> tab = new ArrayList<ClientOrder>();
        //ClientOrder readerclientorder;
        FileInputStream file = null;
        try {
            String fichier = arg;
            file = new FileInputStream(fichier);
            String balise1="";
            String balise2="";
            String commande="";
            if (fichier.equals("clients.xml")){
                type=0;
                balise1 = "client";
                balise2 = "planche";
                commande = "id client :";
            }
            if (fichier.equals("fournisseurs.xml")){
                type=1;
                balise1 = "fournisseur";
                balise2 = "panneau";
                commande = "id fournisseur :";
            }
            XMLInputFactory xmlInFact = XMLInputFactory.newInstance();
            XMLStreamReader reader = xmlInFact.createXMLStreamReader(file);
            tab.add(Integer.toString(type));
            while(reader.hasNext()) {
                if(reader.next() == XMLStreamConstants.START_ELEMENT ){
                    if(reader.getName().toString() == balise1){
                        id = Integer.parseInt(reader.getAttributeValue(0));
                        tab.add(reader.getAttributeValue(0));
                        String commande_ = commande + reader.getAttributeValue(0);
                        //System.out.println(commande_);

                        while(reader.hasNext()){
                            int liseur = reader.next();
                            if(liseur == XMLStreamConstants.START_ELEMENT){
                                    if(reader.getName().toString() == balise2){
                                        //Planche p = readPlanche(reader);
                                        idplanche = Integer.parseInt(reader.getAttributeValue(0));
                                        number = Integer.parseInt(reader.getAttributeValue(1));
                                        date = reader.getAttributeValue(2);
                                        tab.add(reader.getAttributeValue(0));
                                        tab.add(reader.getAttributeValue(1));
                                        tab.add(reader.getAttributeValue(2));
                                        try{
                                            price = Double.parseDouble(reader.getAttributeValue(3));
                                        }
                                        catch(Exception e){
                                            price = (double)(-1);
                                            System.out.println(" Le prix n'est pas un double");     
                                        }
                                         String affichage="";
                                        if(fichier.equals("clients.xml")){
                                            affichage = "Commande n°" + reader.getAttributeValue(0) + " de " + reader.getAttributeValue(1) + " planches à livrer pour le " + reader.getAttributeValue(2) + " au prix maximal de " +reader.getAttributeValue(3);
                                        }
                                        if(fichier.equals("fournisseurs.xml")){
                                            affichage = "Ensemble de panneau n°" + reader.getAttributeValue(0) + " de " + reader.getAttributeValue(1) + " panneaux à livrer pour le " + reader.getAttributeValue(2) + " au prix maximal de " +reader.getAttributeValue(3);
                                        }
                                        tab.add(Double.toString(price));
                                        //System.out.println(affichage);

                                        while(reader.hasNext()){
                                            int liseur2 = reader.next();
                                            if(liseur2 == XMLStreamConstants.START_ELEMENT){
                                                if(reader.getName().toString() == "dim"){
                                                    String dimension = "La longueur est de " + reader.getAttributeValue(0) + "la largeur est de" + reader.getAttributeValue(1);
                                                    //System.out.println(dimension);

                                                    try{
                                                        length = (int)Double.parseDouble(reader.getAttributeValue(0));
                                                    }
                                                    catch(Exception e){
                                                        length = (int)0;
                                                        //System.out.println(" Le length n'est pas un int");     
                                                    }

                                                    try{
                                                        width = (int)Double.parseDouble(reader.getAttributeValue(1));
                                                    }
                                                    catch(Exception e){
                                                        width =(int)0;
                                                        //System.out.println("Le width n'est pas un int");
                                                    }

                                                    tab.add(Integer.toString(length));
                                                    tab.add(Integer.toString(width));
                                                    tab.add(Integer.toString(id));
                                                    // readerclientorder = createClientOrder(date,number,type, price,id,idplanche,length,width);
                                                    //tab.add(readerclientorder);
                                                }
                                               
                                            }

                                            if(liseur2 == XMLStreamConstants.END_ELEMENT){
                                                if(reader.getName().toString() == "dim"){
                                                    break;
                                                }
                                            }
                                      
                                      }   
                                }
                            }
                            if(liseur == XMLStreamConstants.END_ELEMENT ){
                                if(reader.getName().toString() == balise1){
                                    tab.remove(tab.size() - 1);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(IOException exc) {

            System.out.print("Erreur IO: " + exc.toString());
        }
        catch(XMLStreamException exc) {
            System.out.print("Erreur XML: " + exc.toString());

        }
        // ClientOrder[] tab_fin = new ClientOrder[tab.size()];
        // tab.toArray(tab_fin);
        // return tab_fin;
        String[] tab_fin = new String[tab.size()];
        tab.toArray(tab_fin);
        return tab_fin;
    }
}