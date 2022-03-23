package order;
import java.util.List;
import java.util.ArrayList;

public class ClientOrder extends Order{
    private Client client;
    private Plank plank;

    public ClientOrder initClientOrder(){
        return new ClientOrder();
    }

    public ClientOrder createClientOrder(String date, int number,int type, Double price,int id_client,int idPlank,int length,int width){
        return new ClientOrder(date, number, type, price, id_client, idPlank, length, width);
    }

    public ClientOrder(){
        Client client = new Client();
        Plank plank = new Plank();
        this.client=client.initClient();
        this.plank=plank.initPlank();
    }

    private ClientOrder(Plank plank, Client client, int number, int type){
        this.plank = plank;
        this.client=client;
        setnumber(number);
        settype(type);
    }

    private ClientOrder(String date, int number,int type, Double price,int id_client,int idPlank,int length,int width){
        Client client = new Client();
        Plank plank = new Plank();
        this.client=client.createClient(id_client);
        this.plank=plank.createPlank(idPlank,length, width);
        setnumber(number);
        setdate(date);
        setprice(price);
        settype(type);
    }

    public int compare_size(ClientOrder ord1, ClientOrder ord2){
        return plank.compare_size(ord1.plank,ord2.plank);
    }

    public int compare_price(ClientOrder ord1, ClientOrder ord2){
        return compareprice(ord1,ord2);
    }

     public int compare_date(ClientOrder ord1, ClientOrder ord2){
        return comparedate(ord1,ord2);
    }
    public int getx(ClientOrder ord1){
        return plank.getx(ord1.plank);
    } 
    public int gety(ClientOrder ord1){
        return plank.gety(ord1.plank);
    } 

    public int compare_number(ClientOrder ord1, ClientOrder ord2){
        int num1= getnumber(ord1);
        int num2= getnumber(ord2);
        return num1-num2;
    }

    public int get_number(ClientOrder ord){
        return getnumber(ord);
    }
     public int get_type(ClientOrder ord){
        return getntype(ord);
    }

    private void setnumber(ClientOrder ord, int num){
        ord.number=num;
    }


    public void decnumber(ClientOrder ord, int num){
        setnumber(ord,getnumber(ord)-num);
    }


    public int get_client_id(ClientOrder ord){
        return client.getid(ord.client);
    }

    public int get_plank_id(ClientOrder ord){
        return plank.getid(ord.plank);
    }

    public ClientOrder cutplank(ClientOrder order, ClientOrder supplier){
            //Plank[] tab_ret=plank.cut_plank(order.plank, supplier.plank);
            Plank tab_ret=plank.cut_plank(order.plank, supplier.plank);

/*
            List<ClientOrder> tab_sup = new ArrayList<ClientOrder>();
            //tab_sup.add(tab_ret[0]);
            for (int i=1; i<tab_ret.length;i++){
                tab_sup.add(new ClientOrder(tab_ret[i-1],supplier.client));
            }
            ClientOrder[] tab_fin = new ClientOrder[tab_sup.size()];
            tab_sup.toArray(tab_fin);*/
            ClientOrder tab_fin=new ClientOrder(tab_ret,order.client, getnumber(order),getntype(order));
            return tab_fin;
        }
}