<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/bootstrap/js/bootstrap.min.js' /> ">
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>New typeOfCustomer</title>
</head>

<body>

<form:form modelAttribute="typeOfCustomer" method="POST" cssClass="table-bordered rounded" cssStyle="margin: 100px;">

    <div class="form-row" align="center">
        <h3>Add new type of customer</h3>
        <div class="form-group">

            <label  class="col-form-label">Name</label>
            <form:input path="name" class="uk-width-1-1 uk-form-large"
                        type="text"
                        name='name'
                        placeholder="name"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form:form>



</body>
</html>
