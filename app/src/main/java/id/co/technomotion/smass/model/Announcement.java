package id.co.technomotion.smass.model;

/**
 * Created by omayib on 12/26/14.
 */
public class Announcement {
    private int id;
    private String content;
    private String date;
    private String title;

    public Announcement(Builder b) {
        this.id = b.id;
        this.content = b.content;
        this.date = b.date;
        this.title=b.title;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getContentShorted(){
        if(content==null)return "";
        return content.length()>100?content.substring(0,100)+"...":content;
    }
    public String getContent() {
        return content;
    }

    public static class Builder{

        private int id;
        private String content;
        private String date;
        private String title;

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder content(String content){
            this.content=content;
            return this;
        }
        public Builder title(String title){
            this.title=title;
            return this;
        }
        public Builder date(String date){
            this.date=date;
            return this;
        }
        public Announcement build(){
            return new Announcement(this);
        }

    }
}
