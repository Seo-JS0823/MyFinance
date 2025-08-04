package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.access.AccessContext;
import model.access.ResultSetStrategy;
import model.access.StatementStrategy;
import model.dto.UserDto;
import model.immutable.UserQuery;

public class UserDao implements Dao<UserDto> {
	private AccessContext access;
	
	private String create = "INSERT INTO users (userid, password, username, gender, email, birthday) values (?, ?, ?, ?, ?, ?)";
	private String read = "SELECT * FROM users WHERE 1=1 ";
	private String updateSet = "UPDATE users SET ";
	private String updateWhere = " WHERE 1=1 ";
	
	
	public UserDao() {
		this.access = AccessContext.getInstance();
	}
	
	/* Create */
	public void create(final UserDto dto) {
		access.executeUpdate(new StatementStrategy() {
			public PreparedStatement update(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(create);
				
				ps.setString(1, dto.getUserid());
				ps.setString(2, dto.getPassword());
				ps.setString(3, dto.getUsername());
				ps.setString(4, dto.getGender());
				ps.setString(5, dto.getEmail());
				ps.setDate(6, dto.getBirthday());
				
				return ps;
			}
		});
	}
	
	/* ReadOne */
	public UserDto readOne(final UserDto dto) {
		UserDto readUser = new UserDto();
		access.executeQuery(new StatementStrategy() {
			public PreparedStatement update(Connection con) throws SQLException {
				String sql = selectAssemble(dto);
				
				PreparedStatement ps = con.prepareStatement(sql);
				
				ps.setString(1, dto.getUserid());
				ps.setString(2, dto.getPassword());
				
				return ps;
			}
		}, new ResultSetStrategy() {
			public void query(PreparedStatement pstmt) throws SQLException {
				ResultSet rs = pstmt.executeQuery();
				
				List<UserDto> list = readCondition(rs);
				
				if(list.isEmpty()) {
					System.err.println("입력하신 정보로 존재하는 유저가 없습니다.");
					return;
				}
				
				String userid = list.get(0).getUserid();
				String password = list.get(0).getPassword();
				String username = list.get(0).getUsername();
				String gender = list.get(0).getGender();
				String email = list.get(0).getEmail();
				Date birthday = list.get(0).getBirthday();
				Date regdate = list.get(0).getRegdate();
				int flag = list.get(0).getFlag();
				
				readUser.setUserid(userid);
				readUser.setPassword(password);
				readUser.setUsername(username);
				readUser.setGender(gender);
				readUser.setEmail(email);
				readUser.setBirthday(birthday);
				readUser.setFlagAndRegdate(regdate, flag);
				
				if(rs != null) {
					rs.close();
					System.out.println("ResultSet Close SUCCESS - [UserDao - readOne]");
				}
			}
		});
		return readUser;
	}
	
	/* ReadAll */
	public List<UserDto> readAll() {
		List<UserDto> readUsers = new ArrayList<>();
		access.executeQuery(new StatementStrategy() {
			public PreparedStatement update(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(read + UserQuery.SEL_FLAG_0.getSQL());
				
				return ps;
			}
		}, new ResultSetStrategy() {
			public void query(PreparedStatement pstmt) throws SQLException {
				ResultSet rs = pstmt.executeQuery();
				
				readUsers.addAll(readCondition(rs));
				
				if(rs != null) {
					rs.close();
					System.out.println("ResultSet Close SUCCESS - [UserDao - readAll]");
				}
			}
		});
		return readUsers;
	}
	
	/* update */
	public void update(UserDto change, UserDto target) {
		/* target이 존재하는 유저인지 검증 */
		UserDto dto = readOne(target);
		if(dto == null) {
			System.err.println("조회 결과 존재하는 유저가 아닙니다.");
			return;
		}
		access.executeUpdate(new StatementStrategy() {
			public PreparedStatement update(Connection con) throws SQLException {
				String sql = updateAssemble(change, target);
				List<String> params = getUpdateParameters(change, target);
				
				PreparedStatement ps = con.prepareStatement(sql);
				
				for(int i = 0; i < params.size(); i++) {
					ps.setString(i+1, params.get(i));
				}
				
				return ps;
			}
		});
	}
	
