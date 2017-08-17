package generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airshiplay.play.main.entity.MemberRankEntity;
import com.airshiplay.play.main.entity.SettingEntity;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class WebTemplate {
	private static final Logger logger = LoggerFactory.getLogger(WebTemplate.class);

	public static void main(String[] args) throws TemplateException, IOException {
		// 1.创建配置实例Cofiguration
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		File resourcesFile = new File("src/test/resources");
		System.out.println(resourcesFile.getAbsolutePath());
		cfg.setDirectoryForTemplateLoading(resourcesFile);

		for (Class<?> cls : new Class[] {SettingEntity.class }) {
			controller(cfg, cls);
			htmlList(cfg, cls);
			htmlForm(cfg, cls);
			htmlView(cfg, cls);
		}

	}

	public static void controller(Configuration cfg, Class<?> cls) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String servicepkg = cls.getPackage().getName().substring(0, cls.getPackage().getName().lastIndexOf("."));
		String module = servicepkg.substring(servicepkg.lastIndexOf(".") + 1);
		String filepath = pkg2path(servicepkg) + "/controller/" + cls.getSimpleName().substring(0, cls.getSimpleName().lastIndexOf("Entity")) + "Controller.java";
		File file = new File(new File("src/main/java"), filepath);
		if (file.exists()) {
			logger.warn(file.toString() + " exists");
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("controller.ftl");
		Map<String, String> root = new HashMap<String, String>();
		root.put("pkg", servicepkg);
		root.put("file", filepath);
		root.put("module", module);
		root.put("className", cls.getSimpleName());
		root.put("shortClassName", cls.getSimpleName().substring(0, cls.getSimpleName().lastIndexOf("Entity")));
		root.put("importclass", cls.getName());
		Writer out = new FileWriter(file);
		template.process(root, out);
		out.flush();
	}

	private static void htmlForm(Configuration cfg, Class<?> cls) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String servicepkg = cls.getPackage().getName().substring(0, cls.getPackage().getName().lastIndexOf("."));
		String module = servicepkg.substring(servicepkg.lastIndexOf(".") + 1);
		String shortClassName = cls.getSimpleName().substring(0, cls.getSimpleName().lastIndexOf("Entity"));
		String lowerShortClassName = shortClassName.substring(0, 1).toLowerCase() + shortClassName.substring(1, shortClassName.length());
		String filepath = "/bootstrap/" + module + "/" + lowerShortClassName + "/" + lowerShortClassName + "Form.html";
		File file = new File(new File("src/main/resources"), filepath);
		if (file.exists()) {
			logger.warn(file.toString() + " exists");
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("form.ftl");
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("pkg", servicepkg);
		root.put("file", filepath);
		root.put("module", module);
		root.put("className", cls.getSimpleName());
		root.put("shortClassName", shortClassName);
		root.put("importclass", cls.getName());
		root.put("fields", getField(cls));
		Writer out = new FileWriter(file);
		template.process(root, out);
		out.flush();
	}

	private static void htmlList(Configuration cfg, Class<?> cls) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String servicepkg = cls.getPackage().getName().substring(0, cls.getPackage().getName().lastIndexOf("."));
		String module = servicepkg.substring(servicepkg.lastIndexOf(".") + 1);
		String shortClassName = cls.getSimpleName().substring(0, cls.getSimpleName().lastIndexOf("Entity"));
		String lowerShortClassName = shortClassName.substring(0, 1).toLowerCase() + shortClassName.substring(1, shortClassName.length());
		String filepath = "/bootstrap/" + module + "/" + lowerShortClassName + "/" + lowerShortClassName + "List.html";
		File file = new File(new File("src/main/resources"), filepath);
		if (file.exists()) {
			logger.warn(file.toString() + " exists");
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("list.ftl");
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("pkg", servicepkg);
		root.put("file", filepath);
		root.put("module", module);
		root.put("className", cls.getSimpleName());
		root.put("shortClassName", shortClassName);
		root.put("importclass", cls.getName());
		root.put("fields", getField(cls));
		Writer out = new FileWriter(file);
		template.process(root, out);
		out.flush();
	}

	private static void htmlView(Configuration cfg, Class<?> cls) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String servicepkg = cls.getPackage().getName().substring(0, cls.getPackage().getName().lastIndexOf("."));
		String module = servicepkg.substring(servicepkg.lastIndexOf(".") + 1);
		String shortClassName = cls.getSimpleName().substring(0, cls.getSimpleName().lastIndexOf("Entity"));
		String lowerShortClassName = shortClassName.substring(0, 1).toLowerCase() + shortClassName.substring(1, shortClassName.length());
		String filepath = "/bootstrap/" + module + "/" + lowerShortClassName + "/" + lowerShortClassName + "View.html";
		File file = new File(new File("src/main/resources"), filepath);
		if (file.exists()) {
			logger.warn(file.toString() + " exists");
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("view.ftl");
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("pkg", servicepkg);
		root.put("file", filepath);
		root.put("module", module);
		root.put("className", cls.getSimpleName());
		root.put("shortClassName", shortClassName);
		root.put("importclass", cls.getName());
		root.put("fields", getField(cls));
		Writer out = new FileWriter(file);
		template.process(root, out);
		out.flush();
	}

	public static List<Map<String, Object>> getField(Class<?> cls) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals("serialVersionUID")
					|| (!field.getType().isPrimitive() && !field.getType().equals(String.class) && !field.getType().equals(Date.class) && !field.getType().isEnum())) {
				continue;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fieldName", field.getName());
			map.put("fieldType",field.getType().isEnum()?"Enum": field.getType().getSimpleName());
			
			if(field.getType().isEnum()){
				List<String> enumlist=new ArrayList<String >();
				Object []os=field.getType().getEnumConstants();
				for(Object o:os){
					enumlist.add(o.toString());
				}
				map.put("enumList", enumlist);
			}
			
			
			
			Annotation[] annos = field.getAnnotations();
			for (Annotation anno : annos) {
				// //////
				if (anno instanceof NotNull) {
					map.put("notnull", true);
				} else {
					map.put("notnull", false);
				}
				// //////
				if (anno instanceof Column) {
					if (!((Column) anno).nullable()) {
						map.put("notnull", true);
					}
					if (((Column) anno).length() != 0) {
						map.put("size", ((Column) anno).length());
					}
				}
				if (anno instanceof Size) {
					map.put("minSize", ((Size) anno).min());
					map.put("maxSize", ((Size) anno).max());
				}
			}
			list.add(map);
		}
		return list;
	}

	public static String pkg2path(String pkg) {
		return pkg.replace(".", "/");
	}
}
