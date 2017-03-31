package com.airshiplay.play.maven;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/**
 * Goal which touches a timestamp file.
 *
 * @deprecated Don't use!
 */
@Mojo(name = "test")
public class TestMojo extends AbstractMojo {
	/**
	 * Location of the file.
	 */
	@Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
	private File outputDirectory;

	public void execute() throws MojoExecutionException {
		String content = null;
		try {
			content = read("/Users/lig/Documents/workspace/play-platform/play-application/play-wechat-business/src/main/java/com/airshiplay/play/wechat/entity/WechatUserEntity.java");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // java源文件
			// 创建解析器
		ASTParser parsert = ASTParser.newParser(AST.JLS3);
		// 设定解析器的源代码字符
		parsert.setSource(content.toCharArray());
		// 使用解析器进行解析并返回AST上下文结果(CompilationUnit为根节点)
		CompilationUnit result = (CompilationUnit) parsert.createAST(null);

		// 获取类型
		List<?> types = result.types();

		// 取得类型声明
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		System.out.println("类型==" + typeDec);
		System.out.println(typeDec.getTypes());

		// ##############获取源代码结构信息#################
		// 引用import
		List<?> importList = result.imports();
		// 取得包名
		PackageDeclaration packetDec = result.getPackage();
		// 取得类名
		String className = typeDec.getName().toString();
		// 取得函数(Method)声明列表
		MethodDeclaration methodDec[] = typeDec.getMethods();
		// 取得函数(Field)声明列表
		FieldDeclaration fieldDec[] = typeDec.getFields();

		// 输出包名
		System.out.println("包:");
		System.out.println(packetDec.getName());
		// 输出引用import
		System.out.println("引用import:");
		for (Object obj : importList) {
			ImportDeclaration importDec = (ImportDeclaration) obj;
			System.out.println(importDec.getName());
		}
		// 输出类名
		System.out.println("类:");
		System.out.println(className);
		System.out.println("类 javadoc=" + typeDec.getJavadoc() + "\n\n\n" + typeDec.getJavadoc().tags());

		List<?> a = typeDec.getJavadoc().tags();
		for (int i = 0; i < a.size(); i++) {
			TagElement tag = (TagElement) a.get(i);

			System.out.println(tag.fragments().get(0).getClass());
			System.out.println(tag.getTagName() + "ddd" + tag.fragments());
		}
		;
		for (Object modifiObj : typeDec.modifiers()) {
			if (modifiObj instanceof Modifier) {
				Modifier modify = (Modifier) modifiObj;
				System.out.println("class modify=" + modify + "-");
			} else if (modifiObj instanceof MarkerAnnotation) {
				MarkerAnnotation marker = (MarkerAnnotation) modifiObj;
				System.out.println("class marker" + marker.getTypeName() + "-");
			} else if (modifiObj instanceof NormalAnnotation) {
				NormalAnnotation normal = (NormalAnnotation) modifiObj;
				System.out.println("class normal" + normal.getTypeName() + normal.properties() + normal.values() + "-");
			}

		}
		// 循环输出函数名称
		System.out.println("========================");
		System.out.println("函数:");
		for (MethodDeclaration method : methodDec) {
			/*
			 * System.out.println(method.getName());
			 * System.out.println("body:");
			 * System.out.println(method.getBody());
			 * System.out.println("Javadoc:" + method.getJavadoc());
			 * 
			 * System.out.println("Body:" + method.getBody());
			 * 
			 * System.out.println("ReturnType:" + method.getReturnType());
			 */
			System.out.println("=============");
			System.out.println(method);
		}

		// 循环输出变量
		System.out.println("变量:");
		for (FieldDeclaration fieldDecEle : fieldDec) {
			// public static
			System.out.println("\n\n\nfiled all=" + fieldDecEle);
			for (Object modifiObj : fieldDecEle.modifiers()) {
				if (modifiObj instanceof Modifier) {
					Modifier modify = (Modifier) modifiObj;
					System.out.println("modify=" + modify + "-");
				} else if (modifiObj instanceof MarkerAnnotation) {
					MarkerAnnotation marker = (MarkerAnnotation) modifiObj;
					System.out.println("marker" + marker + "-");
				} else {
					System.out.println("modifiObj" + modifiObj + "-");
				}
			}
			System.out.println("field type=" + fieldDecEle.getType().getClass());
			for (Object obj : fieldDecEle.fragments()) {
				VariableDeclarationFragment frag = (VariableDeclarationFragment) obj;
				System.out.println("[FIELD_NAME:]" + frag.getName());
			}
		}
	}

	private static String read(String filename) throws IOException {
		File file = new File(filename);
		byte[] b = new byte[(int) file.length()];
		@SuppressWarnings("resource")
		FileInputStream fis = new FileInputStream(file);
		fis.read(b);
		return new String(b);

	}

}
