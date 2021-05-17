<%--
  Created by IntelliJ IDEA.
  User: ajaykumarv
  Date: 12/4/19
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Queries</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
    <a class="navbar-brand" href="index.jsp">Airline</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="query1.jsp">Query1 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="query2.jsp">Query2 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="query3.jsp">Query3 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="query4.jsp">Query4 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="query5.jsp">Query5 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="query6.jsp">Query6 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="query7.jsp">Query7 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="query8.jsp">Query8 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="query9.jsp">Query9 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="query10.jsp">Query10 <span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>
    </div>
</nav>

<div class="container">
    <h4>Query 1</h4>
    <p class="lead">English query: Which flights are available from Texas to New York? </p>
    <p class="lead"><b>SQL: SELECT FLIGHT_NO, REMAINING_SEATS, FARE
        FROM FLIGHT
        WHERE FLIGHT_NO IN (SELECT FLIGHT_NO
        FROM ROUTE
        WHERE Route_Name='TX->NY') AND remaining_seats > 0;</b></p>
    <form action="QueriesServlet" method="post">
        <input type="hidden" name="qid" value="query1">
        <input type="submit" value="query" class="btn btn-dark">
    </form>

    <%
        ArrayList<ArrayList<String>> flight = (ArrayList<ArrayList<String>>)  request.getAttribute("listData");
        if( flight != null && flight.size() > 0 ) {

    %>
    <table class="table table-hover table-dark">
        <thead>
        <tr>
            <th scope="col">FLIGHT_NO</th>
            <th scope="col">FARE</th>
            <th scope="col">NO_OF_SEATS</th>
            <th scope="col">AIRLINE_CODE</th>
        </tr>
        </thead>
        <tbody>
        <% for (ArrayList<String> list : flight) { %>
        <tr>
            <% for (String temp : list) {%>
            <td><%=temp%></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>

    <% }%>





</div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>

