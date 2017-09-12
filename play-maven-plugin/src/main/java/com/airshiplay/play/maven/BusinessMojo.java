package com.airshiplay.play.maven;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Mojo(name = "business")
public class BusinessMojo extends AbstractPlayMojo {

	@Parameter(property = "project", required = true, readonly = true)
	private MavenProject project;

	@Parameter(defaultValue = "${project.basedir}/src/main/java", property = "sourceDir", required = true)
	private File sourceDirectory;

	@Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
	private File outputDirectory;

	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("business code file generator start.....");
		try {
			List<Map<String, Object>> list = getEntityList();
			Configuration cfg = getConfiguration();
			for (Map<String, Object> map : list) {
				repo(cfg, map);
				service(cfg, map);
			}
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		getLog().info("business code file generator end.....");
	}

	public void repo(Configuration cfg, Map<String, Object> map) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String filepath = pkg2path(map.get("modulePackage").toString()) + "/repo/" + map.get("simpleName") + "Repository.java";
		File file = new File(getOutDirectory(), filepath);
		if (file.exists()) {
			getLog().info("exists file=" + filepath);
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("business/repo.ftl");
		Writer out = new FileWriter(file);
		template.process(map, out);
		out.flush();
		getLog().info("new file=" + filepath);
	}

	public void service(Configuration cfg, Map<String, Object> map) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String filepath = pkg2path(map.get("modulePackage").toString()) + "/service/" + map.get("simpleName") + "Service.java";
		File file = new File(getOutDirectory(), filepath);
		if (file.exists()) {
			getLog().info("exists file=" + filepath);
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("business/service.ftl");
		Writer out = new FileWriter(file);
		template.process(map, out);
		out.flush();
		getLog().info("new file=" + filepath);
	}

	public File getSourceDirectory() {
		return sourceDirectory;
	}

	@Override
	public File getOutDirectory() {
		return sourceDirectory;
	}

}
