package generate;

import com.airshiplay.play.main.entity.MemberRankEntity;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class BusinessTemplate {
	private static final Logger logger = LoggerFactory.getLogger(BusinessTemplate.class);

	public static void main(String[] args) throws TemplateException, IOException {
		// 1.创建配置实例Cofiguration
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		File resourcesFile = new File("src/test/resources");
		System.out.println(resourcesFile.getAbsolutePath());
		cfg.setDirectoryForTemplateLoading(resourcesFile);

		
		for(Class<?>cls:new Class[]{MemberRankEntity.class}){
			repo(cfg, cls);
			service(cfg, cls);
		}

	}

	public static void repo(Configuration cfg, Class<?> cls) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		
		cls.getPackage().getName().lastIndexOf(".");
		String servicepkg = cls.getPackage().getName().substring(0, cls.getPackage().getName().lastIndexOf("."));
		String filepath = pkg2path(servicepkg) + "/repo/" + cls.getSimpleName() + "Repository.java";
		File file = new File(new File("src/main/java"), filepath);
		if (file.exists()) {
			logger.warn(file.toString() + " exists");
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("repo.ftl");
		Map<String, String> root = new HashMap<String, String>();
		root.put("pkg", servicepkg);
		root.put("file", filepath);
		root.put("className", cls.getSimpleName());
		root.put("importclass", cls.getName());
		Writer out = new FileWriter(file);
		template.process(root, out);
		out.flush();
	}

	public static void service(Configuration cfg, Class<?> cls) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String servicepkg = cls.getPackage().getName().substring(0, cls.getPackage().getName().lastIndexOf("."));
		String filepath = pkg2path(servicepkg) + "/service/" + cls.getSimpleName() + "Service.java";
		File file = new File(new File("src/main/java"), filepath);
		if (file.exists()) {
			logger.warn(file.toString() + " exists");
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("service.ftl");
		Map<String, String> root = new HashMap<String, String>();
		root.put("pkg", servicepkg);
		root.put("file", filepath);
		root.put("className", cls.getSimpleName());
		root.put("importclass", cls.getName());
		Writer out = new FileWriter(file);
		template.process(root, out);
		out.flush();
	}

	public static String pkg2path(String pkg) {
		return pkg.replace(".", "/");
	}
}
