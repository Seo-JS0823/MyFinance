package model.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccessContext {
	
	private static AccessContext accessContext = null;
	private static DBAccess access = null;
	
	private AccessContext() {}
	
	public static AccessContext getInstance() {
		if(accessContext == null && access == null) {
			access = new DBAccess();
			accessContext = new AccessContext();
			return accessContext;
		}
		return accessContext;
	}
	
	public void executeUpdate(StatementStrategy pstmt) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = access.getConnection();
			ps = pstmt.update(con);
			ps.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode() == 1062) {
				System.out.println("존재하는 아이디 입니다.");
				return;
			}
			e.printStackTrace();
			throw new RuntimeException("SQLException AccessContext: " + e.getErrorCode());
		} finally {
			try {
				if(ps != null) ps.close(); System.out.println("PreparedStatement Close SUCCESS - [AccessContext - executeUpdate]");
				if(con != null) con.close(); System.out.println("Connection Close SUCCESS - [AccessContext - executeUpdate]");
			} catch (SQLException e) {}
		}
	}
	
	public void executeQuery(StatementStrategy pstmt, ResultSetStrategy rss) {
		Connection con = access.getConnection();
		PreparedStatement ps = null;
		try {
			ps = pstmt.update(con);
			rss.query(ps);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQLException AccessContext: " + e.getMessage());
		} finally {
			try {
				if(ps != null) ps.close(); System.out.println("PreparedStatement Close SUCCESS - [AccessContext - executeQuery]");
				if(con != null) con.close(); System.out.println("Connection Close SUCCESS - [AccessContext - executeQuery]");
			} catch (SQLException e) {}
		}	
	}
	
	
	
}
