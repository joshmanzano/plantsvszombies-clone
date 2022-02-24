import java.awt.event.*;
import java.util.*;
import javax.swing.JButton;
import java.lang.*;

public class ThirdMapController{
    
    private ThirdMapView mapview;
    private ThirdMapModel mapmodel;
    private Thread mapthread;
	private Thread projectilethread;
	private Thread zombiethread;
	private Thread zombiewalkthread;
	private Thread peashootthread;
	private Runnable maprunnable;
	private Runnable projectilerunnable;
	private Runnable zombierunnable;
	private boolean won;
    private int interval;
    private int interval2;
    private Random random;
    
    public ThirdMapController(ThirdMapView mapview, ThirdMapModel mapmodel){
        
        this.mapview = mapview;
        this.mapmodel = mapmodel;
        
        mapview.selectaddListener(new SelectMouseListener());
        mapview.sunfloweraddActionListener(new SunFlowerActionListener());
        mapview.peashooteraddActionListener(new PeaShooterActionListener());
		mapview.cherrybombaddActionListener(new CherryBombActionListener());
		mapview.fastforwardaddActionListener(new FastForwardActionListener());
		mapview.slowdownaddActionListener(new SlowDownActionListener());
        mapview.setSunlight(mapmodel.getSun());
		
        random = new Random();
		
		int settime = 1800;
		
		interval = settime * mapmodel.getGamespeed(); 
		interval2 = (settime * 10) * mapmodel.getGamespeed();	
		won = true;
		
		maprunnable = new Runnable(){	
			
          public void run(){
			  
			 try{
				 
				 while(setInterval()){
					 if((interval * mapmodel.getGamespeed()) % (10 * mapmodel.getGamespeed()) == 0){
						 Sun sun = mapmodel.spawnSun(mapview.getsunlayerWidth(),mapview.getsunlayerHeight());
						 mapview.spawnSun(sun.getX(),sun.getY(),new SunActionListener());
					 }
					 
					 if((interval * mapmodel.getGamespeed()) % (5 * mapmodel.getGamespeed()) == 0){
						 int row = random.nextInt(5);
						 mapmodel.addZombie(new Zombie(50,10,3,row,8));
						 mapview.addFlagZombie(row);  
						 for(int i = 0 ; i < 5 ; i++){
							 row = random.nextInt(5);
							 mapmodel.addZombie(new Zombie(50,10,4,row,8));
							 mapview.addZombie(row); 						 
						 }
						 for(int i = 0 ; i < 5 ; i++){
							 row = random.nextInt(5);
							 mapmodel.addZombie(new Zombie(100,10,4,row,8));
							 mapview.addConeZombie(row); 						 
						 }  
					 }

					 for(int row = 0 ; row < 5 ; row++ ){
						 for(int col = 0 ; col < 9 ; col++ ){
							 try{
								 if(mapmodel.checkPlant(row,col)){
									 if(mapmodel.getPlantName(row,col).equals("Sunflower")){
										 if(((Sunflower) mapmodel.getPlant(row, col)).produceSun()){
											mapview.flowerspawnSun(row,col,new SunActionListener());
										 } 
									 }else if(mapmodel.getPlantName(row,col).equals("Peashooter")){
										 boolean found = false;
										 for(int i = 0 ; i < mapmodel.getNumOfZombies() && !found ; i++){
											if(mapmodel.checkZombie(i)){
												if(row == mapmodel.getZombie(i).getRow()){ 
													found = true;
													if(((Peashooter) mapmodel.getPlant(row, col)).shootPea()){
														mapview.addPeaProjectile(row,col);
														mapmodel.addProjectile(new Projectile(10,4,row));
													}               
												}                                
											}
										 }
										 if(!found){
											((Peashooter) mapmodel.getPlant(row,col)).resetPea();
										 }

									 }else if(mapmodel.getPlantName(row,col).equals("Cherrybomb")){
									 	if(((Cherrybomb) mapmodel.getPlant(row, col)).explosion()){
											mapmodel.removePlant(row,col);
											mapview.removePlant(row,col);
											for(int j = 0 ; j < mapmodel.getNumOfZombies() ; j++){
												try{
													if(mapmodel.checkZombie(j)){
														if((mapmodel.getZombie(j).getRow() == row+1 && mapmodel.getZombie(j).getCol() == col)||(mapmodel.getZombie(j).getRow() == row-1 && mapmodel.getZombie(j).getCol() == col)||(mapmodel.getZombie(j).getRow() == row && mapmodel.getZombie(j).getCol() == col+1)||(mapmodel.getZombie(j).getRow() == row && mapmodel.getZombie(j).getCol() == col-1)||(mapmodel.getZombie(j).getRow() == row+1 && mapmodel.getZombie(j).getCol() == col+1)||(mapmodel.getZombie(j).getRow() == row-1 && mapmodel.getZombie(j).getCol() == col-1)||(mapmodel.getZombie(j).getRow() == row+1 && mapmodel.getZombie(j).getCol() == col-1)||(mapmodel.getZombie(j).getRow() == row+1 && mapmodel.getZombie(j).getCol() == col-1)||(mapmodel.getZombie(j).getRow() == row-1 && mapmodel.getZombie(j).getCol() == col+1)||(mapmodel.getZombie(j).getRow() == row && mapmodel.getZombie(j).getCol() == col)){
															mapview.removeZombie(mapmodel.getZombie(j).getRow(),mapmodel.getZombie(j).getID());
															mapmodel.removeZombie(j);
														}
													}
												} catch (IndexOutOfBoundsException e) {} catch (NullPointerException e) {}											
											}

										}
									 }
								 }
						     } catch (IndexOutOfBoundsException e) {} catch (NullPointerException e) {}
						 }
					  }			 
					 
					 Thread.sleep(1000 / mapmodel.getGamespeed());

				 } 	
				 
			 } catch (InterruptedException iex) {}
              
          }		
		};

		zombierunnable = new Runnable(){
		
			public void run(){
			
				try{	

					while(setInterval3()){
						
						if(mapmodel.getNumOfZombies() == mapview.getNumOfZombies()){
						  for(int i = 0 ; i < mapmodel.getNumOfZombies() ; i++){
							  
							  try{
							  
								  if(mapmodel.checkZombie(i)){
									  if(mapmodel.checkPlant(mapmodel.getZombie(i).getRow(),mapmodel.getZombie(i).getCol())){
										  if(mapmodel.getPlant(mapmodel.getZombie(i).getRow(),mapmodel.getZombie(i).getCol()).reduceHealth(mapmodel.getZombie(i).getDamage())){
											 mapmodel.removePlant(mapmodel.getZombie(i).getRow(),mapmodel.getZombie(i).getCol());
											 mapview.removePlant(mapmodel.getZombie(i).getRow(),mapmodel.getZombie(i).getCol());
										  }
									  }else{
										  if(mapview.checkColZombie(i,mapmodel.getZombie(i).getCol())){
											 if(mapmodel.getZombie(i).getCol()==0){
												mapview.GameOver();
												won = false;
												interval = 1;
												interval2 = 1;
											}else{
												mapmodel.getZombie(i).changePosition(mapmodel.getZombie(i).getRow(),mapmodel.getZombie(i).getCol()-1);                            
											 }
										  }
										  if(mapmodel.getZombie(i).move()){
											zombiewalkthread = new Thread(new walkZombie(i));
											zombiewalkthread.start();
										  }
									  }                  
								  }							  
							  
							  } catch (IndexOutOfBoundsException e) {} catch (NullPointerException e) {}

						  } 						
						}
						
						Thread.sleep(200 / mapmodel.getGamespeed());

					}

				} catch (InterruptedException iex) {}			
			
			}

		};
		
        projectilerunnable = new Runnable(){					

		  int max;	
			
          public void run(){   
			  
              try{
			  
				  while(setInterval2()){

					  boolean unequal = false;
					  
					  if(mapmodel.getNumOfProjectiles() == mapview.getNumOfProjectiles() && mapmodel.checkNumOfProjectiles() && mapview.checkNumOfProjectiles()){

						  for(int i = 0 ; i < mapview.getNumOfProjectiles() && !unequal ; i++){

							  boolean hit = false;
							  
							 	try{
									
								  if(mapmodel.checkProjectile(i) && mapview.checkProjectile(i)){

									  if(mapmodel.getNumOfZombies() == mapview.getNumOfZombies()){
										  for(int j = 0 ; j < mapmodel.getNumOfZombies() && !hit ; j++){
											  if(mapmodel.checkZombie(j)){
												if(mapmodel.getZombie(j).getRow() == mapmodel.getProjectile(i).getRow() && mapview.checkHitPeaProjectile(i,j)){
													if(mapmodel.getZombie(j).reduceHealth(mapmodel.getProjectile(i).getDamage())){
														mapview.removeZombie(mapmodel.getZombie(j).getRow(),mapmodel.getZombie(j).getID());
														mapmodel.removeZombie(j);
													}
													mapview.removePeaProjectile(mapmodel.getProjectile(i).getRow(),mapmodel.getProjectile(i).getID());
													mapmodel.removeProjectile(i);
													hit = true;
												}                           
											  }
										  }						  
									  }

									  if(!hit){
										  if(mapview.checkEndProjectile(i)){
											mapview.removePeaProjectile(mapmodel.getProjectile(i).getRow(),mapmodel.getProjectile(i).getID());
											mapmodel.removeProjectile(i);
										  }else{
											mapview.walkPeaProjectile(mapmodel.getProjectile(i).getRow(),mapmodel.getProjectile(i).getID());
										  }                       
									  }
								  	}
									
								} catch (IndexOutOfBoundsException e){} catch (NullPointerException e) {}
						  }

					  }	
					  
					  Thread.sleep(10 / mapmodel.getGamespeed());

				  }			  
			  
			  } catch (InterruptedException iex) {}
			
          }			
		
		};
		
		mapthread = new Thread(maprunnable);
		projectilethread = new Thread(projectilerunnable);
		zombiethread = new Thread(zombierunnable);
		mapthread.start();
		projectilethread.start();
		zombiethread.start();
		
    }
    
