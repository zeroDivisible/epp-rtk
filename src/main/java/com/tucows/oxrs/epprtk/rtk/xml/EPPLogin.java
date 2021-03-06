/*
**
** EPP RTK Java
** Copyright (C) 2001-2002, Tucows, Inc.
** Copyright (C) 2003, Liberty RMS
**
**
** This library is free software; you can redistribute it and/or
** modify it under the terms of the GNU Lesser General Public
** License as published by the Free Software Foundation; either
** version 2.1 of the License, or (at your option) any later version.
** 
** This library is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
** Lesser General Public License for more details.
** 
** You should have received a copy of the GNU Lesser General Public
** License along with this library; if not, write to the Free Software
** Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
** 
*/

/*
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epprtk/rtk/xml/EPPLogin.java,v 1.1 2004/12/07 15:53:27 ewang2004 Exp $
 * $Revision: 1.1 $
 * $Date: 2004/12/07 15:53:27 $
 */

package com.tucows.oxrs.epprtk.rtk.xml;

import java.io.*;
import java.util.*;

import com.tucows.oxrs.epprtk.rtk.*;
import org.openrtk.idl.epprtk.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class to represent the EPP login to the server.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2004/12/07 15:53:27 $
 * @see com.tucows.oxrs.epprtk.rtk.EPPClient
**/
public class EPPLogin extends EPPXMLBase implements epp_Login
{

    private epp_LoginReq action_request_;
    private epp_LoginRsp action_response_;

    /**
     * Default constructor.
     */
    public EPPLogin () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Login response XML String
     * @throws org.openrtk.idl.epprtk.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epprtk.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPLogin (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPLogin(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }
    
    /**
     * Accessor method for the login request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epprtk.epp_LoginReq
     */
    public void setRequestData(epp_LoginReq value) { action_request_ = value; }
    /**
     * Accessor method for the login response data.
     * @return value org.openrtk.idl.epprtk.epp_LoginRsp
     */
    public epp_LoginRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epprtk.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_LoginReq)
     * @see org.openrtk.idl.epprtk.epp_Action
     */
    public String toXML() throws epp_Exception, epp_XMLException
    {
        String method_name = "toXML(EPPLoginReq)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null ||
             action_request_.m_cmd == null )
        {
            throw new epp_XMLException("missing login request or creds data");
        }

        if ( action_request_.m_services == null )
        {
            throw new epp_XMLException("missing services in login");
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);
        
        Element command = doc.createElement("command");
        Element login = doc.createElement("login");

        epp_Command command_data = action_request_.m_cmd;

        addXMLElement(doc, login, "clID", action_request_.m_client_id);
        addXMLElement(doc, login, "pw",   action_request_.m_password);
        if ( action_request_.m_new_password != null )
        {
            addXMLElement(doc, login, "newPW", action_request_.m_new_password);
        }

        if ( action_request_.m_options != null )
        {
            Element options_element = doc.createElement("options");
            if ( action_request_.m_options.m_version != null )
            {
                addXMLElement(doc, options_element, "version", action_request_.m_options.m_version);
            }
            if ( action_request_.m_options.m_lang != null )
            {
                addXMLElement(doc, options_element, "lang", action_request_.m_options.m_lang);
            }
            login.appendChild( options_element );
        }

        // Now for services
	Element services_element = doc.createElement("svcs");

	for (int count=0; count<action_request_.m_services.length; count++)
	{
	    String service = action_request_.m_services[count];
	    addXMLElement(doc, services_element, "objURI", service);
	}

        if (action_request_.m_extensions != null)
	{
            Element extensions_element = doc.createElement("svcExtension");

            for (int count=0; count<action_request_.m_extensions.length; count++)
            {
		String extension = action_request_.m_extensions[count];
                addXMLElement(doc, extensions_element, "extURI", extension);
            }
	    
	    services_element.appendChild(extensions_element);
	}

        login.appendChild( services_element );
        
        command.appendChild( login );

        prepareExtensionElement( doc, command, command_data.m_extensions );

        if ( command_data.m_client_trid != null )
        {
            addXMLElement(doc, command, "clTRID", command_data.m_client_trid);
        }

        root.appendChild( command );
        doc.appendChild( root );
        
        String request_xml;
        
            try
        {
            request_xml = createXMLFromDoc(doc);
        }
        catch (IOException xcp)
        {
            throw new epp_XMLException("IOException in building XML ["+xcp.getMessage()+"]");
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return request_xml;
    }

    /**
     * Parses a new XML String and populates the response data member.
     * Implemented method from org.openrtk.idl.epprtk.epp_Action interface.
     * @param A new XML String to parse
     * @throws epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epprtk.epp_Exception if the server has responded with an error code 
     * @see org.openrtk.idl.epprtk.epp_Action
     */
    public void fromXML(String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "fromXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        xml_ = xml;

        try
        {
            Element epp_node = getDocumentElement();
            Node response_node = epp_node.getElementsByTagName("response").item(0);

            if ( response_node == null )
            {
                throw new epp_XMLException("unparsable or missing response");
            }

            action_response_ = new epp_LoginRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

        }
        catch (epp_Exception xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            throw xcp;
        }
        catch (Exception xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            throw new epp_XMLException("unable to parse xml ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
        }

    }
    
}
