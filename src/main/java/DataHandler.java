import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;

public class DataHandler {
    protected Path mainPath;
    protected Path dataPath;
    protected File dataPathFile;
    protected Path dukeDataPath;
    protected File dukeDataPathFile;

    public DataHandler(){
        mainPath = Paths.get(System.getProperty("user.dir"));
        dataPath = Paths.get(mainPath.toString(), "data", "");
        dataPathFile = new File(dataPath.toString()); // dataPathFile is dataPath, but changed type for File methods.
        assertDataDir();
        dukeDataPath = Paths.get(dataPath.toString(), "duke.txt");
        dukeDataPathFile = new File(dukeDataPath.toString());
        assertDukeData();
    }

    public void assertDataDir(){
        if (!dataPathFile.exists()){
            dataPathFile.mkdirs();
            System.out.println("data directory created");
        }
    }

    public void assertDukeData(){
        if (!dukeDataPathFile.exists()){
            try{
                dukeDataPathFile.createNewFile();
                System.out.println("duke.txt created");
            }
            catch(Exception e) {
                System.out.println("Error creating duke.txt");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void loadData(ArrayList<Task> tasks){
        BufferedReader reader;
        int taskIdx = 0;
        try {
            reader = new BufferedReader(new FileReader(dukeDataPath.toString()));
            String line = reader.readLine();
            while (line != null) {
                String taskType = getTask(line.charAt(0)); // Perform data check too
                boolean isDone = getDone(line.charAt(4)); // Perform data check too
                boolean isTodo = taskType.equals("todo")? true : false;
                int sep = isTodo? line.length() : line.substring(8).indexOf('|') - 1 + 8;; // index of whitespace before '|'
                String description = line.substring(8, sep);;
                String date = isTodo? "" : line.substring(sep+3);
                switch (taskType){
                    case "todo":
                        tasks.add(new Todo(description));
                        tasks.get(taskIdx).setDone(isDone);
                        break;
                    case "deadline":
                        tasks.add(new Deadline(description, date));
                        tasks.get(taskIdx).setDone(isDone);
                        break;
                    case "event":
                        tasks.add(new Event(description, date));
                        tasks.get(taskIdx).setDone(isDone);
                        break;
                }
                taskIdx++;
                line = reader.readLine();
            }
            reader.close();
        } catch (ParseException e) {
            System.out.println("Please ensure date is in dd/MM/yyyy HHmm format.");

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void saveData(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(dukeDataPath.toString());
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            char taskType = task.getClass().getName().charAt(0);
            boolean isDone = task.isDone;
            int isDoneInt = isDone? 1 : 0;
            String description = task.getDescription();
            String line = null;
            if(taskType == 'T'){
                line = taskType + " | " + isDoneInt + " | " + description;
            }
            else if(taskType == 'D'){
                Deadline deadline = (Deadline) task;
                line = taskType + " | " + isDoneInt + " | " + description + " | " + deadline.getBy();
            }
            else if(taskType == 'E'){
                Event event = (Event) task;
                line = taskType + " | " + isDoneInt + " | " + description + " | " + event.getAt();
            }
            fw.write(line);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public String getTask(char x){
        if(x == 'T'){
            return "todo";
        }
        else if(x == 'D'){
            return "deadline";
        }
        else if(x == 'E'){
            return "event";
        }
        else{
            System.out.println("Data (type of task) does not adhere to format.");
            System.exit(1);
        }
        return ""; // remove warning
    }

    public boolean getDone(char x){
        if(x == '1'){
            return true;
        }
        else if(x == '0'){
            return false;
        }
        else{
            System.out.println("Data (is Done) does not adhere to format.");
            System.exit(1);
        }
        return false; // remove warning
    }
}
