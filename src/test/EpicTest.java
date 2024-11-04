import tasks.Epic;
import tasks.Subtask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EpicTest {

    @Test
    public void testEpicCannotAddItselfAsSubtask() {
        Epic epic = new Epic("Эпик 1", "Описание 1", 1);
        Subtask subtask = new Subtask("Подзадача для эпика 1", "Описание", 2, epic.getId());
        epic.addSubtask(subtask);

        Subtask invalidSubtask = new Subtask("Некорректная подзадача", "Описание", epic.getId(), epic.getId());
        epic.addSubtask(invalidSubtask);

        assertFalse(epic.getSubtasks().contains(invalidSubtask), "Эпик не должен добавляться как подзадача самому себе.");
    }

    @Test
    public void testSubtaskCannotBeEpic() {
        Subtask subtask = new Subtask("Подзадача 1", "Описание", 1, 0);
        Epic epic = new Epic("Эпик 1", "Описание 1", 2);

        assertNotEquals(epic.getId(), subtask.getId(), "Подзадача не может выступать эпиком");
    }
}
