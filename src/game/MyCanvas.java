package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import sun.audio.*;

public class MyCanvas extends Canvas implements KeyListener {
	// global variables - accessible in all methods
	Goodguy link = new Goodguy(10,10,30,30,"files/monkey.png");
	LinkedList badguys = new LinkedList();
	Image backimg =  Toolkit.getDefaultToolkit().getImage("files/background.jpg");
	Image done = Toolkit.getDefaultToolkit().getImage("files/done.png");
	Image winner = Toolkit.getDefaultToolkit().getImage("files/attempt2.jpg");
	Image homescreen = Toolkit.getDefaultToolkit().getImage("files/homescreen.jpg");
	Image gif = Toolkit.getDefaultToolkit().getImage("files/tenor.png");
	String easy = "Clicks";
	String end = "With";
	String and = "And";
	String endd = "Deaths";
	String gg = "Congrats";
	String ggg = "You";
	String gggg = "Won";
	Timer timer = new Timer("Timer");
	Rectangle ban = new Rectangle(570,170,100,33); 
	int deaths = 0;
	boolean StartGame = false;
	String start2 = "Press 2 for normal mode mode";
	String start = "Press 1 for easy mode";
	String start3 = "Press 3 for difficult mode";
	String start4 = "Press 4 for impossible mode";
	int clicks = 0;
	int counter = 0;
	int total = 0;
	String time = "Time:";
	String s = "Seconds";
	String last = "Move the";
	String last2 = "arrow keys";
	String last3 = "to guide";
	String last4 = "the monkey";
	String last5 = "to the banana.";
	String last6 = "avoid the";
	String last7 = "badguys!";
	boolean shot = true;
	String ihigh = "Your highscore is";
	String inewhigh = "New Highscore!";
	int inow = 100;
			
			
	
	 
	
	
	public MyCanvas() { // default constructor!
		this.setSize(600,400); // set same size as MyScreen
		this.addKeyListener(this); // add the listerner to your canvas
		playIt("files/music.wav");
		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		int speed = 20;
		for (int i = 0; i<20; i++) {
			Badguy bg = new Badguy(rand.nextInt(winwidth), rand.nextInt(winheight),50,50, "files/pls.png");
			Rectangle r = new Rectangle(100,100,30,30);
			if (r.contains(link.getxCoord(), link.getyCoord())) {
				System.out.println("badguy on top of badguy");
				continue;
				
		
			}
			badguys.add(bg);
		}
		
	
		
		
			TimerTask repeatedTask = new TimerTask() {
			
	        public void run() {	   
	        	if (StartGame == true) {
	        		
	           
	        	if (shot == true) {
	        		
	        	 for(int i = 0; i < badguys.size(); i++) {// draw bad guys
	                Badguy bg = (Badguy) badguys.get(i);         
                    int chooser = rand.nextInt(4);              
                    if (chooser == 0) {
                    	counter = counter + 1;
                    	bg.setxCoord(bg.getxCoord() + 15);            
                    	
                    } else if (chooser == 1) {
                    	counter = counter + 1;
                    	bg.setyCoord(bg.getyCoord() + 15);  
                    } else if (chooser == 2) {
                    	counter = counter + 1;
                    	bg.setyCoord(bg.getyCoord() - 15);
                    } else {
                    	counter = counter + 1;
                    	bg.setxCoord(bg.getxCoord() - 15);
                    
                    
                    

                    }
                    	
                    }
	            }
	            repaint();
	        }
	        }
		};
	 
		
        long delay  = 100L;
        long period = 100L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);

	} 
	
	
	
	 
	public void playIt(String filename) {
	
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
				
			}
		}		
	

	@Override
	public void paint(Graphics g) {
		g.drawImage(homescreen, 0,0,800,800,this);
		Font newFont = new Font("Serif", Font.BOLD, 30);
		g.setFont(newFont);
		g.setColor(Color.BLACK);		
		g.drawString(last, 10, 50);
		g.drawString(last2, 10, 100);
		g.drawString(last3, 10, 150);
		g.drawString(last4, 10, 200);
		g.drawString(last5, 10, 250);
		g.setColor(Color.RED);
		g.drawString(last6, 10, 300);
		g.drawString(last7, 10, 350);
		Font yes = new Font("Serif", Font.BOLD, 26);
		g.setFont(yes);		
		g.drawString(start, 220,50);
		g.setColor(Color.MAGENTA);
		g.drawString(start2, 220, 150);
		g.setColor(Color.BLUE);
		g.drawString(start3, 220, 250);
		g.setColor(Color.BLACK);
		g.drawString(start4, 220, 350);		
		if (StartGame == true) {
			g.drawImage(backimg, 0, 0, 800, 800,this);
			g.drawImage(done,550,170, 100, 33,this);
			g.drawImage(link.getImg(), link.getxCoord(), link.getyCoord(), link.getWidth(), link.getHeight(),this);
			
				
			
		    
			for(int i = 0; i <  20; i++) {
			   Badguy bg = (Badguy) badguys.get(i);
			   g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this);
			   
			   if (ban.contains(link.getxCoord(), link.getyCoord()))	{	
				   shot = false;
				   total = counter / 200;
				   if (bg.getHeight() == 85 && bg.getWidth() == 85) {
					   if (total < inow) {					
				   //counter = counter - 1;				   
				    //playIt("files/winner2.wav");	
				    g.setFont(newFont);
					g.setColor(Color.RED);				   
				    g.drawImage(winner, 0, 0, 700, 700,this);
					g.drawString(gg,5,20);
					g.drawString(ggg,5,50);
					g.drawString(gggg,5,80);
					g.drawImage(gif, 200, 200, 100, 100,this);
					g.drawString(and,500,320);
					g.drawString(endd, 500, 377);
					g.drawString(Integer.toString(deaths), 500, 350);
					g.drawString(end, 520, 20);
					g.drawString(Integer.toString(clicks), 520, 50);
					g.drawString(easy, 520, 80);
					g.drawString(Integer.toString(total), 11, 350);
					g.drawString(time, 10, 320);
					g.drawString(s, 10, 377);
					g.drawString(inewhigh, 100, 200);
					g.drawString(Integer.toString(total), 130, 200);
					inow = total;
					
					
					
					
					
					
					
		
					   }	
				
				}
			   
				   
				   
			   }
					
				}
			}
		for(int i = 0; i <  20; i++) {
			   Badguy bg = (Badguy) badguys.get(i);		   
		if (ban.contains(link.getxCoord(), link.getyCoord()))	{	
			   shot = false;
			   total = counter / 200;
			   if (bg.getHeight() == 30 && bg.getWidth() == 30) {
				   if (total > inow) {
					   System.out.println("I should be seeing " + Integer.toString(total));
			   //counter = counter - 1;				   
			    //playIt("files/winner2.wav");	
			    g.setFont(newFont);
				g.setColor(Color.RED);				   
			    g.drawImage(winner, 0, 0, 700, 700,this);
				g.drawString(gg,5,20);
				g.drawString(ggg,5,50);
				g.drawString(gggg,5,80);
				g.drawString(and,500,320);
				g.drawString(endd, 500, 377);
				g.drawString(Integer.toString(deaths), 500, 350);
				g.drawString(end, 520, 20);
				g.drawString(Integer.toString(clicks), 520, 50);
				g.drawString(easy, 520, 80);
				g.drawString(Integer.toString(total), 11, 350);
				g.drawString(time, 10, 320);
				g.drawString(s, 10, 377);
				g.drawString(ihigh, 300, 200);
				g.drawString(Integer.toString(inow), 330, 200);
				   }
			   }
		}
		}
				
		
	
	for(int i = 0; i <  20; i++) {
		   Badguy bg = (Badguy) badguys.get(i);		   
		   
		   if (ban.contains(link.getxCoord(), link.getyCoord()))	{	
			   shot = false;
			   total = counter / 200;
			   if (bg.getHeight() == 55 && bg.getWidth() == 55) {
			   //counter = counter - 1;				   
			    //playIt("files/winner2.wav");	
			    g.setFont(newFont);
				g.setColor(Color.RED);				   
			    g.drawImage(winner, 0, 0, 700, 700,this);
				g.drawString(gg,5,20);
				g.drawString(ggg,5,50);
				g.drawString(gggg,5,80);
				g.drawString(and,500,320);
				g.drawString(endd, 500, 377);
				g.drawString(Integer.toString(deaths), 500, 350);
				g.drawString(end, 520, 20);
				g.drawString(Integer.toString(clicks), 520, 50);
				g.drawString(easy, 520, 80);
				g.drawString(Integer.toString(total), 11, 350);
				g.drawString(time, 10, 320);
				g.drawString(s,10, 377);																															
				
			}		   			   			   
		   }				
			}
	
	for(int i = 0; i <  20; i++) {
		   Badguy bg = (Badguy) badguys.get(i);		   
		   
		   if (ban.contains(link.getxCoord(), link.getyCoord()))	{	
			   shot = false;
			   total = counter / 200;
			   if (bg.getHeight() == 30 && bg.getWidth() == 30) {
			   //counter = counter - 1;				   
			    //playIt("files/winner2.wav");	
			    g.setFont(newFont);
				g.setColor(Color.RED);				   
			    g.drawImage(winner, 0, 0, 700, 700,this);
				g.drawString(gg,5,20);
				g.drawString(ggg,5,50);
				g.drawString(gggg,5,80);
				g.drawString(and,500,320);
				g.drawString(endd, 500, 377);
				g.drawString(Integer.toString(deaths), 500, 350);
				g.drawString(end, 520, 20);
				g.drawString(Integer.toString(clicks), 520, 50);
				g.drawString(easy, 520, 80);
				g.drawString(Integer.toString(total), 11, 350);
				g.drawString(time, 10, 320);
				g.drawString(s, 10, 377);
				
			
			}
		   
			   
			   
		   }
				
			}
	
	for(int i = 0; i <  20; i++) {
		   Badguy bg = (Badguy) badguys.get(i);		   
		   
		   if (ban.contains(link.getxCoord(), link.getyCoord()))	{	
			   shot = false;
			   total = counter / 200;
			   if (bg.getHeight() == 70 && bg.getWidth() == 70) {
			   //counter = counter - 1;				   
			    //playIt("files/winner2.wav");	
			    g.setFont(newFont);
				g.setColor(Color.RED);				   
			    g.drawImage(winner, 0, 0, 700, 700,this);
				g.drawString(gg,5,20);
				g.drawString(ggg,5,50);
				g.drawString(gggg,5,80);
				g.drawString(and,500,320);
				g.drawString(endd, 500, 377);
				g.drawString(Integer.toString(deaths), 500, 350);
				g.drawString(end, 520, 20);
				g.drawString(Integer.toString(clicks), 520, 50);
				g.drawString(easy, 520, 80);
				g.drawString(Integer.toString(total), 11, 350);
				g.drawString(time, 10, 320);
				g.drawString(s, 10, 377);
				
				
				
		
				
				
			
			}
		   
			   
			   
		   }
				
			}
	
	for(int i = 0; i <  20; i++) {
		   Badguy bg = (Badguy) badguys.get(i);		   
		   
		   if (ban.contains(link.getxCoord(), link.getyCoord()))	{	
			   shot = false;
			   total = counter / 200;
			   if (bg.getHeight() == 85 && bg.getWidth() == 85) {
			   //counter = counter - 1;				   
			    //playIt("files/winner2.wav");	
			    g.setFont(newFont);
				g.setColor(Color.RED);				   
			    g.drawImage(winner, 0, 0, 700, 700,this);
				g.drawString(gg,5,20);
				g.drawString(ggg,5,50);
				g.drawString(gggg,5,80);
				g.drawString(and,500,320);
				g.drawString(endd, 500, 377);
				g.drawString(Integer.toString(deaths), 500, 350);
				g.drawString(end, 520, 20);
				g.drawString(Integer.toString(clicks), 520, 50);
				g.drawString(easy, 520, 80);
				g.drawString(Integer.toString(total), 11, 350);
				g.drawString(time, 10, 320);
				g.drawString(s, 10, 377);
				
				
				
				
				
				
			
			}
		   
			   
			   
		   }
				
			}
}
	
		
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		link.moveIt(e.getKeyCode(),this.getWidth(),this.getHeight());
		clicks = clicks + 1;
		
		
		if (key == KeyEvent.VK_2) {
			StartGame = true;
			for(int i = 0; i < 20; i++) {
				Badguy bg = (Badguy) badguys.get(i);
				bg.setHeight(55);
				bg.setWidth(55);
			}
		}
		if (key == KeyEvent.VK_1) {
			StartGame = true;
			for(int i = 0; i < 20; i++) {
				Badguy bg = (Badguy) badguys.get(i);
				bg.setHeight(30);
				bg.setWidth(30);
			}						
		}
		if (key == KeyEvent.VK_3) {
			StartGame = true;
			for(int i = 0; i < 20; i++) {
				Badguy bg = (Badguy) badguys.get(i);
				bg.setHeight(70);
				bg.setWidth(70);
			}
		}
		if (key == KeyEvent.VK_4) {
			StartGame = true;
			for(int i = 0; i < 20; i++) {
				Badguy bg = (Badguy) badguys.get(i);
				bg.setHeight(85);
				bg.setWidth(85);
			}
		}
		
			
		
		for(int i = 0; i < 20; i++) {
			Badguy bg = (Badguy) badguys.get(i);
			Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
			if (r.contains(link.getxCoord(), link.getyCoord())) {
				playIt("files/no.wav");
				deaths = deaths + 1;
				System.out.println("badguy hit by link");
				link.setxCoord(10);
				link.setyCoord(150);
	
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}