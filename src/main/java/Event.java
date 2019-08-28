import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String at;
    protected Date date;

    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = at;
        date = new SimpleDateFormat("dd/MM/yy HHmm").parse(at);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String getAt() {
        return at;
    }
}