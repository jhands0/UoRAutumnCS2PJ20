package SpaceshipCoursework;

import java.io.Serializable;

/**
* @author yh006150
*  Class to create a seeker type spaceship
*/
public class Seeker extends Spaceship implements Serializable {
		private int x;
		private int y;
		private Direction dir;
		private int ID;
		private int size;
		private Spaceship ship;
		
		/** 
		 * constructor to create a seeker based on the superclass spaceship
		 * @param x_coord
		 * @param y_coord
		 * @param d
		 * @param siz
		 * @param s
		 */
		Seeker(int x_coord, int y_coord, Direction d, int siz, Spaceship s) {
			super(x_coord, y_coord, d, siz);
			dir = d;
			x = x_coord;
			y = y_coord;
			ID = super.getNoOfItems();
			size = siz;
			ship = s;
		}
		
		/** 
		 * function to check if the seeker object is at the input coordinates
		 * @param x_coord
		 * @param y_coord
		 * @return false
		 * @return true
		 */
		public boolean isHere(int x_coord, int y_coord) {
			if (x != x_coord || y != y_coord) { //if x or y is not equal to input params
				return false;
			}
			return true;
		}
		
		//public void displaySeeker(ConsoleCanvas c) {
		//	c.showIt(x, y, ID);
		//}
		
		/** 
		 * function to update seeker's direction based on position of the ship it's tracking
		 */
		public void track() {
			int xDistance = ship.getX() - x; //finds the distance between seeker's co-ordinates and tracked ship's co-ordinates, in x and y direction
			int yDistance = ship.getY() - y;
			//changes direction based on the sign of each of these distances
			if (Integer.signum(xDistance) == 0 && Integer.signum(yDistance) == -1) {
				dir = Direction.North;
			}
			
			if (Integer.signum(xDistance) == 1 && Integer.signum(yDistance) == -1) {
				dir = Direction.NorthEast;
			}
			
			if (Integer.signum(xDistance) == 1 && Integer.signum(yDistance) == 0) {
				dir = Direction.East;
			}
			
			if (Integer.signum(xDistance) == 1 && Integer.signum(yDistance) == 1) {
				dir = Direction.SouthEast;
			}
			
			if (Integer.signum(xDistance) == 0 && Integer.signum(yDistance) == 1) {
				dir = Direction.South;
			}
			
			if (Integer.signum(xDistance) == -1 && Integer.signum(yDistance) == 1) {
				dir = Direction.SouthWest;
			}
			
			if (Integer.signum(xDistance) == -1 && Integer.signum(yDistance) == 0) {
				dir = Direction.West;
			}
			
			if (Integer.signum(xDistance) == -1 && Integer.signum(yDistance) == -1) {
				dir = Direction.NorthWest;
			}
		}
		
		/** 
		 * function to try to move a seeker based on it's direction, and changes it's direction if it can't
		 * @param g
		 */
		public void tryToMove(Galaxy g) {
			int dx = 0; //initially, the movement is zero
			int dy = 0;
			switch(dir){ //movement changes based on the dir attribute
				case North:
					dx = 0;
					dy = -1;
					break;
					
				case NorthEast:
					dx = 1;
					dy = -1;
					break;
					
				case East:
					dx = 1;
					dy = 0;
					break;
					
				case SouthEast:
					dx = 1;
					dy = 1;
					break;
					
				case South:
					dx = 0;
					dy = 1;
					break;
					
				case SouthWest:
					dx = -1;
					dy = 1;
					break;
					
				case West:
					dx = -1;
					dy = 0;
					break;
					
				case NorthWest:
					dx = -1;
					dy = -1;
					break;
			}
			if (g.canMoveHere(x+dx, y+dy) == false) { //if the seeker cannot move to it's next location
				Direction new_dir = Direction.getNextDirection(dir); //direction is changed anti-clockwise
				if (x+dx == 0 || x+dx == g.getX() || y+dy == 0 || y+dy == g.getY()) { //if the next location is the arena's edge
					new_dir = Direction.getNextDirection(new_dir); //change direction 3 times, in total doing a 180 turn
					new_dir = Direction.getNextDirection(new_dir);
					new_dir = Direction.getNextDirection(new_dir);
				}
				dir = new_dir; //sets dir to the new direction
			}
			else
			{
				x = x + dx; //updates the co-ordinates of the seeker
				y = y + dy;
			}
		}
		
		/** 
		 * function to set x and y co-ords to input params
		 * @param new_x
		 * @param new_y
		 */
		public void setXY(int new_x, int new_y) {
			//sets x and y to it't new input parameters
			x = new_x;
			y = new_y;
		}
		
		/** 
		 * function to draw the seeker on the canvas by calling showSquare()
		 * @param mc
		 */
		public void drawSeeker(MyCanvas mc) {
			mc.showSquare(x, y, size); //draws a seeker by calling show square with inputs, x, y, and size
		}
		
		
		/** 
		 * function to return information about the seeker as a String
		 * @return output
		 */
		public String toString() {
			//adds information about the seeker to a string then returns the string
			String output = "Object " + ID + " is a Seeker at " + x + ", " + y + " and is travelling " + dir.toString() + " towards Object " + ship.getID();
			return output;
		}
		
		public static void main(String[] args) {
		}

}