package chipset.techtatva.model.events;

/**
 * Created by saketh on 19/8/15.
 */
public class Event {
    private int Event_id;
    private String Event_name;
    private String Description;
    private String StartTime;
    private String EndTime;
    private String Location;
    private int Day;
    private String ContactName;
    private String ContactNumber;
    private String Date;
    private int CatId;
    private int EventMaxTeamNumber;


    public void setDescription(String description) {
        Description = description;
    }

    public void setCatId(int catId) {
        CatId = catId;
    }

    public void setEvent_id(int event_id) {
        Event_id = event_id;
    }

    public void setEvent_name(String event_name) {
        Event_name = event_name;
    }

    public void setEventMaxTeamNumber(int eventMaxTeamNumber) {
        EventMaxTeamNumber = eventMaxTeamNumber;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDay(int day) {
        Day = day;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getDescription() {
        return Description;
    }

    public int getCatId() {
        return CatId;
    }

    public int getEvent_id() {
        return Event_id;
    }

    public String getEvent_name() {
        return Event_name;
    }

    public int getEventMaxTeamNumber() {
        return EventMaxTeamNumber;
    }

    public int getDay() {
        return Day;
    }

    public String getContactName() {
        return ContactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public String getDate() {
        return Date;
    }

    public String getEndTime() {
        return EndTime;
    }

    public String getLocation() {
        return Location;
    }

    public String getStartTime() {
        return StartTime;
    }
}

