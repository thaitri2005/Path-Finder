module edu.vinuni.pathfinder {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.vinuni.pathfinder to javafx.fxml;
    exports edu.vinuni.pathfinder;
}