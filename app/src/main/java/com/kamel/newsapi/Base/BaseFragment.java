package com.kamel.newsapi.Base;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

public class BaseFragment extends Fragment {

    MaterialDialog dialog;
    protected BaseActivity activity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= ((BaseActivity) context);
    }

     
    public MaterialDialog showMessage(int titleResId,
                                                  int contentResId,
                                                  int posTextResId,
                                                  int nagTextResId,
                                                  MaterialDialog.SingleButtonCallback onPos,
                                                  MaterialDialog.SingleButtonCallback onNag
    ){
        return
                activity.showMessage(titleResId,contentResId,posTextResId,nagTextResId,onPos,onNag);

    }

    public MaterialDialog showMessageOk(String titleResId,
                                        String contentResId,
                                        String posTextResId){
        return
                activity.showMessageOk(titleResId,contentResId,posTextResId);

    }

    public MaterialDialog showProgressBar(int titleResId, int contentResId){
        return activity.showProgressBar(titleResId,contentResId);
    }

    public void hideProgressBar(){
        activity.hideProgressBar();
    }

   
   

}
