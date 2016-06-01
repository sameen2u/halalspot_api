package com.halal.sa.common.error;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbstractErrorResponse {

	private Integer id;
    private String description;
    private List<String> examples;
    private List<Error> errors;
    private String type;

	public AbstractErrorResponse(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

    public AbstractErrorResponse(Integer id, String description, String type) {
    	this.id = id;
		this.description = description;
		this.type = type;
	}

	/**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the examples
     */
    public List<String> getExamples() {
        return examples;
    }

    /**
     * @param examples the examples to set
     */
    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    /**
     * @return the errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String str) {
        type = str;
    }
}
