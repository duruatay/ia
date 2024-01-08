package backend.controller;

import backend.model.Plan;
import backend.service.ScheduleService;

import java.time.LocalTime;

public class ScheduleController {

    private ScheduleService scheduleService;
    public ScheduleController(){
        scheduleService = new ScheduleService();
    }
    public void addPlan(String name, String day, LocalTime start, LocalTime end){
        scheduleService.addPlan(new Plan(name, day, start, end));
    }
}
