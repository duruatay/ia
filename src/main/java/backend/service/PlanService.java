package backend.service;

import backend.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class PlanService {
    List<Plan> plans;

    public PlanService() {
        plans = new ArrayList<>();
    }

    public boolean addPlan(String name, String day, String startTime, String endTime) {
        // TODO: Add validation logic on the input data. If valid add the plan and return true, else return false.
        Plan plan = new Plan(name, day, startTime, endTime);
        plans.add(plan);
        return true;
    }

    public boolean changePlan(String currentName, String newName, String day, String startTime, String endTime) {
        // TODO: Add validation logic on the input data. If valid add the plan and return true, else return false.
        for(Plan plan: plans) {
            if(currentName.equals(plan.getName())) {
                plan.setName(newName);
                plan.setDay(day);
                plan.setStart(startTime);
                plan.setEnd(endTime);
                return true;
            }
        }
        return false;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
}
