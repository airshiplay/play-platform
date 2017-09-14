package com.airshiplay.play.maven;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

@Mojo(name = "webappadminlte")
public class WebappAdminlteMojo extends AbstractPlayMojo {

	@Parameter(property = "project", required = true, readonly = true)
	private MavenProject project;

	@Parameter(defaultValue = "${project.basedir}/src/main/java", property = "sourceDir", required = true)
	private File sourceDirectory;

	@Parameter(defaultValue = "${project.basedir}/src/main/resources", property = "resourceDir", required = true)
	private File resourceDirectory;

	@Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
	private File outputDirectory;

	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("webapp code file generator start.....");
		try {
			List<Map<String, Object>> list = getEntityList();
			Configuration cfg = getConfiguration();
			for (Map<String, Object> map : list) {
				controller(cfg, map);
				htmlList(cfg, map);
				htmlForm(cfg, map);
				htmlView(cfg, map);
			}
		} catch (TemplateNotFoundException e) {
			throw new MojoFailureException(e.getMessage());
		} catch (MalformedTemplateNameException e) {
			throw new MojoFailureException(e.getMessage());
		} catch (ParseException e) {
			throw new MojoFailureException(e.getMessage());
		} catch (IOException e) {
			throw new MojoFailureException(e.getMessage());
		} catch (TemplateException e) {
			throw new MojoFailureException(e.getMessage());
		}
		getLog().info("webapp code file generator end.....");
	}

	public void controller(Configuration cfg, Map<String, Object> map) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {

		String shortSimpleName = map.get("shortSimpleName").toString();
		String filepath = pkg2path(map.get("modulePackage").toString()) + "/controller/" + shortSimpleName + "Controller.java";
		File file = new File(sourceDirectory, filepath);
		if (file.exists()) {
			getLog().info("exists file=" + filepath);
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("webappadminlte/controller.ftl");
		Writer out = new FileWriter(file);
		template.process(map, out);
		out.flush();
		getLog().info("new file=" + filepath);
	}

	private void htmlList(Configuration cfg, Map<String, Object> map) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String shortSimpleName = map.get("shortSimpleName").toString();

		String lowerShortClassName = shortSimpleName.substring(0, 1).toLowerCase() + shortSimpleName.substring(1, shortSimpleName.length());
		String filepath = "/META-INF/templates/" + map.get("moduleName").toString() + "/" + lowerShortClassName + "/" + lowerShortClassName + "List.html";
		File file = new File(resourceDirectory, filepath);
		if (file.exists()) {
			getLog().info("exists file=" + filepath);
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("webappadminlte/list.ftl");
		Writer out = new FileWriter(file);
		template.process(map, out);
		out.flush();
		getLog().info("new file=" + filepath);
	}

	private void htmlForm(Configuration cfg, Map<String, Object> map) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String shortSimpleName = map.get("shortSimpleName").toString();
		String lowerShortClassName = shortSimpleName.substring(0, 1).toLowerCase() + shortSimpleName.substring(1, shortSimpleName.length());

		String filepath = "/META-INF/templates/" + map.get("moduleName").toString() + "/" + lowerShortClassName + "/" + lowerShortClassName + "Form.html";
		File file = new File(resourceDirectory, filepath);
		if (file.exists()) {
			getLog().info("exists file=" + filepath);
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("webappadminlte/form.ftl");
		Writer out = new FileWriter(file);
		template.process(map, out);
		out.flush();
		getLog().info("new file=" + filepath);
	}

	private void htmlView(Configuration cfg, Map<String, Object> map) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String shortSimpleName = map.get("shortSimpleName").toString();
		String lowerShortClassName = shortSimpleName.substring(0, 1).toLowerCase() + shortSimpleName.substring(1, shortSimpleName.length());

		String filepath = "/META-INF/templates/" + map.get("moduleName").toString() + "/" + lowerShortClassName + "/" + lowerShortClassName + "View.html";
		File file = new File(resourceDirectory, filepath);
		if (file.exists()) {
			getLog().info("exists file=" + filepath);
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("webappadminlte/view.ftl");

		Writer out = new FileWriter(file);
		template.process(map, out);
		out.flush();
		getLog().info("new file=" + filepath);
	}

	public File getSourceDirectory() {
		return new File(sourceDirectory.getAbsolutePath().replace("webapp-adminlte", "business"));
	}

	@Override
	public File getOutDirectory() {
		return sourceDirectory;
	}

}
