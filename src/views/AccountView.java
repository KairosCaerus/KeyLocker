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
	private ListView<HBox> accountList;
	private ArrayList<ArrayList<String>> clientAccountList;
	private ArrayList<ArrayList<String>> organizedAccountList;

	public AccountView() {
		accountRoot = new BorderPane();

		accountRoot.setPadding(new Insets(35, 30, 20, 30));

		// Creating title & logo box
		Label title = new Label("Account Summary");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));

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

		accountList = new ListView<HBox>();
		accountList.setPrefHeight(300);
		accountList.setPrefWidth(475);
		accountList.setFixedCellSize(40);
		
		HBox sides = new HBox();
		sides.getChildren().addAll(sidebarVBox, accountList);
		sides.setAlignment(Pos.CENTER);
		sides.setSpacing(10);
		
		
		VBox allContent = new VBox();
		allContent.getChildren().addAll(title, sides);
		allContent.setAlignment(Pos.CENTER);
		allContent.setSpacing(10);
		

		// Adding nodes to root and setting background color
		accountRoot.setCenter(allContent);
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

	/**
	 * Returns Button object for delete
	 * 
	 * @return creatorBtn
	 */
	public Button getDeleteBtn() {
		return deleteBtn;
	}
	
	/**
	 * Returns list of account information in alphabetical order
	 * 
	 * @return organizedAcountList
	 */
	public ArrayList<ArrayList<String>> getAccounts() {
		return organizedAccountList;
	}

	/**
	 * Formats the list of account information into a list.
	 * 
	 * @param currentUser
	 * @param db
	 */
	public void addAccounts(String currentUser, UserDatabaseController db) {
		organizedAccountList = organizeAccounts(currentUser, db);
		accountList.getItems().clear();
		accountList.getItems().add(createAccountListItem("Service", "Username", "Password"));

		for (ArrayList<String> account : organizedAccountList) {
			for(int i = 0; i < organizedAccountList.size(); ++i) {
				ArrayList<String> currentAccount = clientAccountList.get(i);
				if(PasswordManager.decrypt(account.get(1)).compareTo(PasswordManager.decrypt(currentAccount.get(1))) == 0
						&& PasswordManager.decrypt(account.get(2)).compareTo(PasswordManager.decrypt(currentAccount.get(2))) == 0 ) {

					accountList.getItems()
					.add(createAccountListItem(PasswordManager.decrypt(currentAccount.get(1)), PasswordManager.decrypt(currentAccount.get(2)), PasswordManager.decrypt(currentAccount.get(3))));
				}
			}
		}
	}
	
	// Helper function to format list items
	private HBox createAccountListItem(String str1, String str2, String str3) {
		HBox hbox = new HBox();
		Label lbl1 = new Label(str1);
		lbl1.setMinWidth(150);
		Label lbl2 = new Label(str2);
		lbl2.setMinWidth(150);
		Label lbl3 = new Label(str3);
		lbl3.setMinWidth(150);
		hbox.getChildren().addAll(lbl1, lbl2, lbl3);
		return hbox;
	}
	
	/**
	 * Sorts the list of accounts in alphabetical order
	 * 
	 * @param currentUser
	 * @param db
	 * @return ordered list of accounts
	 */
	public ArrayList<ArrayList<String>> organizeAccounts(String currentUser, UserDatabaseController db) {
		clientAccountList = db.getClientAccounts(currentUser);
		organizedAccountList = db.getClientAccounts(currentUser);
		Collections.sort(organizedAccountList, new Comparator<ArrayList<String>>() {
			@Override
			public int compare(ArrayList<String> first, ArrayList<String> second) {

				if(PasswordManager.decrypt(first.get(1)).compareTo(PasswordManager.decrypt(second.get(1))) == 0) {
					return PasswordManager.decrypt(first.get(2)).toLowerCase().compareTo(PasswordManager.decrypt(second.get(2)).toLowerCase());
				}
				return PasswordManager.decrypt(first.get(1)).toLowerCase().compareTo(PasswordManager.decrypt(second.get(1)).toLowerCase());
			}
		});
		return organizedAccountList;
	}

	/**
	 * Returns the list view of the account list
	 * 
	 * @return accountList
	 */
	public ListView<HBox> returnAccountListView() {
		return accountList;
	}
}