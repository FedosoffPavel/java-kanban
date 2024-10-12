package test.manager;

import main.manager.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskTest {

    @Test
    public void testTaskEquality() {
        Task task1 = new Task("Задача 1", "Описание 1", 1);
        Task task2 = new Task("Задача 1", "Описание 1", 1);
        Task task3 = new Task("Задача 2", "Описание 2", 2);

        assertEquals(task1, task2, "Задачи с одинаковым идентификатором должны быть одинаковыми.");
        assertNotEquals(task1, task3, "Задачи с разными идентификаторами не должны быть одинаковыми.");
    }
}