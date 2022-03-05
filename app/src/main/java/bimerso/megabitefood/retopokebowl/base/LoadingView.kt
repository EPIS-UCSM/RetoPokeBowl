package bimerso.megabitefood.retopokebowl.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import bimerso.megabitefood.retopokebowl.R

class LoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate()
    }

    private fun inflate() {
        LayoutInflater.from(context).inflate(R.layout.loading, this)
        isClickable = true
    }

    fun removeFromParent() {
        this.parent?.let {
            (it as ViewGroup).removeView(this)
        }
    }
}
