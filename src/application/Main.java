package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Main extends Application {

	private static Button[] buttons;
	static int zaehler =0;

	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Vote Trump!");
			primaryStage.getIcons().add(new Image("https://pbs.twimg.com/profile_images/649272612997210112/u2cilcdq.jpg"));

			BorderPane root = new BorderPane();
			GridPane grid = new GridPane();
			Scene scene = new Scene(root, 500, 600);
			buttons = new Button[25];
			Button reset = new Button();

			for (int i = 0; i < 25; i++) {
				buttons[i] = new Button();
				// buttons[i].resize(120, 112);
				buttons[i].setStyle("-fx-base:blue");
				buttons[i].setMinSize(100, 100);

				grid.add(buttons[i], i % 5, i / 5);
				final int j = i;
				buttons[i].setOnAction(f -> {
					zaehler++;
					onClick(j);
					reset.setText(""+zaehler);
				});
			}

			reset.setOnAction(f -> {
				for (Button b : buttons) {
					b.setStyle("-fx-base:blue");
					zaehler = 0;
					reset.setText(""+zaehler);
				}
			});

			reset.setMinSize(500, 100);
			root.setTop(grid);
			root.setBottom(reset);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void onClick(int i) {
		changeStyle(buttons[i]);
		if (i % 5 != 0)
			changeStyle(buttons[i - 1]);
		if (i % 5 != 4)
			changeStyle(buttons[i + 1]);
		if (i / 5 != 0)
			changeStyle(buttons[i - 5]);
		if (i / 5 != 4)
			changeStyle(buttons[i + 5]);
	}

	public static void changeStyle(Button b) {
		String style = b.getStyle().equals("-fx-base:blue") ? "-fx-base:yellow" : "-fx-base:blue";
		b.setStyle(style);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
