package sample;

import java.time.LocalDate;

public class ToDoItem implements Comparable<ToDoItem>{
    private String shortDesc;
    private String longDesc;
    private LocalDate deadline;

    public ToDoItem(String shortDesc, String longDesc, LocalDate deadline){
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.deadline = deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
    @Override
    public String toString(){
        return shortDesc;
    }
    @Override
    public int compareTo(ToDoItem item){
        return getDeadline().compareTo(item.getDeadline());
    }
}
