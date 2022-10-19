package org.saikumo.cloud.common;


public enum ApiStatus {
	FILE_EXIST_AND_NOT_OVERWRITE(20001,"文件已存在且不覆盖"),

	DELETE_FILE_FAILURE(50001,"删除文件失败");



	private final Integer statusCode;
	private final String statusMessage;


	ApiStatus(Integer statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	@Override
	public String toString() {
		return this.statusMessage;
	}


}
