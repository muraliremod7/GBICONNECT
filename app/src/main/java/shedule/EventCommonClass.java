package shedule;

/**
 * Created by Hari Prahlad on 17-05-2016.
 */
public class EventCommonClass {



    private String EventName;
    private String EventLocation;
    private String EventDate;
    public EventCommonClass(){

    }
    public EventCommonClass(String EventName,String EventLocation,String EventDate) {
        this.EventName = EventName;
        this.EventLocation = EventLocation;
        this.EventDate = EventDate;
    }
    public  String getEventName() {
        return EventName;
    }

    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    public  String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String EventDate) {
        this.EventDate = EventDate;
    }

    public String getEventLocation() {
        return EventLocation;
    }

    public void setEventLocation(String EventLocation) {
        this.EventLocation = EventLocation;
    }

}
