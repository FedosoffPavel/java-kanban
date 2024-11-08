import main.InMemoryHistoryManager;
import main.Managers;
import main.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManagersTest {

    @Test
    public void testTaskManagerInitialization() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "Диспетчер задач должен быть инициализирован.");
    }

    @Test
    public void testHistoryManagerInitialization() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        assertNotNull(historyManager, "Исторический менеджер должен быть инициализирован.");
    }
}