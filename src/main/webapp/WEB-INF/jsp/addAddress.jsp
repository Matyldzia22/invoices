<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/js/bootstrap.min.js' /> ">
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>New Address</title>
</head>

<body>

<form:form modelAttribute="address" method="POST" cssClass="table-bordered rounded" cssStyle="margin: 100px;">

    <div class="form-row" align="center">
        <h3>Add new address</h3>

        <div class="form-group">


            <form:input path="street" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='street'
                        placeholder="street"/>
        </div>
        <div class="form-group">


            <form:input path="number" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='number'
                        placeholder="number"/>
        </div>
        <div class="form-group">


            <form:input path="city" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='city'
                        placeholder="city"/>
        </div>
        <div class="form-group">


            <form:input path="postCode" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='postCode'
                        placeholder="postCode"/>
        </div>
        <form:select path="customer">
            <form:option value="NONE"> --SELECT--</form:option>
            <form:options items="${listOfCustomers}"></form:options>
        </form:select>


        <div class="form-group">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form:form>


</body>
</html>
