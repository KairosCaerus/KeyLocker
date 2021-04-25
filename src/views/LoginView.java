package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView implements ViewInterface{
	
	private BorderPane root;
	private TextField usernameInput;
	private PasswordField passwordInput;
	private Label inputLbl;
	private Button loginBtn;

	public LoginView() {
		root = new BorderPane();
		
		// TODO: Add lock image 
		// Creating title & logo box
		HBox logoTitleBox = new HBox();
		Label title = new Label("Key Locker");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		logoTitleBox.getChildren().add(title);
		logoTitleBox.setAlignment(Pos.CENTER);
		
		// Creating username label and text field
		Label usernameLbl = new Label("Username: ");
		usernameLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
		
		usernameInput = new TextField();
		usernameInput.setPrefWidth(200);
		usernameInput.setPrefHeight(30);
		
		// Creating password label and password field
		Label passwordLbl = new Label("Password: ");
		passwordLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
		
		passwordInput = new PasswordField();
		passwordInput.setPrefWidth(200);
		passwordInput.setPrefHeight(30);
		
		// Adding login nodes to grid
		GridPane textFields = new GridPane();
		textFields.add(usernameLbl, 0, 0);
		textFields.add(usernameInput, 1, 0);
		textFields.add(passwordLbl, 0, 1);
		textFields.add(passwordInput, 1, 1);
		textFields.setVgap(5);
		textFields.setHgap(3);
		textFields.setAlignment(Pos.CENTER);
		
		// Adding Login button and Input label
		inputLbl = new Label("");
		loginBtn = new Button("Login");
		inputLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		loginBtn.setFont(Font.font("Arial", 20));
		

		
		// Adding nodes to VBox and setting its alignment to center
		VBox centerVBox = new VBox();
		centerVBox.getChildren().addAll(logoTitleBox, textFields, loginBtn, inputLbl);
		centerVBox.setAlignment(Pos.CENTER);
		centerVBox.setSpacing(10);

		
		// Adding nodes to root and setting background color
		root.setCenter(centerVBox);
		root.setStyle("-fx-background-color: cornflowerblue");
	}
	
	/**
	 * Return root of the class in the form of a BorderPane object 
	 * 
	 * @return root of LoginView
	 */
	@Override
	public Pane getRootPane() {
		return root;
	}
	
	/**
	 * Returns Button object for login
	 * 
	 * @return loginBtn
	 */
	public Button getLoginBtn() {
		return loginBtn;
	}
	
	/**
	 * 
	 * 
	 * @param username String passed username from user input
	 * @param password String passed password from user input
	 * @param inputLbl Label to be changed
	 * @return true of login info was successful
	 */
	public boolean checkLogin() {
		if(!usernameInput.getText().equals("username") || !passwordInput.getText().equals("password")) {
			inputLbl.setText("Username or Password is incorrect");
			inputLbl.setTextFill(Color.RED);
			return false;
		}
		inputLbl.setText("");
		usernameInput.setText("");
		passwordInput.setText("");
		return true;
		
	}
}
