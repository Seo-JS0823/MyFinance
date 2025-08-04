package model.immutable;

public enum UserQuery {
	SEL_USERID(" AND userid = ?"),
	SEL_PASSWORD(" AND password = ?"),
	SEL_USERNAME(" AND username = ?"),
	SEL_GENDER(" AND gender = ?"),
	SEL_EMAIL(" AND email = ?"),
	SEL_BIRTHDAY(" AND birthday = ?"),
	SEL_REGDATE(" AND regdate = ?"),
	SEL_FLAG(" AND flag = ?"),
	SEL_FLAG_0(" AND flag = 0"),
	SEL_FLAG_1(" AND flag = 1"),
	UP_EMAIL(" email = ?,"),
	UP_PASSWORD(" password = ?,");
	
	private String sql;
	
	UserQuery(String sql) {
		this.sql = sql;
	}
	
	public String getSQL() {
		return this.sql;
	}
	
}
