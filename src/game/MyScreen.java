package game;

import javax.swing.JFrame;
public class MyScreen extends JFrame {

	public MyScreen() {
	// this is current insance, setter are mutator methods which change properties
		this.setSize(600,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	// main method is required to run the program
	public static void main(String[] args) {
		// insantiate an individual instance of MyScreen by calling on contructor
		MyScreen screen = new MyScreen();
		MyCanvas canvas = new MyCanvas ();
		screen.getContentPane().add(canvas);
	}
}