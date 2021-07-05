package com.heroku.spacey.dao;

import com.heroku.spacey.entity.Status;

import java.util.List;

public interface StatusDao {
    List<Status> getAllStatuses();
    Long getStatusId(String statusName);
    String getStatusName(long id);
    long insertStatus(String statusName);
}
