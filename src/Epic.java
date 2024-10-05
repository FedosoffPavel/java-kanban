import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Subtask> subtasks;

    public Epic(String title, String description, int id) {
        super(title, description, id);
        this.subtasks = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void updateStatusEpic() {
        if (subtasks.isEmpty()) {
            this.status = Status.NEW;
        } else {
            boolean allDone = true;
            boolean anyInProgress = false;
            for (Subtask subtask : subtasks) {
                if (subtask.getStatus() == Status.IN_PROGRESS) {
                    anyInProgress = true;
                }
                if (subtask.getStatus() != Status.DONE) {
                    allDone = false;
                }
            }
            if (allDone) {
                this.status = Status.DONE;
            } else if (anyInProgress) {
                this.status = Status.IN_PROGRESS;
            } else {
                this.status = Status.NEW;
            }
        }
    }
    @Override
    public String toString() {
        return "Epic{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
