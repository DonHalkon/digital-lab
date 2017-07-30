<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Create New Reagent</title>
 </head>
 <body>
 <center>
  <h2>Create New Reagent</h2>
  <form:form method="POST" action="save">
      <table>
       <tr>
           <td><form:label path="receiptDate">receiptDate:</form:label></td>
           <td><form:input path="receiptDate" value="${publication.receiptDate}"/></td>
       </tr>
      <tr>
        <td>
         <form:label path="compound.id">compound:</form:label>
        </td>
        <td>
         <form:select path="compound.id" cssStyle="width: 150px;">    
       <option value="-1">Select a compound</option>
       <c:forEach items="${compounds}" var="compound">
        <option value="${reagent.compound}">${compound.name}</option>
       </c:forEach>
      </form:select>
     </td>
          
       </tr>
       <tr>
          <td>
         <form:select path="location.id" cssStyle="width: 150px;">    
       <option value="-1">Select a location</option>
       <c:forEach items="${locations}" var="location">
        <option value="${reagent.location}">${location.descr}</option>
       </c:forEach>
      </form:select>
     </td>
       </tr>
       <tr>
           <td><form:label path="comments">comments:</form:label></td>
           <td><form:textarea path="comments" value="${reagent.comments}" cssStyle="width: 150px;"/></td>
       </tr>
       <tr>
         <td>&nbsp;</td>
         <td><input type="submit" value="SAVE"/></td>
        </tr>
   </table> 
  </form:form>
  <br/>
</center>
 </body>
</html>