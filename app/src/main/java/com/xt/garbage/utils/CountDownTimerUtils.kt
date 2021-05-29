package com.xt.garbage.utils

import android.graphics.Color
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import java.lang.ref.WeakReference

/**
 *@author:DIY
 *@date: 2021/5/28
 */
class CountDownTimerUtils(textView: TextView,millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
    private var mTextView:WeakReference<TextView>? = null
    init {
        mTextView = WeakReference(textView)
    }

    override fun onTick(millisUntilFinished: Long) {
        if(mTextView?.get() == null) {
            myCancel()
            return
        }
        mTextView?.get()?.let {
            it.isClickable  = false
            it.text = (millisUntilFinished / 999).toString() + "s重新发送"
            var spannableString:SpannableString = SpannableString(it.text.toString())
            var span:ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#00BF60"))
            spannableString.setSpan(span,0,2,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            it.text = spannableString

        }
    }

    private fun myCancel() {
        this?.let { this.cancel() }
    }

    override fun onFinish() {
        if(mTextView?.get() == null) {
            myCancel()
            return
        }
        mTextView?.get()?.let {
            it.text = "重新发送"
            it.isClickable = true
        }
    }


}