public class Zombie{
    
    protected int health;
    protected int damage;
    protected int speed;
    private int move;
	private int id;
    private int row;
    private int col;
    public static int ID = 0;
	
    public Zombie(int health, int damage, int speed, int row, int col){
		
        this.row = row;
        this.col = col;
        
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.move = speed/2;
        this.id = ID;
		this.ID++;
		
    }

    public boolean reduceHealth(int damage){ 
        this.health -= damage;
        if(this.health <= 0){
            return true;
        }
            return false;
    }	
	
    public boolean move(){ // returns true if it is time for the zombie to move
        if(move==1){
            move = speed/2;
            return true;
        }
            move--;
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
    
    public void changePosition(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public int getID(){
        return id;
    }
    
}

