package com.brianml31.instamoon;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.brianml31.insta_moon.Brian;
import com.brianml31.insta_moon.LongClickMenuHandler;
import com.instagram.mainactivity.InstagramMainActivity;

import java.net.URI;

public class KotlinInvoker {
    //In this class are the invocation lines for InstaMoon functions.

    public static void setLongClickMenuHandlerKotlin(InstagramMainActivity instagramMainActivity, View v){
        LongClickMenuHandler.Companion.setLongClickMenuHandler(instagramMainActivity, v);
    }

    public static void setCtxKotlin(Application application){
        Brian.Companion.setCtx(application);
    }

    public static void validateUriHostKotlin(URI uri){
        Brian.Companion.validateUriHost(uri);
    }

    public static void hideSeen(){
        if(Brian.Companion.hideSeenDM()){
            return;
        }else{
            Log.i("","mark_thread_seen-");
        }
    }

    public static boolean disableAds(){
        if(Brian.Companion.disableAds()){
            return false;
        }else{
            Log.i("","SponsoredContentController.insertItem");
            return true;
        }
    }

    public static void after_onActivityResultKotlin(Activity activity, int requestCode, int resultCode, Intent data){
        com.brianml31.insta_moon.InstagramMainActivity.Companion.after_onActivityResult(activity, requestCode, resultCode, data);
    }

}
