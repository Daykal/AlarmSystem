package alarmsystem.model;

public class MotionSensor extends Sensor {
    public MotionSensor(String id, SensorLocation sensorLocation) {
        super(id, sensorLocation, SensorType.MOTION);
    }

    @Override
    public void trigger(boolean isSystemOn) {
        if (isSystemOn) {
            setTriggered(true);
        }
    }
}
