package backend.model;


import java.time.LocalTime;

public class Plan {
    private String name;
    private String day;
    private LocalTime start;
    private LocalTime end;

    public Plan(String name, String day, LocalTime start, LocalTime end){
        this.name = name;
        this.day = day;
        this.start = start;
        this.end = end;
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

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

}
