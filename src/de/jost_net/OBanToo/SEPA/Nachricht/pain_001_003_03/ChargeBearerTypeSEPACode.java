//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.08 at 08:16:28 PM CET 
//


package de.jost_net.OBanToo.SEPA.Nachricht.pain_001_003_03;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeBearerTypeSEPACode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChargeBearerTypeSEPACode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SLEV"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChargeBearerTypeSEPACode")
@XmlEnum
public enum ChargeBearerTypeSEPACode {

    SLEV;

    public String value() {
        return name();
    }

    public static ChargeBearerTypeSEPACode fromValue(String v) {
        return valueOf(v);
    }

}
