package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
                showWindowError("An account with that username already exists");
                return;
            } else if (DataBaseConnection.checkEmailExists(newUser)) {
                showWindowError("An account with that email already exists.");
                return;
            } else if (DataBaseConnection.checkTelExists(newUser)) {
                showWindowError("An account with that phone number already exists.");
                return;
            }

            // Insertar el usuario en la base de datos
            DataBaseConnection.insertUser(newUser);
            System.out.println("Usuario registrado correctamente.");
            showWindowGood("You are already registered");

            // Volver a la pantalla de inicio de sesión
            handleBackToLoginButtonAction(event);

        } catch (DNIIllegalException d){
            showWindowError("The DNI or NIE is incorrect.");

        }catch (NullPointerException n){
            showWindowError("There is an empty field, all fields must be completed.");

        }catch (SurnameIllegalException s){
            showWindowError("The last name is empty and/or it should only contain letters.");

        }catch (NameIllegalException na){
            showWindowError("The first name is empty and/or it should only contain letters.");

        }catch (NumberIllegalException es){
            showWindowError("The number is too short or too long, remember it must be 9 digits");

        }catch(EmailIllegalException ma ){
            showWindowError("The email does not have the correct format");

        } catch (Exception e) {
            System.err.println("Error al registrar al usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showWindowError(String text){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/error.fxml"));
            Parent dialogParent = loader.load();
            Scene dialogScene = new Scene(dialogParent);

            ErrorController puwc=loader.getController();
            puwc.setErrorText(text);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(dialogScene);
            stage.show();
        } catch (Exception ex) {
            System.err.println("Error: "+ ex.getMessage());

        }
    }
    public void showWindowGood(String text){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/good.fxml"));
            Parent dialogParent = loader.load();
            Scene dialogScene = new Scene(dialogParent);

            ErrorController puwc=loader.getController();
            puwc.setErrorText(text);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(dialogScene);
            stage.show();
        } catch (Exception ex) {
            System.err.println("Error: "+ ex.getMessage());

        }
    }



    private int calculateAge(LocalDate birthDate) {
        // Obtener la fecha actual
        LocalDate currentDate = LocalDate.now();
        // Calcular el periodo entre la fecha de nacimiento y la fecha actual
        return Period.between(birthDate, currentDate).getYears();
    }


}
