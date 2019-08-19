import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
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
                    System.out.println((i+1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
            }
            else if(inStr.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(inStr.length() >= 4 && inStr.substring(0, 4).equals("done")){
                boolean exists = false;
                for(int i=0; i<nextIndex; i++){
                    if(tasks[i].getDescription().equals(inStr.substring(5))){
                        exists = true;
                        tasks[i].setDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                    }
                }
                if(!exists){
                    System.out.println("No such task!");
                }
            }
            else{
                tasks[nextIndex] = new Task(inStr);
                System.out.println("added: " + inStr);
                nextIndex++;
            }
        }
    }
}