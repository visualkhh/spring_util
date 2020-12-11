package com.company.service.monitor.controllers.brain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(BrainUserController.URI_PREFIX)
public class BrainUserController {
    public static final String URI_PREFIX = "/brains";
}
