package test.manager;

import main.manager.tasks.Subtask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class SubtaskTest {
    @Test
    public void testSubtaskEquality() {
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание 1", 1, 1);
        Subtask subtask2 = new Subtask("Подзадача 1", "Описание 1", 1, 2);
        Subtask subtask3 = new Subtask("Подзадача 2", "Описание 2", 2, 1);

        assertEquals(subtask1, subtask2, "Подзадачи с одинаковым идентификатором должны быть одинаковыми.");
        assertNotEquals(subtask1, subtask3, "Подзадачи с разными идентификаторами не должны быть одинаковыми.");
    }
}
