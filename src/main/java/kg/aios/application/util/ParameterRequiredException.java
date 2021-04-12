package kg.aios.application.util;

public class ParameterRequiredException extends RuntimeException {

	private static final long serialVersionUID = -26081211949063671L;

	private final String parameterName;

	public ParameterRequiredException(String message, String parameterName) {
		super(message);
		this.parameterName = parameterName;
	}

	public String getParameterName() {
		return parameterName;
	}
}
