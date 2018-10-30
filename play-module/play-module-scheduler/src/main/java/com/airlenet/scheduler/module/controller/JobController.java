package com.airlenet.scheduler.module.controller;

import java.util.Set;

import com.airlenet.data.domain.Result;
import com.airlenet.scheduler.module.entity.JobEntity;
import com.airlenet.scheduler.module.entity.JobPrimaryKey;
import com.airlenet.scheduler.module.model.JobModel;
import com.airlenet.scheduler.module.service.JobService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.jobs.ee.mail.SendMailJob;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import com.airlenet.scheduler.module.entity.JobType;
import com.airlenet.scheduler.module.model.JobModel;
import com.airlenet.webapp.BaseController;

import javax.validation.Valid;

@Controller
@RequestMapping("/job")
public class JobController extends BaseController {

	@Autowired
	private ObjectFactory<Scheduler> scheduler;

	@Autowired
	private JobService jobService;
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<JobModel> doPage(Pageable pageable) throws SchedulerException {
		Set<JobKey> jobKeys = scheduler.getObject().getJobKeys(GroupMatcher.anyJobGroup());
		return new PageImpl<>(Lists.newArrayList(jobKeys), pageable, jobKeys.size()).map(jobKey -> {
			JobModel jobModel = new JobModel();
			try {
				JobDetail jobDetail = scheduler.getObject().getJobDetail(jobKey);
				jobModel.setJobName(jobDetail.getKey().getName());
				jobModel.setJobGroup(jobDetail.getKey().getGroup());
				jobModel.setJobClass(jobDetail.getJobClass());
				jobModel.setDescription(jobDetail.getDescription());
				jobModel.setDurable(jobDetail.isDurable());
				jobModel.setRequestsRecovery(jobDetail.requestsRecovery());
				// jobModel.setData(jobDetail.getJobDataMap());
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			return jobModel;
		});
	}

	@RequestMapping(value = { "", "/", "/list" }, method = RequestMethod.GET)
	public String show(Model model) {
		model.addAttribute("jobTypes", JobType.values());
		return "/job/list";
	}

	@RequestMapping(value = { "/add", "/edit" }, method = RequestMethod.GET)
	public String add(Model model) {
		JobModel jobModel = new JobModel();
		JobType jobType = getParameter("type", JobType.class, JobType.no_op);
		jobModel.setJobClass(jobType.getJobClass());
		return edit(jobModel, model);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(JobModel entity, Model model) {
		model.addAttribute("jobType", jobClassToType(entity.getJobClass()));
		model.addAttribute("entity", entity);
		return "/job/edit";
	}

	private JobType jobClassToType(Class<? extends Job> jobClass) {
		for (JobType jobType : JobType.values()) {
			if (jobType.getJobClass().isAssignableFrom(jobClass)) {
				return jobType;
			}
		}

		return null;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid JobModel jobModel, BindingResult bindingResult) {

		try {
			scheduler.getObject().addJob(JobBuilder.newJob(SendMailJob.class)
					.usingJobData(new JobDataMap(jobModel.getData()))
					.withDescription(jobModel.getDescription())
					.storeDurably(true)
					.requestRecovery(true)
					.withIdentity(jobModel.getJobName())

					.build(),false);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
//		onValidate(entity, bindingResult);
//		if (bindingResult.hasErrors()) {
//			return Result.validateError().error(bindingResult.getAllErrors());
//		}JobPrimaryKey jobPrimaryKey = new JobPrimaryKey();
//		jobPrimaryKey.setName("");
//		jobPrimaryKey.setGroup("");
//		jobPrimaryKey.setScheduler("");
//		entity.setId(jobPrimaryKey);
//		entity.setJobClassName("org.quartz.jobs.ee.mail.SendMailJob");
//		jobService.save(entity);
//		onBeforeSave(entity);

//		getService().save(entity);
//		onAfterSave(entity);

		return Result.success();
	}

}
