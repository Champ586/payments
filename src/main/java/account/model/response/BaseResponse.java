package account.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse {

    private HttpStatus status;

    private Error error;

    public BaseResponse() {
        this.status = HttpStatus.OK;
        this.error = null;
    }

    public static BaseResponse getServerErrorResponse(String message) {
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, new Error(message));
    }
}
