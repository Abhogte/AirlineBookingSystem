import oracle.jdbc.OracleResultSet;
import oracle.jdbc.driver.OracleDriver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QueriesServlet", urlPatterns = {"/QueriesServlet"})
public class QueriesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String qid = request.getParameter("qid");
        if(qid.equals("query1"))
            query1(request, response);
        else if(qid.equals("query2"))
            query2(request, response);
        else if (qid.equals("query3"))
            query3(request, response);
        else if(qid.equals("query4"))
            query4(request, response);
        else if(qid.equals("query5"))
            query5(request, response);
        else if(qid.equals("query6"))
            query6(request, response);
        else if (qid.equals("query7"))
            query7(request, response);
        else if (qid.equals("query8"))
            query8(request, response);
        else if (qid.equals("query9"))
            query9(request, response);
        else if (qid.equals("query10"))
            query10(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void query10(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String query = "SELECT F.FLIGHT_NO, REMAINING_SEATS, FARE, ROUND(DURATION, 2) as \"DURATION\" FROM FLIGHT F JOIN ROUTE R ON F.FLIGHT_NO = R.FLIGHT_NO WHERE SOURCE_CODE IN (SELECT LOC_CODE FROM LOCATION WHERE LOC_NAME='"+source+"') AND DESTINATION_CODE IN (SELECT LOC_CODE FROM LOCATION WHERE LOC_NAME='"+destination+"')";
        DAO connection = new DAO();
        connection.openConnection();
        ResultSet rset = connection.executeQuery(query);
        ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
        ArrayList<String> data = new ArrayList<String>();
        try{
            if(rset.next()) {

                data.add(rset.getString("FLIGHT_NO"));
                data.add(rset.getString("REMAINING_SEATS"));
                data.add(rset.getString("FARE"));
                data.add(rset.getString("DURATION"));
                flight.add(data);
                data = new ArrayList<String>();
                while (rset.next()) {
                    data.add(rset.getString("FLIGHT_NO"));
                    data.add(rset.getString("REMAINING_SEATS"));
                    data.add(rset.getString("FARE"));
                    data.add(rset.getString("DURATION"));
                    flight.add(data);
                    data = new ArrayList<String>();
                }
                request.setAttribute("listData", flight);
            }
            else {
                request.setAttribute("comment", "No Records found!!");

            }
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query10.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void query9(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = "SELECT F.flight_no, R.route_name, ROUND(DURATION, 2) as \"DURATION\" FROM FLIGHT F JOIN ROUTE R ON F.flight_no = R.flight_no ORDER BY DURATION";
        String sort = request.getParameter("sort");
        if(sort.equals("dsc"))
            query += " desc";
        DAO connection = new DAO();
        connection.openConnection();
        ResultSet rset = connection.executeQuery(query);
        ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
        ArrayList<String> data = new ArrayList<String>();
        try{
            while( rset.next() ) {
                data.add(rset.getString("FLIGHT_NO"));
                data.add(rset.getString("ROUTE_NAME"));
                data.add(rset.getString("DURATION"));
                flight.add(data);
                data = new ArrayList<String>();
            }
            request.setAttribute("listData", flight);

            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query9.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void query8(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = "SELECT AIRPORT_CODE, AIRPORT_NAME, LOC_NAME FROM airport a Join location l on a.loc_code = l.loc_code WHERE LOC_NAME IN ('";
        String[] loc = request.getParameterValues("loc");
        if(loc == null)
        {
            request.setAttribute("comment", "Please select location(s)...");
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query8.jsp");
            rd.forward(request, response);
            return;
        }
        for (int i=0; i<loc.length; i++)
        {
            query += loc[i]+"'";
            if(i != loc.length-1)
                query += ",'";
        }
        query += ")";
        DAO connection = new DAO();
        connection.openConnection();
        ResultSet rset = connection.executeQuery(query);
        ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
        ArrayList<String> data = new ArrayList<String>();
        try{
            while( rset.next() ) {
                data.add(rset.getString("AIRPORT_CODE"));
                data.add(rset.getString("AIRPORT_NAME"));
                data.add(rset.getString("LOC_NAME"));
                flight.add(data);
                data = new ArrayList<String>();
            }
            request.setAttribute("listData", flight);

            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query8.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void query7(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String airlineName = request.getParameter("airline");
        String query = "SELECT airline_name, baggage_allowance FROM AIRLINE WHERE airline_name = '"+airlineName+"'";
        DAO connection = new DAO();
        connection.openConnection();
        ResultSet rset = connection.executeQuery(query);
        ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
        ArrayList<String> data = new ArrayList<String>();
        try{
            while( rset.next() ) {
                data.add(rset.getString("airline_name"));
                data.add(rset.getString("baggage_allowance"));
                flight.add(data);
                data = new ArrayList<String>();
            }
            request.setAttribute("listData", flight);

            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query7.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void query6(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num = request.getParameter("num");
        String sort = request.getParameter("sort");
        String query = "SELECT l.loc_name AS \"Source Location\", COUNT(*) AS \"Number of Bookings\" FROM BOOKING B JOIN ROUTE R ON b.route_id = r.route_id JOIN location L ON r.source_code = l.loc_code GROUP BY l.loc_name HAVING COUNT(*) > "+num+" ORDER BY l.loc_name";
        if(sort.equals("dsc"))
            query += " desc";
        DAO connection = new DAO();
        connection.openConnection();
        ResultSet rset = connection.executeQuery(query);
        ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
        ArrayList<String> data = new ArrayList<String>();
        try {
            if(rset.next()) {
                data.add(rset.getString("Source Location"));
                data.add(rset.getString("Number of Bookings"));
                flight.add(data);
                data = new ArrayList<String>();
                while (rset.next()) {
                    data.add(rset.getString("Source Location"));
                    data.add(rset.getString("Number of Bookings"));
                    flight.add(data);
                    data = new ArrayList<String>();
                }
                request.setAttribute("listData", flight);
            }
        else {
                request.setAttribute("comment", "No Records found!!");

            }
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query6.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void query5(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = "SELECT * FROM airport WHERE loc_code IN (SELECT loc_code FROM location WHERE loc_code='TX' OR loc_code='CA')";
        DAO connection = new DAO();
        connection.openConnection();
        ResultSet rset = connection.executeQuery(query);
        ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
        ArrayList<String> data = new ArrayList<String>();
        try{
            while( rset.next() ) {
                data.add(rset.getString("AIRPORT_CODE"));
                data.add(rset.getString("AIRPORT_NAME"));
                data.add(rset.getString("LOC_CODE"));
                flight.add(data);
                data = new ArrayList<String>();
            }
            request.setAttribute("listData", flight);

            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query5.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void query4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("sort");
        String query = "SELECT F.flight_no, R.route_name, f.fare, f.remaining_seats FROM FLIGHT F JOIN ROUTE R ON F.flight_no = R.flight_no WHERE R.SOURCE_CODE = 'CA' AND f.remaining_seats > 0 ORDER BY f.fare";
        if(sort.equals("dsc"))
            query += " desc";
        DAO connection = new DAO();
        connection.openConnection();
        ResultSet rset = connection.executeQuery(query);
        ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
        ArrayList<String> data = new ArrayList<String>();
        try{
            while( rset.next() ) {
                data.add(rset.getString("FLIGHT_NO"));
                data.add(rset.getString("ROUTE_NAME"));
                data.add(rset.getString("FARE"));
                data.add(rset.getString("REMAINING_SEATS"));
                flight.add(data);
                data = new ArrayList<String>();
            }
            request.setAttribute("listData", flight);

            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query4.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    protected void query1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query= "SELECT FLIGHT_NO, REMAINING_SEATS, FARE, AIRLINE_CODE FROM FLIGHT WHERE FLIGHT_NO IN (SELECT FLIGHT_NO FROM ROUTE WHERE Route_Name='TX->NY') AND remaining_seats > 0";
        try {
            DAO connection = new DAO();
            connection.openConnection();
            ResultSet rset = connection.executeQuery(query);
            ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
            ArrayList<String> data = new ArrayList<String>();
            while( rset.next() ) {
                data.add(rset.getString("FLIGHT_NO"));
                data.add(rset.getString("REMAINING_SEATS"));
                data.add(rset.getString("FARE"));
                data.add(rset.getString("AIRLINE_CODE"));
                flight.add(data);
                data = new ArrayList<String>();
            }
            request.setAttribute("listData", flight);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query1.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void query2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            String query = "SELECT F.flight_no, R.route_name, MIN(f.fare) FROM FLIGHT F JOIN ROUTE R ON F.flight_no = R.flight_no WHERE R.route_name = 'AZ->UT' AND f.remaining_seats > 0 GROUP BY F.flight_no, r.route_name";
            DAO connection = new DAO();
            connection.openConnection();
            ResultSet rset = connection.executeQuery(query);
            ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
            ArrayList<String> data = new ArrayList<String>();
            while( rset.next() ) {
                data.add(rset.getString("FLIGHT_NO"));
                data.add(rset.getString("ROUTE_NAME"));
                data.add(rset.getString("MIN(F.FARE)"));

                flight.add(data);
                data = new ArrayList<String>();
            }

            request.setAttribute("listData", flight);

            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query2.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void query3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fare1 = request.getParameter("fare1");
        String fare2 = request.getParameter("fare2");
        String query = "SELECT FLIGHT_NO, FARE, NO_OF_SEATS, AIRLINE_CODE  FROM FLIGHT WHERE FARE BETWEEN "+fare1 +"AND "+fare2;
        DAO connection = new DAO();
        connection.openConnection();
        ResultSet rset = connection.executeQuery(query);
        ArrayList<ArrayList<String>> flight = new ArrayList<ArrayList<String>>();
        ArrayList<String> data = new ArrayList<String>();
        try{
            if(rset.next()) {
                data.add(rset.getString("FLIGHT_NO"));
                data.add(rset.getString("FARE"));
                data.add(rset.getString("NO_OF_SEATS"));
                data.add(rset.getString("AIRLINE_CODE"));
                flight.add(data);
                data = new ArrayList<String>();
                while (rset.next()) {
                    data.add(rset.getString("FLIGHT_NO"));
                    data.add(rset.getString("FARE"));
                    data.add(rset.getString("NO_OF_SEATS"));
                    data.add(rset.getString("AIRLINE_CODE"));
                    flight.add(data);
                    data = new ArrayList<String>();
                }
                request.setAttribute("listData", flight);


            }
            else {
                request.setAttribute("comment", "No Records found!!");

            }
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/query3.jsp");
            rd.forward(request, response);
            connection.closeConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
