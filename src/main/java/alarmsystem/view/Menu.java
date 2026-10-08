package alarmsystem.view;

import alarmsystem.controller.AlarmSystem;
import alarmsystem.model.SensorLocation;
import alarmsystem.model.SensorType;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final AlarmSystem alarmSystem;
    private boolean isRunning = true;

    public Menu(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (isRunning) {
            System.out.println("ALARM SYSTEM by MAHDI JAFARI");
            System.out.println("1. See all sensors label");
            System.out.println("2. Trigger all sensors");
            System.out.println("3. Reset all sensors");
            System.out.println("4. Add a Sensor");
            System.out.println("5. Delete a sensor");
            System.out.println("6. Trigger a sensor");
            System.out.println("7. Reset a sensor");
            System.out.println("8. Amount of triggered sensors");
            System.out.println("9. Amount of all sensors");
            System.out.println("10. Is system Alarming?");
            System.out.println("11. Turn alarm system ON");
            System.out.println("12. Turn alarm system OFF");
            System.out.println("13. Is alarm system ON?");
            System.out.println("0. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> alarmSystem.printAllSensorsLabel();
                case "2" -> alarmSystem.triggerAllSensors();
                case "3" -> alarmSystem.resetAllSensors();
                case "4" -> handleAddSensor();
                case "5" -> handleDeleteSensor();
                case "6" -> handleTriggerSensor();
                case "7" -> handleResetSensor();
                case "8" ->
                        System.out.println("Amount of triggered sensors now: " + alarmSystem.alarmedSensorsAmount());
                case "9" -> System.out.println("Amount of all sensors: " + alarmSystem.sensorsAmount());
                case "10" -> System.out.println("Is system alarming? " + alarmSystem.isSystemAlarming());
                case "11" -> alarmSystem.turnSystemOn();
                case "12" -> alarmSystem.turnSystemOff();
                case "13" -> System.out.println("Is alarm system ON? " + alarmSystem.isSystemOn());
                case "0" -> isRunning = false;
                default -> System.out.println("Invalid option, try again :)");
            }
            if (isRunning) {
                System.out.println("Press any key to continue...");
                scanner.nextLine();
            }

        }
        scanner.close();
    }


    private void handleAddSensor() {
        System.out.println("Enter sensor ID:");
        String id = scanner.nextLine().trim();
        System.out.println("Enter sensor's type from these options: ");
        for (SensorType sensorType : SensorType.values()) {
            System.out.println(sensorType);
        }
        String sensorType = scanner.nextLine().trim().toUpperCase();
        System.out.println("Enter sensor's location from these options: ");
        for (SensorLocation sensorLocation : SensorLocation.values()) {
            System.out.println(sensorLocation);
        }
        String sensorLocation = scanner.nextLine().trim().toUpperCase();
        try {
            alarmSystem.addSensor(id, sensorLocation, sensorType);
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong inputs, sensor was not added");
        }
    }

    private void handleDeleteSensor() {
        System.out.println("Enter the ID of the sensor you want to remove: ");
        String id = scanner.nextLine().trim();
        alarmSystem.deleteSensor(id);
    }

    private void handleTriggerSensor() {
        System.out.println("Enter the ID of the sensor you want to trigger: ");
        String id = scanner.nextLine().trim();
        alarmSystem.triggerSensor(id);
    }

    private void handleResetSensor() {
        System.out.println("Enter the ID of the sensor you want to reset: ");
        String id = scanner.nextLine().trim();
        alarmSystem.resetSensor(id);
    }

}
