package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Controller {
    private List<ToDoItem> toDoList;
    @FXML
    private ListView<ToDoItem>toDoListView;
    @FXML
    private TextArea details;
    @FXML
    private Label deadlineDate;
    @FXML
    private BorderPane mainBP;
    @FXML
    private Button saveBtn;
    public void initialize(){
        List<ToDoItem> L = DataManager.getInstance().getToDoList();
        Collections.sort(L);
        toDoListView.getItems().setAll(L);
        toDoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    public void handleClickListViewItem(){
        ToDoItem item =  toDoListView.getSelectionModel().getSelectedItem();
        try{
            details.setText(item.getLongDesc());
        } catch(NullPointerException e){
            details.setText("");
        }
        try {
            deadlineDate.setText(item.getDeadline().toString());
        }catch(NullPointerException e){
            deadlineDate.setText(LocalDate.now().toString());
        }
    }

    @FXML
    public void showNewDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBP.getScene().getWindow());
        dialog.setTitle("");
        dialog.setHeaderText("Add something on your to do list");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dialog.fxml"));
        try{

            dialog.getDialogPane().setContent(loader.load());
        }catch(IOException e){
            System.out.println("Oops, something happened...");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            DialogController controller = loader.getController();
            controller.saveDialog();
            List<ToDoItem> L = DataManager.getInstance().getToDoList();
            Collections.sort(L);
            toDoListView.getItems().setAll(L);
        }
    }
    @FXML
    public void deleteSelectedItem(){
        try{
            int i = toDoListView.getSelectionModel().getSelectedIndex();
            DataManager.getInstance().getToDoList().remove(i);
            List<ToDoItem> L = DataManager.getInstance().getToDoList();
            Collections.sort(L);
            toDoListView.getItems().setAll(L);
            details.setText("");
            deadlineDate.setText("");
        }catch(IndexOutOfBoundsException e) {

        }

    }
    public void saveContent(){
        try{
            int i = toDoListView.getSelectionModel().getSelectedIndex();
            toDoListView.getItems().get(i).setLongDesc(details.getText());
        } catch(NullPointerException e){

        }
    }
}
