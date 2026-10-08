package alarmsystem.controller;

public interface AlarmSystem {
    void printAllSensorsLabel();

    void triggerAllSensors();

    void resetAllSensors();

    void addSensor(String id, String sensorLocation, String sensorType);

    void deleteSensor(String id);

    void triggerSensor(String id);

    void resetSensor(String id);

    void turnSystemOff();

    void turnSystemOn();

    int alarmedSensorsAmount();

    int sensorsAmount();

    boolean isSystemAlarming();

    boolean isSystemOn();
}
