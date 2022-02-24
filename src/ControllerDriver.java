import java.awt.event.*;

public class ControllerDriver {

	ViewDriver viewdriver;
	
	public ControllerDriver(){
		
		viewdriver = new ViewDriver();	
		viewdriver.addLevelOneActionListener(new LevelOne());
		viewdriver.addLevelTwoActionListener(new LevelTwo());
		viewdriver.addLevelThreeActionListener(new LevelThree());		
	}
	
    public static void main(String[] args) {
		
		ControllerDriver controllerdriver = new ControllerDriver();

    }
    
	class LevelOne implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
					
			viewdriver.setVisible(false);
			
			MapView mapview = new MapView();        
			mapview.setVisible(true);
			mapview.setResizable(false);
			mapview.initialize();
			mapview.validate();
			mapview.repaint();

			MapModel mapmodel = new MapModel();

			MapController mapcontroller = new MapController(mapview, mapmodel);
		
		}
		
	}

	class LevelTwo implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
					
			viewdriver.setVisible(false);
			
			SecondMapView secondmapview = new SecondMapView();        
			secondmapview.setVisible(true);
			secondmapview.setResizable(false);
			secondmapview.initialize();
			secondmapview.validate();
			secondmapview.repaint();

			SecondMapModel secondmapmodel = new SecondMapModel();

			SecondMapController secondmapcontroller = new SecondMapController(secondmapview, secondmapmodel);
		
		}
		
	}	

	class LevelThree implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
					
			viewdriver.setVisible(false);
			
			ThirdMapView thirdmapview = new ThirdMapView();        
			thirdmapview.setVisible(true);
			thirdmapview.setResizable(false);
			thirdmapview.initialize();
			thirdmapview.validate();
			thirdmapview.repaint();

			ThirdMapModel thirdmapmodel = new ThirdMapModel();

			ThirdMapController thirdmapcontroller = new ThirdMapController(thirdmapview, thirdmapmodel);
		
		}
		
	}	
	
}

