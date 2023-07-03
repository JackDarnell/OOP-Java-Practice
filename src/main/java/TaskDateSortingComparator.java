import java.util.Comparator;

public class TaskDateSortingComparator implements Comparator<Task> {
    public int compare(Task task1, Task task2) {
        return task1.getDueDate().compareTo(task2.getDueDate());
    }
}
