<?xml version="1.0" encoding="UTF-8" ?>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/style.css' />">


    <title>Invoice By number</title>
</head>

<body>


<nav class="navbar navbar-default" style="background-color: firebrick">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Invoices</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/addInvoice">Add invoice</a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Invoice Items<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addInvoiceItem">Add invoice item</a></li>
                    <li><a href="/invoiceItems">Show all invoice items</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Customers<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addCustomer">Add new customer</a></li>
                    <li><a href="/customers">Show all customers</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Address<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addAddress">Add new address</a></li>
                    <li><a href="/address">Show all addresses</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Products<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addProduct">Add new product</a></li>
                    <li><a href="/products">Show all products</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Tax Brackets<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addTax">Add new tax bracket</a></li>
                    <li><a href="/taxBrackets">Show all tax brackets</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Type of Customers<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addType">Add new type</a></li>
                    <li><a href="/typeOfCustomers">Show all types</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Price Groups<span
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
            <th>INVOICE</th>
            <th>id</th>
            <th>number</th>
            <th>confirm date</th>
            <th>selling date</th>
            <th>invoice date</th>
            <th>sum</th>
        </tr>
        </thead>
        <tbody>

        <tr modelAttribute="invoice">
            <td> </td>
            <td>${invoice.id}</td>
            <td>${invoice.number}</td>
            <td>${invoice.confirmDate}</td>
            <td>${invoice.sellingDate}</td>
            <td>${invoice.invoiceDate}</td>
            <td>${invoice.sum}</td>
        <thead>
        <tr>
            <th>PRODUCT</th>
            <th>name</th>
            <th>number</th>
            <th>brutto price</th>
            <th>netto price</th>
            <th>vat</th>
        </tr>
        </thead>

        <c:forEach items="${listOfInvoiceItems}" var="invoiceItem">
            <td> </td>
            <td>${invoiceItem.product.name}</td>
            <td>${invoiceItem.number}</td>
            <td>${invoiceItem.product.bruttoPrice}</td>
            <td>${invoiceItem.product.nettoPrice}</td>
            <td>${invoiceItem.product.vat}%</td>
        </c:forEach>
        <thead>
        <tr>
            <th>CUSTOMER</th>
            <th>name</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>email</th>
            <th>nip</th>
            <th>phoneNumber</th>
            <th>city</th>
            <th>street</th>
            <th>number</th>
            <th>postcode</th>
            <th>price group</th>
            <th>tax bracket</th>
            <th>type of customer</th>
        </tr>
        </thead>

        <tr modelAttribute="invoice">
            <td> </td>
            <td>${invoice.customer.name}</td>
            <td>${invoice.customer.firstName}</td>
            <td>${invoice.customer.lastName}</td>
            <td>${invoice.customer.email}</td>
            <td>${invoice.customer.nip}</td>
            <td>${invoice.customer.phoneNumber}</td>
            <td>${invoice.address.city}</td>
            <td>${invoice.address.street}</td>
            <td>${invoice.address.number}</td>
            <td>${invoice.address.postCode}</td>
            <td>${invoice.customer.priceGroup.name}</td>
            <td>${invoice.customer.taxBracket.number}%</td>
            <td>${invoice.customer.typeOfCustomer.name}</td>


        </tr>


        </tbody>
    </table>
</div>
</body>
</html>