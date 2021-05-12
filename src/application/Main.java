package application;

import java.util.ArrayList;

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
import views.EditorView;
import views.GeneratorView;
import views.LoginView;
import lib.PasswordManager;

public class Main extends Application {
	private Scene scene;
	private String curUser;
	private String curPswd;

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
			EditorView editorView = new EditorView();

			// Setting size of window
			scene = new Scene(loginView.getRootPane(), 800, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Key Locker");
			primaryStage.show();

			// Handles pressing enter to login (in Login page)
			loginView.getRootPane().addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					// If the user sends the correct login, switch roots.
					if (event.getCode() == KeyCode.ENTER) {
						if (dbHandler.verifyClientLogin(PasswordManager.encrypt(loginView.getUsernameInput()),
								PasswordManager.encrypt(loginView.getPasswordInput()))) {
							curUser = loginView.getUsernameInput();
							curPswd = loginView.getPasswordInput();
							loginView.successfulLogin();
							accountView.getCurrentUser(returnCurrentUser());
							accountView.addAccounts(curUser, dbHandler);
							scene.setRoot(accountView.getRootPane());
						} else {
							loginView.failedLogin();
						}
					}
				}
			});

			// Handles clicking of the login button (in Login page)
			loginView.getLoginBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// If the user sends the correct login, switch roots.
					if (dbHandler.verifyClientLogin(PasswordManager.encrypt(loginView.getUsernameInput()),
							PasswordManager.encrypt(loginView.getPasswordInput()))) {
						curUser = loginView.getUsernameInput();
						loginView.successfulLogin();
						accountView.getCurrentUser(returnCurrentUser());
						accountView.addAccounts(curUser, dbHandler);
						scene.setRoot(accountView.getRootPane());
					} else {
						loginView.failedLogin();
					}
				}
			});

			// Handles clicking of the SignUp button(in Login page)
			loginView.getSignUpBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switch root to CreateUserView
					scene.setRoot(createUserView.getRootPane());
				}
			});

			// Handles clicking of the Back to Login button (in Sign Up page)
			createUserView.getLoginBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switch root to CreateUserView
					scene.setRoot(loginView.getRootPane());
				}
			});

			// Handle clicking of Create An Account (in Sign Up page)
			createUserView.getCreateAccountBtn().addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							// if passwords match and the user is created
							if (!createUserView.getPasswordInput().equals(createUserView.getConfirmPasswordInput())) {
								// Switch root to LoginView
								createUserView.incorrectPasswords();
							} else if (!dbHandler.addClientToDatabase(
									PasswordManager.encrypt(createUserView.getUsernameInput()),
									PasswordManager.encrypt(createUserView.getPasswordInput()))) {
								createUserView.failedCreateAccount();
							} else {
								createUserView.successfulCreateAccount();
								scene.setRoot(loginView.getRootPane());
							}
						}
					});

			// Handles clicking of the Create new account button (in Account Summary page)
			accountView.getCreatorBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switch to Account Creator root when button is clicked
					scene.setRoot(creatorView.getRootPane());
				}
			});

			// Handles clicking of the Password Generator button (in Account Summary page)
			accountView.getGeneratorBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switches to Password Generator root when button is clicked
					scene.setRoot(generatorView.getRootPane());
				}
			});

			// TODO: delete from database
			// Handles clicking of the Create new account button (in Account Summary page)
			accountView.getDeleteBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					dbHandler.deleteClient(PasswordManager.encrypt(curUser), PasswordManager.encrypt(curPswd));
					// Switch to Account Creator root when button is clicked
					curUser = "";
					curPswd = "";
					scene.setRoot(loginView.getRootPane());
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
					if (creatorView.fieldNotEmpty()) {
						String service = creatorView.getAccountName();
						String username = creatorView.getUsername();
						String password = creatorView.getPassword();
						String notes = creatorView.getNotes();

						dbHandler.addClientAccount(curUser, PasswordManager.encrypt(service),
								PasswordManager.encrypt(username), PasswordManager.encrypt(password),
								PasswordManager.encrypt(notes));

						// Sets up accounts on Account Summary
						accountView.addAccounts(curUser, dbHandler);

						// If the required fields aren't empty, switch to main root
						scene.setRoot(accountView.getRootPane());

						// Reset textfields
						creatorView.resetFields();
					}
				}
			});

			// Handles clicking back to main button (in Account Creator)
			creatorView.getReturnBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Switches back to main root when button is clicked
					scene.setRoot(accountView.getRootPane());

					accountView.addAccounts(curUser, dbHandler);

					// reset textfields
					creatorView.resetFields();
				}
			});

			// Handles clicking of the Back to Main button (in Account Creator)
			accountView.getLogoutBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// Exit application when button is clicked
					curUser = "";
					curPswd = "";
					scene.setRoot(loginView.getRootPane());
				}
			});

			// clicking a list item (in Account Summary)
			accountView.returnAccountListView().addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							if (event.getClickCount() == 2) {
								int selectedItemIndex = accountView.returnAccountListView().getSelectionModel()
										.getSelectedIndex();
								ArrayList<String> selectedItem = dbHandler.getClientAccounts(curUser)
										.get(selectedItemIndex);
								scene.setRoot(editorView.getRootPane());
								editorView.setExistingField(PasswordManager.decrypt(selectedItem.get(1)),
										PasswordManager.decrypt(selectedItem.get(2)),
										PasswordManager.decrypt(selectedItem.get(3)),
										PasswordManager.decrypt(selectedItem.get(4)));
							}
							creatorView.resetFields();
						}
					});

			// Handles clicking finish editing account button (in Account Editor)
			editorView.getFinishBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					int selectedItemIndex = accountView.returnAccountListView().getSelectionModel().getSelectedIndex();
					ArrayList<String> selectedItem = dbHandler.getClientAccounts(curUser).get(selectedItemIndex);

					dbHandler.deleteClientAccount(curUser, selectedItem.get(1), selectedItem.get(2),
							selectedItem.get(3));

					dbHandler.addClientAccount(curUser, PasswordManager.encrypt(editorView.getAccountName()),
							PasswordManager.encrypt(editorView.getUsername()),
							PasswordManager.encrypt(editorView.getPassword()),
							PasswordManager.encrypt(editorView.getNotes()));

					// Sets up accounts on Account Summary
					accountView.addAccounts(curUser, dbHandler);

					// If the required fields aren't empty, switch to main root
					scene.setRoot(accountView.getRootPane());

					// Reset textfields
					editorView.resetFields();
					creatorView.resetFields();
				}
			});

			// Handles clicking back to main button (in Account Editor)
			editorView.getReturnBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					// Sets up accounts on Account Summary
					accountView.addAccounts(curUser, dbHandler);

					// Switches back to main root when button is clicked
					scene.setRoot(accountView.getRootPane());

					// reset textfields
					editorView.resetFields();
					creatorView.resetFields();
				}
			});

			// Handles clicking delete button (in Account Editor)
			editorView.getDeleteBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					int selectedItemIndex = accountView.returnAccountListView().getSelectionModel().getSelectedIndex();
					ArrayList<String> selectedItem = dbHandler.getClientAccounts(curUser).get(selectedItemIndex);

					dbHandler.deleteClientAccount(curUser, selectedItem.get(1), selectedItem.get(2),
							selectedItem.get(3));

					scene.setRoot(accountView.getRootPane());

					accountView.addAccounts(curUser, dbHandler);

					// reset textfields
					editorView.resetFields();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns current user
	 * 
	 * @return
	 **/
	public String returnCurrentUser() {
		String currentUser = curUser;
		return currentUser;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
