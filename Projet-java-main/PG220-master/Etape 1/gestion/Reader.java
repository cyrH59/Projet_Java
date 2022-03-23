package gestion;

import java.util.List;
import java.util.ArrayList;

import order.ClientOrder;

class Reader{
    private ClientOrder clientorder;
    private ReaderXml readerxml;
    private WriterXml writerxml;

    public Reader(){
        ClientOrder clientorder = new ClientOrder();
        ReaderXml readerxml = new ReaderXml();
        WriterXml writerxml = new WriterXml();
        this.clientorder = clientorder.initClientOrder() ;
        this.readerxml = readerxml.initReaderXml();
        this.writerxml = writerxml.initWriterXml();
    }

    public ClientOrder createClientOrder(String date, int number,int type, Double price,int id_client,int idPlank,int length,int width){
        return clientorder.createClientOrder(date,number,type,price,id_client,idPlank,length,width);
    }

    public ClientOrder[] read(String xml){
        String date; 
        int number;
        int type;
        Double price;
        int id_client;
        int idPlank;
        int length;
        int width;
        ClientOrder readerclientorder;
        String [] tab = readerxml.reader_xml(xml);
        List<ClientOrder> tab_order = new ArrayList<ClientOrder>();
        //System.out.println("type :" + tab[0]);
        type = Integer.parseInt(tab[0]);
        for(int i=1; i< tab.length; i=i+7){
                // System.out.println("id_client:" + tab[i]);
                //  System.out.println("idPlank:" + tab[i+1]);
                //   System.out.println("number:" + tab[i+2]);
                //    System.out.println("date :" + tab[i+3]);
                //     System.out.println("price:" + tab[i+4]);
                //      System.out.println("length :" + tab[i+5]);
                //       System.out.println("width :" + tab[i+6]);
                id_client = Integer.parseInt(tab[i]);
                idPlank = Integer.parseInt(tab[i+1]);
                number = Integer.parseInt(tab[i+2]);
                date   = tab[i+3];
                price = Double.parseDouble(tab[i+4]);
                length = (int)Double.parseDouble(tab[i+5]);
                width = (int)Double.parseDouble(tab[i+6]);
                readerclientorder = createClientOrder(date,number,type, price,id_client,idPlank,length,width);
                tab_order.add(readerclientorder);
        }
        ClientOrder[] tab_fin = new ClientOrder[tab_order.size()];
        tab_order.toArray(tab_fin);
        return tab_fin;
    }

    public void write( int[] id_client,int[] plank,int[] id_supplier,int[] pannel,int[] x,int[] y){
        writerxml.writer_xml(id_client ,plank,id_supplier,pannel,x,y);
    }

    public void write_svg(int [] weigth, int [] heigth, int [] number){
        writerxml.writesvg(weigth,heigth,number);
    }

    public int compare_size(ClientOrder ord1, ClientOrder ord2){
        return clientorder.compare_size(ord1,ord2);
    }

    public int compare_number(ClientOrder ord1, ClientOrder ord2){
        return clientorder.compare_number(ord1,ord2);
    }

    public int get_number(ClientOrder ord){
        return(clientorder.getnumber(ord));
    }

    public int get_client_id(ClientOrder ord){
        return(clientorder.get_client_id(ord));
    }
    public int getx(ClientOrder ord){
        return(clientorder.getx(ord));
    }
    public int gety(ClientOrder ord){
        return(clientorder.gety(ord));
    }
    public int get_plank_id(ClientOrder ord){
        return(clientorder.get_plank_id(ord));
    }

    public void dec_number(ClientOrder ord,int num){
        clientorder.decnumber(ord,num);
    }

    public ClientOrder get_clientorder(){
        return this.clientorder;
    }
}