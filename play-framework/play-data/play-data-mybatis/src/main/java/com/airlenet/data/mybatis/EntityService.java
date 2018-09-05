package com.airlenet.data.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class EntityService<T extends BaseEntity ,I extends Serializable> {
    @Autowired
    private EntityMapper<T> entityMapper;
    protected final Class<T> entityClass;
    public List<T> findAll() {
        return entityMapper.selectAll();
    }
    public EntityService() {
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        entityClass = (Class<T>) resolvableType.as(EntityService.class).getGeneric(0).resolve();
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public EntityMapper<T> getMapper() {
        return entityMapper;
    }


    public List<T> findAll(Iterable<I> ids) {
        return entityMapper.selectByExample(Example.builder(entityClass).andWhere(WeekendSqls.<T>custom().andIn(T::getId,ids)).build());
    }

    public void deleteAllInBatch() {
        entityMapper.deleteByExample(new Example(entityClass));
    }

    public T getOne(I id) {
        return  entityMapper.selectByPrimaryKey(id);
    }

    public PageInfo<T> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return  new PageInfo<>(entityMapper.selectAll());
    }

    public <S extends T> S save(S entity) {
        entityMapper.insert(entity);
        return entity;
    }

    public Optional<T> findOne(I id) {
        return Optional.of(entityMapper.selectByPrimaryKey(id));
    }

    public boolean exists(I id) {
        return entityMapper.existsWithPrimaryKey(id);
    }

    public long count() {
        return entityMapper.selectCountByExample(new Example(entityClass));
    }

    public void delete(I id) {
        entityMapper.deleteByPrimaryKey(id);
    }

    public void delete(T entity) {
        entityMapper.delete(entity);
    }

    public void deleteAll() {
        entityMapper.deleteByExample(new Example(entityClass));
    }

    public List<T> findAll(Example example) {
        return  entityMapper.selectByExample(example);
    }

    public PageInfo<T> findAll(Example example, Page page) {
        PageHelper.startPage(page);
        return new PageInfo<T>(entityMapper.selectByExample(example));
    }

    public  long count(Example  example) {
        return entityMapper.selectCountByExample(example);
    }

    public  boolean exists(Example  example) {
        return entityMapper.selectCountByExample(example)!=0;
    }

}
