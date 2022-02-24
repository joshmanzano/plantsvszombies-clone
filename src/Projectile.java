public class Projectile{
	
	protected int damage;
	protected int speed;
	private int row;
	private int id;
	public static int ID = 0;
	
	public Projectile(int damage, int speed, int row){
		this.damage = damage;
		this.speed = speed;
		this.row = row;
		this.id = ID;
		this.ID++;
	}
	
    public int getSpeed(){
        return this.speed;
    }
    
    public int getDamage(){
        return this.damage;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getID(){
        return id;
    }	
	
}
