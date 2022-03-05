package bimerso.megabitefood.retopokebowl.base

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    protected var ctx: Context? = null
    private var processRunning = false
    private val loadingView by lazy { LoadingView(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        ctx = this
        super.onCreate(savedInstanceState)
    }
    fun showProgressDialog() {
        try {
            if (!this.isFinishing) {
                processRunning = true
                if (loadingView.parent == null) {
                    addContentView(loadingView, ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun hideProgressDialog() {
        try {
            processRunning = false
            loadingView.removeFromParent()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    protected fun showMessage(title: String?, message: String?) {
        MasterDialog(this, title!!, message!!, "Aceptar", false,) {
            //ok
        }.show()
    }
    protected fun showErrorMessage(title: String?, message: String?) {
        if (!this.isFinishing){
            MasterDialog(this, title!!, message!!, "Aceptar", false,) {
                //ok
            }.show()
        }
    }
    override fun finish() {
        super.finish()
    }
    override fun onPause() {
        super.onPause()
        loadingView.removeFromParent()
    }

    override fun onBackPressed() {
        if(!processRunning){
            super.onBackPressed()
        }
    }
}