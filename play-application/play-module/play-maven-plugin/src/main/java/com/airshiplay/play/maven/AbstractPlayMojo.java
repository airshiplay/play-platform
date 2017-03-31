package com.airshiplay.play.maven;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

@SuppressWarnings({ "unused", "rawtypes" })
public abstract class AbstractPlayMojo extends AbstractMojo {

	public Configuration getConfiguration() {
		// 1.创建配置实例Cofiguration
		Configuration configurer = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		ClassTemplateLoader ctl = new ClassTemplateLoader(getClass().getClassLoader(), "");
		TemplateLoader[] loaders = new TemplateLoader[] { ctl };
		MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
		configurer.setTemplateLoader(mtl);
		return configurer;
	}

	public abstract File getSourceDirectory();

	public abstract File getOutDirectory();

	public List<Map<String, Object>> getEntityList() throws IOException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Collection<File> coll = FileUtils.listFiles(getSourceDirectory(), new String[] { "java" }, true);
		for (Iterator<File> iterator = coll.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			Map<String, Object> map = getEntity(file);
			if (map == null)
				continue;
			list.add(map);
			getLog().debug("" + map);
		}
		return list;
	}

	public Map<String, Object> getEntity(File file) throws IOException {
		// getLog().debug("load file ="+file.getAbsolutePath());
		Map<String, Object> map = new HashMap<String, Object>();
		String content = FileUtils.readFileToString(file, "UTF-8");
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		// 设定解析器的源代码字符
		parser.setSource(content.toCharArray());
		// 使用解析器进行解析并返回AST上下文结果(CompilationUnit为根节点)
		CompilationUnit result = (CompilationUnit) parser.createAST(null);

		// 获取类型
		List<?> types = result.types();
		if (types == null || types.isEmpty()) {
			getLog().warn("不支持文件类" + file.getName());
			return null;
		}
		// 取得类型声明
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		boolean skip = true;
		for (Object modifiObj : typeDec.modifiers()) {
			if (modifiObj instanceof MarkerAnnotation) {
				MarkerAnnotation marker = (MarkerAnnotation) modifiObj;
				if (marker.getTypeName().getFullyQualifiedName().equals("Entity")) {
					skip = false;
				}
			} else if (modifiObj instanceof NormalAnnotation) {
				NormalAnnotation normal = (NormalAnnotation) modifiObj;
				// getLog().debug(normal.getTypeName().getFullyQualifiedName()+""+normal.values());
				if (normal.getTypeName().getFullyQualifiedName().equals("Table")) {
					skip = false;
					@SuppressWarnings("unchecked")
					List<MemberValuePair> pairs = (List<MemberValuePair>) normal.values();
					for (MemberValuePair pair : pairs) {

						if ("name".equals(pair.getName().getFullyQualifiedName())) {
							if (pair.getValue() instanceof StringLiteral) {
								String tableName = ((StringLiteral) pair.getValue()).getLiteralValue();
								map.put("moduleName", tableName.substring(0, tableName.indexOf("_")));
							}
						}

					}

				}
			}
		}
		if (skip) {
			return null;
		}
		{// 内部类
			for (int i = 1; i < types.size(); i++) {
				TypeDeclaration type = (TypeDeclaration) types.get(i);
				getLog().info("其他类" + type);
			}

		}
		// System.out.println("System.out.println();"+result);
		// for (Object o : typeDec.getTypes()) {
		// System.out.println("========" + o + o.getClass());
		// }
		// System.out.println("========"+
		// (typeDec.getTypes().length>0?typeDec.getTypes()[0]:""));
		// System.out.println("========"+
		// typeDec.getBodyDeclarationsProperty());
		// System.out.println("========"+ typeDec);
		// System.out.println(result);
		// 引用import

		List importList = result.imports();
		// 取得包名
		PackageDeclaration packetDec = result.getPackage();
		// 取得类名
		String simpleName = typeDec.getName().toString();
		// 取得函数(Method)声明列表
		MethodDeclaration methodDec[] = typeDec.getMethods();
		// 取得函数(Field)声明列表
		FieldDeclaration fieldDec[] = typeDec.getFields();
		String moduleName = map.get("moduleName").toString();
		map.put("package", packetDec.getName().getFullyQualifiedName());
		map.put("modulePackage", packetDec.getName().getFullyQualifiedName().substring(0, packetDec.getName().getFullyQualifiedName().indexOf(moduleName) + moduleName.length()));
		map.put("className", packetDec.getName() + "." + simpleName);
		map.put("simpleName", simpleName);
		if (simpleName.lastIndexOf("Entity") == -1) {
			getLog().error(new RuntimeException("类名必须以Entity结尾"));
		}
		map.put("shortSimpleName", simpleName.substring(0, simpleName.lastIndexOf("Entity")));

		List a = typeDec.getJavadoc().tags();
		String functionName = null;
		String author = null;
		String version = null;
		for (int i = 0; i < a.size(); i++) {
			TagElement tag = (TagElement) a.get(i);

			if (tag.getTagName() == null && functionName == null) {
				functionName = ((TextElement) tag.fragments().get(0)).getText();
			} else if (tag.getTagName().equals("@name")) {
				functionName = ((TextElement) tag.fragments().get(0)).getText();
			} else if (tag.getTagName().equals("@author")) {
				author = ((TextElement) tag.fragments().get(0)).getText();
			} else if (tag.getTagName().equals("@version")) {
				version = ((TextElement) tag.fragments().get(0)).getText();
			}
			// List elements = tag.fragments();
			// for (int j = 0; j < elements.size(); j++) {
			// TextElement ele = (TextElement) elements.get(0);
			//
			// }
		}
		map.put("functionShowName", functionName.trim());
		map.put("author", author);
		map.put("version", version);

		List<Map<String, Object>> fileds = new ArrayList<Map<String, Object>>();
		for (FieldDeclaration fieldDecEle : fieldDec) {
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			String fieldName = ((VariableDeclarationFragment) (fieldDecEle.fragments().get(0))).getName().getFullyQualifiedName();// 字段名称
			String fieldType = fieldDecEle.getType().toString();
			// if (fieldDecEle.getType().isPrimitiveType()) {// 基本类型
			// fieldType =((PrimitiveType)fieldDecEle.getType()).toString();
			// } else if (fieldDecEle.getType().isSimpleType()) {// 对象类型
			// fieldType=((SimpleType)
			// fieldDecEle.getType()).getName().getFullyQualifiedName();
			// } else if (fieldDecEle.getType().isParameterizedType()) {// 集合类型
			// fieldType =((ParameterizedType)
			// fieldDecEle.getType()).toString();
			// }
			if ("serialVersionUID".equals(fieldName) || "enum".equals(fieldType)) {
				continue;
			}
			fieldMap.put("fieldName", fieldName);
			fieldMap.put("fieldType", fieldType);
			fieldMap.put("formtype", fieldName.equals("Date") ? "date" : "text");// 默认类型text
			// 变量修饰
			List modifiers = fieldDecEle.modifiers();
			for (Object modifiObj : modifiers) {
				if (modifiObj instanceof Modifier) {
					Modifier modify = (Modifier) modifiObj;
				} else if (modifiObj instanceof MarkerAnnotation) {
					MarkerAnnotation marker = (MarkerAnnotation) modifiObj;
					String name = marker.getTypeName().getFullyQualifiedName();
					if ("Column".equals(name)) {

					} else if ("NotNull".equals(name)) {
						fieldMap.put("notnull", true);
					} else if ("OneToOne".equals(name)) {

					} else if ("JoinColumn".equals(name)) {
					} else if ("Enumerated".equals(name)) {
					} else if ("OneToMany".equals(name)) {
					} else if ("ManyToOne".equals(name)) {
					} else {
						getLog().warn("不支持此修饰符" + modifiObj + marker.getClass());
					}

				} else if (modifiObj instanceof NormalAnnotation) {
					NormalAnnotation normal = (NormalAnnotation) modifiObj;
					String name = normal.getTypeName().getFullyQualifiedName();

					@SuppressWarnings("unchecked")
					List<MemberValuePair> pairs = (List<MemberValuePair>) normal.values();
					if ("Column".equals(name)) {
						for (MemberValuePair pair : pairs) {
							if ("nullable".equals(pair.getName().getFullyQualifiedName())) {
								if (((BooleanLiteral) pair.getValue()).booleanValue()) {
									fieldMap.put("notnull", true);
								}
							} else if ("length".equals(pair.getName().getFullyQualifiedName())) {
								fieldMap.put("size", ((NumberLiteral) pair.getValue()).getToken());
							} else if ("name".equals(pair.getName().getFullyQualifiedName())) {

							} else {
								getLog().warn("不支持此修饰符" + normal + "属性" + pair.getName());
							}
						}
					} else if ("Size".equals(name)) {
						for (MemberValuePair pair : pairs) {

							if ("min".equals(pair.getName().getFullyQualifiedName())) {
								fieldMap.put("minSize", ((NumberLiteral) pair.getValue()).getToken());
							} else if ("max".equals(pair.getName().getFullyQualifiedName())) {
								fieldMap.put("maxSize", ((NumberLiteral) pair.getValue()).getToken());
							} else {
								getLog().warn("不支持此修饰符" + normal + "属性" + pair.getName());
							}
						}
					} else if ("JoinColumn".equals(name)) {
					} else if ("Enumerated".equals(name)) {
					} else if ("OneToOne".equals(name)) {
					} else {
						getLog().warn("不支持此修饰符" + normal + normal.getClass());
					}

				} else if (modifiObj instanceof SingleMemberAnnotation) {
					SingleMemberAnnotation singleMember = (SingleMemberAnnotation) modifiObj;
					String name = singleMember.getTypeName().getFullyQualifiedName();

					if ("Enumerated".equals(name)) {
						fieldMap.put("formtype", "enum");
						fieldMap.put("enumList", new ArrayList<Map<String, String>>());
					} else if ("Max".equals(name)) {
						fieldMap.put("maxValue", ((NumberLiteral) singleMember.getValue()).getToken());
					} else {
						getLog().warn("不支持此修饰符" + singleMember + singleMember.getClass());
					}
				} else {
					getLog().warn("不支持此修饰符" + modifiObj + modifiObj.getClass());
				}

			}
			/** 修饰符结束 */
			/** Field Javadoc */

			if (fieldDecEle.getJavadoc() != null) {

				List fieldDocs = fieldDecEle.getJavadoc().tags();
				for (int i = 0; i < fieldDocs.size(); i++) {
					TagElement tag = (TagElement) fieldDocs.get(i);
					if (tag.getTagName() == null) {// 后面增加支持
						String fieldShowName = ((TextElement) tag.fragments().get(0)).getText();
						fieldMap.put("fieldShowName", fieldShowName);
					} else if (tag.getTagName().equals("@formtype")) {
						String formtype = ((TextElement) tag.fragments().get(0)).getText();
						fieldMap.put("formtype", formtype.trim());
					}
				}
				if ("select2".equals(fieldMap.get("formtype"))) {
					String m = fieldType.substring(0, fieldType.lastIndexOf("Entity"));
					fieldMap.put("select2Url", "/" + moduleName + "/" + m.substring(0, 1).toLowerCase() + m.substring(1, m.length()) + "/page");
				}

			} else {
				fieldMap.put("fieldShowName", fieldName);
			}

			fileds.add(fieldMap);
		}

		map.put("fields", fileds);

		return map;
	}

	public static String pkg2path(String pkg) {
		return pkg.replace(".", File.separator);
	}
}
