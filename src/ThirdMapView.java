import java.awt.*;
import java.awt.Component.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.lang.*;

class ThirdBoard extends JPanel{
	
	Image background = new ImageIcon("FF.png").getImage(); 
	
    @Override 
    public void paintComponent(Graphics g) { 
    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
	
}

class ThirdPlantBoard extends JPanel{

	Image background = new ImageIcon("Woodpanel.jpg").getImage(); 
	
	public ThirdPlantBoard(){
		super();
		this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));	
	}
	
    @Override 
    public void paintComponent(Graphics g) { 
    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
	
}

public class ThirdMapView extends JFrame{

    private JButton[][] select = new JButton[5][9];
    
    private int screenwidth = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int screenheight = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    private Image peashootercursor = new ImageIcon("Peashooter.gif").getImage().getScaledInstance(screenwidth/21, screenheight/12, Image.SCALE_DEFAULT);
    private Image sunflowercursor = new ImageIcon("Sunflower.gif").getImage().getScaledInstance(screenwidth/21, screenheight/12, Image.SCALE_DEFAULT);
    
	private ImageIcon sunflowerico = new ImageIcon(new ImageIcon("Sunflower.gif").getImage().getScaledInstance(screenwidth/21, screenheight/12, Image.SCALE_DEFAULT));
	private ImageIcon peashooterico = new ImageIcon(new ImageIcon("Peashooter.gif").getImage().getScaledInstance(screenwidth/19, screenheight/12, Image.SCALE_DEFAULT));
	private ImageIcon cherrybombico = new ImageIcon(new ImageIcon("Cherrybomb.gif").getImage().getScaledInstance(screenwidth/17, screenheight/10, Image.SCALE_DEFAULT));
	private ImageIcon sunico = new ImageIcon(new ImageIcon("Sun.gif").getImage().getScaledInstance(screenwidth/21, screenheight/12, Image.SCALE_DEFAULT));
	private ImageIcon zombieico = new ImageIcon(new ImageIcon("Zombie.gif").getImage().getScaledInstance(screenwidth/18, screenheight/7, Image.SCALE_DEFAULT));
	private ImageIcon conezombieico = new ImageIcon(new ImageIcon("Conezombie.gif").getImage().getScaledInstance(screenwidth/18, screenheight/7, Image.SCALE_DEFAULT));
	private ImageIcon flagzombieico = new ImageIcon(new ImageIcon("Flagzombie.gif").getImage().getScaledInstance(screenwidth/18, screenheight/7, Image.SCALE_DEFAULT));
	private ImageIcon peaico = new ImageIcon(new ImageIcon("Pea.gif").getImage().getScaledInstance(screenwidth/50, screenheight/25, Image.SCALE_DEFAULT));
	private ImageIcon sunflowerbuttonico = new ImageIcon(new ImageIcon("Sunflower.png").getImage().getScaledInstance(screenwidth/8 - screenwidth/45, screenheight/9, Image.SCALE_DEFAULT));
	private ImageIcon peashooterbuttonico = new ImageIcon(new ImageIcon("Peashooter.png").getImage().getScaledInstance(screenwidth/8 - screenwidth/45, screenheight/9, Image.SCALE_DEFAULT));
	private ImageIcon cherrybombbuttonico = new ImageIcon(new ImageIcon("Cherrybomb.png").getImage().getScaledInstance(screenwidth/8 - screenwidth/45, screenheight/9, Image.SCALE_DEFAULT));
	private ImageIcon wallnutbuttonico = new ImageIcon(new ImageIcon("Wallnut.png").getImage().getScaledInstance(screenwidth/8 - screenwidth/45, screenheight/9, Image.SCALE_DEFAULT));
    private JButton sunflower = new JButton();
    private JButton peashooter = new JButton();
	private JButton cherrybomb = new JButton();
	private JButton wallnut = new JButton();
	private JButton fastforward = new JButton("Fast Forward");
	private JButton slowdown = new JButton("Slow Down");
    private JLayeredPane[][] tiles = new JLayeredPane[5][9];
	private JLayeredPane[] rows = new JLayeredPane[5];
	private JLayeredPane sunlayer = new JLayeredPane();
	private JLayeredPane center = new JLayeredPane();
    private ThirdBoard board = new ThirdBoard();
	private JPanel rowboard = new JPanel();
    private ThirdPlantBoard plantboard = new ThirdPlantBoard();
    private JLabel[][] plants = new JLabel[5][9];
    private ArrayList<JLabel> zombies = new ArrayList<>();
    private ArrayList<JLabel> projectiles = new ArrayList<>();
    private JPanel status = new JPanel(new BorderLayout());
	private JLabel sun = new JLabel();
	private JLabel sunvalue = new JLabel();

