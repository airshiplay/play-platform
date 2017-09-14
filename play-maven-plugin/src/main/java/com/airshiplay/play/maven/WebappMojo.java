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

@Mojo(name = "webapp")
public class WebappMojo extends AbstractPlayMojo {

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
		String filepath = pkg2path(map.get("modulePackage").toString()) + "/rest/" + shortSimpleName + "RestController.java";
		File file = new File(sourceDirectory, filepath);
		if (file.exists()) {
			getLog().info("exists file=" + filepath);
			return;
		}
		file.getParentFile().mkdirs();
		freemarker.template.Template template = cfg.getTemplate("webapp/controller.ftl");
		Writer out = new FileWriter(file);
		template.process(map, out);
		out.flush();
		getLog().info("new file=" + filepath);
	}


	public File getSourceDirectory() {
		return new File(sourceDirectory.getAbsolutePath().replace("webapp", "business"));
	}

	@Override
	public File getOutDirectory() {
		return sourceDirectory;
	}

}
