package Controllers;

import Models.Course;
import Models.Student;
import Utilities.MagicData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentUpdateController implements Initializable {

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> studentNumCol;

    @FXML
    private TableColumn<Student, String> firstNameCol;

    @FXML
    private TableColumn<Student, String> lastNameCol;

    @FXML
    private TableColumn<Student, String> avgGradeCol;

    @FXML
    private TableColumn<Student, Integer> numOfCoursesCol;

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<Course> coursesComboBox;

    @FXML
    private Spinner<Integer> gradeSpinner;

    @FXML
    private Label rowsReturnedLabel;

    @FXML
    private Label studentSelectedLabel;

    @FXML
    private Button addCourseButton;

    private ArrayList<Student> allStudents;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allStudents = MagicData.getStudents();
        studentNumCol.setCellValueFactory(new PropertyValueFactory<>("studNum"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        avgGradeCol.setCellValueFactory(new PropertyValueFactory<>("avgGradeString"));
        numOfCoursesCol.setCellValueFactory(new PropertyValueFactory<>("numOfCourses"));
        tableView.getItems().addAll(MagicData.getStudents());

        // Configure the selection model
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldStudent, newStudent)->{
           studentSelectedLabel.setText(newStudent.toString());
        });

        //Configure the combobox
        coursesComboBox.setPromptText("Select a course");
        coursesComboBox.getItems().addAll(MagicData.getCourseCodes());

        //configure the spinner object
        SpinnerValueFactory<Integer> gradeFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 75);
        gradeSpinner.setValueFactory(gradeFactory);
        gradeSpinner.setEditable(true);
        TextField spinnerEditor = gradeSpinner.getEditor();
        spinnerEditor.textProperty().addListener((observableValue, oldValue, newValue)->
        {
            try {
                Integer.parseInt(newValue);
            } catch (NumberFormatException e)
            {
                spinnerEditor.setText(oldValue);
            }
        });

        searchTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            ArrayList<Student> filtered = new ArrayList<>();

            // loop over the students and see which ones contain the text from the
            // search field (searchTextField)
            for(Student student: allStudents){
                if(student.contains(searchTextField.getText()))
                    filtered.add(student);
            }

            // clear out the students from the tableview and add only the filtered ones
            tableView.getItems().clear();
            tableView.getItems().addAll(filtered);
            updateLabels();
        });
        updateLabels();
    }

    private void updateLabels()
    {
        rowsReturnedLabel.setText("Rows returned: " + tableView.getItems().size());
    }

    @FXML
    private void addGrade()
    {
        Student student = tableView.getSelectionModel().getSelectedItem();
        Course course = coursesComboBox.getValue();
        int grade = gradeSpinner.getValue();

        if (student != null && course != null && grade>=0 && grade <= 100)
        {
            student.addCourse(course, grade);
        }
        updateLabels();
    }
}
