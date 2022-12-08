package SpaceshipCoursework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


import javafx.scene.paint.Color;

/**
* @author yh006150
*  Class that creates a galaxy, and maintains functions within the scope of the galaxy
*/
public class Galaxy implements Serializable{
	private ArrayList<Spaceship> spaceships;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Seeker> seekers;
	private ArrayList<Object> items;
	private Random randomGenerator; 
	private int x;
	private int y;
	
	/** 
	 * constructor to create a seeker based on the superclass spaceship
	 * @param x_size
	 * @param y_size
	 */
	Galaxy(int x_size, int y_size) {
		spaceships = new ArrayList<Spaceship>();
		asteroids = new ArrayList<Asteroid>();
		seekers = new ArrayList<Seeker>();
		items = new ArrayList<Object>();
		randomGenerator = new Random();
		x = x_size;
		y = y_size;
	}
	
	/** 
	 * function to add either an asteroid, spaceship or seeker to the arena with unique co-ordinates
	 */
	public void addSpaceship() {
		int x_rand = randomGenerator.nextInt(x-1)+1;
		int y_rand = randomGenerator.nextInt(y-1)+1;
		int size_rand = randomGenerator.nextInt(10)+1;
		Direction dir_rand = Direction.getRandomDirection();
		while ((isSpaceshipAt(x_rand, y_rand) == true) || (isAsteroidAt(x_rand, y_rand) == true) || (isSeekerAt(x_rand, y_rand) == true)) {
			x_rand = randomGenerator.nextInt(x-1)+1;
			y_rand = randomGenerator.nextInt(y-1)+1;
		}
		int typeOfShip = randomGenerator.nextInt(10);
		if (typeOfShip <= 2) {
			Asteroid obj = new Asteroid(x_rand, y_rand, size_rand);
			asteroids.add(obj);
			items.add(obj);
		}
		else if ((typeOfShip > 2) && (typeOfShip < 5)) {
			if(spaceships.isEmpty() != true) {
				int shipToFollow = randomGenerator.nextInt(spaceships.size());
				Seeker obj = new Seeker(x_rand, y_rand, dir_rand, size_rand, spaceships.get(shipToFollow));
				seekers.add(obj);
				items.add(obj);
			}
			else {
				Spaceship obj = new Spaceship(x_rand, y_rand, dir_rand, size_rand);
				spaceships.add(obj);
				items.add(obj);
			}
		}
		else {
			Spaceship obj = new Spaceship(x_rand, y_rand, dir_rand, size_rand);
			spaceships.add(obj);
			items.add(obj);
		}
		
	}
	
	 /** 
	 * function to check if any spaceship is at a particular coordinate by calling isHere() on all items in the spaceships array
	 * @param x_coord
	 * @param y_coord
	 * @return true
	 * @return false
	 */
	public boolean isSpaceshipAt(int x_coord, int y_coord) {
		for (int i = 0; i < spaceships.size(); i++) {
			if (spaceships.get(i).isHere(x_coord, y_coord) == true) {
				return true;
			}
		}
		return false;
	}
	
