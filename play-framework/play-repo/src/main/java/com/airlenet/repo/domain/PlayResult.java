package com.airlenet.repo.domain;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author airlenet
 * @version 2017-10-07
 */

public class PlayResult {
    public String result;
    private String message;
    public Object content;
    public Integer pages;
    public Long total;

    private Language lang;

    private Map<String, Object> extraProperties = new HashMap<>();

    /**
     * @param content
     * @return
     */
    public static PlayResult ok(Object content) {
        PlayResult flexResult = new PlayResult();
        flexResult.result = "ok";
        flexResult.content = content;
        return flexResult;
    }


    public static PlayResult ok() {
        PlayResult flexResult = new PlayResult();
        flexResult.result = "ok";
        return flexResult;
    }

    /**
     * @param content
     * @return
     */
    public static <T> PlayResult okPage(PageInfo<T> content) {
        PlayResult flexResult = new PlayResult();
        flexResult.result = "ok";
        flexResult.content = content.getList();
        flexResult.total = content.getTotal();
        flexResult.pages = content.getPages();
        return flexResult;
    }

    /**
     * 业务错误
     *
     * @param code        业务错误码
     * @param msgTemplate 消息模板
     * @param args        消息模板参数
     * @return
     */
    public static PlayResult error(String code, String msgTemplate, Object[] args) {
        PlayResult flexResult = new PlayResult();
        flexResult.result = code;
        flexResult.lang = new Language(msgTemplate, args);
        return flexResult;
    }

    public static PlayResult error( String msgTemplate, Object[] args) {
        PlayResult flexResult = new PlayResult();
        flexResult.result = "10000";//后期通过国际化语言的msgTemplate 字段进行统一编码，在序列化拦截的地方
        flexResult.lang = new Language(msgTemplate, args);
        return flexResult;
    }

    public PlayResult extraProperties(String key, Object value) {
        this.extraProperties.put(key, value);
        return this;
    }

    public Language getLang() {
        return lang;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getExtraProperties() {
        return extraProperties;
    }

    public Object getContent() {
        return content;
    }

    public static class Language {
        private String template;
        private Object[] args;

        public Language(String template, Object[] args) {
            this.template = template;
            this.args = args;
        }

        public Object[] getArgs() {
            return args;
        }

        public String getTemplate() {
            return template;
        }

    }

    public void message(String message) {
        this.message = message;
    }


    public String getResult() {
        return result;
    }

    public Integer getPages() {
        return pages;
    }

    public Long getTotal() {
        return total;
    }
}
