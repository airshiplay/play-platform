package com.airlenet.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestResult {
	private static RestResult init() {
		return new RestResult();
	}

	public static RestResult success() {
		return init().code(ResultCode.success).message("");
	}

	public static RestResult failure() {
		return init().code(ResultCode.failure).message("");
	}

	public static RestResult validateError() {
		return init().code(ResultCode.validateError).message("");
	}

	public static RestResult accessDenide() {
		return init().code(ResultCode.accessDenide).message("");
	}

	public static RestResult notLogin() {
		return init().code(ResultCode.notLogin).message("");
	}

	public static RestResult exception() {
		return init().code(ResultCode.exception).message("");
	}

	public static RestResult notFound() {
		return init().code(ResultCode.notFound).message("");
	}

	public static RestResult unknown() {
		return init().code(ResultCode.unknown).message("");
	}

	public static RestResult captchaError() {
		return init().code(ResultCode.captchaError).message("");
	}
	private String code;
	private String message;

	@JsonIgnore
	private Map<String, Object> extraProperties = new HashMap<>();
	private List<ObjectError> errors = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public RestResult code(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public RestResult message(String message) {
		this.message = message;
		return this;
	}

	public Map<String, Object> getExtraProperties() {
		return extraProperties;
	}

	public List<ObjectError> getErrors() {
		return errors;
	}

	public RestResult addProperties(String key, Object value) {
		this.extraProperties.put(key, value);
		return this;
	}

	public RestResult setContent(Object value){
        this.extraProperties.put("content",value);
		return this;
	}
	public RestResult extraProperties(Map<String, Object> extraProperties) {
		this.extraProperties = extraProperties;
		return this;
	}

	public RestResult error(List<ObjectError> errors) {
		this.errors = errors;
		return this;
	}

	public RestResult error(String objectName, String defaultMessage) {
		this.errors.add(new ObjectError(objectName, defaultMessage));
		return this;
	}

	public RestResult error(String objectName, String field, String defaultMessage) {
		this.errors.add(new FieldError(objectName, field, defaultMessage));
		return this;
	}

	public static class ResultCode {
		public static String success="success";
		public static String failure="failure";
		public static String validateError="validateError";
		public static String accessDenide="accessDenide";
		public static String notFound="notFound";
		public static String notLogin="notLogin";
		public static String captchaError="captchaError";
		public static String exception="exception";
		public static String unknown="unknown";

	}
}
