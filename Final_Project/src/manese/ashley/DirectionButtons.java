package manese.ashley;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DirectionButtons extends GridPane{

	private Button btUp;
	private Button btDown;
	private Button btRight;
	private Button btLeft;
	private Text text;
	
	
	
	

	public DirectionButtons() {
		
		this.btUp = new Button("   Up   ");
		this.btDown = new Button("Down");
		this.btRight = new Button("Right");
		this.btLeft = new Button("Left");
		
		//this.text = new Text("Move player");
		super.setPadding(new Insets(30, 30, 30, 30));
		super.setStyle("-fx-background-color: darkseagreen");
		super.setPrefHeight(100);
		//super.add(text, 0, 10);
		super.add(btUp, 2, 2);
		super.add(btDown, 2, 4);
		super.add(btLeft, 0, 3);
		super.add(btRight,5 , 3);
	}
		
	public Button getBtUp() {
		return btUp;
	}


	public Button getBtDown() {
		return btDown;
	}


	public Button getBtRight() {
		return btRight;
	}


	public Button getBtLeft() {
		return btLeft;
	}


	public void setBtNorth(Button btNorth) {
		this.btUp = btNorth;
	}


	public void setBtSouth(Button btSouth) {
		this.btDown = btSouth;
	}


	public void setBtEast(Button btEast) {
		this.btRight = btEast;
	}


	public void setBtWest(Button btWest) {
		this.btLeft = btWest;
	}
	
	
	
	
	

}
