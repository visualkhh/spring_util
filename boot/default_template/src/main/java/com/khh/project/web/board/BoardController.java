package com.khh.project.web.board;

import com.khh.project.web.board.domain.Board;
import com.khh.project.web.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardRepository repository;


    @RequestMapping({"","/"})
    @ResponseBody
    String home() {
        return "boar main";
    }
    @RequestMapping("/add")
    @ResponseBody
    Board add(@RequestParam(name = "name",required = true) String name) {
        Board board = new Board();
        board.setAddr("asd");
        board.setName(name);
        board = repository.save(board);
        return board;
    }
}
