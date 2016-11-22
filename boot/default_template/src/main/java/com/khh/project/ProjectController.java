package com.khh.project;

import com.khh.project.web.board.domain.Board;
import com.khh.project.web.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller("/")
public class ProjectController {
    @Autowired
    BoardRepository boardRepository;
    @RequestMapping({"","/"})
//    @ResponseBody
    String home(@RequestParam(name = "name",required = false) String name, Model model) {
        List<Board> boards = new ArrayList<>();
        if(null==name){
            boards = boardRepository.findAll();
        }else{
            boards.add(boardRepository.findByName(name));
        }
        log.debug("indeeeeexxxxxx"+boards);
        model.addAttribute("board",boards);
        return "board/index";
    }
}
