<%--
  Created by IntelliJ IDEA.
  User: stell
  Date: 2021-08-20
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data"> <!--파일 enctype 인코딩 어떻게 하시겠습니까 -->
    <input type="text" name="title" value="test...">
    <input type="file" name="uploadFiles" multiple> <!-- multiple 입력하는 창은 하나지만 한번에 여러개 올리도록 해줌 -->
    <button type="submit">Submit</button>
</form>
</body>
</html>
