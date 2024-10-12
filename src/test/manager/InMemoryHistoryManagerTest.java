package test.manager;

import main.manager.InMemoryHistoryManager;
import main.manager.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryHistoryManagerTest {private InMemoryHistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void testAddTask() {
        Task task1 = new Task("Задача 1", "Описание 1", 1);
        Task task2 = new Task("Задача 2", "Описание 2", 2);

        historyManager.add(task1);
        historyManager.add(task2);

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size(), "Размер истории должен быть 2");
        assertEquals(task1, history.get(0), "Первая задача в истории должна быть task1");
        assertEquals(task2, history.get(1), "Вторая задача в истории должна быть task2");
    }

    @Test
    void testGetHistory() {
        Task task1 = new Task("Задача 1", "Описание 1", 1);
        Task task2 = new Task("Задача 2", "Описание 2", 2);

        historyManager.add(task1);
        historyManager.add(task2);

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size(), "Размер истории должен быть 2");
        assertTrue(history.contains(task1), "История должна содержать Задача 1");
        assertTrue(history.contains(task2), "История должна содержать Задача 2");
    }

    @Test
    void testLimitHistorySize() {
        for (int i = 1; i <= 11; i++) {
            Task task = new Task("Задача " + i, "Описание " + i, i);
            historyManager.add(task);
        }

        List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size(), "Размер истории должен быть ограничен до 10");
        assertEquals("Задача 2", history.get(0).getTitle(), "Первая задача в истории должна быть Задача 2");
        assertEquals("Задача 11", history.get(9).getTitle(), "Последняя задача в истории должна быть Задача 11");
    }
}
