package order;

class Client implements Validable{
    private int id_client;


    public Client initClient(){
        return new Client();
    }

    public Client createClient(int id_client){
        return new Client(id_client);
    }

    public Client(){
        this(0);
    }

    public Client(int id){
        setid(id);
    }

    private void setid(int id){
        this.id_client=id;
    }

    public int getid(){
        return id_client;
    }

    public int getid(Client client){
        return client.id_client;
    }

    public boolean isvalide(){
        if (this.id_client >=0){
            return true;
        }
        return false;
    }
}