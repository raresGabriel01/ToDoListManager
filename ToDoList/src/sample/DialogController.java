package sample;


import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDesc;
    @FXML
    private TextArea longDesc;
    @FXML
    private DatePicker deadline;
    @FXML
    public void saveDialog(){
        LocalDate d;
        if(deadline.getValue() == null){
            d = LocalDate.now();
        }else {
            d = deadline.getValue();
        }
        String sd, ld;
        if(shortDesc.getText().trim().isEmpty()){
            sd = "empty";
        }else{
            sd = shortDesc.getText().trim();
        }

        if(longDesc.getText().trim().isEmpty()){
            ld = "empty";
        }else{
            ld = longDesc.getText().trim();
        }

        ToDoItem item = new ToDoItem(sd,ld, d);
        DataManager.getInstance().add(item);
    }
}
