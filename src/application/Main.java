package application;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
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
			
			TextField usernameInput = new TextField();
			usernameInput.setPrefWidth(200);
			usernameInput.setPrefHeight(30);
			
			// Creating password label and password field
			Label passwordLbl = new Label("Password: ");
			passwordLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			PasswordField passwordInput = new PasswordField();
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
			Label inputLbl = new Label("");
			Button loginBtn = new Button("Login");
			inputLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			loginBtn.setFont(Font.font("Arial", 20));
			
			// Handles clicking of the login button
			loginBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					checkLogin(usernameInput.getText(), passwordInput.getText(), inputLbl);
				}
			});
			
			// Adding nodes to VBox and setting its alignment to center
			VBox centerVBox = new VBox();
			centerVBox.getChildren().addAll(logoTitleBox, textFields, loginBtn, inputLbl);
			centerVBox.setAlignment(Pos.CENTER);
			centerVBox.setSpacing(10);

			
			// Adding nodes to root and setting background color
			root.setCenter(centerVBox);
			root.setStyle("-fx-background-color: cornflowerblue");
			
			// Handles pressing enter to login
			root.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent> () {
				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.ENTER) {
						checkLogin(usernameInput.getText(), passwordInput.getText(), inputLbl);
					}

				}
			});
			
			// Setting size of window
			Scene scene = new Scene(root,800,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Key Locker");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param username String passed username from user input
	 * @param password String passed password from user input
	 * @param inputLbl Label to be changed
	 * @return true of login info was successful
	 */
	public boolean checkLogin(String username, String password, Label inputLbl) {
		if(!username.equals("username") || !password.equals("password")) {
			inputLbl.setText("Username or Password is incorrect");
			inputLbl.setTextFill(Color.RED);
			return false;
		}
		inputLbl.setText("Login Successful");
		inputLbl.setTextFill(Color.LIGHTGREEN);
		return true;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
