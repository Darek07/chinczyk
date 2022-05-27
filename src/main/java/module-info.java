module com.example.chinczyk {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chinczyk to javafx.fxml;
    exports com.example.chinczyk;
}