    public boolean setInterval(){
        if(interval <= 1){
			interval2 = 1;
			if(won){
				mapview.GameWin();			
			}
            return false;
        }
            interval--;
			return true;
    }
	
    public boolean setInterval2(){
        if(interval2 == 1){
            return false;
        }
			return true;
    }    
	
	public boolean setInterval3(){
        if(interval2 == 1){
            return false;
        }
			return true;
	}
    
	class walkZombie implements Runnable{
				
		private int walkinterval = 4;
		private int id;
		
		public walkZombie(int id){
			this.id = id;
		}
		
		public void run(){
			
			try{	

				while(walkInterval()){
					
					try{
					
					if(mapview.checkZombie(id)){
						mapview.walkZombie(mapmodel.getZombie(id).getRow(),mapmodel.getZombie(id).getID());					
					}else{
						stopInterval();
					}
					
					} catch (IndexOutOfBoundsException e){} catch (NullPointerException e) {}	
					Thread.sleep(10 / mapmodel.getGamespeed());

				}

			} catch (InterruptedException iex) {}			
			
		}	
		
		public boolean walkInterval(){
			
			if(walkinterval==1){
				return false;
			}
			
			walkinterval--;
			return true;
			
		}
		
		public void stopInterval(){
			walkinterval = 1;
		}
	
	}
	
