package com.brain.revanth.sampleapplication2.events;

/**
 * Created by murali507 on 3/18/2017.
 */

public class EventCommonClass {
    public String eventName;
    public String eventId;
    public String eventLocation;
    public String collegeName;
    public String startDate;
    public String endDate;
    public String eventDescription;
        public EventCommonClass(){

        }
    public EventCommonClass(String eventName, String eventId, String eventLocation, String collegeName, String startDate, String endDate, String eventDescription){
        this.eventName = eventName;
        this.eventId = eventId;
        this.eventLocation = eventLocation;
        this.collegeName = collegeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventDescription = eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

}
