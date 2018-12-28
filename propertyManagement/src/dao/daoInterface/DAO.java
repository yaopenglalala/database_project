package dao.daoInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void update(Connection connection, String sql, Object... args) throws Exception;

    T get(Connection connection, String sql, Object... args) throws Exception;

    List<T> getList(Connection connection, String sql, Object... args) throws Exception;

    <E> E getValue(Connection connection, String sql, Object... args) throws SQLException, Exception;
}
