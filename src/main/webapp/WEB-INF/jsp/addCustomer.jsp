<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

    <title>New customer</title>
</head>

<body>
<nav class="navbar navbar-default" style="background-color: firebrick">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Invoices</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/addInvoice">Add invoice</a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Invoice Items<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addInvoiceItem">Add invoice item</a></li>
                    <li><a href="/invoiceItems">Show all invoice items</a></li>
                </ul>
            </li>
            <li class="dropdown active"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Customers<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addCustomer">Add new customer</a></li>
                    <li><a href="/customers">Show all customers</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Address<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addAddress">Add new address</a></li>
                    <li><a href="/address">Show all addresses</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Products<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addProduct">Add new product</a></li>
                    <li><a href="/products">Show all products</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Tax Brackets<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addTax">Add new tax bracket</a></li>
                    <li><a href="/taxBrackets">Show all tax brackets</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Type of Customers<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/addType">Add new type</a></li>
                    <li><a href="/typeOfCustomers">Show all types</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Price Groups<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/add">Add new group</a></li>
                    <li><a href="/priceGroups">Show all groups</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<form:form modelAttribute="customer" method="POST" cssClass="table-bordered rounded" cssStyle="margin: 100px;">

    <div class="form-row" align="center">
        <h3>Add new customer</h3>
        <div class="form-group">

                <%--<label  class="col-form-label">FirstName</label>--%>
            <form:input path="firstName" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='firstName'
                        placeholder="firstName"/>
        </div>
        <div class="form-group">

                <%--<label  class="col-form-label">LastName</label>--%>
            <form:input path="lastName" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='lasttName'
                        placeholder="lastName"/>
        </div>
        <div class="form-group">

                <%--<label  class="col-form-label">Name</label>--%>
            <form:input path="name" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='name'
                        placeholder="name"/>
        </div>
        <div class="form-group">

                <%-- <label  class="col-form-label">NIP</label> --%>
            <form:input path="nip" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='nip'
                        placeholder="nip"/>
        </div>
        <div class="form-group">

                <%--<label  class="col-form-label">phone-number</label> --%>
            <form:input path="phoneNumber" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='phoneNumber'
                        placeholder="phoneNumber"/>
        </div>
        <div class="form-group">

                <%--<label  class="col-form-label">email</label> --%>
            <form:input path="email" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='email'
                        placeholder="email"/>
        </div>

            <%--<label  class="col-form-label">price group</label> --%>
        <form:select path="priceGroupId" id="listOfGroups">
            <form:option value="0"> --SELECT--</form:option>
            <form:options items="${listOfGroups}" itemValue="id" path="priceGroup"></form:options>
        </form:select>
        <form:select path="taxBracketId" id="listOfTaxBrackets">
            <form:option value="0"> --SELECT--</form:option>
            <form:options items="${listOfBrackets}" itemValue="id" path="taxBracket"></form:options>
        </form:select>
        <form:select path="typeOfCustomerId" id="listOfTypes">
            <form:option value="0"> --SELECT--</form:option>
            <form:options items="${listOfTypes}" itemValue="id" path="typeOfCustomer"></form:options>


        </form:select>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form:form>
<div class="form-group" id="demo"></div>

<script type="text/javascript">
    function myFunction() {

        document.getElementById("demo").innerHTML = '<html><body><div>Your code</div></body></html>';
    }
</script>

</body>
</html>
