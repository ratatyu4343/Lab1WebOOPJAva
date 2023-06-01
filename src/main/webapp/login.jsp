<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ua">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Вхід</title>
</head>
<body>
    <h2 class="ml-5">Вхід у систему</h2>
    <form method="post" action="login">
        <div class="ml-5"><input type="text" name="login" placeholder="Логін"></div><br>
        <div class="ml-5"><input type="password" name="password" placeholder="Пароль"></div><br>
        <div class="form-group ml-5">
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
        <button class="btn-success ml-5" type="submit">Увійти</button>
    </form>
</body>
</html>
