package com.arash.altafi.permission.broadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast

class SMSReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent!!.extras
            val msgList: Array<SmsMessage?>
            var msgFrom: String?
            if (bundle != null) {
                val pdus = bundle["pdus"] as Array<Any>?
                msgList = arrayOfNulls(pdus!!.size)
                for (i in pdus!!.indices) {
                    msgList[i] = SmsMessage.createFromPdu(pdus!![i] as ByteArray)
                    msgFrom = msgList[i]?.originatingAddress
                    val msgBody = msgList[i]?.messageBody
                    Toast.makeText(context, "Message from : $msgFrom : $msgBody", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}