<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/style.css' />">
    <title>New priceGroup</title>
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
            <li class="dropdown"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Tax Brackets<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/taxBrackets">Show all tax brackets</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Type of Customers<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addType">Add new type</a></li>
                    <li><a href="/typeOfCustomers">Show all types</a></li>
                </ul>
            </li>
            <li class="dropdown active"><a class="dropdown-toggle" style="color: white" data-toggle="dropdown" href="#">Price Groups<span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/add">Add new group</a></li>
                    <li><a href="/priceGroups">Show all groups</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<form:form modelAttribute="priceGroup" method="POST" cssClass="table-bordered rounded" cssStyle="margin: 100px;">

    <div class="form-row" align="center">
        <h3>Add new priceGroup</h3>

        <div class="form-group">

            <label class="col-form-label" style="width: 100px;">Name</label>
            <form:errors path="name" cssClass="alert alert-danger" cssStyle="width: 150px;" element="div"/>
            <form:input path="name"
                        type="text"
                        name='name'
                        cssStyle="width: 200px;"
                        cssClass="form-control"
            />
        </div>
        <div class="form-group">


            <label class="col-form-label" style="width: 100px;">Discount</label>
            <form:errors path="discount" cssClass="alert alert-danger" cssStyle="width: 150px;" element="div"/>
            <form:input path="discount"
                        type="number"
                        step="0.5"
                        name='discount'
                        cssStyle="width: 200px;"
                        cssClass="form-control"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form:form>


</body>
</html>
