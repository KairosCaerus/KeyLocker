package views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import application.Main;
import application.UserDatabaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.NodeStyler;
import lib.PasswordManager;

public class AccountView implements ViewInterface {

	private BorderPane accountRoot;
	private Label inputLbl;
	private Button creatorBtn;
	private Button generatorBtn;
	private Button logoutBtn;
	private Button deleteBtn;
	private String curUser;
	private ListView<String> accountList;
	private ArrayList<ArrayList<String>> clientAccountList;

	public AccountView() {

		UserDatabaseController dbHandler = UserDatabaseController.getDBInstance();
		Main main = new Main();
		ObservableList<String> items = FXCollections.observableArrayList("hello");
		accountRoot = new BorderPane();

		accountRoot.setPadding(new Insets(35, 30, 20, 30));

		// TODO: Add lock image
		// Creating title & logo box
		HBox titleBox = new HBox();
		Label title = new Label("Account Summary");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		titleBox.getChildren().add(title);
		titleBox.setAlignment(Pos.CENTER);
		accountRoot.setTop(titleBox);

		// Adding Create New Account button, Password Generator button, Sign Out
		inputLbl = NodeStyler.createInputLabel("");

		creatorBtn = NodeStyler.createButton("Add New Account");

		generatorBtn = NodeStyler.createButton("Password Generator");

		logoutBtn = NodeStyler.createButton("Sign Out");
		
		deleteBtn = NodeStyler.createButton("Delete KeyLocker Account");

		// Adding nodes to VBox and setting its alignment to center
		VBox sidebarVBox = new VBox();
		sidebarVBox.getChildren().addAll(creatorBtn, generatorBtn, logoutBtn, deleteBtn, inputLbl);
		sidebarVBox.setAlignment(Pos.CENTER);
		sidebarVBox.setSpacing(10);

		accountList = new ListView<String>();
		accountList.setPrefHeight(300);
		accountList.setPrefWidth(475);
		accountList.setFixedCellSize(40);

		// Adding nodes to VBox and setting its alignment to center
		VBox sidebarVBox2 = new VBox();
		sidebarVBox2.getChildren().add(accountList);
		sidebarVBox2.setAlignment(Pos.CENTER);

		// Adding nodes to root and setting background color
		accountRoot.setLeft(sidebarVBox);
		accountRoot.setRight(sidebarVBox2);
		accountRoot.setStyle("-fx-background-color: cornflowerblue");
	}

	/**
	 * Return root of the class in the form of a BorderPane object
	 * 
	 * @return root of AccountView
	 */
	@Override
	public Pane getRootPane() {
		return accountRoot;
	}

	/**
	 * Returns Button object for sign out
	 * 
	 * @return logoutBtn
	 */
	public Button getLogoutBtn() {
		return logoutBtn;
	}

	/**
	 * Returns Button object for generator
	 * 
	 * @return generatorBtn
	 */
	public Button getGeneratorBtn() {
		return generatorBtn;
	}

	/**
	 * Returns Button object for creator
	 * 
	 * @return creatorBtn
	 */
	public Button getCreatorBtn() {
		return creatorBtn;
	}

	public Button getDeleteBtn() {
		return deleteBtn;
	}
	
	public void getCurrentUser(String currentUser) {
		curUser = currentUser;
	}
	
	public ArrayList<ArrayList<String>> returnAccounts() {
		return clientAccountList;
	}

	public void addAccounts(String currentUser, UserDatabaseController db) {
		clientAccountList = db.getClientAccounts(currentUser);
		accountList.getItems().clear();
//		Collections.sort(clientAccountList, new Comparator<ArrayList<String>>() {
//			@Override
//			public int compare(ArrayList<String> first, ArrayList<String> second) {
//				if(PasswordManager.decrypt(first.get(1)).compareTo(PasswordManager.decrypt(second.get(1))) == 0) {
//					return PasswordManager.decrypt(first.get(2)).compareTo(PasswordManager.decrypt(second.get(2)));
//				}
//				return PasswordManager.decrypt(first.get(1)).compareTo(PasswordManager.decrypt(second.get(1)));
//			}
//		});
		for (ArrayList<String> accounts : clientAccountList) {
			accountList.getItems()
					.add(PasswordManager.decrypt(accounts.get(1)) + " - " + PasswordManager.decrypt(accounts.get(2)));
		}
	}

	public ListView<String> returnAccountListView() {
		return accountList;
	}
}