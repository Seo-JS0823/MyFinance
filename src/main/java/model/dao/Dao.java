package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	
	/* INSERT */
	void create(final T dto);
	
	/* SELECT 1건 */
	T readOne(final T dto);
	
	/* SELECT 전체 */
	List<T> readAll();
	
	/* SELECT 조건 전체 */
	List<T> readCondition(ResultSet rs) throws SQLException;
	
	/* UPDATE 1건 */
	void update(final T change, final T target);
	
}
