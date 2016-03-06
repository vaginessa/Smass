package id.co.technomotion.smass.controller.client;

import android.content.Context;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.co.technomotion.smass.utils.Api;
import id.co.technomotion.smass.utils.ClientToServer;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by omayib on 12/28/14.
 */
public class ApiClient {
    public Observable<String> registerGcm(final GoogleCloudMessaging gcm, final String senderId){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String regId="";
                try {
                    regId= gcm.register(senderId);
                    System.out.println("registering... restult "+regId);
                    subscriber.onNext(regId);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
    public Observable<String> signup(final String email, final String password, final String gcmKeyId) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data="";
                ArrayList<NameValuePair> listParam=new ArrayList<NameValuePair>();
                listParam.add(new BasicNameValuePair("email",email));
                listParam.add(new BasicNameValuePair("password",password));
                listParam.add(new BasicNameValuePair("gcm",gcmKeyId));
                try {
                    data= ClientToServer.postRequest(Api.SIGN_UP(),listParam);
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
    public Observable<String> login(final String email, final String password, final String gcmKeyId) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data="";
                ArrayList<NameValuePair> listParam=new ArrayList<NameValuePair>();
                listParam.add(new BasicNameValuePair("email",email));
                listParam.add(new BasicNameValuePair("password",password));
                listParam.add(new BasicNameValuePair("gcm",gcmKeyId));
                try {
                    data= ClientToServer.postRequest(Api.LOGIN(),listParam);
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<String> signout(final String token) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data="";
                try {
                    data= ClientToServer.putRequest(Api.SIGN_OUT(token));
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
    public Observable<String> getFollowedCategories(final String token) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data="";
                try {
//                    data= ClientToServer.getRequest("https://smass.herokuapp.com/api/v1/mobile/categories?token=pCRKpb8dxRf7fS2WKNvk70L6dntlz5nxbDpVcNxtzad6pYL90dkNrdvd6R50&id=20");
                    data= ClientToServer.getRequest(Api.CATEGORIES_FOLLOWED(token));
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
    public Observable<String> getCategories(final String token) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data="";
                try {
//                    data= ClientToServer.getRequest("https://smass.herokuapp.com/api/v1/mobile/categories?token=pCRKpb8dxRf7fS2WKNvk70L6dntlz5nxbDpVcNxtzad6pYL90dkNrdvd6R50&id=20");
                    data= ClientToServer.getRequest(Api.CATEGORIES_ALL(token));
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<String> getAnnouncements(final String token, final int categoryId) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data="";
                try {
                    data= ClientToServer.getRequest(Api.ANNOUNCEMENTS(token,categoryId));
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<String> getAnnouncementDetail(final String token, final int announcementId) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data="";
                try {
                    data= ClientToServer.getRequest(Api.ANNOUNCEMENT_DETAIL(token,announcementId));
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<String> follow(final String token, final int categoryId) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data="";
                try {
                    data= ClientToServer.putRequest(Api.FOLLOW_CATEGORY(token,categoryId));
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
    public Observable<String> unfollow(final String token, final int categoryId) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data="";
                try {
                    data= ClientToServer.putRequest(Api.UN_FOLLOW(token,categoryId));
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

}
