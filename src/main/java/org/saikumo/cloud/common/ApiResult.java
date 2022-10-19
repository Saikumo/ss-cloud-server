package org.saikumo.cloud.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResult<T> {
	private Integer statusCode;
	private String statusMessage;
	private T data;

	private static final Integer SUCCESS_STATUS_CODE = 20000;
	private static final Integer FAILURE_STATUS_CODE = 50000;

	private ApiResult(Integer statusCode) {
		this.statusCode = statusCode;
	}

	private ApiResult(Integer statusCode, String statusMessage) {
		this(statusCode);
		this.statusMessage = statusMessage;
	}

	public static <T> ApiResult<T> success() {
		return new ApiResult<>(SUCCESS_STATUS_CODE);
	}

	public static <T> ApiResult<T> success(T data) {
		ApiResult<T> apiResult = ApiResult.success();
		apiResult.setData(data);
		return apiResult;
	}

	public static <T> ApiResult<T> failure(String statusMessage) {
		return new ApiResult<>(FAILURE_STATUS_CODE, statusMessage);
	}

	public static <T> ApiResult<T> of(ApiStatus apiStatus){
		return new ApiResult<>(apiStatus.getStatusCode(),apiStatus.getStatusMessage());
	}
}



