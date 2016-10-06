<%@ page import="java.sql.*" %>
<html>
<head>
<title>Basic Oracle from a jsp</title>
</head>
<body>
  <%
    out.println("Programme: instructor.jsp <br>"); 
    try {
      // Load the JDBC driver 
      Class.forName("oracle.jdbc.OracleConnection");
      out.println("Driver loaded <br>");
    } 
    catch (ClassNotFoundException e){ 
      out.println("Could not load the driver <br>"); 
    }
    String user = "n01077691";
	String passwd = "oracle";
	try {
      Connection conn = DriverManager.getConnection
         ("jdbc:oracle:thin:@munro.humber.ca:1521:msit" , user, passwd); 
      out.println("Database connected <br><br>");
      // 2. Create a statement
      String last_name  = request.getParameter("value1");
      String stringQuery =
	  ("SELECT i.last_name, i.first_name, c.course_no, c.description FROM instructor i INNER JOIN section s ON i.instructor_id = s.instructor_id INNER JOIN course c ON s.course_no = c.course_no where i.last_name = ?");
      PreparedStatement preState = conn.prepareStatement(stringQuery); 
      preState.setString(1, last_name);
      // 3. Create a result set  AND  4. Execute the query statement
	  ResultSet rset = preState.executeQuery();
	  // 5. Iterate through the result and print the instructor table
      out.println("LName &nbsp&nbsp  FName &nbsp&nbsp&nbsp&nbsp&nbsp Course_no &nbsp&nbsp&nbsp&nbsp&nbsp Description<br><br>"); 
      while(rset.next())
         out.println(rset.getString("Last_name") + "&nbsp&nbsp&nbsp&nbsp&nbsp" + rset.getString(2) + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + rset.getString(3) + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + rset.getString(4) + "<br>" );
      // 6. Close statement
      preState.close();
    }
    catch (SQLException e){ 
      out.println("Could not make Oracle connection"); 
    } 
	%>
</body>
</html>
