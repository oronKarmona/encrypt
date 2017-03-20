package jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class StatusJaxb {
	
	private String exceptionName,exceptionMsg,exceptionStackTrace;
	
	private String status,path,time;
	
	private String opertaion;
	
	
	public StatusJaxb(String exceptionName, String exceptionMsg,
			String exceptionStackTrace, String status, String path,
			String opertaion) {
		super();
		this.exceptionName = exceptionName;
		this.exceptionMsg = exceptionMsg;
		this.exceptionStackTrace = exceptionStackTrace;
		this.status = status;
		this.path = path;
		this.opertaion = opertaion;
	}
	
	
	public StatusJaxb() {
		super();
	}


	public StatusJaxb(String status, String path, String time, String opertaion) {
		super();
		this.status = status;
		this.path = path;
		this.time = time;
		this.opertaion = opertaion;
	}

	
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	@XmlElement
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@XmlElement
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@XmlElement
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@XmlElement
	public String getOpertaion() {
		return opertaion;
	}
	public void setOpertaion(String opertaion) {
		this.opertaion = opertaion;
	}
	@XmlElement
	public String getExceptionStackTrace() {
		return exceptionStackTrace;
	}
	public void setExceptionStackTrace(String exceptionStackTrace) {
		this.exceptionStackTrace = exceptionStackTrace;
	}
	@XmlElement
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

}
