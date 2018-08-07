

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reading Is Fundamental</title>


</head>
<body>

<div class="container">
    <form name="login" action="/ReadingIsFundamentalWeb/acao?parametro=login" method="post" class="form-signin">

        <h2 class="form-signin-heading">Autenticação</h2>

        <label>Login</label><br>
        <input type="email" name="email" placeholder="Endereço de e-mail" class="form-control" id="inputEmail" required autofocus value="">
        <br>

        <label>Senha</label><br>
        <input type="password" name="senha" placeholder="senha" class="form-control" id="inputPassword" required>
        <br>

        <input type="submit" name="enviar" value="Entrar">
    </form>

</div>
</body>
</html>