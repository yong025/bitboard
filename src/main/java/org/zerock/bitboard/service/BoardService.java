package org.zerock.bitboard.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dao.BoardDAO;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardService {

    INSTANCE;

    public List<BoardDTO> getlist(PageDTO pageDTO)throws RuntimeException{

        log.info("Getlist....");
        log.info(pageDTO);
        return BoardDAO.INSTANCE.list(pageDTO);
    }


    public void insert(BoardDTO boardDTO){
        log.info("insert ..........");
        BoardDAO.INSTANCE.insert(boardDTO);
    }

    public BoardDTO select(Integer bno){

        log.info("select ..........");
        log.info(BoardDAO.INSTANCE.select(bno));

       return BoardDAO.INSTANCE.select(bno);
    }


    public void update(BoardDTO boardDTO){

        log.info("update....");
        BoardDAO.INSTANCE.update(boardDTO);

    }

    public void delete(Integer bno){

        log.info("delete.......");
        BoardDAO.INSTANCE.delete(bno);
    }
}
