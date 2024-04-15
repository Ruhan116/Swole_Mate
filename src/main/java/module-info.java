module com.example.swole_mate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.swole_mate to javafx.fxml;
    exports com.example.swole_mate;
}