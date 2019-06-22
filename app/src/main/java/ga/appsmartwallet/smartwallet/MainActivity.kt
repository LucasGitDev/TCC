package ga.appsmartwallet.smartwallet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar?.hide()
        Handler().postDelayed(runnable, 2500)
    }

    val runnable: Runnable = Runnable {
        if (true){
            val intent = Intent(applicationContext, Principal::class.java)

            val activityOptionsCompat: ActivityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(applicationContext, R.anim.abc_grow_fade_in_from_bottom, R.anim.none)
            ActivityCompat.startActivity(this, intent, activityOptionsCompat.toBundle())

            finish()
        }
    }
}
