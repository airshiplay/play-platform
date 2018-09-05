package com.airlenet.admin.client;

import com.airlenet.admin.service.UserRemoteService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "play-admin-provider")//,configuration = FeignClientsConfigurationCustom.class
public interface UserClient extends UserRemoteService {
}
