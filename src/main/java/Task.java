
public class Task {

    private String name;
    private String description;
    private Priority priority;
    private int estimatedHours;

    //constructor
    public Task(String name, String description, Priority priority, int estimatedHours) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.estimatedHours = estimatedHours;
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
}
