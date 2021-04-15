package manese.ashley;

import java.util.Random;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

public class GridHandler extends FlowPane {
	private Button btStart;
	private ComboBox<String> comboBox;
	private ComboBox<String> debugDropDown;
	private Button btContinue;
	private int row ; 
	private int column;
	private Location[][] grid;
	//private TextField numOfObstacles;
	
	

	public GridHandler() {
		this.btStart = new Button("Start");
		this.btStart.setFont(new Font("Times,", 15));
		this.btStart.setStyle("-fx-background-color: black; -fx-border-color: white;-fx-text-base-color: white;");;

		this.btContinue = new Button("Continue");
		this.btContinue.setFont(new Font("Times,", 15));
		this.btContinue.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-text-base-color: white;");;
		
		
		this.debugDropDown = new ComboBox<>();
		this.debugDropDown.setPromptText("Run on debug mode?");
		this.debugDropDown.setPadding(new Insets(5,5,5,5));
		this.debugDropDown.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-text-base-color: white;");;
		this.debugDropDown.getItems().addAll("yes", "no");
		
		//sthis.numOfObstacles = new TextField();
		//this.numOfObstacles.setPromptText("Enter number of obstacles");

		this.comboBox = new ComboBox<>();
		this.comboBox.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-text-base-color: white;");
		this.comboBox.getItems().addAll("5 x 5", "7 x 7", "10 x 7");
		this.comboBox.setPadding(new Insets(5,5,5,5));
		this.comboBox.setPromptText("Choose size of game");

		super.setPadding(new Insets(10,10,10,10));
		super.setHgap(15);
		super.setStyle("-fx-background-color: black");
		super.getChildren().addAll(this.debugDropDown ,this.comboBox,this.btStart, this.btContinue);
	}
	
//
//	public TextField getNumOfObstacles() {
//		return numOfObstacles;
//	}
//
//	public void setNumOfObstacles(TextField numOfObstacles) {
//		this.numOfObstacles = numOfObstacles;
//	}

	public Button getBtContinue() {
		return btContinue;
	}

	public void setBtContinue(Button btContinue) {
		this.btContinue = btContinue;
	}
	public Button getBtStart() {
		return btStart;
	}

	public ComboBox<String> getComboBox() {
		return comboBox;
	}

	public ComboBox<String> getDebugDropDown() {
		return debugDropDown;
	}

	public void setDebugDropDown(ComboBox<String> debugDropDown) {
		this.debugDropDown = debugDropDown;
	}

	public void setBtStart(Button btStart) {
		this.btStart = btStart;
	}

	public void setComboBox(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public Location[][] intialize(Player player){
		this.row = GlobalFields.row;
		this.column = GlobalFields.column;
		this.grid = new Location[this.row][this.column];


		for(int i = 0; i < this.row; i++) {
			for(int j = 0; j < this.column; j++) {
				Location location = new Location(i,j);
				grid[i][j] = location;

			}
		}

		if(this.comboBox.getValue().equals("5 x 5")) {
			for(int i = 0; i < 3; i++) {
				itemsOnGrid();
			}

		}
		else if(this.comboBox.getValue().equals("7 x 7")) {
			for(int i = 0; i < 5; i++) {
				itemsOnGrid();
			}
		}
		else if(this.comboBox.getValue().equals("10 x 7")) {
			for(int i = 0; i < 8; i++) {
				itemsOnGrid();
			}
		}

		placeItemOnGrid(new MythicalCreature());
		placeItemOnGrid(player);

		printGrid(this.row, this.column, this.grid);

		return grid;
	}

	public void itemsOnGrid() {
		placeItemOnGrid(new SmallCreature());
		placeItemOnGrid(new Trap());
		placeItemOnGrid(new PowerUp());
	}

	public void placeItemOnGrid(Item item) {

		boolean isResolved = false;

		while(!isResolved) {
			Location l = retrieveLocation();
			if(item instanceof MythicalCreature) {
				MythicalCreature mythicalCreature = (MythicalCreature)item;
				l.setMythicalCreature((MythicalCreature)item);
				GlobalFields.krakenLocation = l;
				mythicalCreature.setKrakenLocation(l);
				isResolved = true;
			}
			else if(item instanceof Trap) {
				Trap trap = (Trap)item;

				if(trap.validate(l)) {
					l.setTrap((Trap)item);
					isResolved = true;
				}	
			}
			else if(item instanceof SmallCreature) {
				SmallCreature creature = (SmallCreature)item;
				if(creature.validate(l)) {
					l.setSmallCreature((SmallCreature)item);
					isResolved = true;
				}

			}
			else if(item instanceof PowerUp) {
				PowerUp powerUp = (PowerUp)item;

				if(powerUp.validate(l)) {
					l.setPowerUp((PowerUp)item);
					isResolved = true;
				}
			}
			else if(item instanceof Player) {
				Player p = (Player)item;
				if(p.validate(l)){
					l.setPlayer((Player)item);
					GlobalFields.playerLocation = l;
					p.setPlayerLocation(l);
					isResolved = true;

				}	
			}
		}


	}

	public static void printGrid(int row, int column, Location[][] grid) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
			//	System.out.print(grid[i][j] + "\t\t " );	
			}

			//System.out.println("\n");
		}
	}
	private Location retrieveLocation() {
		Random r = new Random();
		int x = r.nextInt(this.row);
		int y = r.nextInt(this.column);
		return this.grid[x][y];

	}


}
