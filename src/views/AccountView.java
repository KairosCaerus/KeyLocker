package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AccountView implements ViewInterface{
	
	private BorderPane accountRoot;
	private Label inputLbl;
	private Button creatorBtn;
	private Button generatorBtn;
	private Button logoutBtn;

	public AccountView() {
		accountRoot = new BorderPane();
		
		accountRoot.setPadding(new Insets(35, 10, 20, 20));
		
		// TODO: Add lock image 
		// Creating title & logo box
		HBox titleBox = new HBox();
		Label title = new Label("Account Summary");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		titleBox.getChildren().add(title);
		titleBox.setAlignment(Pos.CENTER);
		accountRoot.setTop(titleBox);
		
		// Adding Create New Account button, Password Generator button, Sign Out
		inputLbl = new Label("");
		inputLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		creatorBtn = new Button("Create New Account");
		creatorBtn.setFont(Font.font("Arial", 20));
		
		generatorBtn = new Button("Password Generator");
		generatorBtn.setFont(Font.font("Arial", 20));
		
		logoutBtn = new Button("Sign Out");
		logoutBtn.setFont(Font.font("Arial", 20));
		
		// Adding nodes to VBox and setting its alignment to center
		VBox sidebarVBox = new VBox();
		sidebarVBox.getChildren().addAll(creatorBtn, generatorBtn, logoutBtn, inputLbl);
		sidebarVBox.setAlignment(Pos.CENTER);
		sidebarVBox.setSpacing(10);
		
		// Adding nodes to root and setting background color
		accountRoot.setLeft(sidebarVBox);
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
}