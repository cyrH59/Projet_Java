package gestion;

import java.util.List;
import java.util.ArrayList;
import java.util.*;
import order.ClientOrder;

class Cut_algo extends Reader{
    
    public static void main(String[] args){
        Reader reader = initReader();
        ClientOrder[] tabOrder=reader.read("clients.xml");
        List<ClientOrder> cut = new ArrayList<ClientOrder>();
        int nbOrder= tabOrder.length;
        ClientOrder[] usedSupp = new ClientOrder[nbOrder];  //Tableau permettant de récupérer l'id du fournisseur qui satisfera un client
        int order_accomplished = 0;
        ClientOrder[] tabSupp = reader.read("fournisseurs.xml");
        int nbSupp= tabSupp.length;
        List<ClientOrder> tabSuppList = new ArrayList<>(Arrays.asList(tabSupp));
        for (int i=0; i< nbOrder; i++){
            //Convertit le tab en liste pour pouvoir add
            for (int j=0; j<nbSupp; j++){
                ClientOrder clientorder = new ClientOrder();

              //  System.out.println("tab order" + i + "tabSupp " +j +" : " + clientorder.compare_size(tabOrder[i],tabSupp[j]));
               // System.out.println("tab order" + i + "tabSupp " +j +" : " + clientorder.compare_number(tabOrder[i],tabSupp[j]));
               // System.out.println("tab order" + i + "tabSupp " +j +" : " + clientorder.compare_price(tabOrder[i],tabSupp[j]));
               // System.out.println("tab order" + i + "tabSupp " +j +" : " + clientorder.comparedate(tabOrder[i],tabSupp[j]));

                if (clientorder.compare_size(tabOrder[i],tabSupp[j]) <= 0 && clientorder.compare_number(tabOrder[i],tabSupp[j]) <= 0 && (clientorder.compare_price(tabOrder[i],tabSupp[j])>=0) && (clientorder.comparedate(tabOrder[i],tabSupp[j])==1)){
                    usedSupp[order_accomplished] = tabSupp[j];
                    order_accomplished = order_accomplished +1;

                    ClientOrder tab=clientorder.cutplank(tabOrder[i],tabSupp[j]);
                    System.out.println("On a satisfait la commande de la planche " + reader.get_plank_id(tabOrder[i])+" du client "+reader.get_client_id(tabOrder[i]));
                    cut.add(tab);

                    /*System.out.println("Nombre de planches restants chez le fournisseur"+reader.get_clientorder().get_number(tabSupp[j]));
                    System.out.println("getnumber" +reader.get_clientorder().get_number(tabOrder[i]));*/
                    reader.dec_number(tabSupp[j],reader.get_clientorder().get_number(tabOrder[i]));
                    //System.out.println("Nombre de planches restants chez le fournisseur"+reader.get_clientorder().get_number(tabSupp[j]));
                    /*for (int k=1; k<tab.length;k++){
                        tabSuppList.add(tab[k]);
                    }*/
                    break;
                }
                /*if ((clientorder.compare_price(tabOrder[i],tabSupp[j])==-1)){
                System.out.println("On ne satisfait pas la commande du panneau" + reader.get_plank_id(tabOrder[i])+" du client "+reader.get_client_id(tabOrder[i])+" à cause du prix qui est invalide");

                }
                if ((clientorder.compare_date(tabOrder[i],tabSupp[j])==-1)){
                        System.out.println("On ne satisfait pas la commande du pannea" + reader.get_plank_id(tabOrder[i])+" du client "+reader.get_client_id(tabOrder[i])+" à cause de la date qui est invalide");

                }*/
            }
        }

        int taille=cut.size();
        int tab_clientfd[];
        int plank[];
        int x[];
        int y[];
        int id_fournisseur[];
        int pannel[];
        int number[];

        tab_clientfd=new int[taille];
        plank=new int[taille];
        x=new int[taille];
        y=new int[taille];
       
       id_fournisseur=new int[taille];
       pannel=new int[taille];
       number=new int[taille];

    // il faut regarder le type pour savoir si on doit remplir id_fournisseur ou tab_client..

        
        for (int p=0;p<order_accomplished;p++){

            tab_clientfd[p]=reader.get_client_id(cut.get(p));
            pannel[p]=reader.get_plank_id(cut.get(p));
            id_fournisseur[p]=reader.get_client_id(usedSupp[p]);
            plank[p]=reader.get_plank_id(usedSupp[p]);
            x[p]= reader.getx(cut.get(p));
            y[p]=reader.gety(cut.get(p));
            number[p]=reader.get_number(cut.get(p));

        }
        // On écrit la découpe des planches dans découpes.xml
        reader.write(tab_clientfd, pannel,id_fournisseur,plank,y,x);
        // Svg
        reader.write_svg(x,y,number);

    }

    public void dec_number(ClientOrder co, int n){
        Reader reader = new Reader();
        reader.dec_number(co,n);
    }

    private ClientOrder cutplank(ClientOrder order, ClientOrder supplier){
        return get_clientorder().cutplank(order,supplier);
    }

    private static Reader initReader(){
        return new Reader();
    }

}