    class SunActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

                ((JButton) e.getSource()).setVisible(false);
                mapmodel.addSun(25);
                mapview.setSunlight(mapmodel.getSun());

        }
    }
    
    class SunFlowerActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(mapmodel.getSun() >= 50){
                mapmodel.changeSelection(0);
                //mapview.sunflowerCursor();
                mapview.showSelect();                
            }else{
             
            }
        }
    }    

    class PeaShooterActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(mapmodel.getSun() >= 100){
                mapmodel.changeSelection(1);
                //mapview.peashooterCursor();
                mapview.showSelect();                
            }else{
                
            }
        }         
    } 

    class CherryBombActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(mapmodel.getSun() >= 150){
                mapmodel.changeSelection(2);
                //mapview.cherrybombCursor();
                mapview.showSelect();                
            }else{
                
            }
        }         
    } 	
	
    class FastForwardActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mapmodel.gamespeedUp();
        }         
    } 	

    class SlowDownActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mapmodel.gamespeedDown();
        }         
    } 
	
    class SelectMouseListener implements MouseListener{

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {

            ((JButton) e.getSource()).setVisible(false);
            mapview.defaultCursor();
            mapview.hideSelect();
            int row = Character.getNumericValue(((JButton) e.getSource()).getName().charAt(0));
            int col = Character.getNumericValue(((JButton) e.getSource()).getName().charAt(2));
            if(!mapmodel.checkPlant(row,col)){
               if(mapmodel.getSelection() == 0){
				   
				  Runnable cd = new Runnable(){
					  
					public void run(){

						try{	
							
							mapview.disableSunflowerButton();
							Thread.sleep(1000 * Sunflower.regenrate);
							mapview.enableSunflowerButton();

						} catch (InterruptedException iex) {}			

					}	
					  
				  };
				   
				  Thread regen = new Thread(cd);
				  regen.start(); 
				   
                  mapmodel.addPlant(new Sunflower(0,0,100,0,10,row,col),row,col);
                  mapview.addSunflower(row,col);
                  mapmodel.removeSun(Sunflower.cost);
                  mapview.setSunlight(mapmodel.getSun());
				   
                }else if(mapmodel.getSelection() == 1){

				  Runnable cd = new Runnable(){
					  
					public void run(){

						try{	
							
							mapview.disablePeashooterButton();
							Thread.sleep(1000 * Peashooter.regenrate);
							mapview.enablePeashooterButton();

						} catch (InterruptedException iex) {}			

					}	
					  
				  };
				   
				  Thread regen = new Thread(cd);
				  regen.start(); 				   
				   
                  mapmodel.addPlant(new Peashooter(8,10,100,10,4,row,col),row,col);
                  mapview.addPeashooter(row,col);
                  mapmodel.removeSun(Peashooter.cost);
                  mapview.setSunlight(mapmodel.getSun());
				   
                }else if(mapmodel.getSelection() == 2){

				  Runnable cd = new Runnable(){
					  
					public void run(){

						try{	
							
							mapview.disableCherrybombButton();
							Thread.sleep(1000 * Peashooter.regenrate);
							mapview.enableCherrybombButton();

						} catch (InterruptedException iex) {}			

					}	
					  
				  };
				   
				  Thread regen = new Thread(cd);
				  regen.start(); 				   
				   
                  mapmodel.addPlant(new Cherrybomb(8,10,9999,10,3,row,col),row,col);
                  mapview.addCherrybomb(row,col);
                  mapmodel.removeSun(Cherrybomb.cost);
                  mapview.setSunlight(mapmodel.getSun());
				   
                }                     
            }		
		
		}
        
        public void mouseEntered(MouseEvent e) {
            if(mapmodel.getSelection() == 0){
                ((JButton) e.getSource()).setIcon(mapview.getSunflowerIcon());
            }else if(mapmodel.getSelection() == 1){
                ((JButton) e.getSource()).setIcon(mapview.getPeashooterIcon());
            }else if(mapmodel.getSelection() == 2){
                ((JButton) e.getSource()).setIcon(mapview.getCherrybombIcon());
            }             
        }

        public void mouseExited(MouseEvent e) {
            ((JButton) e.getSource()).setIcon(null);
        }
    }
    
}
