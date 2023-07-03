import java.time.LocalDate;

public class Task {

    private String name;
    private String description;
    private Priority priority;
    private int estimatedHours;
    private LocalDate dueDate;

    //constructor
    public Task(String name, String description, Priority priority, int estimatedHours, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.estimatedHours = estimatedHours;
        this.dueDate = dueDate;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPrioroity() {
        return priority;
    }

    public int getEsitmatedHours() {
        return estimatedHours;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPriority(Priority priority){
        this.priority = priority;
    }

    public void setEstimatedHours(int estimatedHours){
        this.estimatedHours = estimatedHours;
    }

    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
}