    public ThirdMapView(){   
		
        super("Plants vs Zombies");        
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(screenwidth,screenheight);
        setLayout(new BorderLayout());
        getContentPane().add(center,BorderLayout.CENTER);
        getContentPane().add(plantboard,BorderLayout.NORTH);
        
		sun.setIcon(sunico);
		sunvalue.setFont(new Font("Papyrus", Font.PLAIN,(screenwidth+screenheight)/145));

		status.add(sun,BorderLayout.CENTER);
		status.add(sunvalue,BorderLayout.SOUTH);
        
        sunflower.setIcon(sunflowerbuttonico);
		sunflower.setContentAreaFilled(false);
		sunflower.setBorderPainted(false);
        peashooter.setIcon(peashooterbuttonico);   
		peashooter.setContentAreaFilled(false);
		peashooter.setBorderPainted(false);
        cherrybomb.setIcon(cherrybombbuttonico);   
		cherrybomb.setContentAreaFilled(false);
		cherrybomb.setBorderPainted(false);
		wallnut.setIcon(wallnutbuttonico);   
		wallnut.setContentAreaFilled(false);
		wallnut.setBorderPainted(false);
		
        plantboard.add(status);
        plantboard.add(sunflower);
        plantboard.add(peashooter);
		plantboard.add(cherrybomb);
		plantboard.add(wallnut);
		plantboard.add(fastforward);
		plantboard.add(slowdown);
        
		board.setLayout(new GridLayout(5,9));
		rowboard.setLayout(new GridLayout(5,0));
		
        for(int i = 0 ; i < 5 ; i++ ){
            for(int j = 0 ; j < 9 ; j++ ){
                tiles[i][j] = new JLayeredPane();
				tiles[i][j].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
                board.add(tiles[i][j]);   
            }  
        } 
		
        for(int i = 0 ; i < 5 ; i++ ){
            rows[i] = new JLayeredPane();
			rows[i].setOpaque(false);
            rowboard.add(rows[i]);
			rowboard.setOpaque(false);
        }		
		
    }
    
    public void initialize(){
     
		rowboard.setBounds(0,0,screenwidth,center.getHeight());
		board.setBounds(0,0,screenwidth,center.getHeight());
		sunlayer.setBounds(0,0,screenwidth,center.getHeight());
		center.add(rowboard,JLayeredPane.PALETTE_LAYER);		
		center.add(board,JLayeredPane.DEFAULT_LAYER);		
		center.add(sunlayer,JLayeredPane.MODAL_LAYER);
		
        for(int i = 0 ; i < 5 ; i++ ){
            for(int j = 0 ; j < 9 ; j++ ){
               plants[i][j] = new JLabel();  
               plants[i][j].setIcon(null);
               tiles[i][j].add(plants[i][j],JLayeredPane.DEFAULT_LAYER);
            }  
        }         

        for(int i = 0 ; i < 5 ; i++ ){
            for(int j = 0 ; j < 9 ; j++ ){
                select[i][j] = new JButton();
                select[i][j].setVisible(false);
                select[i][j].setOpaque(false);
                select[i][j].setContentAreaFilled(false);
                select[i][j].setBorderPainted(false);
                select[i][j].setName(i+" "+j);
                select[i][j].setBounds(0,0,tiles[i][j].getWidth(),tiles[i][j].getHeight());
                tiles[i][j].add(select[i][j],JLayeredPane.DEFAULT_LAYER);
            }  
        }	
           
    }
    
