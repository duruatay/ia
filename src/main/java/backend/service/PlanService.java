package backend.service;

import backend.config.SessionInfo;
import backend.model.Plan;
import backend.repository.PlanRepository;

import java.util.ArrayList;
import java.util.List;

public class PlanService {
    private PlanRepository planRepository;
    private List<Plan> plans;

    public PlanService() {
        planRepository = new PlanRepository();
        plans = planRepository.getAllPlans();
    }

    public boolean addPlan(String name, String day, String startTime, String endTime) {
        Plan plan;
        if(SessionInfo.DAUGHTER_SESSION) {
            plan = new Plan(name, day, startTime, endTime, "daughter");
        } else {
            plan = new Plan(name, day, startTime, endTime, "father");
        }
        plans.add(plan);
        planRepository.createPlan(plan);
        return true;
    }

    public boolean changePlan(String currentName, String newName, String day, String startTime, String endTime) {
        String owner = "father";
        if(SessionInfo.DAUGHTER_SESSION) {
            owner = "daughter";
        }

        for(Plan plan: plans) {
            if(currentName.equals(plan.getName()) && plan.getOwner().equals(owner)) {
                plan.setName(newName);
                plan.setDay(day);
                plan.setStart(startTime);
                plan.setEnd(endTime);
                planRepository.updatePlan(plan);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public List<Plan> getDaughterPlans() {
        plans = planRepository.getAllPlans();
        List<Plan> daughterPlans = new ArrayList<>();
        for(Plan p: plans) {
            if(p.getOwner().equals("daughter")) {
                daughterPlans.add(p);
            }
        }
        return daughterPlans;
    }

    public List<Plan> getFatherPlans() {
        plans = planRepository.getAllPlans();
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

    public Plan getPlanByName(String planName) {
        for(Plan p: plans) {
            if(p.getName().equalsIgnoreCase(planName)) {
                return p;
            }
        }
        return null;
    }

    public void deletePlan(Plan plan) {
        planRepository.deletePlan(plan);
        plans.remove(plan);
    }

    public List<Plan> getFilteredPlans(List<String> planNames) {
        List<Plan> plans = planRepository.getAllPlans();
        List<Plan> filteredPlans = new ArrayList<>();

        for(Plan plan: plans) {
            if(planNames.contains(plan.getName())) {
                filteredPlans.add(plan);
            }
        }
        return filteredPlans;
    }

    public void clearAllDaughterPlans() {
        plans.removeAll(getDaughterPlans());
        planRepository.deleteAllPlans();
    }

    public void clearAllFatherPlans() {
        plans.removeAll(getFatherPlans());
        planRepository.deleteAllPlans();
    }
}
