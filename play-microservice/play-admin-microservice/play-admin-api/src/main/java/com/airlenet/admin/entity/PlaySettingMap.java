package com.airlenet.admin.entity;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;

/**
 * @author airlenet
 * @version 2017-12-18
 */
public class PlaySettingMap extends HashMap<String, String> {
    public String getConfigName() {
        return get("config_name");
    }

    public String getConfigUrl() {
        return get("config_url");
    }

    public String getDefaultLanguage(){
        return get("config_default_language");
    }

    public String getConfigMetaTitle() {
        return get("config_meta_title");
    }

    public String getConfigMettaDescription() {
        return get("config_metta_description");
    }

    public String getConfigMetaKeyword() {
        return get("config_meta_keyword");
    }


    public String getConfigMailSmtpHostname() {
        return get("config_mail_smtp_hostname");
    }

    public String getConfigMailSmtpUsername() {
        return get("config_mail_smtp_username");
    }

    public String getConfigMailSmtpPassword() {
        return get("config_mail_smtp_password");
    }

    public int getConfigMailSmtpPort() {
        return NumberUtils.toInt(get("config_mail_smtp_port"),25);
    }

    public int getConfigMailSmtpTimeout() {
        return NumberUtils.toInt(get("config_mailSmtpTimeout"),5);
    }
}
