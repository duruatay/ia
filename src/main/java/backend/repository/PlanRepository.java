package backend.repository;

import backend.config.SessionInfo;
import backend.model.Plan;
import backend.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanRepository {
    private Connection dbConn;

    public PlanRepository() {
        dbConn = DBConnection.getDBConnection();
    }

    public void createPlan(Plan plan) {
        String query = """
                INSERT INTO Plan (name, day, start, end, owner) VALUES ("DEFAULT_NAME", "DEFAULT_DAY", "DEFAULT_START", "DEFAULT_END", "DEFAULT_OWNER");
                """;
        query = query.replace("DEFAULT_NAME", plan.getName());
        query = query.replace("DEFAULT_DAY", plan.getDay());
        query = query.replace("DEFAULT_START", plan.getStart());
        query = query.replace("DEFAULT_END", plan.getEnd());
        query = query.replace("DEFAULT_OWNER", plan.getOwner());

        try {
            dbConn.createStatement().executeUpdate(query);
            System.out.println("Created new Plan " + plan.getName() + " successfully");
        } catch (SQLException e) {
            System.err.println("SQL EXCEPTION " + e.getMessage());
        }
    }

    public List<Plan> getAllPlans() {
        List<Plan> plans = new ArrayList<>();
        String query = """
                SELECT * FROM Plan;
                """;

        try {
            ResultSet resultSet = dbConn.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Plan plan = new Plan(resultSet.getString("name"),
                        resultSet.getString("day"),
                        resultSet.getString("start"),
                        resultSet.getString("end"),
                        resultSet.getString("owner"));
                plan.setId(Integer.parseInt(resultSet.getString("id")));
                plans.add(plan);
            }
        } catch (SQLException e) {
            System.err.println("SQL EXCEPTION " + e.getMessage());
        }

        return plans;
    }

    public void updatePlan(Plan plan) {
        String query = """
                UPDATE Plan
                SET
                    name = "NEW_NAME",
                    day = "NEW_DAY",
                    start = "NEW_START",
                    end = "NEW_END",
                    owner = "NEW_OWNER"
                WHERE id = TARGET_ID;
                """;
        query = query.replace("NEW_NAME", plan.getName());
        query = query.replace("NEW_DAY", plan.getDay());
        query = query.replace("NEW_START", plan.getStart());
        query = query.replace("NEW_END", plan.getEnd());
        query = query.replace("NEW_OWNER", plan.getOwner());
        query = query.replace("TARGET_ID", String.valueOf(plan.getId()));

        try {
            dbConn.createStatement().executeUpdate(query);
            System.out.println("Updated the Plan " + plan.getName() + " successfully");
        } catch (SQLException e) {
            System.err.println("SQL EXCEPTION " + e.getMessage());
        }
    }

    public void deletePlan(Plan plan) {
        String query = """
                    DELETE FROM Plan WHERE id = TARGET_ID;
                """;

        query = query.replace("TARGET_ID", String.valueOf(plan.getId()));
        try {
            dbConn.createStatement().executeUpdate(query);
            System.out.println("Deleted the Plan " + plan.getName() + " successfully");
        } catch (SQLException e) {
            System.err.println("SQL EXCEPTION " + e.getMessage());
        }
    }

    public void deleteAllPlans() {
        String query = """
                    DELETE FROM Plan WHERE owner = "DEFAULT_OWNER";
                """;
        if(SessionInfo.DAUGHTER_SESSION) {
            query = query.replace("DEFAULT_OWNER", "daughter");
        } else {
            query = query.replace("DEFAULT_OWNER", "father");
        }
        try {
            dbConn.createStatement().executeUpdate(query);
            System.out.println("Deleted the all plans successfully");
        } catch (SQLException e) {
            System.err.println("SQL EXCEPTION " + e.getMessage());
        }
    }
}
