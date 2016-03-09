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
<h1>Portlet C</h1>
<form action="<%=urlPortlet%>" method="post">
	
Nombre:	<input type="text" name="nombre" value="<%=nombre%>"/><br />
Dirección: <input type="text" name="direccion" value="<%=direccion%>"/><br />
Teléfono: <input type="text" name="telefono" value="<%=telefono %>"/><br />

</form>