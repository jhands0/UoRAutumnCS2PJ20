package SpaceshipCoursework;

import java.io.Serializable;

public class Seeker extends Spaceship implements Serializable {

		private int x;
		private int y;
		private Direction dir;
		private int ID;
		private int size;
		private Spaceship ship;
		
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
			if (x != x_coord || y != y_coord) {
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
			int xDistance = ship.getX() - x;
			int yDistance = ship.getY() - y;
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
			int dx = 0;
			int dy = 0;
			switch(dir){
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
			if (g.canMoveHere(x+dx, y+dy) == false) {
				Direction new_dir = Direction.getNextDirection(dir);
				if (x+dx == 0 || x+dx == g.getX() || y+dy == 0 || y+dy == g.getY()) {
					new_dir = Direction.getNextDirection(new_dir);
					new_dir = Direction.getNextDirection(new_dir);
					new_dir = Direction.getNextDirection(new_dir);
				}
				dir = new_dir;
			}
			else
			{
				x = x + dx;
				y = y + dy;
			}
		}
		
		/** 
		 * function to set x and y co-ords to input params
		 * @param new_x
		 * @param new_y
		 */
		public void setXY(int new_x, int new_y) {
			x = new_x;
			y = new_y;
		}
		
		/** 
		 * function to draw the seeker on the canvas by calling showSquare()
		 * @param mc
		 */
		public void drawSeeker(MyCanvas mc) {
			mc.showSquare(x, y, size);
		}
		
		
		/** 
		 * function to return information about the seeker as a String
		 * @return output
		 */
		public String toString() {
			String output = "Object " + ID + " is a Seeker at " + x + ", " + y + " and is travelling " + dir.toString() + " towards Object " + ship.getID();
			return output;
		}
		
		public static void main(String[] args) {
		}

}