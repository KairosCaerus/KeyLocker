package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class EditorView {

	private BorderPane EditorRoot;
	private Button finishEdit;
	private Button returnBack;
	private Button deleteAccount;
	private TextField accountNameInput;
	private TextField usernameInput;
	private TextField passwordInput;
	private TextArea notesInput;
	private Label inputLbl;

	public EditorView() {
		EditorRoot = new BorderPane();

		// Creating title & logo box
		HBox logoTitleBox = new HBox();
		Label title = new Label("Edit Existing Account");
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
		passwordInput = NodeStyler.createTextField();

		// Creating notes and text area
		Label notes = NodeStyler.createInputLabel("Notes: ");
		notesInput = NodeStyler.createTextArea();

		// Adding Input label
		inputLbl = NodeStyler.createInputLabel("");
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
		finishEdit = NodeStyler.createButton("Finish Editing Account");
		returnBack = NodeStyler.createButton("Back to Main");
		deleteAccount = NodeStyler.createButton("Delete Account");

		// Adding nodes to VBox and setting its alignment to center
		VBox centerVBox = new VBox();
		centerVBox.getChildren().addAll(logoTitleBox, textFields, finishEdit, deleteAccount, returnBack, inputLbl);
		centerVBox.setAlignment(Pos.CENTER);
		centerVBox.setSpacing(10);

		// Adding nodes to root and setting background color
		EditorRoot.setCenter(centerVBox);
		EditorRoot.setStyle("-fx-background-color: cornflowerblue");
	}

	/**
	 * Checks if account fields are empty
	 * 
	 * @return true if login info was successful
	 */
	public boolean fieldNotEmpty() {
		if (accountNameInput.getText().trim().isEmpty() || usernameInput.getText().trim().isEmpty()
				|| passwordInput.getText().trim().isEmpty()) {
			inputLbl.setText("Missing fields");
			inputLbl.setTextFill(Color.RED);
			return false;
		}
		return true;
	}

	/**
	 * Returns String input for account name
	 * 
	 * @return accountNameInput
	 */
	public String getAccountName() {
		return accountNameInput.getText();
	}

	/**
	 * Returns String input for username
	 * 
	 * @return usernameInput
	 */
	public String getUsername() {
		return usernameInput.getText();
	}

	/**
	 * Returns String input for password
	 * 
	 * @return passwordInput
	 */
	public String getPassword() {
		return passwordInput.getText();
	}

	/**
	 * Returns String input for notes
	 * 
	 * @return notesInput
	 */
	public String getNotes() {
		return notesInput.getText();
	}

	/**
	 * Resets all input fields
	 */
	public void resetFields() {
		inputLbl.setText("");
		accountNameInput.setText("");
		usernameInput.setText("");
		passwordInput.setText("");
		notesInput.setText("");
	}
	
	/**
	 * Returns String input for account name
	 */
	public void setExistingField(String service, String username, String pswd, String notes) {
		inputLbl.setText("");
		accountNameInput.setText(service);
		usernameInput.setText(username);
		passwordInput.setText(pswd);
		notesInput.setText(notes);
	}

	/**
	 * Return root of the class in the form of a Pane object
	 * 
	 * @return root of EditorView
	 */
	public Pane getRootPane() {
		return EditorRoot;
	}

	/**
	 * Returns Button object for Finish
	 * 
	 * @return Finish
	 */
	public Button getFinishBtn() {
		return finishEdit;
	}

	/**
	 * Returns Button object for returnBack
	 * 
	 * @return returnBack
	 */
	public Button getReturnBtn() {
		return returnBack;
	}
	
	/**
	 * Returns Button object for deleteAccount
	 * 
	 * @return deleteAccount
	 */
	public Button getDeleteBtn() {
		return deleteAccount;
	}

}
