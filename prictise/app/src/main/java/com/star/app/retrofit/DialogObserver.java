package com.star.app.retrofit;

import android.content.Context;
import android.util.Log;

import com.star.app.retrofit.utils.GsonUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by suxing on 2018/4/10.
 */
public abstract class DialogObserver<T> implements Observer<T> {

    private static final String success = "200";
    private static final String login = "401";
    private Context mContext;

    public DialogObserver(Context mContext) {
        this.mContext = mContext;
        LoadingDialog.showprogress(mContext, true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.i("star", "onSubscribe");
        // 判断网络是否可用
    }

    @Override
    public void onNext(T t) {
        Log.i("star", "onNext");
        // 根据 响应码，做处理
        String response = (String) t;
        String code = GsonUtil.GsonValueFromKeyStr(response, "code");
        if (login.equals(code)) { // 异地登录
            Log.i("star", "login out");
        } else if (success.equals(code)) { // 成功
            Log.i("star", "success");
            String info = GsonUtil.GsonValueFromKeyStr(response, "info");
            success(info);
        } else {
            Log.i("star", code);
        }
    }

    public abstract void success(String info);

    @Override
    public void onError(Throwable e) {
        LoadingDialog.dismissDialog();
        if (e.toString().contains(login)) { // 异地登录
            Log.i("star", "login out");
        } else { // 网络连接异常/服务器异常
            Log.i("star", "error");
        }

    }

    @Override
    public void onComplete() {
        Log.i("star", "onComplete");
        LoadingDialog.dismissDialog();
    }
}
