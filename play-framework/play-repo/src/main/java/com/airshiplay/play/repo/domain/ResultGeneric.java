package com.airshiplay.play.repo.domain;

import com.airshiplay.play.repo.domain.Result.ResultCode;

public class ResultGeneric<T> {
	private boolean success;
	private String code;
	private String msg;
	private T content;

	public static <T> ResultGeneric<T> success(T content) {
		return new ResultGeneric<T>().code(ResultCode.success).message(
				ResultCode.success.getMessage()).setContent(content);
	}

	public static <T> ResultGeneric<T> failure() {
		return new ResultGeneric<T>().code(ResultCode.failure).message(
				ResultCode.failure.getMessage());
	}

	public static <T> ResultGeneric<T> validateError() {
		return new ResultGeneric<T>().code(ResultCode.validateError).message(
				ResultCode.validateError.getMessage());
	}

	public static <T> ResultGeneric<T> accessDenide() {
		return new ResultGeneric<T>().code(ResultCode.accessDenide).message(
				ResultCode.accessDenide.getMessage());
	}

	public static <T> ResultGeneric<T> notLogin() {
		return new ResultGeneric<T>().code(ResultCode.notLogin).message(
				ResultCode.notLogin.getMessage());
	}

	public static <T> ResultGeneric<T> exception() {
		return new ResultGeneric<T>().code(ResultCode.exception).message(
				ResultCode.exception.getMessage());
	}

	public static <T> ResultGeneric<T> locked() {
		return new ResultGeneric<T>().code(ResultCode.locked).message(
				ResultCode.locked.getMessage());
	}

	public static <T> ResultGeneric<T> unknown() {
		return new ResultGeneric<T>().code(ResultCode.unknown).message(
				ResultCode.unknown.getMessage());
	}

	public static <T> ResultGeneric<T> captchaError() {
		return new ResultGeneric<T>().code(ResultCode.captchaError).message(
				ResultCode.captchaError.getMessage());
	}

	private ResultGeneric<T> code(ResultCode code) {
		if (code == ResultCode.success) {
			setSuccess(true);
		}
		return this;
	}

	private ResultGeneric<T> message(String message) {
		this.setMsg(message);
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getContent() {
		return content;
	}

	public ResultGeneric<T> setContent(T content) {
		this.content = content;
		return this;
	}
}
