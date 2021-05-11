package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import views.AccountView;
import views.CreateUserView;
import views.CreatorView;
import views.GeneratorView;
import views.LoginView;
import lib.PasswordManager;

public class Main extends Application {
	Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		UserDatabaseController dbHandler = UserDatabaseController.getDBInstance();

		try {
			// Initialize loginView, generatorView, accountView, creatorView
			LoginView loginView = new LoginView();
			GeneratorView generatorView = new GeneratorView();
			AccountView accountView = new AccountView();
			CreatorView creatorView = new CreatorView();
			CreateUserView createUserView = new CreateUserView();
			
			// Setting size of window
			scene = new Scene(loginView.getRootPane(), 800, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Key Locker");
			primaryStage.show();

			// Handles pressing enter to login
			loginView.getRootPane().addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					// If the user sends the correct login, switch roots.
					if (event.getCode() == KeyCode.ENTER) {
						if(dbHandler.verifyClientLogin(PasswordManager.encrypt(loginView.getUsernameInput()), PasswordManager.encrypt(loginView.getPasswordInput()))) {
							loginView.successfulLogin();
							scene.setRoot(accountView.getRootPane());
						} else {
							loginView.failedLogin();
						}
					}
				}
			});

			// Handles clicking of the login button
			loginView.getLoginBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// If the user sends the correct login, switch roots.
					if (dbHandler.verifyClientLogin(PasswordManager.encrypt(loginView.getUsernameInput()), PasswordManager.encrypt(loginView.getPasswordInput()))) {
						loginView.successfulLogin();
						scene.setRoot(accountView.getRootPane());
					} else {
						loginView.failedLogin();
					}
				}
			});
			
			// Handles clicking of the SignUp button
			loginView.getSignUpBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switch root to CreateUserView
					scene.setRoot(createUserView.getRootPane());
				}
			});
			
			// Handles clicking of the Login button
			createUserView.getLoginBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switch root to CreateUserView
					scene.setRoot(loginView.getRootPane());
				}
			});
			
			//Handle clicking of Create An Account
			createUserView.getCreateAccountBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// if passwords match and the user is created
					if(!createUserView.getPasswordInput().equals(createUserView.getConfirmPasswordInput())) {
						// Switch root to LoginView
						createUserView.incorrectPasswords();
					} else if(!dbHandler.addClientToDatabase(PasswordManager.encrypt(createUserView.getUsernameInput()), PasswordManager.encrypt(createUserView.getPasswordInput()))){
						createUserView.failedCreateAccount();
					} else {
						createUserView.successfulCreateAccount();
						scene.setRoot(loginView.getRootPane());
					}
				}
			});
			
			// Handles clicking of the Create new account button
			accountView.getCreatorBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switch to Account Creator root when button is clicked
					scene.setRoot(creatorView.getRootPane());
				}
			});
			
			// Handles clicking of the Password Generator button
			accountView.getGeneratorBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switches to Password Generator root when button is clicked
					scene.setRoot(generatorView.getRootPane());
				}
			});
			
			// Handles clicking back to main button (in Password Generator)
			generatorView.getReturnBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switches back to main root when button is clicked
					scene.setRoot(accountView.getRootPane());
				}
			});
			

			// Handles clicking Create Account button (in Account Creator)
			creatorView.getCreateBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(creatorView.fieldEmpty()) {
						// If the required fields aren't empty, switch to main root
						scene.setRoot(accountView.getRootPane());
					}
				}
			});
			
			// Handles clicking back to main button (in Account Creator)
			creatorView.getReturnBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switches back to main root when button is clicked
					scene.setRoot(accountView.getRootPane());
				}
			});
			
			// Handles clicking of the Back to Main button
			accountView.getLogoutBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Exit application when button is clicked
					scene.setRoot(loginView.getRootPane());
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
