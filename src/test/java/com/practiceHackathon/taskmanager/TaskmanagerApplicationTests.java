package com.practiceHackathon.taskmanager;

import com.practiceHackathon.taskmanager.dto.SubtaskSuggestionResponse;
import com.practiceHackathon.taskmanager.service.GeminiAiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TaskmanagerApplicationTests {

    @Autowired
    private GeminiAiService geminiAiService;

    @Test
    void contextLoads() {
    }

    @Test
    void testAiSubtaskGeneration_BackendTask() {
        SubtaskSuggestionResponse response = geminiAiService.generateSubtasks("Build Spring Boot REST API for tasks");
        assertNotNull(response);
        assertNotNull(response.getSubtasks());
        assertTrue(response.getSubtasks().size() >= 3 && response.getSubtasks().size() <= 6);
        assertFalse(response.getSubtasks().isEmpty());
    }

    @Test
    void testAiSubtaskGeneration_EmptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> geminiAiService.generateSubtasks(""));
    }
}
