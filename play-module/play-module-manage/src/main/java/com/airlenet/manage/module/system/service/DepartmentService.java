package com.airlenet.manage.module.system.service;

import com.airlenet.manage.module.system.entity.Department;
import org.springframework.stereotype.Service;

import com.airlenet.data.jpa.HierarchicalEntityService;

@Service
public class DepartmentService extends HierarchicalEntityService<Department, Long> {

}