    public void defaultCursor(){
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void sunflowerCursor(){
        //this.setCursor(sunflowerc);
    }
    
    public void peashooterCursor(){
        //this.setCursor(peashooterc);
    }
    
    public void setSunlight(int sunlight){
        sunvalue.setText("     "+sunlight);
    }
    
    public void addSunflower(int row, int col){
        Insets insets = tiles[row][col].getInsets();
		plants[row][col].setIcon(sunflowerico);
        plants[row][col].setBounds(insets.left+screenwidth/120,insets.top+screenheight/31,screenwidth/21, screenheight/12);
    }
	
    public void addPeashooter(int row, int col){
        Insets insets = tiles[row][col].getInsets();
		plants[row][col].setIcon(peashooterico);
        plants[row][col].setBounds(insets.left+screenwidth/120,insets.top+screenheight/31,screenwidth/21, screenheight/12);
    }
	
    public void addCherrybomb(int row, int col){
        Insets insets = tiles[row][col].getInsets();
		plants[row][col].setIcon(cherrybombico);
        plants[row][col].setBounds(insets.left+screenwidth/120,insets.top+screenheight/31,screenwidth/17, screenheight/10);
    }		
	
	public void disableSunflowerButton(){
		sunflower.setEnabled(false);
	}

	public void enableSunflowerButton(){
		sunflower.setEnabled(true);
	}	

	public void disablePeashooterButton(){
		peashooter.setEnabled(false);
	}	
	
	public void enablePeashooterButton(){
		peashooter.setEnabled(true);
	}	

	public void disableCherrybombButton(){
		cherrybomb.setEnabled(false);
	}	
	
	public void enableCherrybombButton(){
		cherrybomb.setEnabled(true);
	}	
	
    public void addZombie(int row){
        Insets insets = rows[row].getInsets();
        zombies.add(new JLabel(zombieico));
        zombies.get(zombies.size()-1).setBounds(insets.left+(screenwidth-(screenwidth/136)),insets.top+screenheight/80,screenwidth/12, screenheight/7);
        rows[row].add(zombies.get(zombies.size()-1),JLayeredPane.PALETTE_LAYER);
    }

    public void addConeZombie(int row){
        Insets insets = rows[row].getInsets();
        zombies.add(new JLabel(conezombieico));
        zombies.get(zombies.size()-1).setBounds(insets.left+(screenwidth-(screenwidth/136)),insets.top+screenheight/80,screenwidth/12, screenheight/7);
        rows[row].add(zombies.get(zombies.size()-1),JLayeredPane.PALETTE_LAYER);
    }

    public void addFlagZombie(int row){
        Insets insets = rows[row].getInsets();
        zombies.add(new JLabel(flagzombieico));
        zombies.get(zombies.size()-1).setBounds(insets.left+(screenwidth-(screenwidth/136)),insets.top+screenheight/80,screenwidth/12, screenheight/7);
        rows[row].add(zombies.get(zombies.size()-1),JLayeredPane.PALETTE_LAYER);
    }


    
    public void walkZombie(int row, int id){
        Insets insets = rows[row].getInsets();
        zombies.get(id).setLocation(zombies.get(id).getX()-(screenwidth/500),zombies.get(id).getY());
    }
    
    public boolean checkZombie(int id){
        return zombies.get(id) != null;
    }
	
	public boolean checkColZombie(int id, int col){
		return (zombies.get(id).getX() < screenwidth - (screenwidth/8)*(9-col));				
	}
    
    public void removePlant(int row, int col){
        plants[row][col].setIcon(null);
    }

    public void removePeaProjectile(int row, int id){
        rows[row].remove(projectiles.get(id));
        projectiles.set(id,null);
        rows[row].validate();
        rows[row].repaint();
    }
    
    public void removeZombie(int row, int id){
        rows[row].remove(zombies.get(id));
        zombies.set(id,null);
        rows[row].validate();
        rows[row].repaint();    
    }
    
	public void addPeaProjectile(int row, int col){
		
		Insets insets = rows[row].getInsets();   
		projectiles.add(new JLabel(peaico));
		projectiles.get(projectiles.size()-1).setBounds(insets.left+(screenwidth/19)+((screenwidth/9)*col),insets.top+screenheight/30,screenwidth/40, screenheight/25);		
		rows[row].add(projectiles.get(projectiles.size()-1),JLayeredPane.DEFAULT_LAYER);
		
	}
	
    public void walkPeaProjectile(int row, int id){
        Insets insets = rows[row].getInsets();
        projectiles.get(id).setLocation(projectiles.get(id).getX()+(screenwidth/400),projectiles.get(id).getY());
    }
    
    public boolean checkHitPeaProjectile(int id, int id2){
        return (projectiles.get(id).getX() > zombies.get(id2).getX() - (screenwidth/90));
    }
	
	public boolean checkEndProjectile(int id){
		return (projectiles.get(id).getX() > screenwidth);
	}
	
	public boolean checkProjectile(int id){
		return projectiles.get(id) != null;
	}
	
	public int getNumOfProjectiles(){
		return projectiles.size();
	}
	
	public int getNumOfZombies(){
		return zombies.size();
	}
	
	public int getXofPlant(int row, int col){
		return plants[row][col].getX();
	}
    
	public boolean checkNumOfProjectiles(){
		return !projectiles.isEmpty();
	}
	
    public void spawnSun(int x, int y, ActionListener actionlistener){
        JButton sun = new JButton(sunico);
		sun.setBounds(x,y,screenwidth/21, screenheight/12);
        sun.setOpaque(false);
        sun.setContentAreaFilled(false);
        sun.setBorderPainted(false);
		sun.addActionListener(actionlistener);
		sunlayer.add(sun);	
    }
	
	public void flowerspawnSun(int row, int col, ActionListener actionlistener){
		JButton sun = new JButton(sunico);
		sun.setBounds(sunlayer.getWidth() - (sunlayer.getWidth()/9)*(9-col),sunlayer.getHeight() - (sunlayer.getHeight()/5)*(5-row),screenwidth/21, screenheight/12);
        sun.setOpaque(false);
        sun.setContentAreaFilled(false);
        sun.setBorderPainted(false);
		sun.addActionListener(actionlistener);
		sunlayer.add(sun);	
	}
	
    public void showSelect(){
        for(int row = 0 ; row < 5 ; row++ ){
            for(int col = 0 ; col < 9 ; col++ ){
                if(plants[row][col].getIcon()==null){
                    select[row][col].setVisible(true);
                }
            }
        }
    }

    public void hideSelect(){
        for(int row = 0 ; row < 5 ; row++ ){
            for(int col = 0 ; col < 9 ; col++ ){
                select[row][col].setVisible(false);                
            }
        }
    }
    
    public void GameOver(){
        ImageIcon zombiewonico = new ImageIcon(new ImageIcon("ZombiesWon.gif").getImage().getScaledInstance(board.getWidth()-(screenwidth/136), board.getHeight()-(screenheight/73), Image.SCALE_DEFAULT));
        board.removeAll();
		sunflower.setEnabled(false);
		peashooter.setEnabled(false);
        board.setLayout(new FlowLayout());
        board.add(new JLabel(zombiewonico));
		this.validate();
		this.repaint();
        
    }

    public void GameWin(){
        board.removeAll();
		sunflower.setEnabled(false);
		peashooter.setEnabled(false);
        board.setLayout(new FlowLayout());
        board.add(new JLabel("PLAYER HAS WON!"));
		this.validate();
		this.repaint();
        
    }	
	
    public void selectaddListener(MouseListener mouselistener){
        for(int i = 0 ; i < 5 ; i++ ){
            for(int j = 0 ; j < 9 ; j++ ){
                select[i][j].addMouseListener(mouselistener);
            }  
        }       
    }

    public void sunfloweraddActionListener(ActionListener actionlistener){
        sunflower.addActionListener(actionlistener);        
    }

    public void peashooteraddActionListener(ActionListener actionlistener){
        peashooter.addActionListener(actionlistener);        
    }    
	
	public void cherrybombaddActionListener(ActionListener actionlistener){
		cherrybomb.addActionListener(actionlistener);
	}

    public void fastforwardaddActionListener(ActionListener actionlistener){
        fastforward.addActionListener(actionlistener);        
    }

    public void slowdownaddActionListener(ActionListener actionlistener){
        slowdown.addActionListener(actionlistener);        
    } 	
	
    public ImageIcon getSunflowerIcon(){
        return sunflowerico;
    }
 
    public ImageIcon getPeashooterIcon(){
        return peashooterico;
    }    
	
	public ImageIcon getCherrybombIcon(){
		return cherrybombico;
	}
    
	public int getsunlayerWidth(){
		return sunlayer.getWidth();
	}
	
	public int getsunlayerHeight(){
		return sunlayer.getHeight();
	}
	
	public void refreshDisplay(){
		board.repaint();
		rowboard.repaint();
	}
	
	public void displayProjectiles(){
		
		for(int i = 0 ; i < projectiles.size() ; i++){
			System.out.print("ID: "+i+" , State: ");
			if(projectiles.get(i)!=null){
				System.out.println("Active");
			}else{
				System.out.println("Null");
			}
		}
		
	}
	
}

