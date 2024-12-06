package com.asm.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@NotBlank(message="không được để trống username!")
	@Size(min = 3, max = 16, message = "tên đăng nhập phải có từ 3 đến 20 ký tự!")
	String username;
	@NotBlank(message="không được để trống password!")
	@Size(min = 8, max = 16, message = "mật khẩu phải có từ 3 đến 16 ký tự!")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Tên người dùng phải có ít nhất một chữ cái và một số!")
	String password;
	@NotBlank(message="không được để trống email!")
	@Email(message = "email không đúng định dạng!")
	String email;
	String role;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User(
			@NotBlank(message = "không được để trống username!") @Size(min = 3, max = 16, message = "tên đăng nhập phải có từ 3 đến 20 ký tự!") String username,
			@NotBlank(message = "không được để trống password!") @Size(min = 8, max = 16, message = "mật khẩu phải có từ 3 đến 16 ký tự!") @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Tên người dùng phải có ít nhất một chữ cái và một số!") String password,
			@NotBlank(message = "không được để trống email!") @Email(message = "email không đúng định dạng!") String email,
			String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	
	
}
