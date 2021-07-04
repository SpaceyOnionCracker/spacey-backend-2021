package com.heroku.spacey.mapper;

import com.heroku.spacey.entity.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusMapper implements RowMapper<Status> {
    @Override
    public Status mapRow(ResultSet resultSet, int i) throws SQLException {
        Status status = new Status();
        status.setId(resultSet.getLong("statusid"));
        status.setStatusName(resultSet.getString("statusname"));
        return status;
    }
}