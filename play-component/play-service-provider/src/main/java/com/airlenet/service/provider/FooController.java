package com.airlenet.service.provider;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

	@RequestMapping(value = "/call/{id}", method = RequestMethod.GET)
	public Foo call(@PathVariable Integer id, HttpServletRequest request) {
//		try {
//			Thread.sleep(2500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Foo foo = new Foo();
		foo.setId(id);
		foo.setName("ttt");
		foo.setAge(12);
		foo.setLastModifiedDate(new Date());
		foo.setMessage(request.getRequestURL().toString());
		return foo;
	}

}
