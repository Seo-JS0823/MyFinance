package model.exception;

public class SigninException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message = "올바른 정보를 입력하세요.";
	
	public SigninException() {}
	
	public SigninException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return this.message;
	}
}
