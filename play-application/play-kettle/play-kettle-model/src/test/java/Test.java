import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.*;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.filerep.KettleFileRepository;
import org.pentaho.di.repository.filerep.KettleFileRepositoryMeta;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMetaInterface;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lig on 17/8/7.
 */
public class Test {

    public static void main(String[] args) {
//        databaseRepository();
        try {
            System.setProperty("KETTLE_PLUGIN_BASE_FOLDERS","/Users/lig/Documents/workspace/play-application/play-kettle/src/main/resources/plugins");
            KettleEnvironment.init(false);
//            List<PluginTypeInterface> pluginTypes = PluginRegistry.getAddedPluginTypes();
//            EnvUtil.environmentInit();
            //转换
            URL resource = Test.class.getClassLoader().getResource("test1.ktr");

            TransMeta transMeta = new TransMeta(resource.getFile());

            Trans trans = new Trans(transMeta);
            trans.execute(null);
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                System.out.println("error");
            }

            //作业
            JobMeta jobMeta = new JobMeta(Test.class.getClassLoader().getResource("testjob.kjb").getFile(), null);
            Job job = new Job(null, jobMeta);
            //向Job 脚本传递参数，脚本中获取参数值：${参数名}
            //job.setVariable(paraname, paravalue);
            job.start();
            job.waitUntilFinished();
            if (job.getErrors() > 0) {
                System.out.println("decompress fail!");
            }
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库仓库
     */
    public static void databaseRepository() {
        try {
            KettleEnvironment.init(false);
            Map<Class<?>,String> anotherClassMap=new HashMap<Class<?>,String>();
// anotherMeta 就是开发的插件的Meta类了
//            anotherClassMap.put(StepMetaInterface.class,KafkaProducerMeta.class.getName());
            PluginInterface anotherPlugin=new Plugin(
                    new String[]{"MergeField"},
                    StepPluginType.class,
                    StepMetaInterface.class,
                    "转换",
                    "插件的名字",
                    "插件的描述，不影响java执行",
                    "插件的图片，不影响java执行",
                    false,
                    false,
                    anotherClassMap,
                    null,
                    null,
                    null);
            PluginRegistry.getInstance().registerPlugin(StepPluginType.class,anotherPlugin);
            KettleDatabaseRepository repo = new KettleDatabaseRepository();
            repo.init(new KettleDatabaseRepositoryMeta("", "", "", new DatabaseMeta("", "MySQL", "Native(JDBC)", "172.16.26.133", "db-kettle", "3306",
                    "root", "123456")));

            repo.connect("admin", "admin");
            //根据变量查找到模型所在的目录对象,此步骤很重要。
            RepositoryDirectoryInterface directory = repo.findDirectory("/");
            //创建ktr元对象
            TransMeta transMeta = ((Repository) repo).loadTransformation("test", directory, null, true, null);


            Trans trans = new Trans(transMeta);
            trans.execute(null);
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                System.out.println("error");
            }
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件仓库
     */
    public static void fileRepository(){
        try {
            KettleEnvironment.init(false);
           KettleFileRepository repo=new KettleFileRepository();
           repo.init(new KettleFileRepositoryMeta("","","描述","/Users/lig/Downloads/data-integration"));
           TransMeta transMeta = repo.loadTransformation(repo.getTransformationID("test", null), null);
            Trans trans = new Trans(transMeta);
            trans.execute(null);
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                System.out.println("error");
            }
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }
}
