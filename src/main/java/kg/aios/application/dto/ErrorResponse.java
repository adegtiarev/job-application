package kg.aios.application.dto;

public class ErrorResponse {

	public static final int WRONG_PARAMTERS = 123;
	public static final int GENERAL_ERROR = 234;
	public static final int NOT_FOUND = 404;

	private int code;
	private String errorMessage;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static ErrorResponse build(int code, String message) {
		ErrorResponse response = new ErrorResponse();

		response.setCode(code);
		response.setErrorMessage(message);

		return response;
	}

}
