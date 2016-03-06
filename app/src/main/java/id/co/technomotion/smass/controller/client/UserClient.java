package id.co.technomotion.smass.controller.client;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.controller.action.ParsingResponse;
import id.co.technomotion.smass.controller.action.UserAction;
import id.co.technomotion.smass.controller.event.FollowCategoryFailed;
import id.co.technomotion.smass.controller.event.FollowCategorySucceded;
import id.co.technomotion.smass.controller.event.LoadAnnouncementDetailFailed;
import id.co.technomotion.smass.controller.event.LoadAnnouncementDetailSucceded;
import id.co.technomotion.smass.controller.event.LoadAnnouncementListFailed;
import id.co.technomotion.smass.controller.event.LoadAnnouncementListSucceded;
import id.co.technomotion.smass.controller.event.LoadCategoriesSucceded;
import id.co.technomotion.smass.controller.event.LoadFollowedCategoriesSucceded;
import id.co.technomotion.smass.controller.event.SignoutFailed;
import id.co.technomotion.smass.controller.event.SignoutSucceded;
import id.co.technomotion.smass.controller.event.UnfollowCategoryFailed;
import id.co.technomotion.smass.controller.event.UnfollowCategorySucceded;
import id.co.technomotion.smass.model.Announcement;
import id.co.technomotion.smass.model.Category;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by omayib on 12/26/14.
 */
public class UserClient implements UserAction {
    private ApiClient client;

    public UserClient(ApiClient client) {
        this.client = client;

    }


    @Override
    public void signout(String token) {
        client.signout(token)
                .map(new Func1<String, Boolean>() {
                    Boolean succes;
                    @Override
                    public Boolean call(String s) {
                        Parser.SIGN_OUT(s,new ParsingResponse<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                succes=true;
                            }

                            @Override
                            public void onFailure(String failedResult) {
                                succes=false;
                            }
                        });
                        return succes;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                           if(aBoolean){
                               EventBus.getDefault().post(new SignoutSucceded());
                           }else{
                               EventBus.getDefault().post(new SignoutFailed());
                           }
                    }
                })
        ;
    }

    @Override
    public void getCategories(String token) {
        client.getCategories(token)
                .map(new Func1<String, List<Category>>() {
                    @Override
                    public  List<Category> call(String s) {
                        final List<Category> listCategories=new ArrayList<Category>();
                        Parser.CATEGORIES(s,new ParsingResponse<List<Category>>() {
                            @Override
                            public void onSuccess(List<Category> result) {
                                listCategories.addAll(result);
                            }

                            @Override
                            public void onFailure(String failedResult) {

                            }
                        });
                        return listCategories;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Category>>() {
                    @Override
                    public void call(List<Category> categories) {
                        EventBus.getDefault().post(new LoadCategoriesSucceded(categories));
                    }
                });
        }

    @Override
    public void getFollowedCategories(String token) {
        client.getFollowedCategories(token)
                .map(new Func1<String,List<Category>>() {
                    @Override
                    public List<Category> call(String s) {
                        final List<Category> listCategories=new ArrayList<Category>();
                        Parser.CATEGORIES_FOLLOWED(s, new ParsingResponse<List<Category>>() {
                            @Override
                            public void onSuccess(List<Category> result) {
                                listCategories.addAll(result);
                            }

                            @Override
                            public void onFailure(String failedResult) {

                            }
                        });
                        return listCategories;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Category>>() {
                    @Override
                    public void call(List<Category> categories) {
                        EventBus.getDefault().post(new LoadFollowedCategoriesSucceded(categories));
                    }
                });
    }

    @Override
    public void getAnnouncements(String token, int categoryId) {
        client.getAnnouncements(token,categoryId)
                .map(new Func1<String,List<Announcement>>(){

                    @Override
                    public List<Announcement> call(String s) {
                        final List<Announcement> announcementList=new ArrayList<Announcement>();
                        Parser.ANNOUNCEMENTS(s,new ParsingResponse<List<Announcement>>() {
                            @Override
                            public void onSuccess(List<Announcement> result) {
                                announcementList.addAll(result);
                            }

                            @Override
                            public void onFailure(String failedResult) {
                            }
                        });
                        return announcementList;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Announcement>>() {
                    @Override
                    public void call(List<Announcement> announcements) {
                        EventBus.getDefault().post(new LoadAnnouncementListSucceded(announcements));
                    }
                },new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        EventBus.getDefault().post(new LoadAnnouncementListFailed());
                    }
                });

    }

    @Override
    public void getAnnouncementDetail(String token, int announcementId) {
        client.getAnnouncementDetail(token,announcementId)
                .map(new Func1<String, Announcement>() {
                    @Override
                    public Announcement call(String s) {
                        final Announcement[] ann = new Announcement[1];
                        Parser.ANNOUNCEMENT_DETAIL(s,new ParsingResponse<Announcement>() {
                            @Override
                            public void onSuccess(Announcement result) {
                                    ann[0] =result;
                            }

                            @Override
                            public void onFailure(String failedResult) {

                            }
                        });
                        return ann[0];
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Announcement>() {
                    @Override
                    public void call(Announcement announcement) {
                        EventBus.getDefault().post(new LoadAnnouncementDetailSucceded(announcement));
                    }
                },new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        EventBus.getDefault().post(new LoadAnnouncementDetailFailed());
                    }
                });
    }

    @Override
    public void follow(String token, final Category category) {

        client.follow(token,category.getId())
                .map(new Func1<String, Category>() {
                    @Override
                    public Category call(String s) {
                        Parser.FOLLOW_CATEGORY(s, new ParsingResponse<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                category.setFollowed(true);
                            }

                            @Override
                            public void onFailure(String failedResult) {

                                category.setFollowed(false);

                            }
                        });
                        return category;
                    }
                }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Category>() {
            @Override
            public void call(Category category) {
                if(category.isFollowed()){
                    System.out.println("........... "+category);

                    EventBus.getDefault().post(new FollowCategorySucceded(category));
                }else{
                    EventBus.getDefault().post(new FollowCategoryFailed(category));

                }

            }
        },new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {


            }
        });


    }


    @Override
    public void unFollow(String token, final Category category) {
        client.unfollow(token,category.getId())
                .map(new Func1<String, Category>() {
                    @Override
                    public Category call(String s) {
                        Parser.UNFOLLOW_CATEGORY(s,new ParsingResponse<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                category.setFollowed(false);
                                System.out.println("parsing success");
                            }

                            @Override
                            public void onFailure(String failedResult) {
                                System.out.println("parsing failed");
                            }
                        });
                        return category;
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Category>() {
                    @Override
                    public void call(Category category) {
                        System.out.println("==++!!! next "+category.toString());
                        if(category.isFollowed()){
                            EventBus.getDefault().post(new UnfollowCategoryFailed(category));
                        }else{
                            EventBus.getDefault().post(new UnfollowCategorySucceded(category));
                        }
                    }
                });
    }


}
