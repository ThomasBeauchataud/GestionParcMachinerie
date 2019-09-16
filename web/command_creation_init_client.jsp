<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/command/creation/1">
        <div>
            <label>
                Command type
                <select name="command_type">
                    <option value="rent">Rent</option>
                    <option value="sale">Sale</option>
                </select>
            </label>
        </div>
        <div id="sale" style="display:none">
            <label>Sale date<input type="date" name="date"/></label>
        </div>
        <div id="rent">
            <label>Rent start date<input type="date" name="start_date"/></label>
            <label>Rent end date<input type="date" name="end_date"/></label>
        </div>
        <input type="submit" value="Continue"/>
    </form>
</body>
</html>
