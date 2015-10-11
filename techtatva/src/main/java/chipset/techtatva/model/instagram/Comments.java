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

public class Comments {

    @Expose
    private Integer count;
    @Expose
    private List<Object> data = new ArrayList<>();

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<Object> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<Object> data) {
        this.data = data;
    }

}
