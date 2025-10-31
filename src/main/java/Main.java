

import Logging.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Logger logger = new SystemLogger("logs/pharmacy");
        
        
        logger.logInfo("SYSTEM", "Automated Pharmacy Storage System started");
        logger.logMedicationAction("ASPIRIN_100MG", "RESTOCKED", 1000,"ZONE A");
        logger.logMedicationAction("INSULIN_50ML", "DISPENSED", 5, "ZONE B");
        logger.logStorageAction("REFRIGERATED_UNIT_1", "TEMPERATURE_ALERT: 8°C");
        logger.logRobotAction("PICKER_BOT_01", "RETRIEVING_MEDICATION", "ZONE C");
        logger.logError("SYSTEM", "Network connection lost");
        
        LocalDate today = LocalDate.now();
        
        
        System.out.println("=== ERROR Logs ===");
        List<String> errorLogs = logger.searchLogs(".*ERROR.*", today);
        errorLogs.forEach(System.out::println);
        
        System.out.println("\n=== Medication Logs ===");
        List<String> medicationLogs = logger.searchLogs(".*Medication.*", today);
        medicationLogs.forEach(System.out::println);
        
        System.out.println("\n=== Numeric Logs ===");
        List<String> numericLogs = logger.searchLogs(".*\\d+.*", today);
        numericLogs.forEach(System.out::println);
        
        
        System.out.println("\n=== File Operations ===");
        
        
        //boolean moved = logger.moveLogFile(today, "logs/backup");
        //System.out.println("Move " + (moved ? "successed" : "failed"));
        
        //boolean archived = logger.archiveLogFile(today);
        //System.out.println("Archive " + (archived ? "successed" : "failed"));
        
        
        //boolean deleted = logger.deleteLogFile(today);
        //System.out.println("Delete " + (deleted ? "successed" : "failed"));
        
        
        
    }
}