package com.kamel.newsapi.Base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;


public class BaseActivity extends AppCompatActivity {

    MaterialDialog dialog;
    protected BaseActivity activity;
   

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
    }
    
	public MaterialDialog showMessage(int titleResId,
                                                     int contentResId,
                                                     int posTextResId,
                                                     int nagTextResId,
                                                     MaterialDialog.SingleButtonCallback onPos,
                                                     MaterialDialog.SingleButtonCallback onNag
    ){
        dialog= new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(contentResId)
                .positiveText(posTextResId)
                .negativeText(nagTextResId)
                .onNegative(onNag)
                .onPositive(onPos)
                .show();
        return dialog;
    }

    public MaterialDialog showMessageOk(String titleResId,
                                        String contentResId,
                                        String posTextResId
    ){
        dialog= new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(contentResId)
                .positiveText(posTextResId)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
        return dialog;
    }



    public MaterialDialog showProgressBar(int titleResId, int contentResId){
        dialog= new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(contentResId)
                .progress(true,0)
                .cancelable(false)
                .show();
        return dialog;
    }

    public void hideProgressBar(){
        if(dialog!=null&&dialog.isShowing())
            dialog.dismiss();
    }
    
}
