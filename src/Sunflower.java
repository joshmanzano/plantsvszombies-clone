public class Sunflower extends Plant{
    
	public final static int cost = 50;
	public final static int regenrate = 6;
    private int produce;
    private int cooldown;
    
    public Sunflower(int range, int directdmg, int health, int damage, int speed, int row, int col){
        super(range,directdmg,health,damage,speed,row,col);
        this.produce = speed;
    }

    public boolean produceSun(){ // returns true if it is time for the sunflower to produce sun
        if(this.produce==1){
            this.produce = this.speed;
            return true;
        }
            this.produce--;
            return false;     
    }            
}


