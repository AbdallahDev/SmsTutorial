package com.jhr.abdallahsarayrah.smstutorial

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.widget.Toast

/**
 * Created by abdallah.sarayrah on 12/18/2017.
 */

class SmsReceiver : BroadcastReceiver() {
    @SuppressLint("Recycle")
    override fun onReceive(context: Context, intent: Intent) {
        val pdus = intent.extras.get("pdus") as Array<*>
        var mobile = ""
        var txt = ""

        for (i in 0 until pdus.size) {
            txt = SmsMessage.createFromPdu(pdus[i] as ByteArray).displayMessageBody
            mobile = SmsMessage.createFromPdu(pdus[i] as ByteArray).displayOriginatingAddress
        }

        val obj = StudentsDB(context)
        val db = obj.readableDatabase
        val cursor = db.rawQuery("select mark from student where mobile = ?", arrayOf(mobile))
        cursor.moveToFirst()

        val sms = SmsManager.getDefault()
        sms.sendTextMessage(mobile, null, cursor.getString(0), null, null)

        Toast.makeText(context, "$mobile : $txt \n ${cursor.getString(0)}", Toast.LENGTH_SHORT).show()
    }
}
