package application;

import javafx.application.Application;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import views.LoginView;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			LoginView loginView = new LoginView();
			BorderPane root = loginView.getPaneRoot();
			Button loginBtn = loginView.getLoginBtn();
			
			BorderPane empty = new BorderPane();
			

			
			// Setting size of window
			Scene scene = new Scene(root,800,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Key Locker");
			primaryStage.show();
			
			// Handles pressing enter to login
			root.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent> () {
				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.ENTER && loginView.checkLogin()) {
						scene.setRoot(empty);
					}
				}
			});
			
			// Handles clicking of the login button
			loginBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(loginView.checkLogin()) {
						scene.setRoot(empty);
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

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
