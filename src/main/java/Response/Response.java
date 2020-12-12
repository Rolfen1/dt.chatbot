package Response;

public class Response {
    private Integer StatusCode;
    private String Status;
    private String Message;

    public Response() {
        StatusCode = 200;
        Status = "NOK";
        Message = "NOK";
    }

    public Response(Integer statusCode, String status, String message) {
        StatusCode = statusCode;
        Status = status;
        Message = message;
    }

    public Integer getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(Integer statusCode) {
        StatusCode = statusCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "StatusCode:" + StatusCode +
                ", Status:'" + Status + '\'' +
                ", Message:'" + Message + '\'' +
                '}';
    }

    public static Response Ok() {
        return new Response(200, "OK", "OK");
    }
}
