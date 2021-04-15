package manese.ashley;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayGame extends Application{


	public void start(Stage primaryStage) throws Exception {

		GridHandler gridHandler = new GridHandler();
		Scene scene1 = new Scene(gridHandler, 300, 100);

		BorderPane rootPane = new BorderPane();	
		Pane pane = new Pane();



		gridHandler.getBtContinue().setOnAction(act -> {

			java.io.File file = new java.io.File("SavedGameData.txt");

			Scanner fileInput;
			try {
				GlobalFields.dataList = new ArrayList<>();

				fileInput = new Scanner(file);
				while(fileInput.hasNext()) {
					String rowItems = fileInput.nextLine();
					GlobalFields.dataList.add(rowItems);
					System.out.println(rowItems);
				}
				Button btHello = new Button("HELLO");
				Pane pane2 = new Pane();
				pane2.getChildren().add(btHello);


				for(int i = 0; i < GlobalFields.dataList.size(); i++) {
					String dataList = GlobalFields.dataList.get(i);
					if(dataList.contains("row")) {
						String rowStr = String.valueOf(dataList.charAt(4));
						GlobalFields.row = Integer.parseInt(rowStr);
						System.out.println(GlobalFields.row);
					}
					else if(dataList.contains("column")) {
						String columnStr = String.valueOf(dataList.charAt(7));
						GlobalFields.column = Integer.parseInt(columnStr);
						System.out.println(GlobalFields.row);
					}
				}

				Scene scene3 = new Scene(pane2);
				primaryStage.setScene(scene3);



				fileInput.close();

			} catch (FileNotFoundException ex) {

				ex.printStackTrace();
			}


		});


		gridHandler.getComboBox().setOnAction(e ->  {

			GridPane gridPane = new GridPane();
			gridPane = new GridPane();
			gridPane.setPadding(new Insets(15, 15, 15, 15));
			gridPane.setVgap(1);
			gridPane.setHgap(1);

			String input = gridHandler.getComboBox().getValue();
			String debug = gridHandler.getDebugDropDown().getValue();
			//			int numOfObstacels = Integer.parseInt(gridHandler.getNumOfObstacles().getText());
			//			System.out.println(numOfObstacels);

			if(input.equals("5 x 5")) {
				GlobalFields.column = 5;
				GlobalFields.row = 5;
			}else if(input.equals("7 x 7")){
				GlobalFields.column = 7;
				GlobalFields.row = 7;
			}else if(input.equals("10 x 7")) {
				GlobalFields.column = 10;
				GlobalFields.row = 7;
			}

			GlobalFields.grid = gridHandler.intialize(GlobalFields.player);



			for(int i = 0; i < GlobalFields.row; i++) {
				for(int j = 0; j < GlobalFields.column; j++) {
					Location l = GlobalFields.grid[i][j];
					if(debug.equals("yes")) {
						GlobalFields.gridBlock = new ItemHolder(l.toString());
						System.out.println(l.toString());
					}else {
						GlobalFields.gridBlock = new ItemHolder();
					}

					gridPane.add(GlobalFields.gridBlock, j, i);


				}
			}
			GlobalFields.player.checkAdjacent(GlobalFields.grid);
			GlobalFields.lb = new Label(GlobalFields.player.getMessage());
			GlobalFields.lb.setFont(new Font("Times", 30));


			//Pawns for kraken and player
			Image playerImg = new Image("file:pics/Star.GIF");
			Rectangle playerPawn = createPawn(GlobalFields.playerLocation,playerImg);

			Image krakenImg = new Image("file:pics/Kraken.GIF");
			Rectangle krakenPawn = createPawn(GlobalFields.krakenLocation, krakenImg);

			if(debug.equals("no")) {
				krakenPawn.setVisible(false);
			}else {
				krakenPawn.setVisible(true);

			}

			GlobalFields.btForPlayer = new DirectionButtons();
			movePlayer(GlobalFields.btForPlayer,playerPawn);

			GlobalFields.attack = new DirectionButtons();
			BorderPane paneForButtons = new BorderPane();
			Text text = new Text("Move Player \t\t\t Shoot");
			text.setFont(new Font("Times", 20));


			//save game
			Button btSave = new Button("Save"); 
			btSave.setOnAction(action -> {
				java.io.File file = new java.io.File("SavedGameData.txt");

				java.io.PrintWriter output;

				try {
					output = new java.io.PrintWriter(file);
					output.println("row:" + GlobalFields.row);
					output.println("column:" + GlobalFields.column);
					//output.println("Label:" + GlobalFields.lb.toString());


					//Location of items
					output.println("(" + GlobalFields.playerLocation.getPos()[1] + "," + GlobalFields.playerLocation.getPos()[0] + ") = PlayerLocation");
					output.println("(" + GlobalFields.krakenLocation.getPos()[1] + "," + GlobalFields.krakenLocation.getPos()[0] + ") = KrakenLocation");
					for(int i = 0; i < GlobalFields.row ; i++) {
						for(int j = 0; j < GlobalFields.column; j++) {
							if(GlobalFields.grid[i][j].getSmallCreature() != null) {
								Location snake = GlobalFields.grid[i][j];
								output.println("(" + snake.getPos()[1] + "," + snake.getPos()[0] + ") = snakeLoc");
							}
							if(GlobalFields.grid[i][j].getTrap() != null) {
								Location trap = GlobalFields.grid[i][j];
								output.println("(" + trap.getPos()[1] + "," + trap.getPos()[0] + ") = trapLoc");
							}if(GlobalFields.grid[i][j].getPowerUp() != null) {
								Location powerUp = GlobalFields.grid[i][j];
								output.println("(" + powerUp.getPos()[1] + "," + powerUp.getPos()[0] + ") = powerupLoc");
							}
						}

					}
					output.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			});






			paneForButtons.setStyle("-fx-background-color: darkseagreen");
			paneForButtons.setTop(text);
			paneForButtons.setLeft(GlobalFields.btForPlayer);
			paneForButtons.setCenter(GlobalFields.attack);
			paneForButtons.setRight(btSave);


			attack(GlobalFields.attack,krakenPawn);

			pane.getChildren().addAll(gridPane,playerPawn,krakenPawn);
			//pane.setStyle("-fx-background-color: ");
			//Color.
			gridPane.setPrefSize(150, 150);
			rootPane.setCenter(pane);
			rootPane.setBottom(paneForButtons);

			VBox vBox = new VBox();
			vBox.getChildren().add(GlobalFields.lb);
			rootPane.setRight(vBox);

		});




		Scene scene2 = new Scene(rootPane, 200, 200);
		primaryStage.setScene(scene1);
		primaryStage.setTitle("FINAL PROJECT");
		primaryStage.setMinWidth(1000);

		gridHandler.getBtStart().setOnAction(e -> primaryStage.setScene(scene2));
		primaryStage.show();
	}

	//	public void getRowAndColumn(int index, String rowOrColumn, int num) {
	//		if(GlobalFields.dataList.get(index).contains(rowOrColumn)) {
	//			String str = String.valueOf(GlobalFields.dataList.get(index).charAt(GlobalFields.dataList.size() - 1));
	//			num = Integer.parseInt(str);
	//			System.out.println(num);
	//		}
	//
	//	}


	public Rectangle createPawn(Location itemLocation, Image img) {
		int initX = 104 * itemLocation.getPos()[1] + 18;
		int initY = 104 * itemLocation.getPos()[0] + 18;

		Rectangle pawn = new Rectangle(initX, initY, 100,100);
		pawn.setFill(new ImagePattern(img));
		pawn.setStroke(Color.BLACK);
		pawn.setStrokeWidth(2);

		return pawn;
	}

	public static void movePlayer(DirectionButtons buttons, Rectangle playerPawn) {

		buttons.getBtUp().setOnAction(e ->{
			if(playerPawn.getY() > 18) {
				playerPawn.setY(playerPawn.getY() - 104);
			}
			Player.move("up",GlobalFields.playerLocation);
			GridHandler.printGrid(GlobalFields.row, GlobalFields.column, GlobalFields.grid);
			//System.out.println(GlobalFields.player.getMagicWand());

		});

		buttons.getBtDown().setOnAction(e ->{
			if(playerPawn.getY() < 18 + (GlobalFields.row - 1) * 104 ) {
				playerPawn.setY(playerPawn.getY() + 104);
			}

			Player.move("down",GlobalFields.playerLocation);
			GridHandler.printGrid(GlobalFields.row, GlobalFields.column, GlobalFields.grid);

		});

		buttons.getBtRight().setOnAction(e ->{
			if(playerPawn.getX() < 18 + (GlobalFields.column - 1) * 104) {
				playerPawn.setX(playerPawn.getX() + 104);
			}
			Player.move("right", GlobalFields.playerLocation);
			GridHandler.printGrid(GlobalFields.row, GlobalFields.column, GlobalFields.grid);


		});

		buttons.getBtLeft().setOnAction(e ->{
			if(playerPawn.getX() > 18) {
				playerPawn.setX(playerPawn.getX() - 104);
			}
			Player.move("left",GlobalFields.playerLocation);
			GridHandler.printGrid(GlobalFields.row, GlobalFields.column, GlobalFields.grid);

		});


	}

	public void moveKrakenRandomly(Rectangle krakenPawn) {
		krakenPawn.setX(18 + GlobalFields.krakenLocation.getPos()[1] * 104);
		krakenPawn.setY(18 + GlobalFields.krakenLocation.getPos()[0] * 104);
	}
	//actions when player shoots
	public void attack(DirectionButtons attackbts, Rectangle krakenPawn) {
		attackbts.getBtUp().setOnAction(e -> {
			GlobalFields.player.shoot("up",GlobalFields.playerLocation, GlobalFields.krakenLocation);
			moveKrakenRandomly(krakenPawn);
		});

		attackbts.getBtDown().setOnAction(e -> {
			GlobalFields.player.shoot("down",GlobalFields.playerLocation,GlobalFields.krakenLocation);
			moveKrakenRandomly(krakenPawn);
		});

		attackbts.getBtLeft().setOnAction(e -> {
			GlobalFields.player.shoot("left",GlobalFields.playerLocation, GlobalFields.krakenLocation);
			moveKrakenRandomly(krakenPawn);
		});

		attackbts.getBtRight().setOnAction(e -> {
			GlobalFields.player.shoot("right",GlobalFields.playerLocation, GlobalFields.krakenLocation);
			moveKrakenRandomly(krakenPawn);
		});
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}






