package SpaceshipCoursework;

import java.io.Serializable;

public class Spaceship extends Asteroid implements Serializable {

		private int x;
		private int y;
		private Direction dir;
		private int ID;
		private int size;
		
		Spaceship(int x_coord, int y_coord, Direction d, int siz) {
			super(x_coord, y_coord, siz);
			dir = d;
			x = x_coord;
			y = y_coord;
			ID = super.getNoOfItems();
			size = siz;
		}
		
		public boolean isHere(int x_coord, int y_coord) {
			if (x != x_coord || y != y_coord) {
				return false;
			}
			return true;
		}
		
		//public void displaySpaceship(ConsoleCanvas c) {
		//	c.showIt(x, y, ID);
		//}
		
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
		
		public void setXY(int new_x, int new_y) {
			x = new_x;
			y = new_y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void drawSpaceship(MyCanvas mc) {
			mc.showSquare(x, y, size);
		}
		
		public String toString() {
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

