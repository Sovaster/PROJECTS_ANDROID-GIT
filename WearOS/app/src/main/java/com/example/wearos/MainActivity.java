package com.example.wearos;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wearos.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    Sensor gyroscope;
    SensorManager sensorManager;
    SensorEventListener sensorEventListener;

    long previousMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView mTextView = binding.gyroscope;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        previousMillis = 0;

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) return;

                if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
                    long now = System.currentTimeMillis();

                    if (now - previousMillis > 100) {
                        previousMillis = now;

                        float[] rotationMatrix = new float[16];
                        SensorManager.getRotationMatrixFromVector(
                                rotationMatrix, event.values);

                        float[] remappedRotationMatrix = new float[16];
                        SensorManager.remapCoordinateSystem(rotationMatrix,
                                SensorManager.AXIS_X,
                                SensorManager.AXIS_Z,
                                remappedRotationMatrix
                        );

                        float[] orientations = new float[3];
                        SensorManager.getOrientation(remappedRotationMatrix, orientations);

                        for (int i = 0; i < 3; i++) {
                            orientations[i] = (float) (Math.toDegrees(orientations[i]));
                        }

                        float X = orientations[0];
                        float Y = orientations[1];
                        float Z = orientations[2];

                        mTextView.setText(String.format("X: %.0f\nY: %.0f\nZ: %.0f", X, Y, Z));
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {    }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(sensorEventListener);
    }
}