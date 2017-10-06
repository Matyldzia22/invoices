<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/js/bootstrap.min.js' /> ">
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Invoices</title>
</head>

<body>


<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>NUMBER</th>
            <th>CONFIRM DATE</th>
            <th>SELLING DATE</th>
            <th>INVOICE DATE</th>
            <th>SUM</th>
            <th>CITY</th>
            <th>NUMBER</th>
            <th>POSTCODE</th>
            <th>STREET</th>
            <th>CUSTOMER NAME</th>
            <th>CUSTOMER NIP</th>
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
            <td>${invoice.address.city}</td>
            <td>${invoice.address.number}</td>
            <td>${invoice.address.postCode}</td>
            <td>${invoice.address.street}</td>
            <td>${invoice.customer.name}</td>
            <td>${invoice.customer.nip}</td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
</div>
</body>
</html>