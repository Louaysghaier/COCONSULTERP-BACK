package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Interfaces.MeetingInterface;
import com.test.COCONSULT.Reposotories.MeetingsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MeetingServiceImp implements MeetingInterface {
    @Autowired
    MeetingsRepository meetingsRepository;
}
