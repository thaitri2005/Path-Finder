package edu.vinuni.pathfinder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Map;

public class MainApp extends Application {
    private IGraph graph = new Graph();
    private IShortestPathAlgorithm dijkstra = new Dijkstra();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PathFinder");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label addLabel = new Label("Add Route: ");
        GridPane.setConstraints(addLabel, 0, 0);

        TextField fromInput = new TextField();
        fromInput.setPromptText("From");
        GridPane.setConstraints(fromInput, 1, 0);

        TextField toInput = new TextField();
        toInput.setPromptText("To");
        GridPane.setConstraints(toInput, 2, 0);

        TextField vehicleTypeInput = new TextField();
        vehicleTypeInput.setPromptText("Vehicle Type");
        GridPane.setConstraints(vehicleTypeInput, 3, 0);

        TextField weightInput = new TextField();
        weightInput.setPromptText("Cost");
        GridPane.setConstraints(weightInput, 4, 0);

        Button addButton = new Button("Add");
        GridPane.setConstraints(addButton, 5, 0);

        Label getLabel = new Label("Get Cheapest Path: ");
        GridPane.setConstraints(getLabel, 0, 1);

        TextField fromGetInput = new TextField();
        fromGetInput.setPromptText("From");
        GridPane.setConstraints(fromGetInput, 1, 1);

        TextField toGetInput = new TextField();
        toGetInput.setPromptText("To");
        GridPane.setConstraints(toGetInput, 2, 1);

        TextField vehicleTypeGetInput = new TextField();
        vehicleTypeGetInput.setPromptText("Vehicle Type");
        GridPane.setConstraints(vehicleTypeGetInput, 3, 1);

        Button getButton = new Button("Get");
        GridPane.setConstraints(getButton, 5, 1);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 0, 2, 6, 1);

        addButton.setOnAction(e -> {
            try {
                String from = fromInput.getText();
                String to = toInput.getText();
                String vehicleType = vehicleTypeInput.getText();
                int weight = Integer.parseInt(weightInput.getText());
                graph.addEdge(from, to, vehicleType, weight);
                resultLabel.setText("Route added: " + from + " to " + to + " for " + vehicleType + " with cost " + weight);
                fromInput.clear();
                toInput.clear();
                vehicleTypeInput.clear();
                weightInput.clear();
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid weight. Please enter an integer value.");
            } catch (Exception ex) {
                resultLabel.setText("An error occurred: " + ex.getMessage());
            }
        });

        getButton.setOnAction(e -> {
            String from = fromGetInput.getText();
            String to = toGetInput.getText();
            String vehicleType = vehicleTypeGetInput.getText();
            Node source = graph.getNode(from);
            Node target = graph.getNode(to);

            if (source == null || target == null) {
                resultLabel.setText("One or both of the specified nodes do not exist.");
                return;
            }

            Map<Node, Integer> shortestPaths = dijkstra.findShortestPath(graph, source, vehicleType);

            if (shortestPaths.containsKey(target)) {
                int distance = shortestPaths.get(target);
                resultLabel.setText("Cheapest cost from " + from + " to " + to + " for " + vehicleType + " is " + distance);
            } else {
                resultLabel.setText("No path found from " + from + " to " + to);
            }
        });

        grid.getChildren().addAll(addLabel, fromInput, toInput, vehicleTypeInput, weightInput, addButton,
                getLabel, fromGetInput, toGetInput, vehicleTypeGetInput, getButton, resultLabel);

        Scene scene = new Scene(grid, 800, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
