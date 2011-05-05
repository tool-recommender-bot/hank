<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="com.rapleaf.hank.coordinator.*"%>
<%@page import="java.util.*"%>
<%@page import="java.net.*"%>

<%
Coordinator coord = (Coordinator)getServletContext().getAttribute("coordinator");

DomainConfig domainConfig = coord.getDomainConfig(URLDecoder.decode(request.getParameter("n")));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.yaml.snakeyaml.Yaml"%><html>
<head>
  <jsp:include page="_head.jsp" />
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Domain: <%= domainConfig.getName() %></title>
</head>
<body>

<jsp:include page="_top_nav.jsp"/>

<h1>Domain <%= domainConfig.getName() %></h1>

<table>
  <tr>
    <td>
      Current version number:
    </td>
    <td>
      <%= domainConfig.getVersion() %>
    </td>
  </tr>
  <tr>
    <td>
      Number of partitions:
    </td>
    <td>
      <%= domainConfig.getNumParts() %>
    </td>
  </tr>
  <tr>
    <td>
      Partitioner:
    </td>
    <td>
      <%= domainConfig.getPartitioner().getClass().getName() %>
    </td>
  </tr>
  <tr>
    <td>
      Storage engine factory:
    </td>
    <td>
      <%= domainConfig.getStorageEngineFactoryClass().getName() %>
    </td>
  </tr>
  <tr valign=top>
    <td>
      Storage engine factory options:
    </td>
    <td>
      <textarea rows="6" cols="30" disabled=true><%= domainConfig.getStorageEngineOptions() == null 
          ? ""
          : new Yaml().dump(domainConfig.getStorageEngineOptions()) 
          %>
      </textarea>
    </td>
  </tr>
</table>

</body>
</html>