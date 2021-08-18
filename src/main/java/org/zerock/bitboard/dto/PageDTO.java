package org.zerock.bitboard.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

    @Builder.Default // 기본으로 1페이지에 10개로 build해줌
    private int page = 1;
    @Builder.Default
    private int size = 10;

    public int getSkip(){
        //1페이지 - 0   1(페이지) - 1 * 10(가져올 갯수번호까지)
        //2페이지 - 10
        //3페이지 - 20
        return (this.page -1) * size;
    }
}
