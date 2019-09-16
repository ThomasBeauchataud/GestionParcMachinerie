<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>Please login</h1>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <label>Login : <input type="text" name="username"/></label>
            <label>Password : <input type="password" name="password"/></label>
            <input type="submit" value="Connexion"/>
        </form>
    </body>
</html>
