module com.example.proyectolab131 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    exports com.example.proyectolab131;
    exports com.example.proyectolab131.structures;
    exports com.example.proyectolab131.models;
    exports com.example.proyectolab131.enums;
    exports com.example.proyectolab131.persistence;

    //exports com.example.proyectolab131.components;
    opens com.example.proyectolab131 to javafx.fxml;
    opens com.example.proyectolab131.models to com.google.gson;
    opens com.example.proyectolab131.persistence to com.google.gson;
    opens com.example.proyectolab131.structures to com.google.gson;
    opens com.example.proyectolab131.enums to com.google.gson;
}