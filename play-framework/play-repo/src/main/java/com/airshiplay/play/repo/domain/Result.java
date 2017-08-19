package com.airshiplay.play.repo.domain;

import java.util.HashMap;
import java.util.Map;

public class Result {

	public static Result success() {
		return new Result().code(ResultCode.success).message(
				ResultCode.success.getMessage());
	}

	public static Result failure() {
		return new Result().code(ResultCode.failure).message(
				ResultCode.failure.getMessage());
	}

	public static Result error() {
		return new Result().code(ResultCode.error).message(
				ResultCode.error.getMessage());
	}
	
	public static Result validateError() {
		return new Result().code(ResultCode.validateError).message(
				ResultCode.validateError.getMessage());
	}

	public static Result accessDenide() {
		return new Result().code(ResultCode.accessDenide).message(
				ResultCode.accessDenide.getMessage());
	}

	public static Result notLogin() {
		return new Result().code(ResultCode.notLogin).message(
				ResultCode.notLogin.getMessage());
	}

	public static Result exception() {
		return new Result().code(ResultCode.exception).message(
				ResultCode.exception.getMessage());
	}
	public static Result locked() {
		return new Result().code(ResultCode.locked).message(
				ResultCode.locked.getMessage());
	}
	public static Result unknown() {
		return new Result().code(ResultCode.unknown).message(
				ResultCode.unknown.getMessage());
	}

	public static Result captchaError() {
		return new Result().code(ResultCode.captchaError).message(
				ResultCode.captchaError.getMessage());
	}

	private ResultCode code;
	private String message;

	private Map<String, Object> extraProperties = new HashMap<>();

	public ResultCode getCode() {
		return code;
	}

	public Result code(ResultCode code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result message(String message) {
		this.message = message;
		return this;
	}

	public Map<String, Object> getExtraProperties() {
		return extraProperties;
	}

	public Result addContent(Object value) {
		this.extraProperties.put("content", value);
		return this;
	}

	public Result addProperties(String key, Object value) {
		this.extraProperties.put(key, value);
		return this;
	}

	public Result extraProperties(Map<String, Object> extraProperties) {
		this.extraProperties = extraProperties;
		return this;
	}

	public enum ResultCode {
		success("操作成功"), failure("操作失败"), validateError("验证错误"), accessDenide(
				"无权限访问"), notLogin("未登录"), captchaError("验证码错误"), exception(
				"系统异常"), locked("数据锁定"),error("错误"), unknown("未知情况");

		private final String message;

		ResultCode(final String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}
}
