import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected String by;
    protected Date date;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = by;
        date = new SimpleDateFormat("dd/MM/yy HHmm").parse(by);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getBy() {
        return by;
    }
}