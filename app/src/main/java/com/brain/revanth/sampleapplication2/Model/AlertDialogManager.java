package com.brain.revanth.sampleapplication2.Model;

/**
 * Created by Hari Prahlad on 24-04-2016.
 */


import android.content.Context;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.brain.revanth.sampleapplication2.R;

public class AlertDialogManager {
    /**
     * Function to display simple Alert Dialog
     * @param context - application context

     * @param message - alert message
     * @param status - success/failure (used to set icon)
     *               - pass null if you don't want icon
     * */
    public void showAlertDialog(Context context,String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle).create();
        // Setting Dialog Message
        alertDialog.setMessage(message);
        // Setting alert dialog icon
// Setting OK Button
        if(status != null) {
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        // Showing Alert Message
        alertDialog.show();
    }
}