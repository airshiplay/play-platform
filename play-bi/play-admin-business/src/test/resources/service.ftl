package ${pkg}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import ${importclass};
import ${pkg}.repo.${className}Repository;

@Service
public class ${className}Service extends EntityService<${className}, Long> {
	
	@Autowired
	private ${className}Repository ${'${className}'?uncap_first}Repository;
}
