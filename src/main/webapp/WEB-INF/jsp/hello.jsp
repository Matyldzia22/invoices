<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Addresses</title>
</head>

<body>


<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>CITY</th>
            <th>NUMBER</th>
            <th>POSTCODE</th>
            <th>STREET</th>
            <th>CUSTOMER NAME</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${addresses}" var="address">
            <tr>
                <td>${address.id}</td>
                <td>${address.city}</td>
                <td>${address.number}</td>
                <td>${address.postCode}</td>
                <td>${address.street}</td>
                <td>${address.customer.name}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>