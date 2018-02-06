package com.centurylink.pctl.mod.core.dto.error;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Begin Samuel
 */
public class ServerErrorDTO {
    /**
     * constants for ServerErrorDTO
     */
    private String error;
    private String description;
    private List<Object> details = Lists.newArrayList();

    /**
     * ServerErrorDTO no argument constructor
     */
    public ServerErrorDTO() {
    }
    /**
     * ServerErrorDTO string arguments constructor
     */
    public ServerErrorDTO(String error, String description, List<Object> details) {
        this.error = error;
        this.description = description;
        this.details = details;
    }

    /**
     * getError() method returns error
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * setError() method for error
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * getDescription() method for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setDescription() method for description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getDetails() for object list
     * @return details
     */
    public List<Object> getDetails() {
        return details;
    }

    /**
     * setmethod for details
     * @param details
     */
    public void setDetails(List<Object> details) {
        this.details = details;
    }

    /**
     * equals () method overridden
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerErrorDTO that = (ServerErrorDTO) o;

        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return details != null ? details.equals(that.details) : that.details == null;

    }

    /**
     * hashcode() method overridden
     * @return int
     */

    @Override
    public int hashCode() {
        int result = error != null ? error.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }
}
