package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import views.GeneratorView;
import views.LoginView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Initialize loginView and buttons for changing scenes
			LoginView loginView = new LoginView();
			BorderPane root = loginView.getPaneRoot();
			Button loginBtn = loginView.getLoginBtn();

			//Initialize GeneratorView and buttons for changing scenes
			GeneratorView generatorView = new GeneratorView();
			Button generatorReturnBtn = generatorView.getReturnBtn();
			
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
			
			// Handles clicking of the login button
			generatorReturnBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// If the user sends the correct login, switch roots.
					scene.setRoot(root);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
