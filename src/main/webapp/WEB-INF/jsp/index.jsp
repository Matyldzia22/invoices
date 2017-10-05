<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/js/bootstrap.min.js' /> ">
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>First JSP</title>
</head>

<body>


<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>number</th>
            <th>confirmDate</th>
            <th>sellingDate</th>
            <th>invoiceDate</th>
            <th>sum</th>
            <th>addressID</th>
            <th>customerID</th>
        </tr>
        </thead>
        <tbody>
    <c:forEach items="${invoices}" var="invoice">
        <tr>
            <td>${invoice.id}</td>
            <td>${invoice.number}</td>
            <td>${invoice.confirmDate}</td>
            <td>${invoice.sellingDate}</td>
            <td>${invoice.invoiceDate}</td>
            <td>${invoice.sum}</td>
            <td>${invoice.address.id}</td>
            <td>${invoice.customer.id}</td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
</div>
</body>
</html>