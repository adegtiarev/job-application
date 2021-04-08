package kg.aios.application.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import kg.aios.application.dto.ErrorResponse;
import kg.aios.application.util.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NoHandlerFoundException.class, ResourceNotFoundException.class })
	public ErrorResponse handleNotFound(NoHandlerFoundException ex) {
		logger.warn("Not found", ex);
		return ErrorResponse.build(ErrorResponse.NOT_FOUND, "Not found.");
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorResponse handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		logger.warn("Method not supported", ex);
		return ErrorResponse.build(ErrorResponse.GENERAL_ERROR, "Method not supported");
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleRuntimeException(Exception ex) {
		logger.warn("Unexpected error", ex);
		return ErrorResponse.build(ErrorResponse.GENERAL_ERROR, "Unexpected error.");
	}

}
