package com.github.bradmartin.smartwearos

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import org.json.JSONObject
import java.util.*

abstract class DeviceSensors constructor(ctx: Context) : SensorEventListener {

    private val sensorManager = ctx.getSystemService(
        SENSOR_SERVICE
    ) as SensorManager

    /* Returns array of device sensors. If not type argument then ALL sensors on the device are returned. */
    fun getDeviceSensors(type: Int = Sensor.TYPE_ALL): Array<Sensor?> {
        val list = this.sensorManager.getSensorList(type)
        return arrayOfNulls(list.size)
    }

    fun startSensorListener(sensor: Sensor, delay: Int = SensorManager.SENSOR_DELAY_NORMAL) {
        sensorManager.registerListener(this, sensor, delay)
    }

    fun stopSensorListener() {
        sensorManager.unregisterListener(this)
    }

    open fun listenForSensorData(data: SensorChangeResult): SensorChangeResult {
        return data
    }


    // override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    //     return { sensor, accuracy }
    // }

    override fun onSensorChanged(event: SensorEvent?) {
        val type = event?.sensor?.type
        val seconds = Date().time / 1000
        val timestamp = event?.timestamp
        // this is what we will return
        // val result = JSONObject()

        if (type == Sensor.TYPE_LINEAR_ACCELERATION) {
            val data = JSONObject()
            data.put("x", event.values?.get(0))
            data.put("y", event.values?.get(1))
            data.put("z", event.values?.get(2))
            val result = SensorChangeResult(data, "LINEAR_ACCELERATION", seconds, timestamp, Date().time / 1000)
            listenForSensorData(result)
            // the data object will contain the raw data values from the sensor
            // val data = JSONObject()
            // data.put("x", event?.values?.get(0))
            // data.put("y", event?.values?.get(1))
            // data.put("z", event?.values?.get(2))
            // assign the raw data to the result object
            // result.put("data", data)

            // result.put("sensor", "LINEAR_ACCELERATION")
        }
    }

}

class SensorChangeResult(
    val data: JSONObject,
    val sensor: String,
    val seconds: Long,
    val timestamp: Long?,
    val time: Number
)
