<?xml version="1.0" encoding="UTF-8" ?>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/style.css' />">


    <title>Invoices</title>
</head>

<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" style="color: white" href="/">Invoices</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="dropdown"><a  class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Customers<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addCustomer">Add new customer</a></li>
                    <li><a href="/customers">Show all customers</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Address<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/address">Show all addresses</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Products<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addProduct">Add new product</a></li>
                    <li><a href="/products">Show all products</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Tax Brackets<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/taxBrackets">Show all tax brackets</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Type of Customers<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addType">Add new type</a></li>
                    <li><a href="/typeOfCustomers">Show all types</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Price Groups<span
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
            <th></th>
            <th>ID</th>
            <th>NUMBER</th>
            <th>CONFIRM DATE</th>
            <th>SELLING DATE</th>
            <th>INVOICE DATE</th>
            <th>SUM</th>
            <th>CUSTOMER NAME</th>
            <th>CUSTOMER NIP</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${invoices}" var="invoice">
            <tr>
                <td><div class="dropdown">
                    <button class="btn btn-primary btn-sm dropdown-toggle" type="button" data-toggle="dropdown">OPTIONS
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="/invoice/${invoice.id}">Invoice Details</a></li>
                        <li><a href="/${invoice.numberr}/invoiceItems/add">Add items</a></li>
                        <li><a href="/delete/invoice/${invoice.id}">Delete invoice</a></li>
                    </ul>
                </div></td>
                <td>${invoice.id}</td>
                <td>${invoice.numberr}</td>
                <td>${invoice.confirmDate}</td>
                <td>${invoice.sellingDate}</td>
                <td>${invoice.invoiceDate}</td>
                <td>${invoice.sum}</td>
                <td>${invoice.customer.name}</td>
                <td>${invoice.customer.nip}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>