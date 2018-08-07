
<%@page import="entidades.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ENTREI CARAHO</title>


</head>
<body>

<%
    HttpSession sessao = request.getSession();
    Funcionario user2 = (Funcionario) sessao.getAttribute("usuarioLogado");

    if (user2 == null) {
        response.sendRedirect("login.jsp");
%>

<%
    }
%>

<%= user2.getNome()%>





</body>
</html>
