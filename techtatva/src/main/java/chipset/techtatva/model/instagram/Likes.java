package chipset.techtatva.model.instagram;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: chipset
 * Package : chipset.revels.model.instagram
 * Project : Revels
 * Date : 16/1/15
 */

public class Likes {

    @Expose
    private Integer count;
    @Expose
    private List<InstagramDatum_> data = new ArrayList<>();

    /**
     * @return The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The data
     */
    public List<InstagramDatum_> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<InstagramDatum_> data) {
        this.data = data;
    }

}
