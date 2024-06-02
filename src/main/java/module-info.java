module com.example.swole_mate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    opens com.example.swole_mate to javafx.fxml;
    exports com.example.swole_mate;
    exports com.example.swole_mate.controller;
    opens com.example.swole_mate.controller to javafx.fxml;
}

