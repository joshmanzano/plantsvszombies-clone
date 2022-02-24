public class Plant{

    protected int range;
    protected int directdmg;
	protected int health;
	protected int damage;
	protected int speed;
	protected int row;
	protected int col;

	public Plant(int range, int directdmg, int health, int damage, int speed, int row, int col){
		this.health = health;
		this.damage = damage;
		this.speed = speed;
		this.range = range;
		this.directdmg = directdmg;
	}
	
    public boolean reduceHealth(int damage){ // returns true if the plant dies due to damage
        this.health -= damage;
        if(this.health <= 0){
            return true;
        }
            return false;
    }	
    
    public int getSpeed(){
        return this.speed;
    }
    
    public int getDamage(){
        return this.damage;
    }
    
    public int getHealth(){
        return this.health;
    }	
	
    public int getRange(){
        return this.range;
    }
    public int getDirectdmg(){
        return this.directdmg;
    }  	
	
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }	
	
}