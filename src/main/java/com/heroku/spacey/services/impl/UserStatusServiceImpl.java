package com.heroku.spacey.services.impl;

import com.heroku.spacey.dao.StatusDao;
import com.heroku.spacey.entity.Status;
import com.heroku.spacey.services.UserStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserStatusServiceImpl implements UserStatusService {

    private final StatusDao userStatusDao;

    @Override
    public List<Status> getUserStatuses() {
        return userStatusDao.getAllStatuses();
    }
}
