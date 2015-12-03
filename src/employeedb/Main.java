package employeedb;

import java.io.IOException;

import employeedb.model.Employee;
import employeedb.view.EmployeeEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private AnchorPane mainPane;
	
	ObservableList<Employee> employeeData = FXCollections.observableArrayList(); 
	
	public Main()
	{
		employeeData.add(new Employee("Joe", "Schmoe", 80000));
		employeeData.add(new Employee("Rad", "Dude", 40000));
		employeeData.add(new Employee("Lost", "Boy", 0));
		employeeData.add(new Employee("Cookie", "Monster", 1000000));
	}
	
	public ObservableList<Employee> getEmployeeData()
	{
		return employeeData;
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("EmployeeDB");
		
		showMainPane();
		//showEmployeeDB();
	}
	
	private void showMainPane()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Menu.fxml"));
			mainPane = (AnchorPane) loader.load();
			
			Scene scene = new Scene(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/*public void showEmployeeDB()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/EmployeeDB.fxml"));
			AnchorPane employeeDB = (AnchorPane) loader.load();
			
			mainPane.setCenter(employeeDB);
			
			EmployeeDBController controller = loader.getController();
			controller.setMain(this);
			
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public boolean showEmployeeEditDialog(Employee employee)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/EmployeeEditDialog.fxml"));
			AnchorPane employeeEditDialog = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Employee");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(employeeEditDialog);
			dialogStage.setScene(scene);
			
			EmployeeEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setEmployee(employee);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}*/

	public Stage getPrimaryStage()
	{
		return this.primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
