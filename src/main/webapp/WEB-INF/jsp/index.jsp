<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>First JSP</title>
</head>

<body>
<h3>Hi Pankaj</h3><br>
<strong>Current Time is</strong>

<c:forEach items="${priceGroups}" var="priceGroup">
<li>
    <article>
        <p>${priceGroup.discount}</p>
    </article>
</li>

</c:forEach>

</body>
</html>