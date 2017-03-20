package jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Cipher")
public class CipherJaxb 
{
	
	private String m_cipher,s_cipher1,s_cipher2;

	public CipherJaxb(String m_cipher, String s_cipher1, String s_cipher2) {
		super();
		this.m_cipher = m_cipher;
		this.s_cipher1 = s_cipher1;
		this.s_cipher2 = s_cipher2;
	}
	
	public CipherJaxb() {
	super();
	}
	
	
	@XmlElement
	public String getM_cipher() {
		return m_cipher;
	}

	public void setM_cipher(String m_cipher) {
		this.m_cipher = m_cipher;
	}
	@XmlElement
	public String getS_cipher1() {
		return s_cipher1;
	}

	public void setS_cipher1(String s_cipher1) {
		this.s_cipher1 = s_cipher1;
	}
	@XmlElement
	public String getS_cipher2() {
		return s_cipher2;
	}

	public void setS_cipher2(String s_cipher2) {
		this.s_cipher2 = s_cipher2;
	}


}
