<%@page import="com.examen.entidad.Persona"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<%
String nombre="";
String direccion="";
String telefono="";

Persona persona = (Persona)renderRequest.getAttribute("persona");

if(persona!=null){
	nombre= persona.getNombre();
	direccion=persona.getDireccion();
	telefono=persona.getTelefono();
}

%>

<portlet:actionURL var="urlPortlet" />
<h1>Portlet A</h1>
<form action="<%=urlPortlet%>" method="post">
	<input type="submit"  value="Portlet B" name="portletB"/>
	<input type="submit" name="portletC" value="Portlet C" /><br />
	
Nombre:	<input type="text" name="nombre" value="<%=nombre%>"/><br />
Direcci�n: <input type="text" name="direccion" value="<%=direccion%>"/><br />
Tel�fono: <input type="text" name="telefono" value="<%=telefono %>"/><br />

</form>