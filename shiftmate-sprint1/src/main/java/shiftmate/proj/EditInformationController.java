package shiftmate.proj;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EditInformationController implements Initializable
{
    Stage stage;
    Parent scene;

    @FXML
    private TableView <EmployeeInfo> employeeTableView;
    @FXML
    private TableColumn<EmployeeInfo, Integer> employeeIDcolumn;
    @FXML
    private TableColumn<EmployeeInfo, Integer> depIDcolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> fNamecolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> lNamecolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> emailcolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> phonecolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> startDatecolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> contactcolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> contactPhonecolumn;
    @FXML
    private TableColumn<EmployeeInfo,String> depNamecolumn;

    @FXML
    private TextField employeeIDTextField;
    @FXML
    private TextField depIDTextField;
    @FXML
    private TextField fNameTextField;
    @FXML
    private TextField lNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField startDateTextField;
    @FXML
    private TextField contactTextField;
    @FXML
    private TextField contactPhoneTextField;
    @FXML
    private TextField depNameTextField;

    public void EmployeeInfoTable() 
    {
        LinkedList<Hashtable<String,String>> employeeInformation = DBController.getEmployees();  //gets employee data from DBController

        ObservableList<EmployeeInfo> employeeList = FXCollections.observableArrayList();    //list to hold employee objects

        for (int i = 0; i < employeeInformation.size(); i++)                //iterate through the data
        {
            Hashtable<String,String> data = employeeInformation.get(i);

            int employeeID = Integer.parseInt(data.get("employeeID"));      
            int depID = Integer.parseInt(data.get("depID"));
            String fName = data.get("fName");
            String lName = data.get("lName");                                  //extracts attributes of the employees
            String email = data.get("email");
            String phone = data.get("phone");
            String startDate = data.get("startDate");
            String contact = data.get("contact");
            String contactPhone = data.get("contactPhone");
            String depName = data.get("depName");

            EmployeeInfo employeeInfo = new EmployeeInfo(employeeID, depID, fName, lName, email,
            phone, startDate, contact, contactPhone);
            
            employeeInfo.setDepName(depName);
            employeeList.add(employeeInfo);

        }
    
    //fill TableView with data
    employeeTableView.setItems(employeeList);

    employeeIDcolumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
    depIDcolumn.setCellValueFactory(new PropertyValueFactory<>("depID"));
    fNamecolumn.setCellValueFactory(new PropertyValueFactory<>("fName"));
    lNamecolumn.setCellValueFactory(new PropertyValueFactory<>("lName"));
    emailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    phonecolumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    startDatecolumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    contactcolumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
    contactPhonecolumn.setCellValueFactory(new PropertyValueFactory<>("contactPhone"));
    depNamecolumn.setCellValueFactory(new PropertyValueFactory<>("depName"));

    }
    

    //add employee to table/database
    @FXML
    void addEmployeeOnAction(ActionEvent event) throws IOException
    {
        //get text from TextField
        String fName = fNameTextField.getText();
        String lName = lNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String employeeIDText = employeeIDTextField.getText();
        String depIDText = depIDTextField.getText();
        String startDate = startDateTextField.getText();
        String contact = contactTextField.getText();
        String contactPhone = contactPhoneTextField.getText();

        if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty() || employeeIDText.isEmpty() || depIDText.isEmpty()
        || startDate.isEmpty() || contact.isEmpty() || contactPhone.isEmpty())
        { 
        
        StringBuffer missingField = new StringBuffer("Missing: ");

        if (fName.isEmpty())
        {
            missingField.append("  First Name  ");
        }
        if(lName.isEmpty())
        {
            missingField.append("  Last Name  ");
        }
        if (email.isEmpty())
        {
            missingField.append("  Email ");
        }
        if (phone.isEmpty())
        {
            missingField.append("  Phone  ");
            
        }
        if (employeeIDText.isEmpty())
        {
            missingField.append("  Employee ID  ");
        }
        if (depIDText.isEmpty())
        {
            missingField.append("  Department ID  ");
        }
        if (startDate.isEmpty())
        {
            missingField.append("  Start Date  ");
        }
        if (contact.isEmpty())
        {
            missingField.append("  Contact  ");
        }
        if (contactPhone.isEmpty())
        {
            missingField.append("  Contact Phone  ");
        }
    
    
        AlertWindow("Missing Information", missingField.toString(), " ", AlertType.NONE);
    }
    else
    {

        int depID = Integer.parseInt(depIDText);
        int employeeID= Integer.parseInt(employeeIDText);
       

        boolean valid = DBController.addEmployee(fName,lName,phone,email,startDate,depID,contact,contactPhone,employeeID);

        if(valid)
        {
            AlertWindow("Success", "You have sucessfully added an employee");
            EmployeeInfoTable();
        }
        else
        {
            AlertWindow("Fail", "You have not sucessfully added an employee");
        }

    }
}



    //delete selected employee
    @FXML
    void deleteEmployeeOnAction (ActionEvent event) throws IOException
    {
        EmployeeInfo slctdEmp = employeeTableView.getSelectionModel().getSelectedItem();

        if(slctdEmp == null)
        {
            AlertWindow("No employee selected", "Please select an employee to delete");
            return;
        
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText("Delete Employee");
        confirmAlert.setContentText("Are you sure you want to delete the selected employee?");
        confirmAlert.showAndWait().ifPresent(response ->
        {
            if (response == ButtonType.OK)
            {
                boolean delete = DBController.deleteEmployee(slctdEmp.getEmployeeID());
                
                if (delete)
                {
                    AlertWindow("Success", "Employee has been deleted successfully");
                    EmployeeInfoTable();
                }
                else
                {
                    AlertWindow("Fail", "Failed to delete employee");
                }
            }
        });
    }

// edit employee. Select the employee you'd like to edit and click edit button. The Text will go into the TextBoxes
    @FXML
    void editEmployeeOnAction(ActionEvent event) throws IOException     
    {
        EmployeeInfo slctdEmp = employeeTableView.getSelectionModel().getSelectedItem();

        if (slctdEmp != null)
        {
            
            int employeeID = slctdEmp.getEmployeeID();
            int depID = slctdEmp.getDepID();
            

            employeeIDTextField.setText(String.valueOf(employeeID));
            depIDTextField.setText(String.valueOf(depID));
            depNameTextField.setText(slctdEmp.getDepName());
            fNameTextField.setText(slctdEmp.getFName());
            lNameTextField.setText(slctdEmp.getLName());
            emailTextField.setText(slctdEmp.getEmail());
            phoneTextField.setText(slctdEmp.getPhone());
            startDateTextField.setText(slctdEmp.getStartDate());
            contactTextField.setText(slctdEmp.getContact());
            contactPhoneTextField.setText(slctdEmp.getContactPhone());
            
            
        }
        else
        {
            AlertWindow("Select Employee", "Please select an Employee you'd like to edit");
        }
    }
    //Save Employee information after editing it
    @FXML
    void saveEmployeeOnAction(ActionEvent event) throws IOException
    {
        EmployeeInfo slctdEmp = employeeTableView.getSelectionModel().getSelectedItem();
        String fName = fNameTextField.getText();
        String lName = lNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String employeeIDText = employeeIDTextField.getText();
        String depIDText = depIDTextField.getText();
        String startDate = startDateTextField.getText();
        String contact = contactTextField.getText();
        String contactPhone = contactPhoneTextField.getText();

        if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty() || employeeIDText.isEmpty() || depIDText.isEmpty()
        || startDate.isEmpty() || contact.isEmpty() || contactPhone.isEmpty())
        { 
        
        StringBuffer missingField = new StringBuffer("Missing: ");

        if (fName.isEmpty())
        {
            missingField.append("  First Name  ");
        }
        if(lName.isEmpty())
        {
            missingField.append("  Last Name  ");
        }
        if (email.isEmpty())
        {
            missingField.append("  Email ");
        }
        if (phone.isEmpty())
        {
            missingField.append("  Phone  ");
            
        }
        if (employeeIDText.isEmpty())
        {
            missingField.append("  Employee ID  ");
        }
        if (depIDText.isEmpty())
        {
            missingField.append("  Department ID  ");
        }
        if (startDate.isEmpty())
        {
            missingField.append("  Start Date  ");
        }
        if (contact.isEmpty())
        {
            missingField.append("  Contact  ");
        }
        if (contactPhone.isEmpty())
        {
            missingField.append("  Contact Phone  ");
        }
    
        AlertWindow("Missing Information", missingField.toString(), " ", AlertType.NONE);
    }
    else
    {
        if (slctdEmp != null)
        {
            int employeeID = slctdEmp.getEmployeeID();
            int depID = slctdEmp.getDepID();

            slctdEmp.setFName(fNameTextField.getText());
            slctdEmp.setEmployeeID(employeeID);
            slctdEmp.setDepID(depID);
            slctdEmp.setLName(lNameTextField.getText());
            slctdEmp.setDepName(depNameTextField.getText());
            slctdEmp.setEmail(emailTextField.getText());
            slctdEmp.setPhone(phoneTextField.getText());
            slctdEmp.setStartDate(startDateTextField.getText());
            slctdEmp.setContact(contactTextField.getText());
            slctdEmp.setContactPhone(contactPhoneTextField.getText());

            boolean update = DBController.editEmployeeInformation(employeeID, slctdEmp.getFName(), slctdEmp.getLName(), depID,
             slctdEmp.getPhone(), slctdEmp.getEmail(), 
             slctdEmp.getContact(), slctdEmp.getContactPhone(),slctdEmp.getStartDate());

             if (update)
             {
                AlertWindow("Success", "You successfully edited the Employee's Information");
                EmployeeInfoTable();

                fNameTextField.clear();
                lNameTextField.clear();
                emailTextField.clear();
                phoneTextField.clear();
                employeeIDTextField.clear();
                depIDTextField.clear();
                startDateTextField.clear();
                contactTextField.clear();
                contactPhoneTextField.clear();
                depNameTextField.clear();
                
             }
             else
             {
                AlertWindow("Fail", "You did not successfully edit the Employee's Information");
             }

        } 
    }
    }

    @FXML
    void homeButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void createScheduleButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("createWeeklySchedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @FXML
    void editInformationButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("editinformation.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @FXML
    void staffButtonOnAction (ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("staff.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void departmentsButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("departments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) throws IOException
     {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
     }

    void AlertWindow(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void AlertWindow(String title, String message, String headerText, AlertType alertType) 
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initialize(URL url, ResourceBundle rb)
    {
        EmployeeInfoTable();
    }
}