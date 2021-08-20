package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;
import org.zerock.bitboard.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name ="ReadController", value ="/board/read")
public class ReadController extends HttpServlet {

    private Integer getInt(String str){
        try {
            int value = Integer.parseInt(str);//문자열로 받은 값을 숫자로 변경
            if(value <= 0){ // 입력받은 값이 0보다 작으면 null로 리턴(정상적인 페이지요청만 받음)
                return null;
            }
            return value;
        }catch(Exception e){
            return null; //오류가 생기면 null로 반환하라.
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer bno = getInt(request.getParameter("bno"));
        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));


        PageDTO pageDTO = PageDTO.builder().build();//pageDTO에 받은 값을 저장해야한다.

        if(page != null) {// pageDTO내의 page가 null이면 받은 page값을 넣어준다.
            pageDTO.setPage(page);
        }
        if(size != null) {// pageDTO 내의 size가 null이면 받은 size값을 넣어준다.
            pageDTO.setSize(size);
        }

        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        request.setAttribute("boardDTO", boardDTO);
        request.setAttribute("pageDTO", pageDTO);

        request.getRequestDispatcher("/WEB-INF/board/read.jsp").forward(request, response);



    }
}
