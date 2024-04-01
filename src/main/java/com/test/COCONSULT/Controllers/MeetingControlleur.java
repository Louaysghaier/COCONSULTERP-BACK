package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Interfaces.MeetingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Meeting")
public class MeetingControlleur {
    @Autowired
    MeetingInterface meetingInterface;
}
