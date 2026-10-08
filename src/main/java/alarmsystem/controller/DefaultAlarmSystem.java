package alarmsystem.controller;

import alarmsystem.model.*;

import java.util.ArrayList;
import java.util.List;

public class DefaultAlarmSystem implements AlarmSystem {

    private final List<Sensor> sensors = new ArrayList<>();
    private final EventLogger eventLogger = new EventLogger();
    private boolean isSystemOn = true;

    public DefaultAlarmSystem() {
        DoorSensor doorSensor1 = new DoorSensor("DS1", SensorLocation.HALL);
        MotionSensor motionSensor1 = new MotionSensor("MS1", SensorLocation.HALL);
        MotionSensor motionSensor2 = new MotionSensor("MS2", SensorLocation.OFFICE);
        SmokeSensor smokeSensor1 = new SmokeSensor("SS1", SensorLocation.STORAGE);
        DoorSensor doorSensor2 = new DoorSensor("DS2", SensorLocation.GARAGE);

        sensors.addAll(List.of(doorSensor1, motionSensor1, motionSensor2, smokeSensor1, doorSensor2));
    }

    @Override
    public void printAllSensorsLabel() {
        for (Sensor sensor : sensors) {
            System.out.println(sensor.getLabel());
        }
        eventLogger.logEvent("All sensors label been printed");
    }

    @Override
    public void triggerAllSensors() {
        for (Sensor sensor : sensors) {
            sensor.trigger(isSystemOn);
        }
        eventLogger.logEvent("All sensors been triggered");
    }

    @Override
    public void resetAllSensors() {
        for (Sensor sensor : sensors) {
            sensor.reset();
        }
        eventLogger.logEvent("All sensors been reset");
    }

    @Override
    public void addSensor(String id, String sensorLocation, String sensorType) {
        SensorLocation location = SensorLocation.valueOf(sensorLocation);
        SensorType type = SensorType.valueOf(sensorType);
        Sensor createdSensor = switch (type) {
            case SMOKE -> new SmokeSensor(id, location);
            case DOOR -> new DoorSensor(id, location);
            case MOTION -> new MotionSensor(id, location);
        };
        sensors.add(createdSensor);
        eventLogger.logEvent(id + ", Sensor been added");
    }

    @Override
    public void deleteSensor(String id) {
        sensors.removeIf(sensor -> sensor.getId().equalsIgnoreCase(id));
        eventLogger.logEvent(id + ", deleted sensor successfully");
    }

    @Override
    public void triggerSensor(String id) {
        for (Sensor sensor : sensors) {
            if (sensor.getId().equalsIgnoreCase(id)) {
                sensor.trigger(isSystemOn);
                if (sensor.isTriggered()) {
                    eventLogger.logEvent(id + ", sensor been triggered");
                } else {
                    eventLogger.logEvent(id + ", can't turn the sensor ON, because system is OFF");
                }
                return;
            }
        }
    }

    @Override
    public void resetSensor(String id) {
        for (Sensor sensor : sensors) {
            if (sensor.getId().equalsIgnoreCase(id)) {
                sensor.reset();
                eventLogger.logEvent(id + ", sensor been reset");
                return;
            }
        }
    }

    @Override
    public void turnSystemOff() {
        isSystemOn = false;
        for (Sensor sensor : sensors) {
            if (sensor.canResetOnSystemOff()) {
                sensor.reset();
            }
        }
        eventLogger.logEvent("Turned OFF the alarm system");
    }

    @Override
    public void turnSystemOn() {
        isSystemOn = true;
        eventLogger.logEvent("Turned ON the alarm system");
    }

    @Override
    public int alarmedSensorsAmount() {
        int alarmedSensors = 0;
        for (Sensor sensor : sensors) {
            if (sensor.isTriggered()) {
                alarmedSensors++;
            }
        }
        eventLogger.logEvent(alarmedSensors + ", sensor is/are triggered");
        return alarmedSensors;
    }

    @Override
    public int sensorsAmount() {
        int amount = sensors.size();
        eventLogger.logEvent(amount + ", total sensors exist");
        return amount;
    }

    @Override
    public boolean isSystemAlarming() {
        eventLogger.logEvent("Checked if system is alarming");
        for (Sensor sensor : sensors) {
            if (sensor.isTriggered()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSystemOn() {
        eventLogger.logEvent("Checked if alarm system is ON");
        return isSystemOn;
    }
}

