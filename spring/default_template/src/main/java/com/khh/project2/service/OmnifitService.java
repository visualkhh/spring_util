package com.khh.project2.service;

import java.util.List;

import javax.annotation.Resource;
import javax.script.ScriptException;

import com.khh.project2.dao.BoardDAO;
import com.khh.project2.vo.BoardVO;
import org.springframework.stereotype.Service;

import com.khh.project2.vo.BoardNormalVO;
import com.khh.project2.vo.BoardUserIDVO;
import com.khh.project2.vo.BoardWriteVO;


@Service("OmnifitService")
public class OmnifitService {
	
	@Resource(name="BoardDAO")
	BoardDAO boardDAO;
	public List<BoardVO> getContentBoards(String like) {
		return boardDAO.getContentBoards(like);
	}
	public List<BoardWriteVO> getWriteBoards(String nameLike) {
		return boardDAO.getWriteBoards(nameLike);
	}
	public List<BoardUserIDVO> getIDBoards(String nameLike) {
		return boardDAO.getIDBoards(nameLike);
	}
	public List<BoardNormalVO> getNormalBoards(String like) {
	//	return boardDAO.getNormalBoards(like);
//		return boardDAO.getNormalBoards2(like);
		try {
			return boardDAO.getNormalBoards3(like);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Integer setEntityBoard(BoardVO bvo) {
		return boardDAO.setEntityBoard(bvo);
	}
	public Integer setNamedBoard(BoardVO bvo) {
		return boardDAO.setNamedBoard(bvo);
	}
}
