package tiasmardiansyah.springrest.exception;

import java.time.LocalDateTime;

public record ErrorResponse (
    LocalDateTime timestamp,
    int status,
    String error,
    Object message,
    String path
) {
    
}
