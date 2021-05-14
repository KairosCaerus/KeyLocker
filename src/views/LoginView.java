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
import lib.NodeStyler;

public class LoginView implements ViewInterface{
	
	private BorderPane root;
	private TextField usernameInput;
	private PasswordField passwordInput;
	private Label inputLbl;
	private Button loginBtn;
	private Button signUpBtn;

	public LoginView() {
		root = new BorderPane();
		
		// TODO: Add lock image 
		// Creating title & logo box
		HBox logoTitleBox = NodeStyler.createTitleBox();
		
		// Creating username label and text field
		Label usernameLbl = NodeStyler.createInputLabel("Username: ");
		usernameInput = NodeStyler.createTextField();
		
		// Creating password label and password field
		Label passwordLbl = NodeStyler.createInputLabel("Password: ");
		passwordInput = NodeStyler.createPasswordField();
		
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
		inputLbl = NodeStyler.createInputLabel("");
		loginBtn = NodeStyler.createButton("Login");
		signUpBtn = NodeStyler.createButton("Sign Up");
		
		// Adding nodes to VBox and setting its alignment to center
		VBox centerVBox = new VBox();
		centerVBox.getChildren().addAll(logoTitleBox, textFields, loginBtn, inputLbl, signUpBtn);
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
	 * Returns Button object for Sign Up
	 * 
	 * @return signUpBtn
	 */
	public Button getSignUpBtn() {
		return signUpBtn;
	}
	
	/**
	 * Returns String input for username
	 * 
	 * @return usernameInput as a String
	 */
	public String getUsernameInput() {
		return usernameInput.getText();
	}
	
	/**
	 * Returns String input for password
	 * 
	 * @return passwordInput as a String
	 */
	public String getPasswordInput() {
		return passwordInput.getText();
	}
	
	/**
	 * Removes input and failed login text
	 */
	public void resetFields() {
		inputLbl.setText("");
		usernameInput.setText("");
		passwordInput.setText("");
	}
	
	/**
	 * Shows failed login text
	 */
	public void failedLogin(String message) {
		inputLbl.setText(message);
		inputLbl.setTextFill(Color.RED);
		inputLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	}
}
