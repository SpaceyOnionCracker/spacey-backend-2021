package com.heroku.spacey.services;

import com.heroku.spacey.entity.Status;

import java.util.List;

public interface UserStatusService {

    List<Status> getUserStatuses();
}
