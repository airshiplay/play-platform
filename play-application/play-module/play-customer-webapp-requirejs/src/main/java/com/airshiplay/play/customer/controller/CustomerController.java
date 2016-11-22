package com.airshiplay.play.customer.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import com.airshiplay.play.customer.LongTermTaskCallback;
import com.airshiplay.play.customer.entity.CustomerEntity;
import com.airshiplay.play.customer.service.CustomerEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.google.common.base.Strings;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerEntityService customerEntityService;
	
 

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String getUsrList(){
		return "/views/freemarker/customer/user/list";
	}
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getUsrAdd(){
		return "/views/freemarker/customer/user/add";
	}
	@RequestMapping(value="/edit/{userId}",method = RequestMethod.GET)
	public String getUsrEdit(@PathVariable Long userId,Model model){
		CustomerEntity CustomerEntity= customerEntityService.findOne(userId);
		model.addAttribute("user", CustomerEntity);
		return "/views/freemarker/customer/user/edit";
	}
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<CustomerEntity> doPage(Predicate predicate, Pageable pageable) {
		return customerEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid CustomerEntity user, BindingResult bindingResult, String newPassword) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		if(user.isNew()) {
			if(Strings.isNullOrEmpty(newPassword)) {
				return Result.validateError();
			}
			//user.setPassword(passwordEncoder.encode(newPassword));
		}
		customerEntityService.save(user);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") CustomerEntity entity) {
		customerEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") CustomerEntity[] entities) {
		for (CustomerEntity entity : entities) {
			customerEntityService.delete(entity);
		}

		return Result.success();
	}
	@RequestMapping(value = "/asynctask", method = RequestMethod.GET)
	public DeferredResult<ModelAndView> asyncTask() {
		DeferredResult<ModelAndView> deferredResult = new DeferredResult<ModelAndView>(2000L);
		System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
		makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {
			@Override
			public void callback(Object result) {
				System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
				ModelAndView mav = new ModelAndView("remotecalltask");
				mav.addObject("result", result);
				deferredResult.setResult(mav);
			}
		});

		deferredResult.onTimeout(new Runnable() {
			@Override
			public void run() {
				System.out.println("异步调用执行超时！thread id is : " + Thread.currentThread().getId());
				ModelAndView mav = new ModelAndView("remotecalltask");
				mav.addObject("result", "异步调用执行超时");
				deferredResult.setResult(mav);
			}
		});

		return deferredResult;
	}

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

	public void makeRemoteCallAndUnknownWhenFinish(LongTermTaskCallback callback) {

		scheduler.schedule(new Runnable() {
			@Override
			public void run() {
				callback.callback("长时间异步调用完成.");
			}
		}, 2000L, TimeUnit.SECONDS);
	}

	@RequestMapping(value = "/longtimetask", method = RequestMethod.GET)
	public WebAsyncTask<ModelAndView> longTimeTask() {
		System.out.println("/longtimetask被调用 thread id is : " + Thread.currentThread().getId());
		Callable<ModelAndView> callable = new Callable<ModelAndView>() {
			public ModelAndView call() throws Exception {
				Thread.sleep(3000); // 假设是一些长时间任务
				ModelAndView mav = new ModelAndView("longtimetask");
				mav.addObject("result", "执行成功");
				System.out.println("执行成功 thread id is : " + Thread.currentThread().getId());
				return mav;
			}
		};
		WebAsyncTask<ModelAndView> asyncTask = new WebAsyncTask<ModelAndView>(2000, callable);
		asyncTask.onTimeout(new Callable<ModelAndView>() {
			public ModelAndView call() throws Exception {
				ModelAndView mav = new ModelAndView("longtimetask");
				mav.addObject("result", "执行超时");
				System.out.println("执行超时 thread id is ：" + Thread.currentThread().getId());
				return mav;
			}
		});
		return new WebAsyncTask<ModelAndView>(3000, callable);
	}
}
