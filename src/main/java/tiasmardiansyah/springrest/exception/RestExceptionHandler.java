package tiasmardiansyah.springrest.exception;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        List<FieldError> InvalidData = exception.getFieldErrors();

        String path = request.getRequestURI();

        Map<String, String> errorList = new HashMap<>();
        InvalidData.forEach(notValidField -> {
            errorList.put(notValidField.getField(), notValidField.getDefaultMessage());
        });

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(Clock.systemUTC()),
                400,
                "Bad Request",
                errorList,
                path);

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> JwtExceptionHandler(JwtException exception,
            HttpServletRequest request) {

        String path = request.getRequestURI();

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(Clock.systemUTC()),
                500,
                "JWT ERROR",
                exception.getMessage(),
                path);

        return ResponseEntity.badRequest().body(error);
    }
}