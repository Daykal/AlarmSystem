package alarmsystem.model;

public class SmokeSensor extends Sensor {
    public SmokeSensor(String id, SensorLocation sensorLocation) {
        super(id, sensorLocation, SensorType.SMOKE);

    }

    @Override
    public void trigger(boolean isSystemOn) {
        setTriggered(true);
    }

    @Override
    public boolean canResetOnSystemOff() {
        return false;
    }


}
