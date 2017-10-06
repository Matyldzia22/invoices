<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/js/bootstrap.min.js' /> ">
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>PriceGroups</title>
</head>

<body>


<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>DISCOUNT</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${priceGroups}" var="priceGroup">
            <tr>
                <td>${priceGroup.id}</td>
                <td>${priceGroup.name}</td>
                <td>${priceGroup.discount}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>