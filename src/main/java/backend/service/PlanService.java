package backend.service;

import backend.config.SessionInfo;
import backend.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class PlanService {
    List<Plan> plans;

    public PlanService() {
        // TODO: Read the data in plans table of the database to the list
        plans = new ArrayList<>();
        plans.add(new Plan("Play Tennis", "Wednesday", "09:45", "13:30", "daughter"));
    }

    public boolean addPlan(String name, String day, String startTime, String endTime) {
        // TODO: Add validation logic on the input data. If valid add the plan and return true, else return false.
        Plan plan;
        if(SessionInfo.DAUGHTER_SESSION) {
            plan = new Plan(name, day, startTime, endTime, "daughter");
        } else {
            plan = new Plan(name, day, startTime, endTime, "father");
        }
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

    public List<Plan> getDaughterPlans() {
        List<Plan> daughterPlans = new ArrayList<>();
        for(Plan p: plans) {
            if(p.getOwner().equals("daughter")) {
                daughterPlans.add(p);
            }
        }
        return daughterPlans;
    }

    public List<Plan> getFatherPlans() {
        List<Plan> fatherPlans = new ArrayList<>();
        for(Plan p: plans) {
            if(p.getOwner().equals("father")) {
                fatherPlans.add(p);
            }
        }
        return fatherPlans;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
}
