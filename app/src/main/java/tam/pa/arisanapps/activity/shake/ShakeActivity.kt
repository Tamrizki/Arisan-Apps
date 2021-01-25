package tam.pa.arisanapps.activity.shake

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shake.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.home.HomeActivity
import tam.pa.arisanapps.helper.DbHandler
import tam.pa.arisanapps.helper.PoinHelper
import tam.pa.arisanapps.model.DataListMember
import java.util.*
import kotlin.math.sqrt

class ShakeActivity : AppCompatActivity(), View.OnClickListener {
    private var sensorManager: SensorManager? =null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f
    var number: Int = 0
    lateinit var poinHelper: PoinHelper
    lateinit var db: DbHandler
    lateinit var dataMember: MutableList<DataListMember>
    lateinit var dataWinner: DataListMember
    var idGroup: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shake)
        poinHelper = PoinHelper(this)
        db = DbHandler(this)
        setupProgressBar()
        setupSensorAcceleration()
        idGroup = intent.getIntExtra("idgroup", 0)
        dataMember = db.readAndFilterMember(idGroup)

        btnBack.setOnClickListener(this)
        btnRepeat.setOnClickListener(this)
    }

    private fun setupSensorAcceleration() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Objects.requireNonNull(sensorManager)!!.registerListener(sensorListener, sensorManager!!
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH
    }

    private fun setupProgressBar() {
        val outerColor = ArrayList<Int>()
        outerColor.add(Color.parseColor("#3D946A"))
        outerColor.add(Color.parseColor("#FFFFEB3B"))
        radialProgressBar.setOuterProgressColor(outerColor)
    }

    private val sensorListener: SensorEventListener = object : SensorEventListener{
        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration
            currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta
            if (acceleration > 12){
                number++
                radialProgressBar.setOuterProgress(number)
                poinHelper.getVibrator(40)
                if (number == 20){
                    rlShake.visibility = View.INVISIBLE
                    llCongrats.visibility = View.VISIBLE
                    cvButton.visibility = View.VISIBLE
                    lottieAnimGift.playAnimation()
                    poinHelper.getVibrator(500)
                    dataWinner = dataMember[poinHelper.getRandom(dataMember.size)]
                    tvName.text = dataWinner.nameMember
                }
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        }
    }

    override fun onResume() {
        sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(
                Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )
        super.onResume()
    }

    override fun onPause() {
        sensorManager!!.unregisterListener(sensorListener)
        super.onPause()
    }

    override fun onClick(view: View?) {
        if (view == btnBack){
            val input = db.updateMember(DataListMember(dataWinner.id, dataWinner.idGroup, dataWinner.nameMember, dataWinner.phone, dataWinner.statusPayment, "1"))
            if (input){
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                Toast.makeText(this, "Coba lagi untuk mengetuk tombol kembali!", Toast.LENGTH_SHORT).show()
            }
        }else if (view == btnRepeat){
            val intent = Intent(this, ShakeActivity::class.java)
            intent.putExtra("idgroup", idGroup)
            startActivity(intent)
        }
    }
}