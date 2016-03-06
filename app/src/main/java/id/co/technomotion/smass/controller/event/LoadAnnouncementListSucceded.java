package id.co.technomotion.smass.controller.event;

import java.util.ArrayList;
import java.util.List;

import id.co.technomotion.smass.model.Announcement;

/**
 * Created by omayib on 12/30/14.
 */
public class LoadAnnouncementListSucceded {
    public List<Announcement> announcementList=new ArrayList<>();

    public LoadAnnouncementListSucceded(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }
}
