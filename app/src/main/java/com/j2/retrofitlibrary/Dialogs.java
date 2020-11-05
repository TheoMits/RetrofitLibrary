package com.j2.retrofitlibrary;

import android.content.Context;
import android.graphics.Color;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class Dialogs {
    public static void errorDialog(Context context) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(context.getString(R.string.error_dialog))
                .setContentText(context.getString(R.string.error_dialog_content))
                .show();
    }

}
