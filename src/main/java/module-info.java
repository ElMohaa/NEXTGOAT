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

    exports org.example.next_goat.Controllers.ForAndMid.Dribble;
    opens org.example.next_goat.Controllers.ForAndMid.Dribble to javafx.fxml;

    exports org.example.next_goat.Controllers.ForAndMid.ToPass;
    opens org.example.next_goat.Controllers.ForAndMid.ToPass to javafx.fxml;

    exports org.example.next_goat.Controllers.ForAndMid.ControllBall;
    opens org.example.next_goat.Controllers.ForAndMid.ControllBall to javafx.fxml;

    exports org.example.next_goat.Controllers.ForAndMid.Skill;
    opens org.example.next_goat.Controllers.ForAndMid.Skill to javafx.fxml;

    exports org.example.next_goat.Controllers.ForAndMid;
    opens org.example.next_goat.Controllers.ForAndMid to javafx.fxml;

    exports org.example.next_goat.Controllers.Defen;
    opens org.example.next_goat.Controllers.Defen to javafx.fxml;

    exports org.example.next_goat.Controllers.Defen.Seated;
    opens org.example.next_goat.Controllers.Defen.Seated to javafx.fxml;

    exports org.example.next_goat.Controllers.Defen.ControllDefense;
    opens org.example.next_goat.Controllers.Defen.ControllDefense to javafx.fxml;

    exports org.example.next_goat.Controllers.Defen.ToPassDefense;
    opens org.example.next_goat.Controllers.Defen.ToPassDefense to javafx.fxml;

    exports org.example.next_goat.Controllers.Goalkeeper;
    opens org.example.next_goat.Controllers.Goalkeeper to javafx.fxml;
    exports org.example.next_goat.Controllers.Goalkeeper.Soot;
    opens org.example.next_goat.Controllers.Goalkeeper.Soot to javafx.fxml;
    exports org.example.next_goat.Controllers.Goalkeeper.PassGK;
    opens org.example.next_goat.Controllers.Goalkeeper.PassGK to javafx.fxml;

}
