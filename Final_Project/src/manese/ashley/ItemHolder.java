package manese.ashley;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ItemHolder extends StackPane{

	private Text item;
	private String text;
	private Image img;
	private ImageView imageView;

	public ItemHolder() {
		
		super.getChildren().addAll(createSquare());	
	}

	public ItemHolder(String text) {
		this.text = text;
		if(this.text.equals("Snake ")) {
			this.img = new Image("file:pics/Ocean.GIF", 800, 800, true, true);
			this.imageView = new ImageView(this.img);
		}
//		else{
//			this.img = new Image("file:pics/white.GIF", 30, 30, true, true);
//			this.imageView = new ImageView(this.img);
//		}
//		this.img = new Image("file:pics/Ocean.GIF", 80, 80, true, true);
//		this.imageView = new ImageView(this.img);
		
		this.item = new Text(this.text);
		this.item.setFill(Color.BLACK);
		super.getChildren().addAll(createSquare(), this.item);	
	}

	public Text getItem() {
		return item;
	}

	public String getText() {
		return text;
	}

	public Image getImg() {
		return img;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public void setItem(Text item) {
		this.item = item;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static Rectangle createSquare() {
		Rectangle rec = new Rectangle(100,100);
		rec.setFill(null);
		rec.setStroke(Color.DARKSLATEBLUE);
		rec.setStrokeWidth(3);

		return rec;
	}
}




