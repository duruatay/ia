package backend.controller;

import backend.model.Plan;
import backend.service.PlanService;

import java.util.List;

public class PlanController {
    private final PlanService planService;

    public PlanController() {
        planService = new PlanService();
    }

    public boolean addPlan(String name, String day, String startTime, String endTime) {
        return planService.addPlan(name, day, startTime, endTime);
    }

    public boolean changePlan(String currentName, String newName, String day, String startTime, String endTime) {
        return planService.changePlan(currentName, newName, day, startTime, endTime);
    }

    public List<Plan> getPlans() {
        return planService.getPlans();
    }
}
