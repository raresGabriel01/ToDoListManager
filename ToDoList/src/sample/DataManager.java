package sample;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class DataManager {
    private static DataManager instance = new DataManager();
    private static String file = "ToDoList.txt";

    private List<ToDoItem> toDoList;
    private DateTimeFormatter df;

    public static DataManager getInstance(){
        return instance;
    }

    private DataManager(){
        df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<ToDoItem> getToDoList() {
        return toDoList;
    }

    public void add(ToDoItem item){
        toDoList.add(item);
    }

    public void load() throws IOException {
        toDoList = FXCollections.observableArrayList();
        Path path = Paths.get(file);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{
            while((input = br.readLine()) != null){
                String[] v = input.split("\t");
                String shortDesc = v[0];
                String longDesc = v[1];
                String deadline = v[2];
                LocalDate date = LocalDate.parse(deadline, df);

                ToDoItem item = new ToDoItem(shortDesc, longDesc, date);
                toDoList.add(item);
            }
        }finally {
            if(br != null){
                br.close();
            }
        }

    }

    public void save() throws IOException{
        Path path = Paths.get(file);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<ToDoItem> iter = toDoList.iterator();
            while(iter.hasNext()){
                ToDoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDesc(),
                        item.getLongDesc(),
                        item.getDeadline().format(df)));
                bw.newLine();
            }
        }finally{
            if(bw != null){
                bw.close();
            }
        }

    }



}
