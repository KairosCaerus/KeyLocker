package views;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import lib.PasswordManager;

public class GeneratorView implements ViewInterface{

	private BorderPane generatorRoot;
	private TextField lengthInput;
	private CheckBox includeNumber;
	private CheckBox includeSpecialChar;
	private TextField generatedPassword;
	private Button generate;
	private Button returnBack;

	public GeneratorView() {
		generatorRoot = new BorderPane();

		// TODO: Add lock image also????
		// Creating title & logo box
		HBox logoTitleBox = new HBox();
		Label title = new Label("Password Generator");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		logoTitleBox.getChildren().add(title);
		logoTitleBox.setAlignment(Pos.CENTER);

		// Creating password label and text field
		Label passLength = new Label("Password Length: ");
		passLength.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		lengthInput = new TextField();
		lengthInput.setPrefWidth(40);
		lengthInput.setPrefHeight(30);
		
		// Creating number label and a check box
		Label addNum = new Label("Include Numbers 0-9: ");
		addNum.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		includeNumber = new CheckBox();
		includeNumber.setPrefWidth(30);
		includeNumber.setPrefHeight(30);

		// Creating special character label and a check box
		Label addChar = new Label("Include Special Characters: ");
		addChar.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		includeSpecialChar = new CheckBox();
		includeSpecialChar.setPrefWidth(30);
		includeSpecialChar.setPrefHeight(30);
		
		// Adding requirement nodes to grid
		GridPane textFields = new GridPane();
		textFields.add(passLength, 0, 0);
		textFields.add(lengthInput, 1, 0);
		textFields.add(addNum, 0, 1);
		textFields.add(includeNumber, 1, 1);
		textFields.add(addChar, 0, 2);
		textFields.add(includeSpecialChar, 1, 2);
		textFields.setVgap(5);
		textFields.setHgap(3);
		textFields.setAlignment(Pos.CENTER);

		// Adding Generate button and Return to Main Menu button
		generate = new Button("Generate Password");
		generate.setFont(Font.font("Arial", 20));
		returnBack = new Button("Back to Main");
		returnBack.setFont(Font.font("Arial", 20));
		
		// Adding non-editable TextField for the generated password
		generatedPassword = new TextField("");
		generatedPassword.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		generatedPassword.setEditable(false);
		generatedPassword.setDisable(false);
		generatedPassword.setVisible(false);
		generatedPassword.setMaxWidth(300);
		
		// Adding Copy to clip board button
		Button copyButton = new Button("Copy to Clipboard");
		copyButton.setFont(Font.font("Arial", 20));
		copyButton.setVisible(false);
		
		// Handles clicking of the generate button
		generate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// If the user sends the correct login, switch roots.
				int length;
				try {
					length = Integer.parseInt(lengthInput.getText());

				} catch(Exception e){
					length = 8;
				}
				String newPass = PasswordManager.generate(length, includeNumber.isSelected(), includeSpecialChar.isSelected());
				showPass(newPass);
				copyButton.setVisible(true);
			}
		});
		
		// Handles copying password to clip board
		copyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Clipboard clipboard = Clipboard.getSystemClipboard();
				ClipboardContent content = new ClipboardContent();
				content.putString(generatedPassword.getText());
				clipboard.setContent(content);
			}

		});

		// Adding nodes to VBox and setting its alignment to center
		VBox centerVBox = new VBox();
		centerVBox.getChildren().addAll(logoTitleBox, textFields, generate, returnBack, generatedPassword, copyButton);
		centerVBox.setAlignment(Pos.CENTER);
		centerVBox.setSpacing(10);

		// Adding nodes to root and setting background color
		generatorRoot.setCenter(centerVBox);
		generatorRoot.setStyle("-fx-background-color: cornflowerblue");
	}

	/**
	 * Return root of the class in the form of a BorderPane object
	 * 
	 * @return root of GeneratorView
	 */
	@Override
	public Pane getRootPane() {
		return generatorRoot;
	}

	/**
	 * Returns Button object for generate
	 * 
	 * @return generate
	 */
	public Button getGenerateBtn() {
		return generate;
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
	 * Returns TextField object for lengthInput which contains the length
	 * 
	 * @return lengthInput
	 */
	public TextField getPassLength() {
		return lengthInput;
	}

	/**
	 * Returns CheckBox object for includeNumber
	 * 
	 * @return includeNumber;
	 */
	public CheckBox getIncludeNumber() {
		return includeNumber;
	}
	
	/**
	 * Returns CheckBox object for includeSpecialChar
	 * 
	 * @return includeSpecialChar;
	 */
	public CheckBox getSpecialChar() {
		return includeSpecialChar;
	}

	/**
	 * Shows the password that was generated
	 * @param pass password from generation
	 */
	public void showPass(String pass) {
		generatedPassword.setText(pass);
		generatedPassword.setVisible(true);
	}

}
