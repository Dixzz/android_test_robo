package com.lc.tummoc.bengaluru.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import kotlin.math.abs


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val abc = Abc(this)
        findViewById<View>(R.id.wew2).setOnClickListener {
            abc.show()
            /*it.postDelayed(5000) {
                abc.remove()
            }*/
        }
    }

    class Abc(context: Context) : FrameLayout(context) {
        private val TAG = "MainActivity"
        private val windowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        private val button = MaterialButton(context)
        private val flingDetector = object : GestureDetector.SimpleOnGestureListener() {

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                val maxVelocity = ViewConfiguration.get(context).scaledMaximumFlingVelocity
                val normalizedVelocityY = abs(velocityY) / maxVelocity
                val normalizedVelocityX = abs(velocityX) / maxVelocity

                Log.d(
                    TAG,
                    "onFling: normX=$normalizedVelocityX, normY=$normalizedVelocityY, maxV=$maxVelocity"
                )

                return if (normalizedVelocityY > 0.1 || normalizedVelocityX > 0.1) {
                    remove()
                    true
                } else {
                    false
                }
            }
        }
        private val gestureDetector = GestureDetector(context, flingDetector)

        override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
            Log.d(TAG, "dispatchKeyEvent: $event")
            if (event?.keyCode == KeyEvent.KEYCODE_BACK) {
                remove()
                return true
            }
            return super.dispatchKeyEvent(event)
        }
        @SuppressLint("ClickableViewAccessibility")
        fun show() {
            setBackgroundColor(Color.parseColor("#80444444"))
            button.layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
            button.text = "Hello"

            button.setOnClickListener {
                Log.d(TAG, "show: $it")
            }

            var lastX = 0f
            var lastY = 0f

            button.setOnTouchListener { view, motionEvent ->
                if (false) {
                    true
                } else {
                    val action = motionEvent.action
                    when (action) {
                        MotionEvent.ACTION_DOWN -> {
                            lastX = motionEvent.rawX
                            lastY = motionEvent.rawY
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val dX = motionEvent.rawX - lastX
                            val dY = motionEvent.rawY - lastY
                            view.x = view.x + dX
                            view.y = view.y + dY
                        }
                        MotionEvent.ACTION_UP -> {
                            lastX = 0f
                            lastY = 0f
                            view.x = lastX
                            view.y = lastY
                        }
                    }

                    true
                }
            }

            val layoutParams = WindowManager.LayoutParams()
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION
            //layoutParams.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

            /*layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH*/
            layoutParams.format = PixelFormat.TRANSLUCENT

            //layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            this.addView(button)

            windowManager.addView(this, layoutParams)
        }

        fun remove() {
            button.setOnTouchListener(null)
            this.removeAllViewsInLayout()
            windowManager.removeViewImmediate(this)
        }
    }
}