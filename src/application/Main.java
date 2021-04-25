package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import views.AccountView;
import views.CreatorView;
import views.GeneratorView;
import views.LoginView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Initialize loginView, generatorView, accountView, creatorView
			LoginView loginView = new LoginView();
			GeneratorView generatorView = new GeneratorView();
			AccountView accountView = new AccountView();
			CreatorView creatorView = new CreatorView();
			
			// Initialize roots for scene changes
			Pane root = loginView.getRootPane();
			Pane accountRoot = accountView.getRootPane();
			
			// Initialize buttons for event handling
			Button loginBtn = loginView.getLoginBtn();
			Button generatorReturnBtn = generatorView.getReturnBtn();
			Button passwordGenBtn = accountView.getGeneratorBtn();
			Button logoutBtn = accountView.getLogoutBtn();
			Button accountCrtBtn = accountView.getCreatorBtn();
			Button creatorReturnBtn = creatorView.getReturnBtn();
			Button createAccountBtn = creatorView.getCreateBtn();
			
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
						scene.setRoot(accountRoot);
					}
				}
			});

			// Handles clicking of the login button
			loginBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// If the user sends the correct login, switch roots.
					if (loginView.checkLogin()) {
						scene.setRoot(accountRoot);
					}
				}
			});
			
			// Handles clicking of the Create new account button
			accountCrtBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switch to Account Creator root when button is clicked
					scene.setRoot(creatorView.getRootPane());
				}
			});
			
			// Handles clicking of the Password Generator button
			passwordGenBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switches to Password Generator root when button is clicked
					scene.setRoot(generatorView.getRootPane());
				}
			});
			
			// Handles clicking back to main button (in Password Generator)
			generatorReturnBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switches back to main root when button is clicked
					scene.setRoot(accountRoot);
				}
			});
			

			// Handles clicking Create Account button (in Account Creator)
			createAccountBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(creatorView.fieldEmpty()) {
						// If the required fields aren't empty, switch to main root
						scene.setRoot(accountRoot);
					}
				}
			});
			
			// Handles clicking back to main button (in Account Creator)
			creatorReturnBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switches back to main root when button is clicked
					scene.setRoot(accountRoot);
				}
			});
			
			// TODO: exit when pressing logout
			// Handles clicking of the Back to Main button
			logoutBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Exit application when button is clicked
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
