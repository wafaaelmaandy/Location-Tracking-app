package com.example.viva.locationtracker10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Viva on 9/29/2017.
 */

public class Reciever extends BroadcastReceiver {
    static  int id =1;
    final SmsManager smsManager = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            //---get the SMS message passed in---

            SmsMessage[] myMessage = null;
            String sender = null;
            String massage =null;

            DBConnections dbConnections = new DBConnections(context);

            String code = dbConnections.getCode() ;


            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    myMessage = new SmsMessage[pdus.length];
                    for (int i = 0; i < myMessage.length; i++) {
                        myMessage[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        sender = myMessage[i].getOriginatingAddress();
                        massage = myMessage[i].getDisplayMessageBody();
                    }
                    NewMessageNotification notifecation= new NewMessageNotification();
                    notifecation.notify(context, sender, massage,id);
                    id++ ;
                    Toast.makeText(context, "from" + sender + "body" + massage, Toast.LENGTH_LONG).show();
                      if (massage.equals(code)) {

                    String location=   dbConnections.getLocation();
                    smsManager.sendTextMessage(sender, null,location, null, null);


                  }


                } catch (Exception ex) {
                    Log.e("SmsReceiver", "Exception smsReceiver" + ex);

                }

            }
        }}


}
