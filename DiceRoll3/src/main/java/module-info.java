module com.example.diceroll3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.diceroll3 to javafx.fxml;
    exports com.example.diceroll3;
}