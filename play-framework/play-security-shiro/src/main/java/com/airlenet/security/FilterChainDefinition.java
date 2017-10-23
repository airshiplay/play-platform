package com.airlenet.security;

/**
 * @author airlenet
 * @version 2017-10-21
 */
public class FilterChainDefinition {
    private String url;
    private String filterName;

    public FilterChainDefinition() {
    }

    public FilterChainDefinition(String url, String filterName) {
        this.url = url;
        this.filterName = filterName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }
}
