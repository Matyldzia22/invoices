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


<div class="container" style="margin-bottom: 50px" style="align:center">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>FIRST NAME</th>
            <th>LAST NAME</th>
            <th>NIP</th>
            <th>PHONE NUMBER</th>
            <th>EMAIL</th>
            <th>PRICE GROUP</th>
            <th>TAX BRACKET(%)</th>
            <th>TYPE OF CUSTOMER</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.nip}</td>
                <td>${customer.phoneNumber}</td>
                <td>${customer.email}</td>
                <td>${customer.priceGroup.name}</td>
                <td>${customer.taxBracket.number}</td>
                <td>${customer.typeOfCustomer.name}</td>



            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>