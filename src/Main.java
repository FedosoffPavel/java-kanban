public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Задача 1", "Прочистить ковер", 0);
        Task task2 = new Task("Задача 2", "Помыть пол", 0);
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        Epic epic1 = new Epic("Уроки", "Домашнее задание в практикуме", 0);
        taskManager.createEpic(epic1);
        Subtask subtask1 = new Subtask("Подзадача 1", "Прочитать ТЗ", 0, epic1.getId());
        Subtask subtask2 = new Subtask("Подзадача 2", "Написать код", 0, epic1.getId());
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);

        Epic epic2 = new Epic("Проверка ДЗ", "Ожидание проверки ДЗ", 0);
        taskManager.createEpic(epic2);
        Subtask subtask3 = new Subtask("Ожидание", "Лупим в монитор и обновляем страницу со статусом проверки ДЗ", 0, epic2.getId());
        taskManager.createSubtask(subtask3);

        System.out.println("Задачи:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("Эпики:");
        System.out.println(taskManager.getAllEpics());
        System.out.println("Подзадачи:");
        System.out.println(taskManager.getAllSubtasks());
        System.out.println("\n");

        task1.setStatus(Status.IN_PROGRESS);
        task2.setStatus(Status.DONE);
        subtask1.setStatus(Status.IN_PROGRESS);
        subtask2.setStatus(Status.DONE);
        subtask3.setStatus(Status.DONE);

        taskManager.updateTask(task1);
        taskManager.updateTask(task2);
        taskManager.updateTask(subtask1);
        taskManager.updateTask(subtask2);
        taskManager.updateTask(subtask3);

        System.out.println("Задачи после обновления статуса:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("Эпик после обновления статуса:");
        System.out.println(taskManager.getAllEpics());
        System.out.println("Подзадача после обновления статуса:");
        System.out.println(taskManager.getAllSubtasks());
        System.out.println("\n");

        taskManager.deleteTaskById(task1.getId());
        taskManager.deleteTaskById(epic1.getId());

        System.out.println("Задачи после удаления:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("Эпики после удаления:");
        System.out.println(taskManager.getAllEpics());
        System.out.println("Подзадачи после удаления:");
        System.out.println(taskManager.getAllSubtasks());
    }
}