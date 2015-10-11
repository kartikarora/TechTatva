package chipset.techtatva.model.instagram;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("next_min_id")
    @Expose
    private String nextMinId;
    @SerializedName("deprecation_warning")
    @Expose
    private String deprecationWarning;
    @SerializedName("min_tag_id")
    @Expose
    private String minTagId;

    /**
     * @return The nextMinId
     */
    public String getNextMinId() {
        return nextMinId;
    }

    /**
     * @param nextMinId The next_min_id
     */
    public void setNextMinId(String nextMinId) {
        this.nextMinId = nextMinId;
    }

    /**
     * @return The deprecationWarning
     */
    public String getDeprecationWarning() {
        return deprecationWarning;
    }

    /**
     * @param deprecationWarning The deprecation_warning
     */
    public void setDeprecationWarning(String deprecationWarning) {
        this.deprecationWarning = deprecationWarning;
    }

    /**
     * @return The minTagId
     */
    public String getMinTagId() {
        return minTagId;
    }

    /**
     * @param minTagId The min_tag_id
     */
    public void setMinTagId(String minTagId) {
        this.minTagId = minTagId;
    }

}
