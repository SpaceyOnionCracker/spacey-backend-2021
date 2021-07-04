package com.heroku.spacey.controllers;

import com.heroku.spacey.entity.Status;
import com.heroku.spacey.services.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-statuses")
public class UserStatusController {

    private final UserStatusService userStatusService;


    @GetMapping("")
    public List<Status> getUserStatuses() {
        return userStatusService.getUserStatuses();
    }
}
