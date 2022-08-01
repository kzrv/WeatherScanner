package cz.kzrv.WeatherSpringProject.util;

import java.util.Date;

public class ErrorResponse {
    private String msg;
    private Date timestamp;

    public ErrorResponse(String msg, Date timestamp) {
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
