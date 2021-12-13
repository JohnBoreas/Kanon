package com.kanon.charlotte.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Result {

    private static final String FAILED = "failed";
    private static final String SUCCESS = "success";

    /**
     * 300-无结果
     * 400-(参数)验证不通过
     * 500-内部错误
     */
    public static final int STATUS_EMPTY_RESULT = 300;
    public static final int STATUS_VALID_FAILED = 400;
    public static final int STATUS_SERVER_ERROR = 500;

    private String info = SUCCESS;

    private Integer status = 1;

    private String version = "1.0.0";

    @SerializedName("trace_id")
    private String traceId;

    private Object data;

    private Result(Object data) {
        this.data = data;
        this.traceId = "";
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result validFailed(Object data) {
        return failed(data, STATUS_VALID_FAILED);
    }

    public static Result failed(Object data, Integer status) {
        Result result = new Result(data);
        result.setInfo(FAILED);
        result.setStatus(status);
        return result;
    }
}
