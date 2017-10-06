<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/js/bootstrap.min.js' /> ">
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>New product</title>
</head>

<body>

<form:form modelAttribute="product" method="POST" cssClass="table-bordered rounded" cssStyle="margin: 100px;">

    <div class="form-row" align="center">
        <h3>Add new product</h3>
        <div class="form-group">
            <form:input path="name" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='name'
                        placeholder="name"/>
        </div>
        <div class="form-group">
            <label  class="col-form-label">brutto price</label>
            <form:input path="bruttoPrice" class="uk-width-1-1 uk-form-large"
                        type="number"
                        min="0.00"
                        step="0.01"
                        name='bruttoPrice'
                        placeholder="bruttoPrice"/>
        </div>
        <div class="form-group">
            <label  class="col-form-label">netto price</label>
            <form:input path="nettoPrice" class="uk-width-1-1 uk-form-large"
                        type="number"
                        min="0.00"
                        step="0.01"
                        name='bruttoPrice'
                        placeholder="bruttoPrice"/>
        </div>
        <div class="form-group">
            <label  class="col-form-label">vat</label>
            <form:input path="vat" class="uk-width-1-1 uk-form-large"
                        type="numeric"
                        name='vat'
                        placeholder="vat"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form:form>



</body>
</html>
