package cz.fi.muni.pa165.musiclibrary.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author Kovarik Tomas
 */
public class AplicationUserCreateDTO {
	private Long id;

	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 150)
	private String email;
	
	@NotNull
	@Size(min = 3, max = 1024)
	private String passHash;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(this.email);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AplicationUserCreateDTO)) {
			return false;
		}
		AplicationUserCreateDTO other = (AplicationUserCreateDTO) obj;
		return Objects.equals(this.email, other.getEmail());
	}


	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", passHash='" + passHash + '\'' +
			", role='" + role + '\'' +
			'}';
	}
}
