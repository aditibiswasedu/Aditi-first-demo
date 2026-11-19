package com.practiceHackathon.taskmanager.service;

import com.practiceHackathon.taskmanager.dto.SubtaskSuggestionResponse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to generate actionable subtasks for task management.
 * Operates offline with an intelligent rule-based suggestion engine.
 */
@Service
public class GeminiAiService {

    /**
     * Generates 3 to 6 relevant, actionable subtasks based on the supplied task description.
     * Works offline without requiring an external API key or network connection.
     *
     * @param taskDescription The description of the task to break down.
     * @return SubtaskSuggestionResponse containing original task and generated subtasks.
     */
    public SubtaskSuggestionResponse generateSubtasks(String taskDescription) {
        if (taskDescription == null || taskDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be null or empty.");
        }

        String trimmed = taskDescription.trim();
        String lower = trimmed.toLowerCase();
        List<String> subtasks = new ArrayList<>();

        if (containsAny(lower, "test", "testing", "qa", "bug", "verify", "validation")) {
            subtasks.add("Define test scenarios and edge case criteria");
            subtasks.add("Write automated unit tests for core methods");
            subtasks.add("Execute integration testing across components");
            subtasks.add("Log, triage, and verify bug fixes");
            subtasks.add("Generate test execution report and check code coverage");
        } else if (containsAny(lower, "backend", "api", "database", "sql", "endpoint", "crud", "server", "rest")) {
            subtasks.add("Design relational schema and JPA entities");
            subtasks.add("Implement repository layer with required query methods");
            subtasks.add("Write service business logic and validation rules");
            subtasks.add("Create REST controller endpoints and map HTTP status codes");
            subtasks.add("Test endpoints with request.http or Postman collection");
        } else if (containsAny(lower, "frontend", "ui", "ux", "design", "css", "html", "react", "component", "page")) {
            subtasks.add("Draft wireframes and visual design mockups");
            subtasks.add("Set up component structure and layout styling");
            subtasks.add("Implement user interaction and form validation");
            subtasks.add("Integrate backend REST API endpoints and state handling");
            subtasks.add("Verify responsive design across mobile and desktop screens");
        } else if (containsAny(lower, "hackathon", "event", "organize", "plan", "meeting", "workshop")) {
            subtasks.add("Define project goals, timeline, and feature milestones");
            subtasks.add("Assign roles and responsibilities across team members");
            subtasks.add("Prepare pitch deck and live demo scenario");
            subtasks.add("Build and polish Minimum Viable Product (MVP)");
            subtasks.add("Conduct final rehearsal and review judging criteria");
        } else if (containsAny(lower, "deploy", "docker", "cloud", "ci/cd", "pipeline", "release", "production")) {
            subtasks.add("Configure application properties and secure credentials");
            subtasks.add("Create container configuration (Dockerfile)");
            subtasks.add("Set up automated build and CI/CD deployment pipeline");
            subtasks.add("Execute smoke tests on staging environment");
            subtasks.add("Monitor system logs and performance metrics post-deployment");
        } else {
            // General intelligent breakdown adaptive to any input description
            subtasks.add("Define scope, objectives, and success criteria for: " + truncate(trimmed, 50));
            subtasks.add("Gather necessary resources, dependencies, and reference materials");
            subtasks.add("Implement the core solution step-by-step");
            subtasks.add("Review, test for edge cases, and refine output quality");
            subtasks.add("Document results and share progress update with stakeholders");
        }

        return new SubtaskSuggestionResponse(trimmed, subtasks);
    }

    private boolean containsAny(String text, String... keywords) {
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength).trim() + "...";
    }
}
