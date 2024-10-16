package main.manager;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager(); // или любая другая реализация TaskManager
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}