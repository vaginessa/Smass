package id.co.technomotion.smass.controller.client;

import android.content.Context;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.controller.action.Credential;
import id.co.technomotion.smass.controller.action.ParsingResponse;
import id.co.technomotion.smass.controller.event.LoginSucceeded;
import id.co.technomotion.smass.controller.event.RegisterGcmFailed;
import id.co.technomotion.smass.controller.event.RegisterGcmSucceded;
import id.co.technomotion.smass.controller.event.SignupSucceded;
import id.co.technomotion.smass.controller.event.SplashScreenCompleted;
import id.co.technomotion.smass.controller.event.SplashScreenOnProgress;
import id.co.technomotion.smass.model.User;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by omayib on 12/28/14.
 */
public class UserCredential implements Credential {

    private ApiClient apiClient;

    public UserCredential(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void login(final String email, final String password,String gcmKeyId) {


        apiClient.login(email,password,gcmKeyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Parser.LOGIN(s,new ParsingResponse<String>() {
                            @Override
                            public void onSuccess(String result) {
                                EventBus.getDefault().post(new LoginSucceeded(email,result));
                            }

                            @Override
                            public void onFailure(String failedResult) {

                            }
                        });
                    }
                });
    }

    @Override
    public void signup(final String email, String password,String gcmKeyId) {
        apiClient.signup(email,password,gcmKeyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Parser.SIGNUP(s,new ParsingResponse<String>() {
                            @Override
                            public void onSuccess(String result) {
                                EventBus.getDefault().post(new SignupSucceded(email,result));
                            }

                            @Override
                            public void onFailure(String failedResult) {

                            }
                        });
                    }
                });
    }

    @Override
    public void registerGcm(GoogleCloudMessaging gcm, String senderId) {
        apiClient.registerGcm(gcm,senderId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(" result after do on next " + s);
                        EventBus.getDefault().post(new RegisterGcmSucceded(s));

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("on error "+throwable.toString());
                        EventBus.getDefault().post(new RegisterGcmFailed());
                    }
                },new Action0() {
                    @Override
                    public void call() {
                        System.out.println("on complete");
                    }
                });
    }

    @Override
    public void splashScreen(final int duration) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                for (int i = 0; i <duration ; i++) {
                    try {
                        Thread.sleep(1000);
                        EventBus.getDefault().post(new SplashScreenOnProgress(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onNext(true);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        EventBus.getDefault().post(new SplashScreenCompleted());
                    }
                });
    }

}
