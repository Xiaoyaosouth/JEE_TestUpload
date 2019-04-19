<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文件上传</title>
</head>
<body>
  <form method="post" action="./file/uploadMultipartFile.do" enctype="multipart/form-data">
    <input type="file" name="file" value="请选择上传的文件"/>
    <input type="submit" value="提交"/>
  </form>
</body>
</html>