package order;
class Size implements Validable{
    private int length;
    private int width;

    public Size initSize(){
        return new Size();
    }

    public Size CreateSize(int length, int width){
        return new Size(length, width);
    }

    public Size(){
        this(0,0);
    }

    private Size(int length, int width){
        setlength(length);
        setwidth(width);
    }

    public void setlength(int l){
        this.length=l;
    }

    public void setwidth(int w){
        this.width=w;
    }

    public int getwidth(){
        return this.width;
    }

    public int getlength(){
        return this.length;
    }

    public int getwidth(Size size){
        return size.width;
    }

    public int getlength(Size size){
        return size.length;
    }

    public boolean isvalide(){
        if (this.length <=0 || this.width <=0 || this.length<this.width){
            return false;
        }
        return true;
    }
}