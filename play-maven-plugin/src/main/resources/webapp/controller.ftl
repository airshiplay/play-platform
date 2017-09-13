package ${modulePackage}.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresRoles;
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


import com.airshiplay.play.main.api.LogService;
import com.airshiplay.play.main.api.LogService.LogLevel;
import com.airshiplay.play.main.api.LogService.OperateType;

import ${className};
import ${modulePackage}.service.${simpleName}Service;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;
/**
 * ${functionShowName!}
 *
 * @author ${author!}
 * @version ${version!}
 */
@Controller
@RequestMapping("/${moduleName}/${'${shortSimpleName}'?uncap_first}")
public class ${shortSimpleName}Controller {

	@Autowired
	private LogService logService;
	
	@Autowired
	private ${simpleName}Service ${'${simpleName}'?uncap_first}Service;

	@RequiresRoles("admin")
	@RequestMapping(value = "/${'${shortSimpleName}'?uncap_first}List.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询${functionShowName}列表");
		return "classpath:/${moduleName}/${'${shortSimpleName}'?uncap_first}/${'${shortSimpleName}'?uncap_first}List";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/${moduleName}/${'${shortSimpleName}'?uncap_first}/${'${shortSimpleName}'?uncap_first}Form";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("${'${shortSimpleName}'?uncap_first}", ${'${shortSimpleName}'?uncap_first}EntityService.findOne(id));
		return "classpath:/${moduleName}/${'${shortSimpleName}'?uncap_first}/${'${shortSimpleName}'?uncap_first}Form";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("${'${shortSimpleName}'?uncap_first}", ${'${shortSimpleName}'?uncap_first}EntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询${functionShowName}信息");
		return "classpath:/${moduleName}/${'${shortSimpleName}'?uncap_first}/${'${shortSimpleName}'?uncap_first}View";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<${simpleName}> doPage(Predicate predicate, Pageable pageable) {
		return ${'${shortSimpleName}'?uncap_first}EntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid ${simpleName} job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		${'${shortSimpleName}'?uncap_first}EntityService.save(job);

		if(job.isNew()){
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建${functionShowName}成功");
		}else{
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新${functionShowName}成功");
		}
		
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") ${simpleName} entity) {
		${'${shortSimpleName}'?uncap_first}EntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除${functionShowName}成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") ${simpleName}[] entities) {
		for (${simpleName} entity : entities) {
			${'${shortSimpleName}'?uncap_first}EntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除${functionShowName}成功");
		return Result.success();
	}
}
