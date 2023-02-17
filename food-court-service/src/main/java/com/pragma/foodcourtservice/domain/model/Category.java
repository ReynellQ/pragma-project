package com.pragma.foodcourtservice.domain.model;

public class Category {
    private Long id;
    private String name;
    private String description;

    public Category() {
    }

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the id of the category.
     * @return a Long value representing the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the category.
     * @param id the id of the category.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     * @return a String value representing the name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the category.
     * @param name the name of the category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the category.
     * @return a String value representing the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the category.
     * @param description the description id of the food plate.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
