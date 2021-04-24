package views;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GeneratorView {

	private BorderPane generatorRoot;
	private TextField lengthInput;
	private CheckBox includeNumber;
	private CheckBox includeSpecialChar;
	private Label inputLbl;
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

		// Adding Generate button, Return to Main Menu button, and Input label
		inputLbl = new Label("");
		generate = new Button("Generate Password");
		inputLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		generate.setFont(Font.font("Arial", 20));
		returnBack = new Button("Back to Main");
		returnBack.setFont(Font.font("Arial", 20));

		// Adding nodes to VBox and setting its alignment to center
		VBox centerVBox = new VBox();
		centerVBox.getChildren().addAll(logoTitleBox, textFields, generate, returnBack, inputLbl);
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
	public Parent getPaneRoot() {
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
		inputLbl.setText(pass);
		inputLbl.setTextFill(Color.BLACK);
	}

}
