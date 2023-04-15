package SpaceshipCoursework;

public class ConsoleCanvas {
	
	private String[][] canvas;
	private int x_size;
	private int y_size;
	
	ConsoleCanvas(int x, int y) {
		canvas = new String[y+2][x+2];
		x_size = x + 2;
		y_size = y + 2;
		for(int i = 0; i < y_size; i++) {
			for(int j = 0; j < x_size; j++) {
				if((i == y+1) || (i == 0) || (j == x+1) || (j == 0)) {
					canvas[i][j] = "#";
				}
			}
		}
	}
	
	public void showIt(int x, int y, int icon) {
		for(int i = 1; i < y_size; i++) {
			for(int j = 1; j < x_size; j++) {
				if((i == y) && (j == x)) {
					String letter = String.valueOf(icon);
					canvas[i][j] = letter;
				}
			}
		}
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i < y_size; i++) {
			for(int j = 0; j < x_size; j++) {
				if((canvas[i][j] != null) && (canvas[i][j].length() == 1)) {
					output = output + canvas[i][j] + " ";
				}
				else if((canvas[i][j] != null) && (canvas[i][j].length() == 2)) {
					output = output + canvas[i][j];
				}
				else {
					output = output + "  ";
				}
			}
			output = output + "\n";
		}
		return output;
	}
	
	public static void main(String[] args) {
		ConsoleCanvas c = new ConsoleCanvas(20,6);
		c.showIt(10,3,0);
		System.out.println(c.toString());
	}
}