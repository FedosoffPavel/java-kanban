package test.manager;

import main.manager.Managers;
import main.manager.TaskManager;
import main.manager.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InMemoryTaskManagerTest {

    @Test
    public void testTaskManagerInitialization() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "Диспетчер задач должен быть инициализирован.");
    }

    @Test
    public void testAddingTasks() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("Задача 1", "Описание", 1);
        taskManager.createTask(task);

        assertEquals(task, taskManager.getTaskById(1), "Задача должна быть доступна для извлечения по идентификатору.");
    }

    @Test
    public void testTaskIdConflict() {
        TaskManager taskManager = Managers.getDefault();
        Task task1 = new Task("Задача 1", "Описание", 1);
        Task task2 = new Task("Задача 1", "Описание", 1);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        assertEquals(task1, taskManager.getTaskById(1), "Первая задача должна остаться неизменной.");
    }

    @Test
    public void testTaskImmutabilityWhenAdded() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("Задача 1", "Описание", 1);
        taskManager.createTask(task);

        task.setTitle("Новое название задачи");

        assertEquals("Задача 1", taskManager.getTaskById(1).getTitle(), "Название задачи не должно изменяться после добавления.");
    }
}
