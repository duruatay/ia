package backend.model;


public class Plan {
    private String name;
    private String day;
    private String start;
    private String end;
    private String owner;

    public Plan(String name, String day, String start, String end, String owner){
        this.name = name;
        this.day = day;
        this.start = start;
        this.end = end;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
