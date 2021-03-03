package com.umcomponent;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

import android.os.Handler;
import android.widget.Toast;
import android.content.Context;
import android.app.Application;
import android.app.Notification;
import android.widget.RemoteViews;
import android.util.Log;

import com.umeng.commonsdk.UMConfigure;
import com.umcomponent.invokenative.RNUMConfigure;
import com.umcomponent.invokenative.DplusReactPackage;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

public class MainApplication extends Application implements ReactApplication {

  private static final String TAG = MainApplication.class.getName();
  private Handler handler;

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new DplusReactPackage()
      );
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    UMConfigure.setLogEnabled(true);
    RNUMConfigure.init(this, "6039ac6cb8c8d45c138239fd", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,"93b2f0975d1bc9da8fcb24533446f1fe");
    PushAgent mPushAgent = PushAgent.getInstance(this);
    //注册推送服务，每次调用register方法都会回调该接口
    mPushAgent.register(new IUmengRegisterCallback() {
      @Override
      public void onSuccess(String deviceToken) {
        //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
        Log.i("UMENG","注册成功：deviceToken：-------->  " + deviceToken);
      }
      @Override
      public void onFailure(String s, String s1) {
        Log.e("UMENG","注册失败：-------->  " + "s:" + s + ",s1:" + s1);
      }
    });
  }

 
}
