package com.examen;

import java.io.IOException;
import java.io.Serializable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import com.examen.entidad.Persona;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Portlet implementation class PortletA
 */
public class PortletA extends GenericPortlet {

    public void init() {
        viewTemplate = getInitParameter("view-template");
    }

    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        include(viewTemplate, renderRequest, renderResponse);
    }

    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
    @Override
    public void processAction(ActionRequest arg0, ActionResponse arg1) throws PortletException, IOException {
   
    	String accionB = arg0.getParameter("portletB");
    	//String accionC = arg0.getParameter("portletC");
    	
    	String nombre=arg0.getParameter("nombre");
    	String direccion=arg0.getParameter("direccion");
    	String telefono =arg0.getParameter("telefono");
    	
    	Persona persona = new Persona(nombre,direccion,telefono);
    	
    	if(accionB!=null){
    		
    		QName qname = new QName ("http://examen.com","mensajeB", "x") ;
        	arg1.setEvent(qname, persona);
    	
    	}else{
    		QName qname = new QName ("http://examen.com","mensajeC", "x") ;
        	arg1.setEvent(qname, persona);
    	}
    }
    @ProcessEvent(qname="{http://examen.com}mensajeB")
    public void procesarEventoB(EventRequest arg0, EventResponse arg1) throws PortletException, IOException {
    	Event evento = arg0.getEvent();
    	
    	Serializable persona = evento.getValue();
    	//Esto es asi, porque aunque el valor del Evento, en este caso es un String
    	//de forma genérica es un Serializable, y la unica forma de pasar un serializable
    	//es con atributos
    	
    	arg0.setAttribute("persona", persona);
    }
    @ProcessEvent(qname="{http://examen.com}mensajeC")
    public void procesarEventoC(EventRequest arg0, EventResponse arg1) throws PortletException, IOException {
    	Event evento = arg0.getEvent();
    	
    	Serializable persona = evento.getValue();
    	//Esto es asi, porque aunque el valor del Evento, en este caso es un String
    	//de forma genérica es un Serializable, y la unica forma de pasar un serializable
    	//es con atributos
    	
    	arg0.setAttribute("persona", persona);
    }
    protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(PortletA.class);

}
