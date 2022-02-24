public class Player {
    
    private int sunlight;
    
    public Player(int sunlight){
        this.sunlight = sunlight;
    }
    
    public int getSun(){
        return this.sunlight;
    }
    
    public void addSun(int sunlight){
        this.sunlight += sunlight;
    }
    
    public void removeSun(int sunlight){
        this.sunlight -= sunlight;
    }
    
}
