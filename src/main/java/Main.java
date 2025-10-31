import Logging.*;
import TaskManagement.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Logger logger = new SystemLogger("logs/pharmacy");
        TaskManager manager = new TaskManager();

        logger.logInfo("SYSTEM", "Automated Pharmacy Storage System started");

        // Create tasks based on medication actions
        manager.createTask("RESTOCK", "ZONE A", "STORAGE UNIT 3");
        logger.logMedicationAction("ASPIRIN_100MG", "RESTOCKED", 1000, "ZONE A");

        manager.createTask("DISPENSE", "ZONE B", "PATIENT 12");
        logger.logMedicationAction("INSULIN_50ML", "DISPENSED", 5, "ZONE B");

        manager.createTask("TEMPERATURE_CHECK", "REFRIGERATED_UNIT_1", "CONTROL_CENTER");
        logger.logStorageAction("REFRIGERATED_UNIT_1", "TEMPERATURE_ALERT: 8°C");

        manager.createTask("RETRIEVE", "ZONE C", "DISPENSARY");
        logger.logRobotAction("PICKER_BOT_01", "RETRIEVING_MEDICATION", "ZONE C");

        logger.logError("SYSTEM", "Network connection lost");

        // Process tasks
        System.out.println("\n=== Task Processing ===");
        while (manager.hasPendingTasks()) {
            Task task = manager.assignNextTask(); // Assign and get task
            if (task != null) {
                System.out.println("Assigned: " + task);
                manager.completeTask(task);
                System.out.println("Completed: " + task);
            }
        }

        // Log search operations
        LocalDate today = LocalDate.now();

        System.out.println("\n=== ERROR Logs ===");
        List<String> errorLogs = logger.searchLogs(".*ERROR.*", today);
        errorLogs.forEach(System.out::println);

        System.out.println("\n=== Medication Logs ===");
        List<String> medicationLogs = logger.searchLogs(".*Medication.*", today);
        medicationLogs.forEach(System.out::println);

        System.out.println("\n=== Numeric Logs ===");
        List<String> numericLogs = logger.searchLogs(".*\\d+.*", today);
        numericLogs.forEach(System.out::println);

        System.out.println("\n=== File Operations ===");

        // Uncomment these if you want to test file operations
        // boolean moved = logger.moveLogFile(today, "logs/backup");
        // System.out.println("Move " + (moved ? "successed" : "failed"));

        // boolean archived = logger.archiveLogFile(today);
        // System.out.println("Archive " + (archived ? "successed" : "failed"));

        // boolean deleted = logger.deleteLogFile(today);
        // System.out.println("Delete " + (deleted ? "successed" : "failed"));
    }
}
