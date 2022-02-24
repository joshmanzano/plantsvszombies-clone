public class Cherrybomb extends Plant{

	public static int cost = 150;
	public static int regenrate = 12;
	private int explode;
    private int cooldown;
    
    public Cherrybomb(int range, int directdmg, int health, int damage, int speed, int row, int col){
        super(range,directdmg,health,damage,speed,row,col);
        this.explode = speed;
    }

	public boolean explosion(){ // returns true if it is time for the sunflower to produce sun
    if(this.explode==1){
        this.explode = this.speed;
        return true;
        }
        this.explode--;
        return false;     
    } 
	
}