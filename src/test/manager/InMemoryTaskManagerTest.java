package test.manager;

import main.manager.Managers;
import main.manager.TaskManager;
import main.manager.tasks.Epic;
import main.manager.tasks.Subtask;
import main.manager.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = Managers.getDefault();
    }

    @Test
    public void testAddingTasks() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("Задача 1", "Описание", 1);
        taskManager.createTask(task);

        assertEquals(task, taskManager.getTaskById(1), "Задача должна быть доступна для извлечения по идентификатору.");
    }

    @Test
    public void testAddingEpics() {
        Epic epic = new Epic("Эпик 1", "Описание эпика", 1);
        taskManager.createEpic(epic);

        assertEquals(epic, taskManager.getEpicById(1), "Эпик должен быть доступен для извлечения по идентификатору.");
    }

    @Test
    public void testAddingSubtasks() {
        Epic epic = new Epic("Эпик 1", "Описание эпика", 1);
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("Подзадача 1", "Описание подзадачи", 2, epic.getId());
        taskManager.createSubtask(subtask);

        assertEquals(subtask, taskManager.getSubtaskById(2), "Подзадача должна быть доступна для извлечения по идентификатору.");
        assertTrue(taskManager.getSubtasksOfEpic(epic.getId()).contains(subtask), "Подзадача должна быть добавлена к эпикам.");
    }

    @Test
    public void testUpdatingTasks() {
        Task task = new Task("Задача 1", "Описание", 1);
        taskManager.createTask(task);

        task.setTitle("Обновленная задача");
        taskManager.updateTask(task);

        assertEquals("Обновленная задача", taskManager.getTaskById(1).getTitle(), "Название задачи должно быть обновлено.");
    }

    @Test
    public void testUpdatingEpics() {
        Epic epic = new Epic("Эпик 1", "Описание эпика", 1);
        taskManager.createEpic(epic);

        epic.setTitle("Обновленный эпик");
        taskManager.updateEpic(epic);

        assertEquals("Обновленный эпик", taskManager.getEpicById(1).getTitle(), "Название эпика должно быть обновлено.");
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
    public void testUpdatingSubtasks() {
        Epic epic = new Epic("Эпик 1", "Описание эпика", 1);
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("Подзадача 1", "Описание подзадачи", 2, epic.getId());
        taskManager.createSubtask(subtask);

        subtask.setTitle("Обновленная подзадача");
        taskManager.updateSubtask(subtask);

        assertEquals("Обновленная подзадача", taskManager.getSubtaskById(2).getTitle(), "Название подзадачи должно быть обновлено.");
    }

    @Test
    public void testDeletingTasks() {
        Task task = new Task("Задача 1", "Описание", 1);
        taskManager.createTask(task);

        taskManager.deleteTaskById(1);
        assertNull(taskManager.getTaskById(1), "Задача должна быть удалена.");
    }

    @Test
    public void testDeletingEpics() {
        Epic epic = new Epic("Эпик 1", "Описание эпика", 1);
        taskManager.createEpic(epic);

        taskManager.deleteEpicById(1);
        assertNull(taskManager.getEpicById(1), "Эпик должен быть удален.");
    }

    @Test
    public void testDeletingSubtasks() {
        Epic epic = new Epic("Эпик 1", "Описание эпика", 1);
        taskManager.createEpic(epic);

        Subtask subtask = new Subtask("Подзадача 1", "Описание подзадачи", 2, epic.getId());
        taskManager.createSubtask(subtask);

        taskManager.deleteSubtaskById(2);
        assertNull(taskManager.getSubtaskById(2), "Подзадача должна быть удалена.");
    }

    @Test
    public void testDeleteAllTasks() {
        Task task1 = new Task("Задача 1", "Описание", 1);
        Task task2 = new Task("Задача 2", "Описание", 2);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        taskManager.deleteAllTasks();
        assertEquals(0, taskManager.getAllTasks().size(), "Все задачи должны быть удалены.");
    }

    @Test
    public void testDeleteAllEpics() {
        Epic epic1 = new Epic("Эпик 1", "Описание эпика", 1);
        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2", 2);

        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);

        taskManager.deleteAllEpics();
        assertEquals(0, taskManager.getAllEpics().size(), "Все эпики должны быть удалены.");
    }

    @Test
    public void testDeleteAllSubtasks() {
        Epic epic = new Epic("Эпик 1", "Описание эпика", 1);
        taskManager.createEpic(epic);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи", 2, epic.getId());
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", 3, epic.getId());
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);

        taskManager.deleteAllSubtasks();
        assertEquals(0, taskManager.getAllSubtasks().size(), "Все подзадачи должны быть удалены.");
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task("Задача 1", "Описание", 1);
        Task task2 = new Task("Задача 2", "Описание", 2);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        List<Task> tasks = taskManager.getAllTasks();
        assertEquals(2, tasks.size(), "Должно быть 2 задачи.");
    }

    @Test
    public void testGetAllEpics() {
        Epic epic1 = new Epic("Эпик 1", "Описание эпика", 1);
        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2", 2);

        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);

        List<Epic> epics = taskManager.getAllEpics();
        assertEquals(2, epics.size(), "Должно быть 2 эпика.");
    }

    @Test
    public void testGetAllSubtasks() {
        Epic epic = new Epic("Эпик 1", "Описание эпика", 1);
        Epic epic2 = new Epic("Эпик 2", "Описание эпика", 2);
        taskManager.createEpic(epic);
        taskManager.createEpic(epic2);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи", 3, epic.getId());
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", 4, epic.getId());
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 3", 5, epic.getId());
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);
        taskManager.createSubtask(subtask3);

        List<Subtask> subtasks = taskManager.getAllSubtasks();
        assertEquals(3, subtasks.size(), "Должно быть 3 подзадачи.");
    }

    @Test
    public void testTaskImmutabilityWhenAdded() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("Задача 1", "Описание", 1);
        taskManager.createTask(task);

        Task newTask = new Task("Новое название задачи", "Описание", 1);

        assertEquals("Задача 1", taskManager.getTaskById(1).getTitle(), "Название задачи не должно изменяться после добавления.");
        assertNotEquals(newTask.getTitle(), taskManager.getTaskById(1).getTitle(), "Название задачи не должно изменяться после добавления.");
    }

    @Test
    public void testGetNullTask() {
        TaskManager taskManager = Managers.getDefault();

        assertEquals(null, taskManager.getTaskById(567), "Задача с несуществующим идентификатором должна возвращать null.");
    }
}
