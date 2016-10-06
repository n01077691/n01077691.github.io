/*
 * JSP generated by Resin-3.1.6 (built Sun, 04 May 2008 03:25:50 PDT)
 */

package _jsp._jsp;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.sql.*;

public class _instructor__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = com.caucho.jsp.QJspFactory.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/html");
    try {
      out.write(_jsp_string0, 0, _jsp_string0.length);
      
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
	
      out.write(_jsp_string1, 0, _jsp_string1.length);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      com.caucho.jsp.QJspFactory.freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.server.util.CauchoSystem.getVersionId() != -7072480922035483583L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.vfs.Dependency depend;
      depend = (com.caucho.vfs.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void init(ServletConfig config)
    throws ServletException
  {
    com.caucho.server.webapp.WebApp webApp
      = (com.caucho.server.webapp.WebApp) config.getServletContext();
    super.init(config);
    com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
    com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.PageContextImpl(webApp, this);
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("jsp/instructor.jsp"), 2234326488213342584L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  private final static char []_jsp_string1;
  private final static char []_jsp_string0;
  static {
    _jsp_string1 = "\n</body>\n</html>\n".toCharArray();
    _jsp_string0 = "\n<html>\n<head>\n<title>Basic Oracle from a jsp</title>\n</head>\n<body>\n  ".toCharArray();
  }
}
