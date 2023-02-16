package com.pragma.userservice.domain.model;

/**
 * POJO class for Role. Represents a Role in the Model layer.
 *
 */
public class Role {
    private Integer id;
    private String name;
    private String description;

    /**
     * Gets the id of the role.
     * @return an Integer containing the id of the role.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id of the role.
     * @param id the id of the role.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     * @return a String containing the name of the role.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     * @param name the name of the role.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the role.
     * @return a String containing the description of the role.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the role.
     * @param description the description of the role.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
