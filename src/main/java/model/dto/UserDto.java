package model.dto;

import java.sql.Date;

public class UserDto implements Dto<UserDto> {
	/* 인스턴스 변수 */
	private String userid;
	private String password;
	private String username;
	private String gender;
	private String email;
	private Date birthday;
	private Date regdate;
	private Integer flag;
	
	public UserDto() {}
	
	public UserDto(String userid, String password, String username, String gender, String email, Date birthday) {
		this.userid = userid;
		this.password = password;
		this.username = username;
		this.gender = gender.toUpperCase();
		this.email = email;
		this.birthday = birthday;
	}
	
	public UserDto(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}
	
	@Override
	public UserDto toReadOne() {
		UserDto dto = new UserDto();
		dto.setUserid(userid);
		dto.setPassword(password);
		
		return dto;
	}

	/* Getter 영역 */
	public String getUserid() {
		return this.userid;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	
	public Date getRegdate() {
		return this.regdate;
	}
	
	public Integer getFlag() {
		return this.flag;
	}
	
	/* Setter 영역 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setGender(String gender) {
		this.gender = gender.toUpperCase();
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public void setFlagAndRegdate(Date regdate, int flag) {
		this.regdate = regdate;
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		
		StringBuilder toString = new StringBuilder();
		
		toString.append("UserDto [");
		
		if(userid != null) {
			toString.append("userid=" + userid + ",");
		}
		if(password != null) {
			toString.append("password=" + password + ",");
		}
		if(username != null) {
			toString.append("username=" + username + ",");
		}
		if(gender != null) {
			toString.append("gender=" + gender + ",");
		}
		if(email != null) {
			toString.append("email=" + email + ",");
		}
		if(birthday != null) {
			toString.append("birthday=" + birthday.toString() + ",");
		}
		if(regdate != null) {
			toString.append("regdate=" + regdate.toString() + ",");
		}
		
		if(toString.charAt(toString.length() - 1) == ',') {
			toString.setLength(toString.length() - 1);
		}
		
		toString.append("]");
		
		return toString.toString();
	}
	
	/* JSON 응답용 */
	public String jsonData() {
		return this.toString().replace("UserDto ", "").replace("[", "{\"").replace("]", "\"}").replace(",", "\",\"").replace("=", "\":\"");
	}
	
}












