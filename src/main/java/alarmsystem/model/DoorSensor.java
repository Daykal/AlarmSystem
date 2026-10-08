package alarmsystem.model;

public class DoorSensor extends Sensor {
    private boolean isDoorOpen;

    public DoorSensor(String id, SensorLocation sensorLocation) {
        super(id, sensorLocation, SensorType.DOOR);
    }

    @Override
    public void trigger(boolean isSystemOn) {
        if (isSystemOn) {
            setTriggered(true);
            isDoorOpen = true;
        }
    }

    @Override
    public void reset() {
        super.reset();
        isDoorOpen = false;
    }

    @Override
    public String getLabel() {
        return "Sensor type: " + getSensorType() + ", id: '" + getId() + "' at " + getLocation() + ", the door is open? " + isDoorOpen + ", is triggered? " + super.isTriggered();
    }
}
