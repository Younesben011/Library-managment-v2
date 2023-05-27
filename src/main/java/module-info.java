module com.example.librarymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires com.google.api.client.json.gson;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.services.gmail;
    requires activation;
    requires mail;
    requires org.apache.commons.codec;
    requires jdk.httpserver;


    opens com.example.librarymanagement to javafx.fxml;
    exports com.example.librarymanagement;
}