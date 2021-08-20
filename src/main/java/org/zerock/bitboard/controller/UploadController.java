package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;

/** * fileSizeThreshold 서버로 파일을 저장할때 파일의 임시 저장 사이즈
 * maxFileSize 1개의 파일이 최대 사이즈
 * maxRequestSize 서버로 전송 되는 request 의 최대 사이즈 ( 파일 + formData)
 */

@Log4j2
@WebServlet(name = "upload", value = "/upload")
@MultipartConfig(fileSizeThreshold = 1024*1024)
public class UploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/upload.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uploadFolder = "C:\\upload";
        byte[] buffer =new byte[1024*8];

        Collection<Part> parts = request.getParts();

        parts.forEach(part -> {
            log.info(part);

            String type = part.getContentType();

            log.info(type);

            if(type == null){
                log.info("partName: " + part.getName());
                return;
            }

            String fileName = part.getSubmittedFileName();

            String uploadFileName = System.currentTimeMillis() + "_" + fileName;

            log.info(fileName);

            //원본파일 저장
            try (InputStream in = part.getInputStream();
                 OutputStream fos = new FileOutputStream(uploadFolder + File.separator + uploadFileName);
                 //System.currentTimeMillis() 중복된 이름이여도 시간마다 올리는 파일을 다르게 구별해줌.
            ) {
                while (true){
                    int count = in.read(buffer);
                    if(count == -1){ break; }
                    fos.write(buffer, 0, count);
                }

            } catch (IOException e) {

            }//원본파일 저장 끝

            //이미지에 대한 섬네일 생성
            if(type.startsWith("image")){
                try {
                    Thumbnails.of(new File(uploadFolder + File.separator + uploadFileName))
                            .size(100, 100)
                            .toFile(new File(uploadFolder + File.separator + "thumnail_" + uploadFileName));
                }catch (Exception e){

                }
            }

            log.info("----------------------------------------");

        });
    }
}
