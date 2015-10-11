package chipset.techtatva.model.events;

import java.util.ArrayList;

/**
 * Created by saketh on 19/8/15.
 */
public class Category {
    private String CatName;
    private int CatId;
    private String CatType;
    private String Description;
    private ArrayList<Event> Events = new ArrayList<Event>();


    public void setCatId(int catId) {
        CatId = catId;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public void setCatType(String catType) {
        CatType = catType;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setEvents(ArrayList<Event> events) {
        Events = events;
    }

    public ArrayList<Event> getEvents() {
        return Events;
    }

    public int getCatId() {
        return CatId;
    }

    public String getCatName() {
        return CatName;
    }

    public String getCatType() {
        return CatType;
    }

    public String getDescription() {
        return Description;
    }

}
