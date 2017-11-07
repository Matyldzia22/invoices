<?xml version="1.0" encoding="UTF-8" ?>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/style.css' />">
    <title>Customers</title>
</head>

<body>
<nav class="navbar navbar-default" style="background-color: firebrick">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" style="color: white" href="/">Invoices</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="dropdown active"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Customers<span
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
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Products<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addProduct">Add new product</a></li>
                    <li><a href="/products">Show all products</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Tax
                Brackets<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/taxBrackets">Show all tax brackets</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Type of
                Customers<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addType">Add new type</a></li>
                    <li><a href="/typeOfCustomers">Show all types</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" style="color: white" href="#">Price
                Groups<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/add">Add new group</a></li>
                    <li><a href="/priceGroups">Show all groups</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>

<div class="container" style="margin-bottom: 50px" style="align:center">
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th>ID</th>
            <th>NAME</th>
            <th>FIRST NAME</th>
            <th>LAST NAME</th>
            <th>NIP</th>
            <th>PHONE NUMBER</th>
            <th>EMAIL</th>
            <th>PRICE GROUP</th>
            <th>TAX BRACKET</th>
            <th>TYPE OF CUSTOMER</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>
                    <div class="dropdown">
                        <button class="btn btn-primary btn-sm dropdown-toggle" type="button" data-toggle="dropdown">
                            OPTIONS
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="/customer/name/${customer.name}">Customer Details</a></li>
                            <li><a href="/${customer.name}/address/add">Add Address</a></li>
                            <c:if test="${customer.addresses.size() != 0}">
                                <li><a href="/${customer.name}/invoice/add">Add New Invoice</a></li>
                            </c:if>
                        </ul>
                    </div>
                </td>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.nip}</td>
                <td>${customer.phoneNumber}</td>
                <td>${customer.email}</td>
                <td>${customer.priceGroup.name}</td>
                <td>${customer.taxBracket.number}%</td>
                <td>${customer.typeOfCustomer.name}</td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>