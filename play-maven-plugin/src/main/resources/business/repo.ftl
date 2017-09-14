package ${modulePackage}.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import ${className};
import ${modulePackage}.entity.Q${simpleName};
import com.airlenet.repo.jpa.EntityRepository;

public interface ${simpleName}Repository extends EntityRepository<${simpleName}, Long>, QuerydslBinderCustomizer<Q${simpleName}> {

	@Override
	default void customize(QuerydslBindings bindings, Q${simpleName} root) {
		//bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
}
