/* (C)2023 */
package org.springpr.springpr.jpa.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

public interface BaseJdbcDao<T> {

    List<T> query(String sql, RowMapper<T> rowMapper, Object... args);

    int update(final String sql, final Object... args);

    void execute(final String sql, final PreparedStatementCallback<T> action);

    T queryForObject(final String sql, final Class<T> requiredType, final Object... paramVarArgs);

    Map<String, Object> queryForMap(final String sql);

    Map<String, Object> queryForMap(final String sql, final Object... args);

    List<Map<String, Object>> queryForList(final String sql);

    List<T> queryForList(final String sql, final Class<T> elementType);

    List<T> queryForList(String sql, RowMapper<T> rowMapper, Object... args);

    List<T> queryForList(String sql, Class<T> elementType, Object... args);

    Map<String, String> getConnectionInfo();

    List<T> queryForListElementType(String sql, Class<T> elementType, Object... args);

    T queryForObject(String sql, RowMapper<T> rowMapper, Object... args);
}
