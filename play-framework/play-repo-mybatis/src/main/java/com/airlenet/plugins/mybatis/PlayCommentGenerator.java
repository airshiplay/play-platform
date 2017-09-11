package com.airlenet.plugins.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * 此插件使用数据库表中列的注释来生成Java Model中属性的注释
 *
 * @author lig
 * 
 */
public class PlayCommentGenerator extends DefaultCommentGenerator {

	private Properties properties;
	private boolean columnRemarks;

	public PlayCommentGenerator() {
		super();
		properties = new Properties();
	}

	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);
		properties.setProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT, "yyyy-MM-dd HH:mm:ss");
		super.addConfigurationProperties(properties);
		columnRemarks = isTrue(properties.getProperty("columnRemarks"));
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		String columnRemarks = introspectedTable.getRemarks();
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(" * " + columnRemarks);
		innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		// super.addJavaFileComment(compilationUnit);

	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		StringBuilder sb = new StringBuilder();

		topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$

		String remarks = introspectedTable.getRemarks();
		if (StringUtility.stringHasValue(remarks)) {
			topLevelClass.addJavaDocLine(" * Database Table Remarks:");
			String[] remarkLines = remarks.split(System.getProperty("line.separator")); //$NON-NLS-1$
			for (String remarkLine : remarkLines) {
				topLevelClass.addJavaDocLine(" *   " + remarkLine); //$NON-NLS-1$
			}
		}
		topLevelClass.addJavaDocLine(" *"); //$NON-NLS-1$
		sb.append(" * database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		topLevelClass.addJavaDocLine(sb.toString());

		addJavadocTag(topLevelClass, true);

		topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		String columnRemarks = introspectedColumn.getRemarks();
		if (!this.columnRemarks ||columnRemarks==null ||columnRemarks.equals("")) {
			return;
		}
		field.addJavaDocLine("/**");
		field.addJavaDocLine(" * " + columnRemarks);
		addJavadocTag(field, false);
		field.addJavaDocLine(" */");
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
		innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		// super.addGetterComment(method, introspectedTable,
		// introspectedColumn);
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		// super.addSetterComment(method, introspectedTable,
		// introspectedColumn);
	}

	@Override
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {

		javaElement.addJavaDocLine(" *"); //$NON-NLS-1$
		StringBuilder sb = new StringBuilder();
		sb.append(" * "); //$NON-NLS-1$
		sb.append("@date");
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge"); //$NON-NLS-1$
		}
		String s = getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}
}