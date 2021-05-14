package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
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


public class CreatorView implements ViewInterface{

	private BorderPane creatorRoot;
	private Button createAccount;
	private Button returnBack;
	private TextField accountNameInput;
	private TextField usernameInput;
	private PasswordField passwordInput;
	private TextArea notesInput;
	private Label inputLbl;

	public CreatorView() {
		creatorRoot = new BorderPane();

		// TODO: Add lock image also????
		// Creating title & logo box
		HBox logoTitleBox = new HBox();
		Label title = new Label("Add New Account");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		logoTitleBox.getChildren().add(title);
		logoTitleBox.setAlignment(Pos.CENTER);

		// Creating account name and input field
		Label accountName = NodeStyler.createInputLabel("Service: ");
		accountNameInput = NodeStyler.createTextField();
		
		// Creating username and input field
		Label username = NodeStyler.createInputLabel("Username: ");
		usernameInput = NodeStyler.createTextField();

		// Creating password and input field
		Label password = NodeStyler.createInputLabel("Password: ");
		passwordInput = NodeStyler.createPasswordField();
		
		// Creating notes and text area
		Label notes = NodeStyler.createInputLabel("Notes: ");
		notesInput = NodeStyler.createTextArea();
		
		// Adding Input label
		inputLbl = NodeStyler.createInputLabel("");
		
		// Adding requirement nodes to grid
		GridPane textFields = new GridPane();
		textFields.add(accountName, 0, 0);
		textFields.add(accountNameInput, 1, 0);
		textFields.add(username, 0, 1);
		textFields.add(usernameInput, 1, 1);
		textFields.add(password, 0, 2);
		textFields.add(passwordInput, 1, 2);
		textFields.add(notes, 0, 3);
		textFields.add(notesInput, 0, 4, 2, 2);
		textFields.setVgap(5);
		textFields.setHgap(3);
		textFields.setAlignment(Pos.CENTER);

		// Adding Create Account button and Return to Main Menu button
		createAccount = NodeStyler.createButton("Add Account");
		returnBack = NodeStyler.createButton("Back to Main");
		
		// Adding nodes to VBox and setting its alignment to center
		VBox centerVBox = new VBox();
		centerVBox.getChildren().addAll(logoTitleBox, textFields, createAccount, returnBack, inputLbl);
		centerVBox.setAlignment(Pos.CENTER);
		centerVBox.setSpacing(10);

		// Adding nodes to root and setting background color
		creatorRoot.setCenter(centerVBox);
		creatorRoot.setStyle("-fx-background-color: cornflowerblue");
	}
	
	/**
	 * Checks if account fields are empty
	 * 
	 * @return true if login info was successful
	 */
	public boolean fieldNotEmpty() {
		if(accountNameInput.getText().trim().isEmpty() || usernameInput.getText().trim().isEmpty() || passwordInput.getText().trim().isEmpty()) {
			inputLbl.setText("Missing fields");
			inputLbl.setTextFill(Color.RED);
			inputLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			return false;
		}
		return true;
	}
	
	public String getAccountName() {
		return accountNameInput.getText();
	}
	
	public String getUsername() {
		return usernameInput.getText();
	}
	public String getPassword() {
		return passwordInput.getText();
	}
	public String getNotes() {
		return notesInput.getText();
	}
	
	public void resetFields() {
		inputLbl.setText("");
		accountNameInput.setText("");
		usernameInput.setText("");
		passwordInput.setText("");
		notesInput.setText(" ");
	}
	
	/**
	 * Return root of the class in the form of a BorderPane object
	 * 
	 * @return root of CreatorView
	 */
	public Pane getRootPane() {
		return creatorRoot;
	}

	/**
	 * Returns Button object for create
	 * 
	 * @return generate
	 */
	public Button getCreateBtn() {
		return createAccount;
	}

	/**
	 * Returns Button object for returnBack
	 * 
	 * @return returnBack
	 */
	public Button getReturnBtn() {
		return returnBack;
	}
}