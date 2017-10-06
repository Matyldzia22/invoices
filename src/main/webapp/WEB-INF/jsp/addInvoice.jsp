<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/js/bootstrap.min.js' /> ">
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

    <title>New invoice</title>
</head>

<body>

<form:form modelAttribute="invoice" method="POST" cssClass="table-bordered rounded" cssStyle="margin: 100px;">

    <div class="form-row" align="center">
        <h3>Add new invoice</h3>
        <div class="form-group">

                <%--<label  class="col-form-label">FirstName</label>--%>
            <form:input path="number" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='number'
                        placeholder="number"/>
        </div>
        <div class="form-group">

            <label class="col-form-label">confirmDate</label>
            <form:input path="confirmDate" class="uk-width-1-1 uk-form-large"
                        type="datetime-local"
                        name='confirmDate'
                        placeholder="confirmDate"/>
        </div>
        <div class="form-group">

            <label class="col-form-label">sellingDate</label>
            <form:input path="sellingDate" class="uk-width-1-1 uk-form-large"
                        type="datetime-local"
                        name='sellingDate'
                        placeholder="sellingDate"/>
        </div>
        <div class="form-group">

            <label class="col-form-label">invoiceDate</label>
            <form:input path="invoiceDate" class="uk-width-1-1 uk-form-large"
                        type="datetime-local"
                        name='invoiceDate'
                        placeholder="invoiceDate"/>
        </div>
        <div class="form-group">

            <label class="col-form-label">sum</label>
            <form:input path="sum" class="uk-width-1-1 uk-form-large"
                        type="number"
                        min="0.00"
                        step="0.01"
                        name='sum'
                        placeholder="sum"/>
        </div>

            <%--<label  class="col-form-label">price group</label> --%>
        <form:select path="address">
            <form:option value="NONE"> --SELECT--</form:option>
            <form:options items="${listOfAddresses}"></form:options>
        </form:select>
        <form:select path="customer">
            <form:option value="NONE"> --SELECT--</form:option>
            <form:options items="${listOfCustomers}"></form:options>
            <form:option value="" id="abc">New customer</form:option>
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
