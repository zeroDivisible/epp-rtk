package org.openrtk.idl.epp02;


/**
* org/openrtk/idl/epp/epp_PollResData.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from epp.idl
* Thursday, July 26, 2001 6:26:04 PM EDT
*/

public class epp_PollResData implements org.omg.CORBA.portable.IDLEntity
{
  public org.openrtk.idl.epp02.epp_PollResDataType m_type = null;
  public org.openrtk.idl.epp02.epp_PollResDataUnion m_data = null;

  public epp_PollResData ()
  {
  } // ctor

  public epp_PollResData (org.openrtk.idl.epp02.epp_PollResDataType _m_type, org.openrtk.idl.epp02.epp_PollResDataUnion _m_data)
  {
    m_type = _m_type;
    m_data = _m_data;
  } // ctor

  public void setType(org.openrtk.idl.epp02.epp_PollResDataType value) { m_type = value; }
  public org.openrtk.idl.epp02.epp_PollResDataType getType() { return m_type; }

  public void setData(org.openrtk.idl.epp02.epp_PollResDataUnion value) { m_data = value; }
  public org.openrtk.idl.epp02.epp_PollResDataUnion getData() { return m_data; }

  public String toString() { return this.getClass().getName() + ": { m_type ["+m_type+"] m_data ["+m_data+"] }"; }

} // class epp_PollResData