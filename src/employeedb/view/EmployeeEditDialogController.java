package employeedb.view;

import employeedb.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmployeeEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField salaryField;


    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;

        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        salaryField.setText(Integer.toString(employee.getSalary()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            employee.setFirstName(firstNameField.getText());
            employee.setLastName(lastNameField.getText());
            employee.setSalary(Integer.parseInt(salaryField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        
        if (salaryField.getText() == null || salaryField.getText().length() == 0) {
            errorMessage += "No valid salary!\n"; 
        } 
        else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(salaryField.getText());
            } 
            catch (NumberFormatException e) {
                errorMessage += "No valid salary (must be an integer)!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } 
        else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}

