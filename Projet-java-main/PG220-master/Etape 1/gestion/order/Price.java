package order;
class Price implements Validable{
    private Double price; 


    public Price(){
        this.price = new Double(0);
    }

    public Price(Double price){
        this.price = price;
    }

    public Price createprice(){
        return new Price();
    }
    public Price createprice(Double p){
        return new Price(p);

    }

    public void setprice(Double p){
        this.price=p;
    }

    
    
    public int compare_price(Price p1, Price p2){
        if (p1.isvalide()==false || p2.isvalide()==false){
            return -1;
        }
        else{
            if (p1.price>=p2.price){
            return 1;
            }
            else{
                return -1;
            }
        }
    }
    public boolean isvalide(){
        boolean b=this.price instanceof  Double;
        if( b == false) {
            return false;
        }
        if(this.price*100 - Math.floor(this.price * 100) !=0 ){
           return false; 
        }
        if(this.price<0){
            return false;
        }

        return true;
    }
}