import java.util.*;

public class ThirdMapModel {
  
    private Player player;
    private ArrayList<Zombie> zombies;
    private ArrayList<Projectile> projectiles; 
	private Plant plants[][];
	private final Random rng;
    private int selection;
	private int gamespeed;
    
    public ThirdMapModel(){
        
        player = new Player(9999);
        zombies = new ArrayList<>();
        projectiles = new ArrayList<>();
		plants = new Plant[5][9];
		rng = new Random();
		gamespeed = 1;
        
    }
	
	public int getGamespeed(){
		return gamespeed;
	}
	
	public void gamespeedUp(){
		if(!(gamespeed >= 10)){
			gamespeed++;
		}
	}

	public void gamespeedDown(){
		if(!(gamespeed <= 1)){
			gamespeed--;
		}	
	}
	
    public int getSun(){
        return player.getSun();
    }
    
    public void addSun(int sunlight){
        player.addSun(sunlight);
    }   
    
    public void removeSun(int sunlight){
        player.removeSun(sunlight);
    }   
    
	public Sun spawnSun(int widthbound, int heightbound){
		int x = rng.nextInt(widthbound - (widthbound/3));
		int y = rng.nextInt(heightbound/2);
		return new Sun(x,y,25);
	}
	
    public boolean addPlant(Plant e, int row, int col){
        if(plants[row][col] == null){
            plants[row][col] = e;
            return true;
        }
            return false;
    }

    public void addZombie(Zombie e){
        zombies.add(e);
    }	
    
    public void addProjectile(Projectile e){
        projectiles.add(e);
    }
    
    public boolean checkPlant(int row, int col){
        return plants[row][col] != null;
    }    
    
    public String getPlantName(int row, int col){
        return plants[row][col].getClass().getName();
    }

    public Plant getPlant(int row, int col){
        return plants[row][col];
    }
    
    public Zombie getZombie(int i){
        return zombies.get(i);
    }
    
    public Projectile getProjectile(int i){
        return projectiles.get(i);
    }
    
    public boolean checkProjectile(int i){
        return projectiles.get(i) != null;
    }
    
    public boolean checkZombie(int i){
        return zombies.get(i) != null;
    }
    
    public void removePlant(int row, int col){
        plants[row][col] = null;
    }
    
    public void removeProjectile(int i){
        projectiles.set(i,null);
    }
    
    public void removeZombie(int i){
        zombies.set(i,null);
    }
    
    public int getSelection(){
        return selection;
    }
    
    public void changeSelection(int selection){
        this.selection = selection;
    }
    
    public int getNumOfZombies(){
        return zombies.size();
    }
    
    public int getNumOfProjectiles(){
        return projectiles.size();
    }
	
	public boolean checkNumOfProjectiles(){
		return !projectiles.isEmpty();
	}
	
	public boolean checkNumOfZombies(){
		return !zombies.isEmpty();
	}
    
}

