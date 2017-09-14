package ${modulePackage}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlenet.repo.jpa.EntityService;
import ${className};
import ${modulePackage}.repo.${simpleName}Repository;
/**
 * ${functionShowName!}
 *
 * @author ${author!}
 * @version ${version!}
 */
@Service
public class ${simpleName}Service extends EntityService<${simpleName}, Long> {
	
	@Autowired
	private ${simpleName}Repository ${'${simpleName}'?uncap_first}Repository;
}
