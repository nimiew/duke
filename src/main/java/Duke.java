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

        String[] tasks = new String[100];
        int nextIndex = 0;

        Scanner in = new Scanner(System.in);

        while(true){
            String inStr = in.nextLine();
            if(inStr.equals("list")){
                for(int i=0; i<nextIndex; i++){
                    System.out.println((i+1) + ". " + tasks[i]);
                }
            }
            else if(inStr.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else{
                tasks[nextIndex] = inStr;
                nextIndex++;
                System.out.println("added: " + inStr);
            }
        }
    }
}