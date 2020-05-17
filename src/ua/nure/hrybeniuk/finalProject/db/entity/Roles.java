package ua.nure.hrybeniuk.finalProject.db.entity;

import java.util.Locale;

/**
 * Role entity.
 * 
 * @author A.Hrybeniuk
 * 
 */

public enum Roles {
	ADMIN("Admin"), MANAGER("Manager"), CLIENT("Client");

	private String value;

	Roles(String value) {
		this.value = value;
	}

	public static Roles getRole(String role) {
		if(role==null) {
			return null;
		}
		return Roles.valueOf(role.toUpperCase(Locale.getDefault()));
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}

}
