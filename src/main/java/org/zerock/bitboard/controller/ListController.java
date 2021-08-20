package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;
import org.zerock.bitboard.dto.PageMaker;
import org.zerock.bitboard.service.BoardService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name = "ListController", value = "/board/list")
public class ListController extends HttpServlet {

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

        Integer page = getInt(request.getParameter("page"));// 2번  파라미터 수집해서 getInt메서드에 입력.
        Integer size = getInt(request.getParameter("size"));// getInt 메서드 재정의
        //size는 페이지 내의 글 갯수

        PageDTO pageDTO = PageDTO.builder().build();//pageDTO에 받은 값을 저장해야한다.

        if(page != null) {// pageDTO내의 page가 null이면 받은 page값을 넣어준다.
            pageDTO.setPage(page);
        }
        if(size != null) {// pageDTO 내의 size가 null이면 받은 size값을 넣어준다.
            pageDTO.setSize(size);
        }

        log.info("============================1");
        log.info(pageDTO);//파라미터 수집 ck


        List<BoardDTO> dtoList = BoardService.INSTANCE.getlist(pageDTO);//pageDTO던져주면 List<BoardDTO>목록받아온다.
        //page 값 1, size값 10인 pageDTO를 서비스에서 호출한 후 dtolist로 정의한다.
        //BoardDTO의 값은 1페이지에 10개씩 정의

        log.info("============================2");
        log.info(dtoList);

        request.setAttribute("dtolist", dtoList);
        //----------------------------------------------------pagemaker만들고 오기------------------------------
        PageMaker pageMaker = new PageMaker(pageDTO.getPage(),pageDTO.getSize(),123);
        //생성된 객체에 페이지 메이커의 현재 페이지, 페이지 내 출력할 갯수, 데이터의 총 갯수(123이라고 가정)를 pageMaker 인자값에서 받기

        request.setAttribute("pageMaker", pageMaker);

        request.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(request,response);//1번
    }


}
