package com.airlenet.data.jpa;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface EntityService<T extends BaseEntity, I extends Serializable> {

	public Class<T> getEntityClass();

	public EntityRepository<T, I> getRepository();

	public List<T> findAll();
	public List<T> findAll(Sort sort);

	public List<T> findAll(Iterable<I> ids);

	public <S extends T> List<S> save(Iterable<S> entities);

	public void flush();

	public <S extends T> S saveAndFlush(S entity);

	public void deleteInBatch(Iterable<T> entities);

	public void deleteAllInBatch();

	public T getOne(I id);

	public <S extends T> List<S> findAll(Example<S> example);

	public <S extends T> List<S> findAll(Example<S> example, Sort sort);

	public Page<T> findAll(Pageable pageable);

	public <S extends T> S save(S entity);

	public Optional<T> findOne(I id);

	public boolean exists(I id);

	public long count();

	public void delete(I id);

	public void delete(T entity);

	public void delete(Iterable<? extends T> entities);

	public void deleteAll();

	public <S extends T> Optional<S> findOne(Example<S> example);

	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) ;

	public <S extends T> long count(Example<S> example);

	public <S extends T> boolean exists(Example<S> example);
	public Optional<T> findOne(Predicate predicate);

	public Iterable<T> findAll(Predicate predicate);

	public Iterable<T> findAll(Predicate predicate, Sort sort);

	public Iterable<T> findAll(Predicate predicate, OrderSpecifier<?>... orders) ;

	public Iterable<T> findAll(OrderSpecifier<?>... orders);

	public Page<T> findAll(Predicate predicate, Pageable pageable);

	public long count(Predicate predicate);

	public boolean exists(Predicate predicate);

	public T initDomain();

}
