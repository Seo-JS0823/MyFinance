package model.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import model.dao.Dao;
import model.dao.UserDao;
import model.dto.UserDto;
import model.exception.SigninException;

public class UserService {
	private static Dao<UserDto> dao = null;
	
	private static UserService instance = null;
	
	public UserService(Dao<UserDto> dao) {
		UserService.dao = dao;
	}
	
	public static UserService getInstance(UserDao dao) {
		if(instance == null) {
			instance = new UserService(dao);
			return instance;
		}
		return instance;
	}
	
	/* 회원가입 메소드 */
	public UserDto signin(String userid, String password, String username, String gender, String email, Date birthday) {
		if(userid.isEmpty() || password.isEmpty() || username.isEmpty() || gender.isEmpty() || email.isEmpty() || birthday == null) {
			throw new SigninException();
		}
		UserDto target = new UserDto(userid, password, username, gender.toUpperCase(), email, birthday);
		
		dao.create(target);
		
		return dao.readOne(target.toReadOne());
	}
	
	/* 로그인 메소드 */
	public UserDto login(String userid, String password) {
		return dao.readOne(new UserDto(userid, password));
	}
	
	/* 비밀번호 변경 메소드 */
	public String findPassword(String userid, String password, String updateData) {
		UserDto target = new UserDto(userid, password);
		UserDto change = new UserDto();
		change.setPassword(updateData);
		
		dao.update(change, target);
		
		target.setPassword(updateData);
		
		return dao.readOne(target.toReadOne()).jsonData();
	}
	
	/* 이메일 변경 메소드 */
	public UserDto findEmail(String userid, String password, String updateDate) {
		UserDto target = new UserDto(userid, password);
		UserDto change = new UserDto();
		change.setEmail(updateDate);
		
		dao.update(change, target);
		
		return dao.readOne(target.toReadOne());
	}
	
	/* Expenses 테이블 관련 클래스 추가시 */
	/* 회원삭제 메소드 */
	/* 해당 회원의 월별 총 지출액 검색 */
	/* 해당 회원의 카테고리 별 지출내역 검색 */
	/* 해당 회원의 어쩌고저쩌고 */
	
	/* JSON 데이터를 UserDto 객체로 변환 */
	public UserDto userJSONParse(String jsonData) {
		String body = jsonData.replace("{\"", "").replace("\"}", "").replaceAll("\":\"", ":").replaceAll("\",\"", ",");
		Map<String, String> params = new HashMap<>();
		
		/* userid:id */
		String[] param = body.split(",");
		for(int i = 0; i < param.length; i++) {
			String[] kv = param[i].split(":");
			
			params.put(kv[0], kv[1]);
		}
		
		UserDto dto = new UserDto();
		
		for(String key : params.keySet()) {
			if(key.equals("username")) {
				String value = params.get(key);
				dto.setUsername(value);
			}
			if(key.equals("userid")) {
				String value = params.get(key);
				dto.setUserid(value);
			}
			if(key.equals("password")) {
				String value = params.get(key);
				dto.setPassword(value);
			}
			if(key.equals("gender")) {
				String value = params.get(key);
				dto.setGender(value);
			}
			if(key.equals("email")) {
				String value = params.get(key);
				dto.setEmail(value);
			}
			if(key.equals("birthday")) {
				String value = params.get(key);
				dto.setBirthday(Date.valueOf(value));
			}
			if(key.equals("regdate")) {
				String value = params.get(key);
				dto.setRegdate(Date.valueOf(value));
			}
		}
		
		return dto;
	}
	
}
