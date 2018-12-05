package game;

import java.awt.Image;
import java.awt.Toolkit;

public class Goodguy {
	
	private int xCoord = 0;
	private int yCoord = 0;
	private int width = 13;
	private int height = 13;
	private Image img;
	
	/**
	 * Goodguy default constructor
	 */
	public Goodguy() {
		setxCoord(10);
		setyCoord(10);
		setWidth(30);
		setHeight(30);
		setImg("../files/monkey.png");
	}
	
	public Goodguy(int x, int y, int w, int h, String imgpath) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
	}
	
	public void moveIt(int direction, int w, int h) {
		int speed = 20;
		int x = getxCoord();
		int y = getyCoord();
		
		if (direction == 39) {
			x = x + speed;
			if (x > w) { x = x - speed * 3; }
			setxCoord(x);
			setImg("files/monkey.png");
		} else if (direction == 37) {
			if (x < 0) { x = x + speed * 3; }
			x = x - speed;
			setxCoord(x);
			setImg("files/monkey.png");
		} else if (direction == 38) {
			if (y < 0) { y = y + speed * 3; }
			y = y - speed;
			setyCoord(y);
			setImg("files/monkey.png");
		} else if (direction == 40) {
			if ((y + 15) > h) { y = y - speed * 3; }
			y = y + speed;
			setyCoord(y);
			setImg("files/monkey.png");
		}
	}
	
	public void setImg(String impath) {
		this.img = Toolkit.getDefaultToolkit().getImage(impath);
		
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	void setxCoord(int xCoord) {
		// TODO Auto-generated method stub
		this.xCoord = xCoord;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void moveIt(int keyCode) {
		// TODO Auto-generated method stub
		
	}
	
}

	



