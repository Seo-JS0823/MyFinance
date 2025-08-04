package model.access;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ResultSetStrategy {
	void query(PreparedStatement pstmt) throws SQLException;
}
