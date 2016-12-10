package com.khh.project.web.anon;

import com.khh.project.config.web.WebSecurityConfigurerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(AnonController.PATH_ROOT)
public class AnonController {

    public static final String PATH_ROOT = WebSecurityConfigurerAdapter.ANON_PATH;

    @RequestMapping({"","/"})
    String index() {
        return "anon main";
    }
}