	/* DB에서 검색 후 ArrayList<UserDto>로 반환 없으면 예외를 던진다.
	 * DB에서 검색 후 검색 정보가 없을 때 예외를 던지는 게 나을지 Null 반환이 나을지... 고민중이다.
	 */
	public List<UserDto> readCondition(ResultSet rs) throws SQLException {
		List<UserDto> user = new ArrayList<>();
		
		while(rs.next()) {
			
			UserDto readUser = new UserDto();
			String userid = rs.getString("userid");
			String password = rs.getString("password");
			String username = rs.getString("username");
			String gender = rs.getString("gender");
			String email = rs.getString("email");
			Date birthday = rs.getDate("birthday");
			Date regdate = rs.getDate("regdate");
			int flag = rs.getInt("flag");
			
			readUser.setUserid(userid);
			readUser.setPassword(password);
			readUser.setUsername(username);
			readUser.setGender(gender);
			readUser.setEmail(email);
			readUser.setBirthday(birthday);
			readUser.setFlagAndRegdate(regdate, flag);
			
			user.add(readUser);
		}
		return user;
	}
	
	/* Update문 파라미터 구하기 */
	private List<String> getUpdateParameters(UserDto change, UserDto target) {
		List<String> params = new ArrayList<>();
		
		// SET절 쿼리 바인딩 값 추가
		if(change.getEmail() != null) {
			params.add(change.getEmail());
		}
		if(change.getPassword() != null) {
			params.add(change.getPassword());
		}
		
		// WHERE절 쿼리 바인딩 값 추가
		if(target.getUserid() != null) {
			params.add(target.getUserid());
		}
		
		if(target.getPassword() != null) {
			params.add(target.getPassword());
		}
		return params;
	}
	
	/* 동적 쿼리 Select 문자열 조합 */
	private String selectAssemble(UserDto dto) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(read);
		
		if(dto.getUserid() != null) {
			sql.append(UserQuery.SEL_USERID.getSQL());
		}
		
		if(dto.getPassword() != null) {
			sql.append(UserQuery.SEL_PASSWORD.getSQL());
		}
		
		if(dto.getUsername() != null) {
			sql.append(UserQuery.SEL_USERNAME.getSQL());
		}
		
		if(dto.getGender() != null) {
			sql.append(UserQuery.SEL_GENDER.getSQL());
		}
		
		if(dto.getEmail() != null) {
			sql.append(UserQuery.SEL_EMAIL.getSQL());
		}
		
		if(dto.getBirthday() != null) {
			sql.append(UserQuery.SEL_BIRTHDAY.getSQL());
		}
		
		if(dto.getRegdate() != null) {
			sql.append(UserQuery.SEL_REGDATE.getSQL());
		}
		if(dto.getFlag() != null) {
			sql.append(UserQuery.SEL_FLAG.getSQL());
		}
		System.out.println(sql.toString());
		
		return sql.toString();
	}
	
	/* 동적 쿼리 Update 문자열 조합 */
	private String updateAssemble(UserDto change, UserDto target) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(updateSet);
		
		if(change.getEmail() != null) {
			sql.append(UserQuery.UP_EMAIL.getSQL());
		}
		if(change.getPassword() != null) {
			sql.append(UserQuery.UP_PASSWORD.getSQL());
		}
		
		/* 콤마 제거 로직 */
		if(sql.charAt(sql.length() - 1) == ',') {
			sql.setLength(sql.length() - 1);
		}
		
		sql.append(updateWhere);
		
		if(target.getUserid() != null) {
			sql.append(UserQuery.SEL_USERID.getSQL());
		}
		
		if(target.getPassword() != null) {
			sql.append(UserQuery.SEL_PASSWORD.getSQL());
		}
		return sql.toString();
	}
	
}