	 /** 
		 * function to check if any asteroid is at a particular coordinate by calling isHere() on all items in the asteroids array
		 * @param x_coord
		 * @param y_coord
		 * @return true
		 * @return false
		 */
	public boolean isAsteroidAt(int x_coord, int y_coord) {
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).isHere(x_coord, y_coord) == true) {
				return true;
			}
		}
		return false;
	}
	
	 /** 
		 * function to check if any seeker is at a particular coordinate by calling isHere() on all items in the seekers array
		 * @param x_coord
		 * @param y_coord
		 * @return true
		 * @return false
		 */
	public boolean isSeekerAt(int x_coord, int y_coord) {
		for (int i = 0; i < seekers.size(); i++) {
			if (seekers.get(i).isHere(x_coord, y_coord) == true) {
				return true;
			}
		}
		return false;
	}
	
	//public void showSpaceships(ConsoleCanvas c) {
	//	for (int i = 0; i < spaceships.size(); i++) {
	//		( spaceships.get(i)).displaySpaceship(c);
	//	}
	//}
	
	//public void showAsteroids(ConsoleCanvas c) {
	//	for (int i = 0; i < asteroids.size(); i++) {
	//		( asteroids.get(i)).displayAsteroid(c);
	//	}
	//}
	
	 /** 
		 * function to draw all the items in the galaxy by calling their draw functions in their respective lists
		 * @param mc
		 */
	public void drawWorld(MyCanvas mc) {
		mc.clearCanvas();	//initially clears the canvas
		for (int i = 0; i < asteroids.size(); i++) {
			mc.setFillColour(Color.BROWN); //sets the fill colour to brown for asteroids
			asteroids.get(i).drawAsteroid(mc); //calls drawAsteroid to draw the individual asteroid
		}
		for (int j = 0; j < spaceships.size(); j++) {
			mc.setFillColour(Color.WHITE); //sets the fill colour to white for spaceships
			spaceships.get(j).drawSpaceship(mc); //calls drawSpaceship to draw the individual spaceship
		}
		for (int k = 0; k < seekers.size(); k++) { 
			mc.setFillColour(Color.GRAY); //sets the fill colour the gray for seekers
			seekers.get(k).drawSeeker(mc); //calls drawSeeker to draw the individual seeker
		}
	}
	
	 /** 
		 * function to move all items in the galaxy on the input canvas by calling moveAllSpaceships() and moveAllSeekers()
		 * @param mc
		 */
	public void updateGalaxy(MyCanvas mc) {
		moveAllSpaceships();
		moveAllSeekers();
		
	}
	
	 /** 
		 * function to return x
		 * @return x
		 */
	public int getX() {
		return x;
	}
	 /** 
		 * function to return y
		 * @return y
		 */
	public int getY() {
		return y;
	}
	
	 /** 
		 * function to check if a spaceship, asteroid or seeker is at the input coordinates, or the input co-ordinates are at a wall
		 * @param new_x
		 * @param new_y
		 * @return false
		 * @return true
		 */
	public Boolean canMoveHere(int new_x, int new_y) {
		if((new_x == 0) || (new_x == x) || (new_y == 0) || (new_y == y)) { //checks if any of the input co-ords are at a boundary
			return false;
		}
		if(isSpaceshipAt(new_x, new_y) == true) { //checks if any spaceships are at the input co-ords by calling isSpaceshipAt()
			return false;
		}
		if(isAsteroidAt(new_x, new_y) == true) { //checks if any asteroids are at the input co-ords by calling isAsteroidAt()
			return false;
		}
		if(isSeekerAt(new_x, new_y) == true) { //checks if any seekers are at the input co-ords by calling isSeekerAt()
			return false;
		}
		return true;
	}
	
	/**
	 * function that moves all spaceships in the galaxy by calling tryToMove() on every spaceship in the spaceships list
	 */
	public void moveAllSpaceships() {
		for (int i = 0; i < spaceships.size(); i++) {
			spaceships.get(i).tryToMove(this); //tries to move the spaceship
		}
	}
	
	/**
	 * function that moves all seekers in the galaxy by calling track() and tryToMove() on every seeker in the seekers list
	 */
	public void moveAllSeekers() {
		for (int i = 0; i < seekers.size(); i++) {
			seekers.get(i).track(); //updates the seekers direction by calling track()
			seekers.get(i).tryToMove(this); //tries to move the seeker
		}
	}
	
	/**
	 * function that creates information about the galaxy and each item in it as a string
	 * @return output
	 */
	public String toString() {
		String output = "Drone Arena is size " + x + "*" + y + "\n"; //adds the size of the galaxy to the output string
		for(int i = 0; i < items.size(); i++) { 
			output = output + items.get(i).toString() + "\n"; //searches through all items in the items list and adds their toString() output to the output string
		}
		return output;
	}

	public static void main(String[] args) {
		Galaxy obj_1 = new Galaxy(20, 10);
		for (int i = 0; i < 10; i++) {
			obj_1.addSpaceship();
		}
		System.out.println(obj_1.toString());
	}

}
