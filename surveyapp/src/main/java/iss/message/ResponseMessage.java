package iss.message;

public class ResponseMessage {
	private String status;
	private String message;
	public ResponseMessage() {
		// TODO Auto-generated constructor stub
	}	
	public ResponseMessage(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
