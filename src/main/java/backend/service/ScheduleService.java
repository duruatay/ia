package backend.service;

import backend.model.Plan;

import java.util.ArrayList;

public class ScheduleService {
    private ArrayList<Plan> plans;

    public ScheduleService(){
        plans = new ArrayList<>();
    }

    public void addPlan(Plan plan){
        plans.add(plan);
    }
}
