package SpaceshipCoursework;

import java.io.Serializable;

/**
* @author yh006150
*  Class to create an asteroid
*/
public class Asteroid implements Serializable{
	private int x;
	private int y;
	private static int noOfItems = 0;
	private int ID;
	private int size;
	
	/** 
	 * constructor to create an asteroid
	 * @param x_coord
	 * @param y_coord
	 * @param siz
	 */
	Asteroid(int x_coord, int y_coord, int siz) {
		noOfItems++;
		ID = noOfItems;
		x = x_coord;
		y = y_coord;
		size = siz;
	}
	
	/**
	 * function that returns the noOfItems attribute
	 * @return noOfItems
	 */
	public int getNoOfItems() {
		return noOfItems;
	}
	
	/**
	 * function that returns the ID attribute
	 * @return ID
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * function that returns the noOfItems attribute
	 * @param x_coord
	 * @param y_coord
	 * @return false
	 * @return true
	 */
	public boolean isHere(int x_coord, int y_coord) {
		if (x != x_coord || y != y_coord) {
			return false;
		}
		return true;
	}
	
	//public void displayAsteroid(ConsoleCanvas c) {
	//	c.showIt(x, y, ID);
	//}
	
	/**
	 * abstract function that is changed by it's subclasses
	 * @param g
	 */
	public void tryToMove(Galaxy g) {
		//abstract method
	}
	
	/**
	 * function that draws an asteroid on the input canvas by calling showCircle
	 * @param mc
	 */
	public void drawAsteroid(MyCanvas mc) {
		mc.showCircle(x, y, size);
	}
	
	/**
	 * function that creates information about the asteroid as a string and returns the string
	 * @return output
	 */
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

