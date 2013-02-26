package org.openrtk.idl.epp02;


/**
* org/openrtk/idl/epp/epp_Credentials.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from epp.idl
* Thursday, July 26, 2001 6:26:04 PM EDT
*/

public class epp_Credentials implements org.omg.CORBA.portable.IDLEntity
{
  public String m_client_id = null;
  public String m_password = null;
  public String m_new_password = null;
  public org.openrtk.idl.epp02.epp_Options m_options = null;

  public epp_Credentials ()
  {
  } // ctor

  public epp_Credentials (String _m_client_id, String _m_password, String _m_new_password, org.openrtk.idl.epp02.epp_Options _m_options)
  {
    m_client_id = _m_client_id;
    m_password = _m_password;
    m_new_password = _m_new_password;
    m_options = _m_options;
  } // ctor

  public void setClientId(String value) { m_client_id = value; }
  public String getClientId() { return m_client_id; }

  public void setPassword(String value) { m_password = value; }
  public String getPassword() { return m_password; }

  public void setNewPassword(String value) { m_new_password = value; }
  public String getNewPassword() { return m_new_password; }

  public void setOptions(org.openrtk.idl.epp02.epp_Options value) { m_options = value; }
  public org.openrtk.idl.epp02.epp_Options getOptions() { return m_options; }

  public String toString() { return this.getClass().getName() + ": { m_client_id ["+m_client_id+"] m_password ["+m_password+"] m_new_password ["+m_new_password+"] m_options ["+m_options+"] }"; }

} // class epp_Credentials