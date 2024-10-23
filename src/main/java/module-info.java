module org.example.next_goat {
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires com.google.gson;
    requires javafx.web;
    requires javafx.media;


    // Exponer el paquete principal y los controladores a JavaFX
    opens org.example.next_goat.Exceptios to javafx.fxml;
    exports org.example.next_goat.Exceptios;

    // Exponer las clases a JavaFX si usas FXML en ellas
    exports org.example.next_goat.Clases;
    opens org.example.next_goat.Clases to javafx.fxml,com.google.gson;

    // Exponer controladores si necesitas acceso dinámico
    exports org.example.next_goat.Controllers;
    opens org.example.next_goat.Controllers to javafx.fxml;
}
