module org.example.petprohect_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.petprohect_1 to javafx.fxml;
    exports org.example.petprohect_1;
}