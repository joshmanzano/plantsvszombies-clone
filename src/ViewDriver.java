
import java.awt.*;
import java.awt.Component.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.lang.*;

class MainMenu extends JPanel{
	
	Image background = new ImageIcon("MainMenu.png").getImage(); 
	
    @Override 
    public void paintComponent(Graphics g) { 
    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
	
}

public class ViewDriver extends JFrame{

    private int screenwidth = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int screenheight = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();	
	
	private MainMenu mainmenu = new MainMenu();
	private JLabel title = new JLabel();
	
	private JButton levelone = new JButton("LEVEL ONE");
	private JButton leveltwo = new JButton("LEVEL TWO");
	private JButton levelthree = new JButton("LEVEL THREE");
	
	private ImageIcon titleico = new ImageIcon(new ImageIcon("Title.png").getImage().getScaledInstance(screenwidth/3 - screenwidth/10, screenheight/3 - screenheight/10, Image.SCALE_DEFAULT));
	
	public ViewDriver(){
		
        super("Main Menu");        
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(screenwidth,screenheight);
        setLayout(new BorderLayout());
        getContentPane().add(mainmenu,BorderLayout.CENTER);
		
		title.setIcon(titleico);
		
		mainmenu.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
		mainmenu.add(title);
		mainmenu.add(levelone);
		mainmenu.add(leveltwo);
		mainmenu.add(levelthree);
		
		this.setVisible(true);
		
	}
	
	public void addLevelOneActionListener(ActionListener actionlistener){
		levelone.addActionListener(actionlistener);
	}

	public void addLevelTwoActionListener(ActionListener actionlistener){
		leveltwo.addActionListener(actionlistener);
	}	
	
	public void addLevelThreeActionListener(ActionListener actionlistener){
		levelthree.addActionListener(actionlistener);
	}	
	
}