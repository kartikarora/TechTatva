package chipset.techtatva.model.instagram;

import com.google.gson.annotations.Expose;

/**
 * Developer: chipset
 * Package : chipset.revels.model.instagram
 * Project : Revels
 * Date : 16/1/15
 */

public class Meta {

    @Expose
    private Integer code;

    /**
     * @return The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

}
