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
import java.util.HashMap;
import java.util.Map;

/**
 * @author airlenet
 * @version 2017-09-13
 */
@Mojo(name = "new")
public class NewModuleMojo  extends AbstractPlayMojo{
    @Parameter(property = "project", required = true, readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "${project.basedir}/src/main/java", property = "sourceDir", required = true)
    private File sourceDirectory;

    @Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
    private File outputDirectory;
    public File getSourceDirectory() {
        return null;
    }

    public File getOutDirectory() {
        return null;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        File basedir = project.getBasedir();



        String moduleName=System.getProperty("module");

        File business = new File(basedir,"play-"+moduleName+"-business");
        File businessPom = new File(business,"pom.xml");
        if(business.exists()&&businessPom.exists()){
            return;
        }

        File webapp = new File(basedir,"play-"+moduleName+"-webapp");

        if(webapp.exists()){
            return;
        }


        Configuration configuration = getConfiguration();


        try {
            pom(configuration,moduleName);
        } catch (IOException e) {
            throw new MojoFailureException(e.getMessage());
        } catch (TemplateException e) {
            throw new MojoFailureException(e.getMessage());
        }
    }

    public void pom(Configuration
                                   cfg,String moduleName) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException
    {
        Map map = new HashMap();

        map.put("moduleName",moduleName);
        File basedir = project.getBasedir();
        File business = new File(basedir,"play-"+moduleName+"-business");
        File businessPom = new File(business,"pom.xml");
        File webapp = new File(basedir,"play-"+moduleName+"-webapp");
        File webappPom = new File(webapp,"pom.xml");

        File webappadminlte = new File(basedir,"play-"+moduleName+"-webapp-adminlte");
        File webappadminltePom = new File(webappadminlte,"pom.xml");
        /***
         *
         */
        File businessJava = new File(business, "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator+ "airshiplay" + File.separator+ "play" + File.separator+ moduleName);
        businessJava.mkdirs();
        new File(businessJava,"entity").mkdirs();
        new File(businessJava,"repo").mkdirs();
        new File(businessJava,"service").mkdirs();

        File businessResource = new File(business, "src" + File.separator + "main" + File.separator + "resources");
        businessResource.mkdirs();

        businessPom.getParentFile().mkdirs();
        freemarker.template.Template template = cfg.getTemplate("new/businesspom.ftl");
        Writer out = new FileWriter(businessPom);
        template.process(map, out);
        out.flush();
        getLog().info("new file=" + businessPom);


        /***
         *
         */
        File webappJava = new File(webapp, "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator+ "airshiplay" + File.separator+ "play" + File.separator+ moduleName);
        webappJava.mkdirs();
        new File(webappJava,"rest").mkdirs();
        File webappResource = new File(webapp, "src" + File.separator + "main" + File.separator + "resources");
        webappResource.mkdirs();


        webappPom.getParentFile().mkdirs();
        freemarker.template.Template templatewebapp = cfg.getTemplate("new/webapppom.ftl");
        Writer outwebapp = new FileWriter(webappPom);
        templatewebapp.process(map, outwebapp);
        out.flush();
        getLog().info("new file=" + webappPom);

        /**
         *
         */
        File webappadminlteJava = new File(webappadminlte, "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator+ "airshiplay" + File.separator+ "play" + File.separator+ moduleName);
        webappadminlteJava.mkdirs();
        new File(webappadminlteJava,"controller").mkdirs();
        File webappadminlteResource = new File(webappadminlte, "src" + File.separator + "main" + File.separator + "resources");
        webappadminlteResource.mkdirs();
        new File(webappadminlteResource,"META-INF" + File.separator +"templates" + File.separator +moduleName).mkdirs();

        webappPom.getParentFile().mkdirs();
        freemarker.template.Template templatewebappadminlte = cfg.getTemplate("new/webappadminltepom.ftl");
        Writer outwebappadminlte = new FileWriter(webappadminltePom);
        templatewebappadminlte.process(map, outwebappadminlte);
        out.flush();
        getLog().info("new file=" + webappadminltePom);

    }
}
