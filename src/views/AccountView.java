package views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import application.UserDatabaseController;
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
	private ListView<String> accountList;
	private ArrayList<ArrayList<String>> clientAccountList;
	private ArrayList<ArrayList<String>> organizedAccountList;

	public AccountView() {
		accountRoot = new BorderPane();

		accountRoot.setPadding(new Insets(35, 30, 20, 30));

		// Creating title & logo box
		HBox titleBox = new HBox();
		Label title = new Label("Account Summary");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		titleBox.getChildren().add(title);
		titleBox.setAlignment(Pos.CENTER);
		accountRoot.setTop(titleBox);

		// Adding Create New Account button, Password Generator button, Sign Out button, Delete button
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
	
	public ArrayList<ArrayList<String>> getAccounts() {
		return organizedAccountList;
	}

	public void addAccounts(String currentUser, UserDatabaseController db) {
		clientAccountList = db.getClientAccounts(currentUser);
		organizedAccountList = organizeAccounts(currentUser, db);
		accountList.getItems().clear();

		for (ArrayList<String> account : organizedAccountList) {
			for(int i = 0; i < clientAccountList.size(); ++i) {
				ArrayList<String> currentAccount = clientAccountList.get(i);
				if(PasswordManager.decrypt(account.get(1)).compareTo(PasswordManager.decrypt(currentAccount.get(1))) == 0
						&& PasswordManager.decrypt(account.get(2)).compareTo(PasswordManager.decrypt(currentAccount.get(2))) == 0 ) {
					accountList.getItems()
					.add(PasswordManager.decrypt(currentAccount.get(1)) + " - " + PasswordManager.decrypt(currentAccount.get(2)));
				}
			}
		}
	}
	
	public ArrayList<ArrayList<String>> organizeAccounts(String currentUser, UserDatabaseController db) {
		clientAccountList = db.getClientAccounts(currentUser);
		organizedAccountList = db.getClientAccounts(currentUser);
		Collections.sort(organizedAccountList, new Comparator<ArrayList<String>>() {
			@Override
			public int compare(ArrayList<String> first, ArrayList<String> second) {
				if(PasswordManager.decrypt(first.get(1)).compareTo(PasswordManager.decrypt(second.get(1))) == 0) {
					return PasswordManager.decrypt(first.get(2)).compareTo(PasswordManager.decrypt(second.get(2)));
				}
				return PasswordManager.decrypt(first.get(1)).compareTo(PasswordManager.decrypt(second.get(1)));
			}
		});
		
		return organizedAccountList;
	}

	public ListView<String> returnAccountListView() {
		return accountList;
	}
}