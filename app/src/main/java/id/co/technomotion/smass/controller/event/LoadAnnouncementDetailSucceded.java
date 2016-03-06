package id.co.technomotion.smass.controller.event;

import id.co.technomotion.smass.model.Announcement;

/**
 * Created by omayib on 12/30/14.
 */
public class LoadAnnouncementDetailSucceded {
    public Announcement announcement;

    public LoadAnnouncementDetailSucceded(Announcement announcement) {
        this.announcement = announcement;
    }
}
