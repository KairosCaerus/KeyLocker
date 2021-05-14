package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.NodeStyler;

public class CreateUserView implements ViewInterface{
	public BorderPane root;
	private TextField usernameInput;
	private PasswordField passwordInput;
	private PasswordField confirmPasswordInput;
	private Label inputLbl;
	private Button loginBtn;
	private Button createUserBtn;
	
	public CreateUserView(){
		root = new BorderPane();
		
		// Creating username label and text field
		Label usernameLbl = NodeStyler.createInputLabel("Username: ");
		usernameInput = NodeStyler.createTextField();
		
		// Creating password & confirmPassword label and field
		Label passwordLbl = NodeStyler.createInputLabel("Password: ");
		passwordInput = NodeStyler.createPasswordField();
		Label confirmPasswordLbl = NodeStyler.createInputLabel("Confirm Password: ");
		confirmPasswordInput = NodeStyler.createPasswordField();
		
		// Adding login nodes to grid
		GridPane textFields = new GridPane();
		textFields.add(usernameLbl, 0, 0);
		textFields.add(usernameInput, 1, 0);
		textFields.add(passwordLbl, 0, 1);
		textFields.add(passwordInput, 1, 1);
		textFields.add(confirmPasswordLbl, 0, 2);
		textFields.add(confirmPasswordInput, 1, 2);
		textFields.setVgap(5);
		textFields.setHgap(3);
		textFields.setAlignment(Pos.CENTER);
		
		// Adding Login button and Input label
		inputLbl = NodeStyler.createInputLabel("");
		inputLbl.setTextFill(Color.RED);
		createUserBtn = NodeStyler.createButton("Create User");
		loginBtn = NodeStyler.createButton("Back to Login");
		
		// Adding nodes to VBox and setting its alignment to center
		VBox centerVBox = new VBox();
		centerVBox.getChildren().addAll(NodeStyler.createTitleBox(), textFields, createUserBtn, inputLbl, loginBtn);
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
	public Button getCreateUserBtn() {
		return createUserBtn;
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
	 * Returns String input for Confirm Password
	 * 
	 * @return confirmPasswordInput as a String
	 */
	public String getConfirmPasswordInput() {
		return confirmPasswordInput.getText();
	}
	
	/**
	 * Prints that the Create User failed
	 */
	public void failedCreateUser(String message) {
		inputLbl.setText(message);
		inputLbl.setTextFill(Color.RED);
		inputLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	}
	
	/**
	 * Empty username and password inputs
	 */
	public void resetFields() {
		inputLbl.setText("");
		usernameInput.setText("");
		passwordInput.setText("");
		confirmPasswordInput.setText("");
	}
}
