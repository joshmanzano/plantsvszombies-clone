public class Peashooter extends Plant{
	
	public final static int cost = 100;
	public final static int regenrate = 0;
    private int shoot;
    private int cooldown;
    
    public Peashooter(int range, int directdmg, int health, int damage, int speed, int row, int col){
        super(range,directdmg,health,damage,speed,row,col);
        this.shoot = speed;
    }
	
    public boolean shootPea(){ // returns true if it is time for the peashooter to shoot
        if(this.shoot==1){
            this.shoot = this.speed;
            return true;
        }
            this.shoot--;
            return false;     
    }
	
	public void resetPea(){
		this.shoot = this.speed;
	}
    
}

