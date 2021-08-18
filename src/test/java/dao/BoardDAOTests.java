package dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.bitboard.dao.BoardDAO;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

@Log4j2
public class BoardDAOTests {

    @Test
    public void testInsert(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test")
                .content("Test")
                .writer("user1")
                .build();

        BoardDAO.INSTANCE.insert(boardDTO);
    }

    @Test
    public void testselect(){
        log.info(BoardDAO.INSTANCE.select(4));
    }

    @Test
    public void testlist(){

        PageDTO pageDTO = PageDTO.builder().page(3).build();


        BoardDAO.INSTANCE.list(pageDTO).forEach(boardDTO -> log.info(boardDTO));
    }

    @Test
    public void testupdate(){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(4)
                .title("aaaa")
                .content("bbbbbb")
                .build();

        BoardDAO.INSTANCE.update(boardDTO);
    }

    @Test
    public void delete(){

        log.info("delete......");
        BoardDAO.INSTANCE.delete(5);
    }
}
