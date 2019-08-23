import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Task[] tasks = new Task[100];
        int nextIndex = 0;

        Scanner in = new Scanner(System.in);

        while(true){
            String inStr = in.nextLine();
            if(inStr.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i<nextIndex; i++){
                    System.out.println((i+1) + "." + tasks[i].toString());
                }
            }
            else if(inStr.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(inStr.length() >= 5 && inStr.substring(0, 5).equals("done ")){
                boolean exists = false;
                for(int i=0; i<nextIndex; i++){
                    if(tasks[i].getDescription().equals(inStr.substring(5))){
                        exists = true;
                        tasks[i].setDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks[i].toString());
                    }
                }
                if(!exists){
                    System.out.println("No such task!");
                }
            }
            else if(inStr.length() >= 5 && inStr.substring(0, 5).equals("todo ")){
                try { tasks[nextIndex] = new Todo(inStr.substring(5)); }
                catch(RuntimeException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[nextIndex].toString());
                nextIndex++;
                System.out.println("Now you have " + nextIndex + " tasks in the list.");
            }
            else if(inStr.length() >= 9 && inStr.substring(0, 9).equals("deadline ")){
                int first = inStr.indexOf("/by ");
                if(first == -1){
                    System.out.println("When creating deadline task, require \"/by \" substring in command");
                    continue;
                }
                int second = first + 4;
                String description = inStr.substring(9, first-1);
                String by = inStr.substring(second);
                try { tasks[nextIndex] = new Deadline(description, by); }
                catch(RuntimeException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[nextIndex].toString());
                nextIndex++;
                System.out.println("Now you have " + nextIndex + " tasks in the list.");
            }
            else if(inStr.length() >= 6 && inStr.substring(0, 6).equals("event ")){
                int first = inStr.indexOf("/at ");
                if(first == -1){
                    System.out.println("When creating deadline task, require \"/at \" substring in command");
                    continue;
                }
                int second = first + 4;
                String description = inStr.substring(6, first-1);
                String at = inStr.substring(second);
                try { tasks[nextIndex] = new Event(description, at); }
                catch(RuntimeException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[nextIndex].toString());
                nextIndex++;
                System.out.println("Now you have " + nextIndex + " tasks in the list.");
            }
            else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
            }
        }
    }
}