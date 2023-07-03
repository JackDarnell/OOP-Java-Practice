import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

//options:
//create task, print tasks by earliest due date, print tasks by priority, print tasks by highest/lowest estimated hours, help
public class App 
{
    static boolean running = true;
    static Scanner scanner= new Scanner(System.in);

    static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main( String[] args )
    {
        while(running == true){
            System.out.println("Enter an option: (h for help)\n");
            String opt = scanner.nextLine();
            if(opt.equals("h")){
                System.out.println("\nOptions are:\n\nc - create task\np date - print tasks by earliest due date\np priority - print tasks in order of highest priority\np hours - print tasks by order of highest priority\nq - quit\n");
            } else if (opt.equals("c")){
                createTaskFlow();
            } else if (opt.equals("p")){
                printTasksUnordered();
            } else if (opt.equals("q")){
                running = false;
            }
        }
          // Create a Scanner object
    }

    static void createTaskFlow() { //TODO: return error if failed
        System.out.println("Enter the task name");
        String name = scanner.nextLine();
        System.out.println("Enter task description");
        String description = scanner.nextLine();
        Priority priority;
        System.out.println("Enter priority (h - high, m - medium, l - low)");
        while(true){
            String priorityInput = scanner.nextLine();
            if(priorityInput.equals("h")){
                priority = Priority.HIGH;
                break;
            } else if(priorityInput.equals("l")){
                priority = Priority.LOW;
                break;
            } else if(priorityInput.equals("m")){
                priority = Priority.MEDUM;
                break;
            } else {
                System.out.println("Incorrect value, enter either h, m, or l");
            }
        }
        int estimatedHours;
        while(true) {
            System.out.println("Enter estimated number of hours");
            String numHours = scanner.nextLine();
            try {
                estimatedHours = Integer.parseInt(numHours);
                break;
            } catch (Exception e) {
                System.out.println("please enter a number");
            }
        }
        System.out.println("enter a due date in the format: YYYY DD MM");
        String dateInput = scanner.nextLine();
        String dateParts[] = dateInput.split(" ");
        int year = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int month = Integer.parseInt(dateParts[2]);
        LocalDate dueDate = LocalDate.of(year, month, day);

        Task newTask = new Task(name, description, priority, estimatedHours, dueDate);
        tasks.add(newTask);
    }

    static void printTasksUnordered() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i));
        }
    }
}
