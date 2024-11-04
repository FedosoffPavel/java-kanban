package tests;

import main.InMemoryHistoryManager;
import main.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryHistoryManagerTest {
    private InMemoryHistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void testEmptyHistory() {
        List<Task> history = historyManager.getHistory();
        assertTrue(history.isEmpty(), "История должна быть пустой при инициализации");
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
    void testAddDuplicateTask() {
        Task task = new Task("Задача 1", "Описание 1", 1);
        historyManager.add(task);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "Размер истории должен быть 1, так как задача добавлена повторно");
        assertEquals(task, history.get(0), "Задача должна быть последней в истории после повторного добавления");
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
    void testRemoveTask() {
        Task task1 = new Task("Задача 1", "Описание 1", 1);
        Task task2 = new Task("Задача 2", "Описание 2", 2);

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.remove(1);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "Размер истории должен быть 1 после удаления");
        assertEquals(task2, history.get(0), "Оставшаяся задача должна быть task2");
    }

    @Test
    void testRemoveNonExistentTask() {
        Task task1 = new Task("Задача 1", "Описание 1", 1);
        historyManager.add(task1);

        historyManager.remove(999);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "Размер истории не должен измениться при удалении несуществующей задачи");
        assertEquals(task1, history.get(0), "Оставшаяся задача должна быть task1");
    }
}
