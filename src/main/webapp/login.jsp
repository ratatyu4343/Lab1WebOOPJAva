<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ua">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="mystyles.css" />
    <title>Вхід</title>
</head>
<body>
    <h2>Вхід у систему</h2>
    <form method="post" action="login">
        <div><input type="text" name="login" placeholder="Логін"></div><br>
        <div><input type="password" name="password" placeholder="Пароль"></div><br>
        <div>
            <fieldset>
                <legend>Виберіть роль</legend>
                <input id="r1" type="radio" name="rolecheck" value="student" checked/>
                <label for="r1">Учень</label><br>
                <input id="r2" type="radio" name="rolecheck" value="teacher"/>
                <label for="r2">Учитель</label><br>
                <input id="r3" type="radio" name="rolecheck" value="administrator"/>
                <label for="r3">Адміністратор</label>
            </fieldset>
        </div>
        <button type="submit">Увійти</button>
    </form>
</body>
</html>
