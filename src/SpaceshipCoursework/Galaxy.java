package SpaceshipCoursework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


import javafx.scene.paint.Color;

public class Galaxy implements Serializable{
	
	private ArrayList<Spaceship> spaceships;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Seeker> seekers;
	private ArrayList<Object> items;
	private Random randomGenerator; 
	private int x;
	private int y;
	
	Galaxy(int x_size, int y_size) {
		spaceships = new ArrayList<Spaceship>();
		asteroids = new ArrayList<Asteroid>();
		seekers = new ArrayList<Seeker>();
		items = new ArrayList<Object>();
		randomGenerator = new Random();
		x = x_size;
		y = y_size;
	}
	
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

	public boolean isSpaceshipAt(int x_coord, int y_coord) {
		for (int i = 0; i < spaceships.size(); i++) {
			if (spaceships.get(i).isHere(x_coord, y_coord) == true) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAsteroidAt(int x_coord, int y_coord) {
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).isHere(x_coord, y_coord) == true) {
				return true;
			}
		}
		return false;
	}
	
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
	
	public void drawWorld(MyCanvas mc) {
		mc.clearCanvas();
		for (int i = 0; i < asteroids.size(); i++) {
			mc.setFillColour(Color.BROWN);
			asteroids.get(i).drawAsteroid(mc);
		}
		for (int j = 0; j < spaceships.size(); j++) {
			mc.setFillColour(Color.WHITE);
			spaceships.get(j).drawSpaceship(mc);
		}
		for (int k = 0; k < seekers.size(); k++) {
			mc.setFillColour(Color.GRAY);
			seekers.get(k).drawSeeker(mc);
		}
	}
	
	public void updateGalaxy(MyCanvas mc) {
		moveAllSpaceships();
		moveAllSeekers();
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Boolean canMoveHere(int new_x, int new_y) {
		if((new_x == 0) || (new_x == x) || (new_y == 0) || (new_y == y)) {
			return false;
		}
		if(isSpaceshipAt(new_x, new_y) == true) {
			return false;
		}
		if(isAsteroidAt(new_x, new_y) == true) {
			return false;
		}
		if(isSeekerAt(new_x, new_y) == true) {
			return false;
		}
		return true;
	}
	
	public void moveAllSpaceships() {
		for (int i = 0; i < spaceships.size(); i++) {
			spaceships.get(i).tryToMove(this);
		}
	}
	
	public void moveAllSeekers() {
		for (int i = 0; i < seekers.size(); i++) {
			seekers.get(i).track();
			seekers.get(i).tryToMove(this);
		}
	}
	
	public String toString() {
		String output = "Drone Arena is size " + x + "*" + y + "\n";
		for(int i = 0; i < items.size(); i++) {
			output = output + items.get(i).toString() + "\n";
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
