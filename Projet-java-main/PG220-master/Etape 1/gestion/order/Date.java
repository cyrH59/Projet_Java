package order;
import java.text.SimpleDateFormat;

class Date implements Validable{
    public String date;

    public Date(){
        this.date = "00.00.00";
    }

    public Date(int jour,int mois,int année){
        this.date=date.concat(String.valueOf(jour));
        this.date=date.concat(".");
        this.date=date.concat(String.valueOf(mois));
        this.date=date.concat(".");
        this.date=date.concat(String.valueOf(année));
    }

    public Date createdate(){
        return new Date();
    }

    public Date create_date(int jour,int mois,int année){
        return new Date(jour, mois, année);
    }

    public void setdate(String date){
        this.date=date;
    }
    public int compare_date(Date p1, Date p2){
        if (p1.isvalide()==false || p2.isvalide()==false){
            return -1;
        }
        else{
        String jourp1;
        String moisp1;
        String anneep1;
        jourp1=p1.date.substring(0,2);
        moisp1=p1.date.substring(3,5);
        anneep1=p1.date.substring(6,8);
        int jourp1bis=Integer.parseInt(jourp1);
        int moisp1bis=Integer.parseInt(moisp1);
        int anneep1bis=Integer.parseInt(anneep1);   
        String jourp2;
        String moisp2;
        String anneep2;
        jourp2=p2.date.substring(0,2);
        moisp2=p2.date.substring(3,5);
        anneep2=p2.date.substring(6,8);
        int jourp2bis=Integer.parseInt(jourp2);
        int moisp2bis=Integer.parseInt(moisp2);
        int anneep2bis=Integer.parseInt(anneep2);
        if (anneep1bis >anneep2bis){
            return 1;
        }
        else if (anneep1bis <anneep2bis){
            return -1;
        }
        else{
            if (moisp1bis>moisp2bis){
                return 1;
            }
            else if (moisp1bis<moisp2bis){
                return -1;
            }
            else{
                if (jourp2bis>jourp1bis){
                return -1;
                }
                else {
                    return 1;
                }
                
            }
        }
        }
            
        
    }
    public boolean isvalide(){
        String jour;
        String mois;
        String annee;
        jour=this.date.substring(0,2);
        mois=this.date.substring(3,5);
        annee=this.date.substring(6,8);
        int jourbis=Integer.parseInt(jour);
        int moisbis=Integer.parseInt(mois);
        int anneebis=Integer.parseInt(annee);
        if (anneebis >=21 ){
            if (moisbis==(1|3|5|7|8|10|12)){
                if (jourbis <32 && jourbis>=0){
                    return true;
                }
            }
            else if (moisbis==2){
                if((anneebis%4)==0){
                   if (jourbis <29 && jourbis>=0){
                    return true;
                    }
                    

                }
                else{
                    
                
                if (jourbis <30 && jourbis>=0){
                    return true;
                }
                }
            }
            else{
                if (jourbis<31 && jourbis>=0){
                    return true;
                }
            }
        }
        return false;

    }

}