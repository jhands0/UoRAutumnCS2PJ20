package SpaceshipCoursework;
import java.util.random.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;


import javafx.scene.paint.Color;

public class Galaxy implements Serializable{
	
	private ArrayList<Spaceship> spaceships;
	private ArrayList<Asteroid> asteroids;
	private Random randomGenerator; 
	private int x;
	private int y;
	
	Galaxy(int x_size, int y_size) {
		spaceships = new ArrayList<Spaceship>();
		asteroids = new ArrayList<Asteroid>();
		randomGenerator = new Random();
		x = x_size;
		y = y_size;
	}
	
	public void addSpaceship() {
		int x_rand = randomGenerator.nextInt(x-1)+1;
		int y_rand = randomGenerator.nextInt(y-1)+1;
		int size_rand = randomGenerator.nextInt(10)+1;
		Direction dir_rand = Direction.getRandomDirection();
		while ((isSpaceshipAt(x_rand, y_rand) == true) || (isAsteroidAt(x_rand, y_rand) == true)) {
			x_rand = randomGenerator.nextInt(x-1)+1;
			y_rand = randomGenerator.nextInt(y-1)+1;
		}
		int asteroidOrSpaceship = randomGenerator.nextInt(10);
		if (asteroidOrSpaceship <= 2) {
			Asteroid obj = new Asteroid(x_rand, y_rand, size_rand);
			asteroids.add(obj);
		}
		else {
			Spaceship obj = new Spaceship(x_rand, y_rand, dir_rand, size_rand);
			spaceships.add(obj);
		}
		
	}

	public boolean isSpaceshipAt(int x_coord, int y_coord) {
		for (int i = 0; i < spaceships.size(); i++) {
			if ( (spaceships.get(i)).isHere(x_coord, y_coord) == true) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAsteroidAt(int x_coord, int y_coord) {
		for (int i = 0; i < asteroids.size(); i++) {
			if ( ( asteroids.get(i)).isHere(x_coord, y_coord) == true) {
				return true;
			}
		}
		return false;
	}
	
	public void showSpaceships(ConsoleCanvas c) {
		for (int i = 0; i < spaceships.size(); i++) {
			( spaceships.get(i)).displaySpaceship(c);
		}
	}
	
	public void showAsteroids(ConsoleCanvas c) {
		for (int i = 0; i < asteroids.size(); i++) {
			( asteroids.get(i)).displayAsteroid(c);
		}
	}
	
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
	}
	
	public void updateGalaxy(MyCanvas mc) {
		moveAllSpaceships();
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
		return true;
	}
	
	public void moveAllSpaceships() {
		for (int i = 0; i < spaceships.size(); i++) {
			( spaceships.get(i)).tryToMove(this);
		}
	}
	
	public String toString() {
		String output = "Drone Arena is size " + x + "*" + y + "\n";
		for (int i = 0; i < spaceships.size(); i++) {
			output = output + spaceships.get(i).toString() + "\n";
		}
		for (int i = 0; i < asteroids.size(); i++) {
			output = output + asteroids.get(i).toString() + "\n";
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
