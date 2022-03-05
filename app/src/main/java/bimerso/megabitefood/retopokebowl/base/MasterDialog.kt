package bimerso.megabitefood.retopokebowl.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AlertDialog
import bimerso.megabitefood.retopokebowl.R
import kotlinx.android.synthetic.main.layout_dialog_master.*


class MasterDialog(context: Context,
                   private val title: String,
                   private val message: String,
                   private val firstButtonText: String,
                   private val closeIconAvailable: Boolean,
                   val callback:()-> Unit) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog_master)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setParentContainerAnimation()
        initView()
        setCancelable(false)
    }

    private fun setParentContainerAnimation() {
        dialogTermAndConditions.animation = ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
                .apply { duration = 300 }
                .also { it.start() }
    }

    private fun initView(){
        if (!closeIconAvailable)
            ivClose.visibility = View.GONE

        if (title.trim().isEmpty()){
            textViewTitle.visibility = View.GONE
        }

        textViewTitle.text = title.trim()
        textViewMessage.text = message.trim()
        btnAccept.text = firstButtonText.trim()
        initClickListeners()
    }

    private fun initClickListeners() {
        btnAccept.setOnClickListener {
            callback()
            dismiss()
        }
        ivClose.setOnClickListener {
            if (closeIconAvailable)
                dismiss()
        }
    }
}