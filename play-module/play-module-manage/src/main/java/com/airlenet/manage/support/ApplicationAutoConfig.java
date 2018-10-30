package com.airlenet.manage.support;

import com.airlenet.manage.module.system.entity.User;
import com.airlenet.manage.module.system.service.UserService;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationAutoConfig {

	@Bean
	public SmartInitializingSingleton afterInit() {

		return new SmartInitializingSingleton() {

			@Autowired
			private UserService userService;

			@Autowired
			private PasswordEncoder passwordEncoder;

			@Override
			public void afterSingletonsInstantiated() {
				if (userService.count() == 0) {
					User user = new User();
					user.setUsername("admin");
					user.setPassword(passwordEncoder.encode("qwe123"));
					user.setName("管理员");
					userService.save(user);
				}
			}
		};
	}

}
