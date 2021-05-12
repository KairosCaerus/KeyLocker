package lib;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NodeStyler {
	
	/**
	 * Creates a styled PasswordField
	 * 
	 * @return new PasswordField
	 */
	public static PasswordField createPasswordField() {		
		PasswordField password = new PasswordField();
		password.setPrefWidth(200);
		password.setPrefHeight(30);
		return password;
	}
	
	/**
	 * Creates a styled TextField
	 * 
	 * @return new TextField
	 */
	public static TextField createTextField() {		
		TextField text = new TextField();
		text.setPrefWidth(200);
		text.setPrefHeight(30);
		return text;
	}
	
	/**
	 * Creates a styled Label 
	 * 
	 * @param String text for label
	 * @return new Label
	 */
	public static Label createInputLabel(String lblValue) {
		Label lbl = new Label(lblValue);
		lbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
		return lbl;
	}
	
	/**
	 * Creates a styled "Key Locker" HBox
	 * 
	 * @return new HBox
	 */
	public static HBox createTitleBox() {
		HBox logoTitleBox = new HBox();
		Label title = new Label("Key Locker");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		logoTitleBox.getChildren().add(title);
		logoTitleBox.setAlignment(Pos.CENTER);
		return logoTitleBox;
	}
	
	/**
	 * Creates a styled Button
	 * 
	 * @param String text for Button
	 * @return new Button
	 */
	public static Button createButton(String btnValue) {
		Button btn = new Button(btnValue);
		btn.setFont(Font.font("Arial", 20));
		return btn;
	}
	
	public static TextArea createTextArea() {
		TextArea txt = new TextArea();
		txt.setText(" ");
		txt.setPrefWidth(350);
		txt.setPrefHeight(50);
		return txt;
	}
	
}
