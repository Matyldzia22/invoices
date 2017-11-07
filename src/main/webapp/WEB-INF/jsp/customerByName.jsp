<?xml version="1.0" encoding="UTF-8" ?>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/style.css' />">


    <title>Customer by Name</title>
</head>

<body>
<nav class="navbar navbar-default" style="background-color: firebrick">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" style="color: white" href="/">Invoices</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="dropdown"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Customers<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addCustomer">Add new customer</a></li>
                    <li><a href="/customers">Show all customers</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Address<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/address">Show all addresses</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Products<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addProduct">Add new product</a></li>
                    <li><a href="/products">Show all products</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Tax
                Brackets<span
                        class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/taxBrackets">Show all tax brackets</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Type of
                Customers<span
                        class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addType">Add new type</a></li>
                    <li><a href="/typeOfCustomers">Show all types</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Price
                Groups<span
                        class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/add">Add new group</a></li>
                    <li><a href="/priceGroups">Show all groups</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>CUSTOMER</th>
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
        <tr modelAttribute="customer">
            <td></td>
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
        <thead>
        <tr>
            <th>ADDRESS</th>
            <th>number</th>
            <th>street</th>
            <th>postCode</th>
            <th>city</th>
        </tr>
        </thead>
        <c:forEach items="${addresses}" var="address">
            <tr>
                <td></td>
                <td>${address.number}</td>
                <td>${address.street}</td>
                <td>${address.postCode}</td>
                <td>${address.city}</td>
            </tr>
        </c:forEach>
        <thead>
        <tr>
            <th>INVOICE</th>
            <th>number</th>
            <th>confirmDate</th>
            <th>invoiceDate</th>
            <th>sellingDate</th>
            <th>sum</th>
        </tr>
        </thead>
        <c:forEach items="${invoices}" var="invoice">
            <tr>
                <td><a href="/invoice/${invoice.id}">invoice details</a></td>
                <td>${invoice.numberr}</td>
                <td>${invoice.confirmDate}</td>
                <td>${invoice.invoiceDate}</td>
                <td>${invoice.sellingDate}</td>
                <td>${invoice.sum}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="button" class="btn btn-info" value="Back" onclick="window.location.href='/customers'"/>
</div>
</body>
</html>