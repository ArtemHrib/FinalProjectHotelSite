package ua.nure.hrybeniuk.finalProject.db.entity;

/**
 * User entity.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class User extends Entity {

	private static final long serialVersionUID = -8631045956157566592L;

	private String fullName;

	private String phoneNumber;

	private String email;
	
	private String password;

	private Roles role;

	public String getFullName() {
		return fullName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = Roles.getRole(role);
	}

	@Override
	public String toString() {
		return "User [fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", role="
				+ role.value() + "]";
	}

}
