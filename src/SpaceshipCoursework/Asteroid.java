package SpaceshipCoursework;

import java.io.Serializable;

public class Asteroid implements Serializable{
	private int x;
	private int y;
	private static int noOfItems = 0;
	private int ID;
	private int size;
	
	
	Asteroid(int x_coord, int y_coord, int siz) {
		noOfItems++;
		ID = noOfItems;
		x = x_coord;
		y = y_coord;
		size = siz;
	}
	
	public int getNoOfItems() {
		return noOfItems;
	}
	
	public boolean isHere(int x_coord, int y_coord) {
		if (x != x_coord || y != y_coord) {
			return false;
		}
		return true;
	}
	
	public void displayAsteroid(ConsoleCanvas c) {
		c.showIt(x, y, ID);
	}
	
	public void tryToMove(Galaxy g) {
		
	}
	
	public void drawAsteroid(MyCanvas mc) {
		mc.showBall(x, y, size);
	}
	
	public String toString() {
		String output = "Object " + ID + " is an Asteroid at " + x + ", " + y;
		return output;
	}
	
	public static void main(String[] args) {
		Asteroid obj_a1 = new Asteroid(6, 8, 2);
		System.out.println(obj_a1.toString());
		Asteroid obj_a2 = new Asteroid(7, 9, 2);
		System.out.println(obj_a2.toString());
	}

}

