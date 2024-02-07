package backend.controller;

import backend.model.Plan;
import backend.service.PlanService;

import java.util.List;

public class PlanController {
    private final PlanService planService;
    private static PlanController instance;

    private PlanController() {
        planService = new PlanService();
    }

    public static PlanController getInstance() {
        if(instance == null) {
            instance = new PlanController();
        }
        return instance;
    }

    public boolean addPlan(String name, String day, String startTime, String endTime) {
        return planService.addPlan(name, day, startTime, endTime);
    }

    public boolean changePlan(String currentName, String newName, String day, String startTime, String endTime) {
        return planService.changePlan(currentName, newName, day, startTime, endTime);
    }

    public Plan getPlanByName(String planName) {
        return planService.getPlanByName(planName);
    }

    public List<Plan> getPlans() {
        return planService.getPlans();
    }

    public List<Plan> getDaughterPlans() {
        return planService.getDaughterPlans();
    }

    public List<Plan> getFatherPlans() {
        return planService.getFatherPlans();
    }
}
