package org.openrtk.idl.epp02.host;


/**
* org/openrtk/idl/epp/host/epp_HostCheckReq.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from epp_host.idl
* Thursday, July 26, 2001 6:26:10 PM EDT
*/


//////////////////
public class epp_HostCheckReq implements org.omg.CORBA.portable.IDLEntity
{
  public org.openrtk.idl.epp02.epp_Command m_cmd = null;
  public String m_names[] = null;

  public epp_HostCheckReq ()
  {
  } // ctor

  public epp_HostCheckReq (org.openrtk.idl.epp02.epp_Command _m_cmd, String[] _m_names)
  {
    m_cmd = _m_cmd;
    m_names = _m_names;
  } // ctor

  public void setCmd(org.openrtk.idl.epp02.epp_Command value) { m_cmd = value; }
  public org.openrtk.idl.epp02.epp_Command getCmd() { return m_cmd; }

  public void setNames(String[] value) { m_names = value; }
  public String[] getNames() { return m_names; }

  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_names ["+(m_names != null ? java.util.Arrays.asList(m_names) : null)+"] }"; }

} // class epp_HostCheckReq