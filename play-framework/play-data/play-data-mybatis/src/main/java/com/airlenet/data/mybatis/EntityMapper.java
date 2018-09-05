package com.airlenet.data.mybatis;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface EntityMapper<T> extends Mapper<T> {
    default int deleteWithVersion(T t){
        int result = delete(t);
        if(result == 0){
            throw new RuntimeException("删除失败!");
        }
        return result;
    }

    default int updateByPrimaryKeyWithVersion(T t){
        int result = updateByPrimaryKey(t);
        if(result == 0){
            throw new RuntimeException("更新失败!");
        }
        return result;
    }

}
