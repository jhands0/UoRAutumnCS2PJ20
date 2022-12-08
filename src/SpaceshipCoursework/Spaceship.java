package SpaceshipCoursework;

import java.io.Serializable;

/**
* @author yh006150
*  Class to create a spaceship
*/
public class Spaceship extends Asteroid implements Serializable {
		private int x;
		private int y;
		private Direction dir;
		private int ID;
		private int size;
		
		/** 
		 * constructor to create a spaceship based on the superclass asteroid
		 * @param x_coord
		 * @param y_coord
		 * @param d
		 * @param siz
		 */
		Spaceship(int x_coord, int y_coord, Direction d, int siz) {
			super(x_coord, y_coord, siz);
			dir = d;
			x = x_coord;
			y = y_coord;
			ID = super.getNoOfItems(); //calls the asteroid function getNoOfItems to make an ID number
			size = siz;
		}
		
		/** 
		 * function to check if the spaceship object is at the input coordinates
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
		
		//public void displaySpaceship(ConsoleCanvas c) {
		//	c.showIt(x, y, ID);
		//}
		
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
			if (g.canMoveHere(x+dx, y+dy) == false) { //if the spaceship cannot move to it's next location
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
			x = new_x; //sets x and y to the input params
			y = new_y;
		}
		
		/** 
		 * function to return the x coordinate of the ship; used by seeker
		 * @return x
		 */
		public int getX() {
			return x;
		}
		
		/** 
		 * function to return the y coordinate of the ship; used by seeker
		 * @return y
		 */
		public int getY() {
			return y;
		}
		
		/** 
		 * function to draw the spaceship on the canvas by calling showSquare()
		 * @param mc
		 */
		public void drawSpaceship(MyCanvas mc) {
			mc.showSquare(x, y, size); //calls showSquare with params x, y, and size
		}
		
		/** 
		 * function to return information about the spaceship as a String
		 * @return output
		 */
		public String toString() {
			//adds information about the spaceship to a string then returns the string
			String output = "Object " + ID + " is a Spaceship at " + x + ", " + y + " and is travelling " + dir.toString();
			return output;
		}
		
		public static void main(String[] args) {
			Spaceship obj_drone1 = new Spaceship(6, 8, Direction.South, 2);
			System.out.println(obj_drone1.toString());
			Spaceship obj_drone2 = new Spaceship(7, 9, Direction.North, 2);
			System.out.println(obj_drone2.toString());
		}

}

