import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        DataHandler dataHandler = new DataHandler(); // Creates data dir and duke.txt if needed
        ArrayList<Task> tasks = new ArrayList<Task>();; // change to dictionary later
        dataHandler.loadData(tasks);

        int nextIndex = tasks.size();

        Scanner in = new Scanner(System.in);

        while(true){
            String inStr = in.nextLine();
            // List tasks
            if(inStr.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i<nextIndex; i++){
                    System.out.println((i+1) + "." + tasks.get(i).toString());
                }
            }
            // Exit program
            else if(inStr.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            // Done task
            else if(inStr.length() >= 5 && inStr.substring(0, 5).equals("done ")){
                boolean exists = false;
                for(int i=0; i<nextIndex; i++){
                    if(tasks.get(i).getDescription().equals(inStr.substring(5))){
                        exists = true;
                        tasks.get(i).setDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(i).toString());
                    }
                }
                if(!exists){
                    System.out.println("No such task!");
                }
            }
            // Create todo task
            else if(inStr.length() >= 5 && inStr.substring(0, 5).equals("todo ")){
                try { tasks.add(new Todo(inStr.substring(5))); }
                catch(RuntimeException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(nextIndex).toString());
                nextIndex++;
                System.out.println("Now you have " + nextIndex + " tasks in the list.");
            }
            // Create deadline task
            else if(inStr.length() >= 9 && inStr.substring(0, 9).equals("deadline ")){
                int first = inStr.indexOf("/by ");
                if(first == -1){
                    System.out.println("When creating deadline task, require \"/by \" substring in command");
                    continue;
                }
                int second = first + 4;
                String description = inStr.substring(9, first-1);
                String by = inStr.substring(second);
                try { tasks.add(new Deadline(description, by)); }
                catch(RuntimeException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(nextIndex).toString());
                nextIndex++;
                System.out.println("Now you have " + nextIndex + " tasks in the list.");
            }
            // Create event task
            else if(inStr.length() >= 6 && inStr.substring(0, 6).equals("event ")){
                int first = inStr.indexOf("/at ");
                if(first == -1){
                    System.out.println("When creating deadline task, require \"/at \" substring in command");
                    continue;
                }
                int second = first + 4;
                String description = inStr.substring(6, first-1);
                String at = inStr.substring(second);
                try { tasks.add(new Event(description, at)); }
                catch(RuntimeException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(nextIndex).toString());
                nextIndex++;
                System.out.println("Now you have " + nextIndex + " tasks in the list.");
            }
            // Invalid command
            else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
            }
        }
    }
}