package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import lib.Generation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import views.GeneratorView;
import views.LoginView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Initialize loginView and buttons for event handling
			LoginView loginView = new LoginView();
			GeneratorView generatorView = new GeneratorView();
			BorderPane root = loginView.getPaneRoot();
			Button loginBtn = loginView.getLoginBtn();
			Button generate = generatorView.getGenerateBtn();
			// TODO change this to the account page.
			BorderPane empty = new BorderPane();

			// Setting size of window
			Scene scene = new Scene(root, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Key Locker");
			primaryStage.show();

			// Handles pressing enter to login
			root.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					// If the user sends the correct login, switch roots.
					if (event.getCode() == KeyCode.ENTER && loginView.checkLogin()) {
						scene.setRoot(generatorView.getPaneRoot());
					}
				}
			});

			// Handles clicking of the login button
			loginBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// If the user sends the correct login, switch roots.
					if (loginView.checkLogin()) {
						scene.setRoot(generatorView.getPaneRoot());
					}
				}
			});

			// Handles clicking of the generate button
			generate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// If the user sends the correct login, switch roots.
					Generation newGenerate = new Generation();
					String newPass = newGenerate.generate(generatorView);
					generatorView.showPass(newPass);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkLogin(String username, String password, Label inputLbl) {
		if (!username.equals("username") || !password.equals("password")) {
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
