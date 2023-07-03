import java.util.Comparator;

public class TaskPrioritySortingComparator implements Comparator<Task> {
    public int compare(Task task1, Task task2) {
        if(task1.getPrioroity().equals(task2.getPrioroity())){
            return 0;
        } else if(task1.getPrioroity().equals(Priority.HIGH)){ 
            return -1;
        } else if(task1.getPrioroity().equals(Priority.MEDUM) && task2.getPrioroity().equals(Priority.LOW)){
            return -1;
        } else {
            return 1;
        }
    }
}
