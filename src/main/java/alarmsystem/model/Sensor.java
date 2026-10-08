package alarmsystem.model;

public abstract class Sensor {
    private final String id;
    private final SensorLocation sensorLocation;
    private final SensorType sensorType;
    private boolean isTriggered;

    public Sensor(String id, SensorLocation sensorLocation, SensorType sensorType) {
        this.id = id;
        this.sensorLocation = sensorLocation;
        this.sensorType = sensorType;
        isTriggered = false;
    }

    public abstract void trigger(boolean isSystemOn);

    public void reset() {
        setTriggered(false);
    }

    public boolean canResetOnSystemOff() {
        return true;
    }

    public String getLabel() {
        return "Sensor type: " + sensorType + ", id: '" + id + "' at " + sensorLocation + ", is triggered? " + isTriggered;
    }

    @Override
    public String toString() {
        return getLabel();
    }

    public String getId() {
        return id;
    }

    public SensorLocation getLocation() {
        return sensorLocation;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public boolean isTriggered() {
        return isTriggered;
    }

    public void setTriggered(boolean triggered) {
        isTriggered = triggered;
    }
}
