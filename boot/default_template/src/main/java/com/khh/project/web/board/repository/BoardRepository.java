package com.khh.project.web.board.repository;

import com.khh.project.web.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,String> {


    public Board findByName(String name);
}


