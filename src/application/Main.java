package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			// TODO: Add lock image 
			// Creating title & logo box
			HBox logoTitleBox = new HBox();
			Label title = new Label("Key Locker");
			title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
			logoTitleBox.getChildren().add(title);
			logoTitleBox.setAlignment(Pos.CENTER);

			
			// Creating username label and text field
			Label usernameLbl = new Label("username: ");
			usernameLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			TextField usernameInput = new TextField();
			usernameInput.setPrefWidth(200);
			usernameInput.setPrefHeight(30);
			
			// Creating password label and text field
			Label passwordLbl = new Label("password: ");
			passwordLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			TextField passwordInput = new TextField();
			passwordInput.setPrefWidth(200);
			passwordInput.setPrefHeight(30);
			
			GridPane textFields = new GridPane();
			textFields.add(usernameLbl, 0, 0);
			textFields.add(usernameInput, 1, 0);
			textFields.add(passwordLbl, 0, 1);
			textFields.add(passwordInput, 1, 1);
			textFields.setVgap(5);
			textFields.setHgap(3);
			textFields.setAlignment(Pos.CENTER);
			
			// Adding nodes to centerVBox and setting its alignment to center
			VBox centerVBox = new VBox();
			centerVBox.getChildren().add(logoTitleBox);
			centerVBox.getChildren().add(textFields);
			centerVBox.setAlignment(Pos.CENTER);
			centerVBox.setSpacing(10);

			
			// Adding nodes to root and setting background color
			root.setCenter(centerVBox);
			root.setStyle("-fx-background-color: cornflowerblue");
			
			// Setting size of window
			Scene scene = new Scene(root,800,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Key Locker");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
