package com.brain.revanth.sampleapplication2.Model;

/**
 * Created by Hari Prahlad on 17-05-2016.
 */
public class EventCommonClass {

    private String EventId;
    private String EventName;
    private String EventLocation;
    private String EventDate;

    public EventCommonClass(){

    }
    public EventCommonClass(String EventName,String EventLocation,String EventDate,String EventId) {
        this.EventName = EventName;
        this.EventLocation = EventLocation;
        this.EventDate = EventDate;
        this.EventId = EventId;
    }
    public String getEventId() {
        return EventId;
    }

    public void setEventId(String eventId) {
        EventId = eventId;
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
