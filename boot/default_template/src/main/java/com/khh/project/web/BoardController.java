package com.khh.project.web;

import com.khh.project.service.board.BoardService;
import com.khh.project.service.board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(BoardController.PATH_ROOT)
//@Repository
//@Transactional
//@javax.transaction.Transactional
public class BoardController {

    public static final String PATH_ROOT    =   "/board";

    @Autowired
    BoardService service;

    @RequestMapping({"","/"})
    @ResponseBody
    String index() {
        return "boar main";
    }



    @RequestMapping("/list")
    @ResponseBody
    List<Board> list() {
        return service.list();
    }

    @RequestMapping("/add")
    @ResponseBody
    Board add(@RequestParam(name = "name",required = true) String name) {
        return service.add(name);
    }


}
