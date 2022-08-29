package aop.fastcampus.part4.chapter04

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.constraintlayout.motion.widget.MotionLayout
import aop.fastcampus.part4.chapter04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var isGateringMotionAnimating: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            if (binding.scrollView.scrollY > 150f.doToPx(this).toInt()) {
                if (isGateringMotionAnimating.not()) {
                    binding.gatheringDigitalThingsLayout.transitionToEnd()
                }
            } else {
                if (isGateringMotionAnimating.not()) {
                    binding.gatheringDigitalThingsLayout.transitionToStart()
                }
             }
        }

        binding.gatheringDigitalThingsLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
                isGateringMotionAnimating = true
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) = Unit

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                isGateringMotionAnimating = false
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float, ) = Unit

        })

    }

    fun Float.doToPx(context: Context): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)

}