package views;

import javafx.geometry.Pos;
import javafx.scene.Parent;
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


public class CreatorView implements ViewInterface{

	private BorderPane creatorRoot;
	private Button createAccount;
	private Button returnBack;
	private TextField accountNameInput;
	private TextField usernameInput;
	private PasswordField passwordInput;
	private Label inputLbl;

	public CreatorView() {
		creatorRoot = new BorderPane();

		// TODO: Add lock image also????
		// Creating title & logo box
		HBox logoTitleBox = new HBox();
		Label title = new Label("Create New Account");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		logoTitleBox.getChildren().add(title);
		logoTitleBox.setAlignment(Pos.CENTER);

		// Creating account name and input field
		Label accountName = new Label("Account Name: ");
		accountName.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		accountNameInput = new TextField();
		accountNameInput.setPrefWidth(120);
		accountNameInput.setPrefHeight(30);
		
		// Creating username and input field
		Label username = new Label("Username: ");
		username.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		usernameInput = new TextField();
		usernameInput.setPrefWidth(120);
		usernameInput.setPrefHeight(30);
		
		// Creating password and input field
		Label password = new Label("Password: ");
		password.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		passwordInput = new PasswordField();
		passwordInput.setPrefWidth(120);
		passwordInput.setPrefHeight(30);
		
		// Creating notes and text area
		Label notes = new Label("Notes: ");
		notes.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		TextArea notesInput = new TextArea();
		notesInput.setPrefWidth(350);
		notesInput.setPrefHeight(50);
		
		// Adding Input label
		inputLbl = new Label("");
		inputLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
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
		createAccount = new Button("Create Account");
		createAccount.setFont(Font.font("Arial", 20));
		returnBack = new Button("Back to Main");
		returnBack.setFont(Font.font("Arial", 20));
		
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
	public boolean fieldEmpty() {
		if(accountNameInput.getText().trim().isEmpty() || usernameInput.getText().trim().isEmpty() || passwordInput.getText().trim().isEmpty()) {
			inputLbl.setText("Missing fields");
			inputLbl.setTextFill(Color.RED);
			return false;
		}
		inputLbl.setText("");
		usernameInput.setText("");
		passwordInput.setText("");
		return true;
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