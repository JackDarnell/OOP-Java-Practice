import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

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
                System.out.println("\nOptions are:\n\nc - create task\np date - print tasks by earliest due date\np priority - print tasks in order of highest priority\np hours - print tasks by order of highest priority\nq - quit\no - optomize tasks\n");
            } else if (opt.equals("c")){
                createTaskFlow();
            } else if (opt.equals("p")){
                printTasksUnordered();
            } else if (opt.equals("q")){
                running = false;
            } else if (opt.equals("populate")){
                generateFakeData();
            } else if(opt.equals("p date")){
                sortTasksByDate();
                printTasksUnordered();
            }  else if(opt.equals("o")) {
                optimizeTasks();
                printTasksUnordered();
            } else {
                System.out.println("Invalid option, enter h for help");
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

    static void sortTasksByDate() {
        Collections.sort(tasks, new TaskDateSortingComparator());
    }

    static void printTasksUnordered() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("\nTask #" + i + ":\n\nName: " + tasks.get(i).getName() + "\nDescription: " + tasks.get(i).getDescription()+"\nEstimated Hours: " + tasks.get(i).getEsitmatedHours() +"\nDue Date: "+ tasks.get(i).getDueDate() +"\nPriority: " + tasks.get(i).getPrioroity());
        }
    }

    static void generateFakeData() {
        for(int i = 0; i < 5; i++){
            Priority priority;
            int priorityRandom = ThreadLocalRandom.current().nextInt(0, 3); //exclusive max
            if(priorityRandom == 2){
                priority = Priority.HIGH;
            } else if(priorityRandom == 1){
                priority = Priority.MEDUM;
            } else {
                priority = Priority.LOW;
            }
            Task newTask = new Task("test" + i, "test", priority, 2 + i, LocalDate.of(2022+i, 1, 1));
            tasks.add(newTask);
        }
    }

    static void optimizeTasks() {
        //optomize task algorithm
        //Sort tasks by due date, then if tasks share the same date sort by priority
        //then print the tasks in order to be completed
        //use dict to store tasks with the date as the key and an array list of tasks as the value
        Hashtable<LocalDate, ArrayList<Task>> my_dict = new Hashtable<LocalDate, ArrayList<Task>>();
        for(int i = 0; i < tasks.size(); i++) {
            if(my_dict.containsKey(tasks.get(i).getDueDate())) { //if the key already exists just add to the arraylist.
                my_dict.get(tasks.get(i).getDueDate()).add(tasks.get(i));
            } else { //if the key does not exist, create a new array list and add the task to it
                ArrayList<Task> newTaskList = new ArrayList<Task>();
                newTaskList.add(tasks.get(i));
                my_dict.put(tasks.get(i).getDueDate(), newTaskList);
            }
        }
        //loop through hashtable and sort each arraylist by priority
        for(LocalDate key : my_dict.keySet()) {
            Collections.sort(my_dict.get(key), new TaskPrioritySortingComparator());
        }

        //modify original arraylist to represent the hashtable
        tasks.clear();
        for(LocalDate key : my_dict.keySet()) { //o(n * m) where n is possible dates and m is number of tasks on that date
            for(int i = 0; i < my_dict.get(key).size(); i++) {
                tasks.add(my_dict.get(key).get(i));
            }
        }

    }
}
