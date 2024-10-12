package test.manager;

import main.manager.tasks.Epic;
import main.manager.tasks.Subtask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EpicTest {
    @Test
    public void testEpicCannotAddItselfAsSubtask() {
        Epic epic = new Epic("Эпик 1", "Описание 1", 1);
        Subtask subtask = new Subtask("Подзадача для этипа 1", "Описание", 2, epic.getId());

        assertNotEquals(epic.getId(), subtask.getEpicId(), "Эпик не может быть добавлена в качестве отдельной подзадачи");
    }

    @Test
    public void testSubtaskCannotBeEpic() {
        Subtask subtask = new Subtask("Подзадача 1", "Описание", 1, 0);
        Epic epic = new Epic("Эпик 1", "Описание 1", 2);

        assertNotEquals(epic.getId(), subtask.getId(), "Подзадача не может выступать эпиком");
    }
}
