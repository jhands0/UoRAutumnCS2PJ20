package SpaceshipCoursework;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GalaxyInterface extends Application{
	private AnimationTimer timer;					//timer for the spaceships moving animation
	private MyCanvas mc;
	private VBox rtPane;								// scanner used for input from user
    private Galaxy myGalaxy;				// galaxy in which spaceships are shown
    /**
    	 * constructor for GalaxyInterface
    	 * 
     */
    public GalaxyInterface() {
    	 String x_input = JOptionPane.showInputDialog(null, "Input the width of the arena: ");
    	 String y_input = JOptionPane.showInputDialog(null, "Input the height of the arena: ");
    	 int x = Integer.valueOf(x_input);
    	 int y = Integer.valueOf(y_input);
    	 myGalaxy = new Galaxy(x, y);	// create galaxy of size 20*6
    	 									// close scanner
    }
    
    //public void doDisplay() {
    	//int x = myGalaxy.getX();
    	//int y = myGalaxy.getY();
    	//ConsoleCanvas c = new ConsoleCanvas(x,y);
    	//myGalaxy.showSpaceships(c);
    	//myGalaxy.showAsteroids(c);
    	//System.out.println(c.toString());
    //}
    
    private void showAbout() {
	    Alert alert = new Alert(AlertType.INFORMATION);				// define what box is
	    alert.setTitle("About");									// say is About
	    alert.setHeaderText(null);
	    alert.setContentText("30006150 Java Coursework");			// give text
	    alert.showAndWait();										// show box and wait for user to close
	}
    
    /**
	  * set up the mouse event - when mouse pressed, put ball there
	  * @param canvas
	  */
	void setMouseEvents (Canvas canvas) {
	       canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 		// for MOUSE PRESSED event
	    	       new EventHandler<MouseEvent>() {
	    	           @Override
	    	           public void handle(MouseEvent e) {
	    	        	   myGalaxy.addSpaceship();
	    	        	   myGalaxy.drawWorld(mc);							// redraw world
	    	        	   drawStatus();
	    	           }
	    	       });
	}
	
	/**
	 * set up the menu of commands for the GUI
	 * @return the menu bar
	 */
	MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();						// create main menu
	
		Menu mFile = new Menu("File");							// add File main menu
		MenuItem mExit = new MenuItem("Exit");					// whose sub menu has Exit
		mExit.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {					// action on exit is
	        	timer.stop();									// stop timer
		        System.exit(0);									// exit program
		    }
		});
		mFile.getItems().addAll(mExit);							// add exit to File menu
		
		Menu mHelp = new Menu("Help");							// create Help menu
		MenuItem mAbout = new MenuItem("About");				// add About sub men item
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	showAbout();									// and its action to print about
            }	
		});
		mHelp.getItems().addAll(mAbout);						// add About to Help main item
		
		menuBar.getMenus().addAll(mFile, mHelp);				// set main menu with File, Help
		return menuBar;											// return the menu
	}
	
	/**
	 * set up the horizontal box for the bottom with relevant buttons
	 * @return
	 */
	private HBox setButtons() {
	    Button btnStart = new Button("Start");					// create button for starting
	    btnStart.setOnAction(new EventHandler<ActionEvent>() {	// now define event when it is pressed
	        @Override
	        public void handle(ActionEvent event) {
	        	timer.start();									// its action is to start the timer
	       }
	    });

	    Button btnStop = new Button("Pause");					// now button for stop
	    btnStop.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	           	timer.stop();									// and its action to stop the timer
	       }
	    });
	    
	    Button btnSave = new Button("Save");
	    btnSave.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event) {
	    		String filename = JOptionPane.showInputDialog(null, "Input the name of the file you would like to save the galaxy too: ");
	    		saveGalaxy(filename);
	    	}
	    });
	    
	    Button btnLoad = new Button("Load");
	    btnSave.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event) {
	    		String filename = JOptionPane.showInputDialog(null, "Input the name of the file you would like to load the galaxy from: ");
	    		loadGalaxy(filename);
	    		Canvas canvas = new Canvas( myGalaxy.getX(), myGalaxy.getY() );
	    		Group root = new Group();
	    	    root.getChildren().add( canvas );
	    	    mc = new MyCanvas(canvas.getGraphicsContext2D(), myGalaxy.getX(), myGalaxy.getY());
	    	    myGalaxy.drawWorld(mc);
	    	}
	    });

	    														// now add these buttons + labels to a HBox
	    return new HBox(new Label("Run: "), btnStart, btnStop, btnSave, btnLoad);
	}
	
	/**
	 * show where ball is, in pane on right
	 */
	public void drawStatus() {
		rtPane.getChildren().clear();	// clear rtpane
		rtPane.getChildren().add(new Label(myGalaxy.toString()));
	}
    
    public void saveGalaxy(String fileName) {
    	String filepath = "D:\\source\\SpaceshipCoursework\\saves\\" + fileName + ".txt";
    	try {
    		FileOutputStream fileOut = new FileOutputStream(filepath);
    		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
    		objOut.writeObject(myGalaxy);
    		JOptionPane.showMessageDialog(null, "Saved galaxy to " + filepath);
    		objOut.close();
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	
    }
    
    public Object loadGalaxy(String fileName) {
    	String filepath = "D:\\source\\SpaceshipCoursework\\saves\\" + fileName + ".txt";
    	try {
    		FileInputStream fileIn = new FileInputStream(filepath);
    		ObjectInputStream objIn = new ObjectInputStream(fileIn);
    		Object obj = objIn.readObject();
    		JOptionPane.showMessageDialog(null, "Loaded galaxy from " + filepath);
    		objIn.close();
    		return obj;
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
		return null;
    }
    
    /**
	 * main function ... sets up canvas, menu, buttons and timer
	 */
	@Override
	public void start(Stage stagePrimary) throws Exception {
		stagePrimary.setTitle("30006150's Spaceship Coursework");
	    BorderPane bp = new BorderPane();
	    bp.setPadding(new Insets(10, 20, 10, 20));

	    bp.setTop(setMenu());											// put menu at the top

	    Group root = new Group();										// create group with canvas
	    Canvas canvas = new Canvas( myGalaxy.getX(), myGalaxy.getY() );
	    root.getChildren().add( canvas );
	    bp.setLeft(root);												// load canvas to left area

	    mc = new MyCanvas(canvas.getGraphicsContext2D(), myGalaxy.getX(), myGalaxy.getY());

	    GraphicsContext gc = canvas.getGraphicsContext2D();								// context for drawing
	    gc.setFill(Color.BLUEVIOLET);
	    gc.fillRect(0, 0, myGalaxy.getX(), myGalaxy.getY());
	    
	    
	    rtPane = new VBox();											// set vBox on right to list items
		rtPane.setAlignment(Pos.TOP_LEFT);								// set alignment
		rtPane.setPadding(new Insets(2, 5, 5, 2));						// padding
 		bp.setRight(rtPane);											// add rtPane to borderpane right
		  
	    bp.setBottom(setButtons());										// set bottom pane with buttons
	    
	    setMouseEvents(canvas);											// set up mouse events
	    myGalaxy.drawWorld(mc);													// draw world with ball there
	    
	    timer = new AnimationTimer() {									// set up timer
	        public void handle(long currentNanoTime) {					// and its action when on
	        		myGalaxy.moveAllSpaceships();
		            myGalaxy.drawWorld(mc);										// redraw the world
		            drawStatus();										// indicate where ball is
	        }
	    };

	    Scene scene = new Scene(bp, 600, 600);							// set overall scene
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());
        
        stagePrimary.setScene(scene);
        stagePrimary.show();
	  
	}
    
	public static void main(String[] args) {
		Application.launch(args);			//launches the GUI
	}

}