package com.airlenet.admin.service;

 import com.airlenet.admin.entity.UserEntity;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.RequestParam;

 import java.util.Optional;

@RequestMapping("/user")
public interface UserRemoteService {

 @RequestMapping(method = RequestMethod.GET, value ="/users/{id}")
 UserEntity getUser(@PathVariable("id") long id);

 @RequestMapping(method = RequestMethod.GET, value ="/users")
 Optional<UserEntity> findByUsername(@RequestParam("username") String username);
}
