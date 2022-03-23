import java.io.*;
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


public class test{
    public static void main(String[] args) {
     try{
        FileOutputStream file = null;
        String fichier= "decoupes.svg";
        file = new FileOutputStream(fichier);
        XMLOutputFactory xmlOutFact = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = xmlOutFact.createXMLStreamWriter(file);

       
        writer.writeStartElement("svg");
        writer.writeAttribute("width",Integer.toString(2000));
        writer.writeAttribute("height",Integer.toString(2000));
        writer.writeCharacters(System.getProperty("line.separator"));
        // ecriture fichier avec position, taille

        
        writer.writeStartElement("rect");
        writer.writeAttribute("x",Integer.toString(100));
        writer.writeAttribute("y",Integer.toString(200));
        writer.writeAttribute("width",Integer.toString(60));
        writer.writeAttribute("height",Integer.toString(70));
        writer.writeEndElement();
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