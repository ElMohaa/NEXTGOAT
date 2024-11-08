package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.next_goat.Clases.MejoraFisica;
import org.example.next_goat.Clases.Usuario;
import org.example.next_goat.DataBase.DataBaseConnection;
import org.example.next_goat.Exceptios.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class UserSuscribeController {

    @FXML
    private Button signUpButton;

    @FXML
    private Button backToLoginButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;
    @FXML
    private TextField dniField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private DatePicker datePicker;




    @FXML
    private void handleBackToLoginButtonAction(ActionEvent event) {
        try {

            Parent loginPage = FXMLLoader.load(getClass().getResource("/FXML/UserLogin.fxml"));
            Stage stage = (Stage) backToLoginButton.getScene().getWindow();
            stage.setScene(new Scene(loginPage, 315, 615));
            stage.setTitle("Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        try {
            // Crear un nuevo usuario
            Usuario newUser = new Usuario();
            newUser.setNombre_usuario(nameField.getText().trim());
            newUser.setApellidos_usuario(surnameField.getText().trim());
            newUser.setDni_usuario(dniField.getText().trim());
            newUser.setUsername(usernameField.getText().trim());
            newUser.setContrsena(passwordField.getText().trim());
            newUser.setCorreo_usuario(emailField.getText().trim());
            newUser.setTelefono_usuario(phoneField.getText().trim());
            newUser.setDirrecion_vivienda(addressField.getText().trim());

            // Obtener la fecha de nacimiento desde el DatePicker
            LocalDate birthDate = datePicker.getValue();
            newUser.setFecha_nacimiento(Date.valueOf(birthDate));

            // Calcular la edad
            int edad = calculateAge(birthDate);
            newUser.setEdad_ususario(edad);

            // Verificar si el usuario ya existe en la base de datos
            if (DataBaseConnection.checkUserExists(newUser)) {
                // Mostrar un mensaje de error si el usuario ya existe
                showStyledAlert("Error", "Ya existe un usuario con ese nombre.Por favor escriba otro ");
                return; // Salir de la función sin intentar la inserción
            }

            // Insertar el usuario en la base de datos
            DataBaseConnection.insertUser(newUser);
            System.out.println("Usuario registrado correctamente.");


            // Volver a la pantalla de inicio de sesión
            handleBackToLoginButtonAction(event);

        } catch (DNIIllegalException d){
            showStyledAlert("Error de Registro","El DNI o NIE es incorrecto");
        }catch (NullPointerException n){
            showStyledAlert("Error de Registro","Hay un campo vacio, deben estar todos los campos completados");
        }catch (SurnameIllegalException s){
            showStyledAlert("Error de Registro","El apellido esta vacio o/y solo debe contener letras");
        }catch (NameIllegalException na){
            showStyledAlert("Error de Registro","El nombre esta vacio o/y solo debe contener letras");
        }catch (NumberIllegalException es){
            showStyledAlert("Error de Registro","El numero es demasiado corto o largo, recuerda son 9 digitos");
        }catch(EmailIllegalException ma ){
            showStyledAlert("Error de Registro","El email no tiene el formato correcto");
        } catch (Exception e) {
            System.err.println("Error al registrar al usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void showStyledAlert(String title, String message) {
        // Crear la alerta de tipo ERROR o cualquier tipo que desees
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);  // Título de la alerta
        alert.setHeaderText("¡Error!");  // Texto del encabezado
        alert.setContentText(message);  // Mensaje de contenido

        // Obtener el DialogPane de la alerta
        DialogPane dialogPane = alert.getDialogPane();

        // Cargar el archivo CSS
        dialogPane.getStylesheets().add(getClass().getResource("/CSS/alertas.css").toExternalForm());

        // Aplicar una clase personalizada si lo necesitas
        dialogPane.getStyleClass().add("custom-alert");

        // Mostrar la alerta
        alert.showAndWait();
    }



    private int calculateAge(LocalDate birthDate) {
        // Obtener la fecha actual
        LocalDate currentDate = LocalDate.now();
        // Calcular el periodo entre la fecha de nacimiento y la fecha actual
        return Period.between(birthDate, currentDate).getYears();
    }


}
