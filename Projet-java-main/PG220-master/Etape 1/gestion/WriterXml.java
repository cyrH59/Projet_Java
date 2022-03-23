package gestion;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import java.io.IOException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.lang.*;

class WriterXml{

    public WriterXml(){
        
    }

    public WriterXml initWriterXml(){
        return new WriterXml();
    }

public void writer_xml(int [] client_id, int []pannel, int [] id_supplier,int []plank, int []x, int [] y){
    try{
    FileOutputStream file = null;
    String filedec= "decoupes.xml";
    file = new FileOutputStream(filedec);
    XMLOutputFactory xmlOutFact = XMLOutputFactory.newInstance();
    XMLStreamWriter writer = xmlOutFact.createXMLStreamWriter(file);
            
    // début balise decoupes : 
    writer.writeStartDocument("1.0");
    writer.writeCharacters(System.getProperty("line.separator"));
    writer.writeStartElement("decoupes");
    writer.writeCharacters(System.getProperty("line.separator"));
    writer.writeStartElement("decoupe");
            // insertion des elements importants :
    int size=plank.length;
    int k;
    for (k=0;k<size;k++){
        writer.writeCharacters(System.getProperty("line.separator"));
        writer.writeStartElement("client");
        writer.writeAttribute("id",Integer.toString(client_id[k]));
        writer.writeAttribute("panneau",Integer.toString(pannel[k]));
        writer.writeEndElement();

        writer.writeCharacters(System.getProperty("line.separator"));
        writer.writeStartElement("fournisseur");
        writer.writeAttribute("id",Integer.toString(id_supplier[k]));
        writer.writeAttribute("planche",Integer.toString(plank[k]));
        writer.writeEndElement();

        writer.writeCharacters(System.getProperty("line.separator"));
        writer.writeStartElement("position");
        writer.writeAttribute("x",Integer.toString(x[k]));
        writer.writeAttribute("y",Integer.toString(y[k]));
        writer.writeEndElement();
    }
            // fin balise : 
    writer.writeCharacters(System.getProperty("line.separator"));
    writer.writeEndElement();
    writer.writeCharacters(System.getProperty("line.separator"));
    writer.writeEndElement();
    }

     catch(IOException exc) {

            System.out.print("Erreur IO: " + exc.toString());
        }
        catch(XMLStreamException exc)
        {
            System.out.print("Erreur XML: " + exc.toString());

        }
       

}

    public void writesvg(int [] weigth, int [] height, int [] number) { 
        try{
            FileOutputStream file = null;
            String filedec= "decoupes.svg";
            file = new FileOutputStream(filedec);
            XMLOutputFactory xmlOutFact = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = xmlOutFact.createXMLStreamWriter(file);

        
            writer.writeStartElement("svg");
            writer.writeAttribute("width",Integer.toString(1000));
            writer.writeAttribute("height",Integer.toString(1000));
            writer.writeCharacters(System.getProperty("line.separator"));
            // ecriture fichier avec position, taille

            // test random element : 
            
            int position_initialex=100;
            int position_initialey=100; 
            int nbx=weigth.length;
            int nby=height.length;
            int x=0;
            int y=0;
            int ligne=0;
            x=100;
            
            int r=0;
            int g=0;
            int b=0;
            for (int p=0;p<nbx; p++){
                    int numberbis=number[p];
                    Random ra = new Random();
                   

                    r= ra.nextInt(255);
                    g= ra.nextInt(255);
                    b= ra.nextInt(255);

                   
                    System.out.println(numberbis);
                    for (int k=0;k<numberbis; k++){
                    x=x+50+weigth[p];
                    if (x>900){
                        x=100;
                        ligne++;
                    }
                    
                    writer.writeStartElement("rect");
                    writer.writeAttribute("x",Integer.toString(x));
                    writer.writeAttribute("y",Integer.toString(ligne*125+25));
                    writer.writeAttribute("width",Integer.toString(weigth[p]));
                    writer.writeAttribute("height",Integer.toString(height[p]));
                    writer.writeAttribute("style","fill:rgb("+Integer.toString(r)+","+Integer.toString(g)+","+Integer.toString(b)+")");
                     // le 50 est là pour rajouter de la marge
                    
                    writer.writeEndElement();
                    writer.writeCharacters(System.getProperty("line.separator"));
                }
            

            }
             writer.writeEndElement();
                

        }
        catch(IOException exc) {

                System.out.print("Erreur IO: " + exc.toString());
            }
        catch(XMLStreamException exc)
            {
                System.out.print("Erreur XML: " + exc.toString());

            }
        

    }

}