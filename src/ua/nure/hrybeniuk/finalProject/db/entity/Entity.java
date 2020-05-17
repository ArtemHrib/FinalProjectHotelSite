package ua.nure.hrybeniuk.finalProject.db.entity;

import java.io.Serializable;

/**
 * Root of all entities with id field.
 * 
 * @author A.Hrybeniuk
 * 
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 7156853265467769863L;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
