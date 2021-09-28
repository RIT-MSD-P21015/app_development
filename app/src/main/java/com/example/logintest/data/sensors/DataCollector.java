package com.example.logintest.data.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class DataCollector implements SensorEventListener {

    private final SensorManager sensorManager;

    private final Sensor accel_sense;
    private final Sensor gyro_sense;
    private final Sensor grav_sense;
    private final Sensor lin_accel_sense;
    private final Sensor rot_sense;
    private final Sensor step_counter;

    private final TestDataManager tdm;

    /**
     * Initialize data collector class
     *
     * @param context current app context
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public DataCollector(Context context, TestDataManager tdm) {

        // grab sensor manager to handle other sensors
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        // initialize sensors
        accel_sense = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyro_sense = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        grav_sense = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        lin_accel_sense = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        rot_sense = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        step_counter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        this.tdm = tdm;
    }

    /**
     * Start recording sensor data
     */
    public void start() {
        // use GAME sensor delay, which is fast enough to handle movements without noticeable lag
        sensorManager.registerListener(this, accel_sense, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, gyro_sense, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, grav_sense, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, lin_accel_sense, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, rot_sense, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, step_counter, SensorManager.SENSOR_DELAY_GAME);
    }

    /**
     * Stop recording
     */
    public void stop() {
        sensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        TestData data = new TestData(event.accuracy, event.timestamp, event.values);

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                tdm.addAccelData(data);
                break;
            case Sensor.TYPE_GYROSCOPE:
                tdm.addGyroData(data);
                break;
            case Sensor.TYPE_GRAVITY:
                tdm.addGravData(data);
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                tdm.addLinAccelData(data);
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                tdm.addRotData(data);
                break;
            case Sensor.TYPE_STEP_COUNTER:
                tdm.addStepData(data);
                break;
            default:
                // do nothing for weird cases
                break;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // unused
    }

}
