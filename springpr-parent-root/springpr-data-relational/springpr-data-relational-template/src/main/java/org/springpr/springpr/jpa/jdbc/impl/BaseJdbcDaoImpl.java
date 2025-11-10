/* (C)2023 */
package org.springpr.springpr.jpa.jdbc.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.jpa.jdbc.BaseJdbcDao;

@Repository("baseJdbcDao")
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class BaseJdbcDaoImpl<T> implements BaseJdbcDao<T> {
    @Autowired protected JdbcTemplate jdbcTemplate;

    @Override
    public List<T> query(final String sql, final RowMapper<T> rowMapper, final Object... args) {
        return this.jdbcTemplate.query(sql, rowMapper, args);
    }

    @Override
    public int update(final String sql, final Object... args) {
        return this.jdbcTemplate.update(sql, args);
    }

    @Override
    public void execute(final String sql, final PreparedStatementCallback<T> action) {
        this.jdbcTemplate.execute(sql, action);
    }

    @Override
    public T queryForObject(final String sql, final Class<T> requiredType, final Object... args) {
        return this.jdbcTemplate.queryForObject(sql, requiredType, args);
    }

    @Override
    public T queryForObject(final String sql, final RowMapper<T> rowMapper, final Object... args) {
        return this.jdbcTemplate.queryForObject(sql, rowMapper, args);
    }

    @Override
    public Map<String, Object> queryForMap(final String sql) {
        return this.jdbcTemplate.queryForMap(sql);
    }

    @Override
    public Map<String, Object> queryForMap(final String sql, final Object... args) {
        return this.jdbcTemplate.queryForMap(sql, args);
    }

    @Override
    public List<Map<String, Object>> queryForList(final String sql) {
        return this.jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<T> queryForList(
            final String sql, final RowMapper<T> rowMapper, final Object... args) {
        return this.jdbcTemplate.query(sql, rowMapper, args);
    }

    @Override
    public List<T> queryForList(final String sql, final Class<T> elementType) {
        return this.jdbcTemplate.queryForList(sql, elementType);
    }

    @Override
    public List<T> queryForList(
            final String sql, final Class<T> elementType, final Object... args) {
        return this.jdbcTemplate.queryForList(sql, elementType, args);
    }

    @Override
    public List<T> queryForListElementType(
            final String sql, final Class<T> elementType, final Object... args) {
        return this.jdbcTemplate.queryForList(sql, elementType, args);
    }

    @Override
    public Map<String, String> getConnectionInfo() {
        final Map<String, String> dbConnectionInfoMap = new HashMap<>();
        try {
            final Connection connection = this.jdbcTemplate.getDataSource().getConnection();
            final Boolean isConnectionClosed = connection.isClosed();

            log.debug("Connection Status:{}", isConnectionClosed);
            if (!isConnectionClosed) {
                dbConnectionInfoMap.put("URL", connection.getMetaData().getURL());
                dbConnectionInfoMap.put("UserName", connection.getMetaData().getUserName());
                dbConnectionInfoMap.put(
                        "DefaultTransactionIsolation",
                        String.valueOf(connection.getMetaData().getDefaultTransactionIsolation()));
                dbConnectionInfoMap.put("DriverName", connection.getMetaData().getDriverName());
                dbConnectionInfoMap.put(
                        "DriverMajorVersion",
                        String.valueOf(connection.getMetaData().getDriverMajorVersion()));
                dbConnectionInfoMap.put(
                        "DriverMinorVersion",
                        String.valueOf(connection.getMetaData().getDriverMinorVersion()));
                dbConnectionInfoMap.put(
                        "Max Connections possible",
                        String.valueOf(connection.getMetaData().getMaxConnections()));
                dbConnectionInfoMap.put(
                        "DatabaseProductName", connection.getMetaData().getDatabaseProductName());
                dbConnectionInfoMap.put(
                        "DatabaseProductVersion",
                        connection.getMetaData().getDatabaseProductVersion());
            }
        } catch (final Exception e) {
            log.error("Exception while connecting to Database.", e);
        }
        return dbConnectionInfoMap;
    }
}
