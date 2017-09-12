package ${modulePackage}.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import ${className};
import ${modulePackage}.entity.Q${simpleName};
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface ${simpleName}Repository extends BaseJpaRepository<${simpleName}, Long>, QuerydslBinderCustomizer<Q${simpleName}> {

	@Override
	default void customize(QuerydslBindings bindings, Q${simpleName} root) {
		//bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
}
