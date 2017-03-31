import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PackageConvert {
    public static void main(String[] args) throws IOException {
//		 String basePath =
//		 "/Developer/gitsource/castle-platform/castle-framework";
//		 String distPath =
//		 "/Users/lig/Documents/workspace/play-platform/play-framework";

//		String basePath = "/Developer/gitsource/castle-platform/castle-module";
//		String distPath = "/Users/lig/Documents/workspace/play-platform/play-module";
        String basePath = "/Developer/gitsource/castle-platform/castle-vendor";
        String distPath = "/Users/lig/Documents/workspace/play-platform/play-vendor";

        listFie(new File(basePath), basePath, distPath);

    }

    public static void listFie(File p, String basePath, String destPath)
            throws IOException {
        File[] files = p.listFiles();

        for (File file : files) {

            if (file.isDirectory()) {
                listFie(file, basePath, destPath);
            } else {
                System.out.println(file.getAbsolutePath());
                String path = file.getAbsolutePath();
                if (!path.contains("/target/")
                        && !path.contains("/.settings/")
                        && (path.endsWith(".xml") || path.endsWith(".java") || path
                        .endsWith(".gitignore"))) {
                    String endPath = path.substring(basePath.length());
                    String resultPath = destPath
                            + endPath.replace("castle", "play")
                            .replace("whenling", "airshiplay")
                            .replace("Castle", "Play");
                    System.out.println(resultPath);
                    renameFile(file, new File(resultPath));
                }

            }

        }

    }

    public static void renameFile(File source, File dest) throws IOException {
        dest.getParentFile().mkdirs();
        dest.createNewFile();
        BufferedReader reader = new BufferedReader(new FileReader(source));
        BufferedWriter writer = new BufferedWriter(new FileWriter(dest));
        String line = null;
        while ((line = reader.readLine()) != null) {

            writer.write(line.replace("castle-platform", "play-parent")
                    .replace("com.whenling", "com.airshiplay")
                    .replace("castle", "play").replace("孔祥溪", "")
                    .replace("Castle", "Play").replace("/home/mdmwebsite", "/home/website")
                    .replace("mdm.whenling.com", "127.0.0.1:8080")
                    .replace("com/whenling/", "com/airshiplay/"));
            writer.write("\n");
        }
        writer.flush();
        writer.close();
    }
}
