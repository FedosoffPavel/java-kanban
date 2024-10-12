import manager.TaskManager;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        createTasks(taskManager);
        createEpicsAndSubtasks(taskManager);

        displayAllTasksAndEpics(taskManager);

        updateTaskStatuses(taskManager);

        System.out.println("\nИзменение статусов");
        displayAllTasksAndEpics(taskManager);

        deleteTasksAndEpics(taskManager);

        System.out.println("\nУдаление");
        displayAllTasksAndEpics(taskManager);
    }

    private static void createTasks(TaskManager taskManager) {
        Task task1 = new Task("Задача 1", "Описание задачи 1", 0);
        Task task2 = new Task("Задача 2", "Описание задачи 2", 0);

        taskManager.createTask(task1);
        taskManager.createTask(task2);
    }

    private static void createEpicsAndSubtasks(TaskManager taskManager) {
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1",0);
        taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача 1 для Эпика 1", "Описание подзадачи 1", 0, epic1.getId());
        Subtask subtask2 = new Subtask("Подзадача 2 для Эпика 1", "Описание подзадачи 2", 0, epic1.getId());

        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);

        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2", 0);
        taskManager.createEpic(epic2);

        Subtask subtask3 = new Subtask("Подзадача 1 для Эпика 2", "Описание подзадачи 1", 0, epic2.getId());
        taskManager.createSubtask(subtask3);
    }

    private static void displayAllTasksAndEpics(TaskManager taskManager) {
        System.out.println("\nВсе задачи:");
        taskManager.getAllTasks().forEach(System.out::println);
        System.out.println("\nВсе эпики:");
        taskManager.getAllEpics().forEach(System.out::println);
        System.out.println("\nВсе подзадачи для Эпика 1:");
        taskManager.getSubtasksOfEpic(3).forEach(System.out::println);
        System.out.println("\nВсе подзадачи для Эпика 2:");
        taskManager.getSubtasksOfEpic(6).forEach(System.out::println);
    }

    private static void updateTaskStatuses(TaskManager taskManager) {
        Task task1 = taskManager.getTaskById(1);
        Subtask subtask1 = taskManager.getSubtaskById(4);
        Subtask subtask2 = taskManager.getSubtaskById(5);
        Subtask subtask3 = taskManager.getSubtaskById(7);

        task1.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(task1);
        subtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);
        subtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask2);
        subtask3.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask3);
    }

    private static void deleteTasksAndEpics(TaskManager taskManager) {
        Task task1 = taskManager.getTaskById(1);
        Epic epic1 = taskManager.getEpicById(3);

        taskManager.deleteTaskById(task1.getId());
        taskManager.deleteEpicById(epic1.getId());
    }
}