package SpaceshipCoursework;
import java.util.Random;

public enum Direction {
North,
NorthEast,
East,
SouthEast,
South,
SouthWest,
West,
NorthWest;
	
	public static Direction getRandomDirection() {
		Random random = new Random();
		return values()[random.nextInt(8)];
	}
	
	public static Direction getNextDirection(Direction dir) {
		switch(dir) {
			case North:
				return Direction.NorthEast;
				
			case NorthEast:
				return Direction.East;
		
			case East:
				return Direction.SouthEast;
				
			case SouthEast:
				return Direction.South;
			
			case South:
				return Direction.SouthWest;
			
			case SouthWest:
				return Direction.West;
				
			case West:
				return Direction.NorthWest;
				
			case NorthWest:
				return Direction.North;
			
			default:
				return dir;
		}
	}
}
