package ${pkg}.controller;

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

import ${importclass};
import ${pkg}.service.${className}Service;
import com.airlenet.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/${module}/${'${shortClassName}'?uncap_first}")
public class ${shortClassName}Controller {

	@Autowired
	private ${className}Service ${'${className}'?uncap_first}Service;

	@RequestMapping(value = "/${'${shortClassName}'?uncap_first}List.view", method = RequestMethod.GET)
	public String getList() {
		return "classpath:/${module}/${'${shortClassName}'?uncap_first}/${'${shortClassName}'?uncap_first}List";
	}

	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/${module}/${'${shortClassName}'?uncap_first}/${'${shortClassName}'?uncap_first}Form";
	}

	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("${'${shortClassName}'?uncap_first}", ${'${shortClassName}'?uncap_first}EntityService.findOne(id));
		return "classpath:/${module}/${'${shortClassName}'?uncap_first}/${'${shortClassName}'?uncap_first}Form";
	}

	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("${'${shortClassName}'?uncap_first}", ${'${shortClassName}'?uncap_first}EntityService.findOne(id));
		return "classpath:/${module}/${'${shortClassName}'?uncap_first}/${'${shortClassName}'?uncap_first}View";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<${className}> doPage(Predicate predicate, Pageable pageable) {
		return ${'${shortClassName}'?uncap_first}EntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid ${className} job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		${'${shortClassName}'?uncap_first}EntityService.save(job);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") ${className} entity) {
		${'${shortClassName}'?uncap_first}EntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") ${className}[] entities) {
		for (${className} entity : entities) {
			${'${shortClassName}'?uncap_first}EntityService.delete(entity);
		}

		return Result.success();
	}
}
