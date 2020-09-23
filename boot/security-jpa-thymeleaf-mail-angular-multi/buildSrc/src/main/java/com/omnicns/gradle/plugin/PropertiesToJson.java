package com.omnicns.gradle.plugin;

import com.google.gson.Gson;
import org.gradle.api.DefaultTask;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
//import org.gradle.internal.impldep.com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import javax.inject.Inject;
import java.io.*;
import java.util.*;

public class PropertiesToJson extends DefaultTask {
    // Use an abstract getter and setter method
//    @Input
//    abstract URI getUri();
//    abstract void setUri(URI uri);


    // A nested instance
//    private final Resource resource;
//
//    @Inject
//    public PropertiesToJson(ObjectFactory objectFactory) {
//        // Use an injected ObjectFactory to create a Resource object
//        resource = objectFactory.newInstance(Resource.class);
//    }
//
//    public Resource getResource() {
//        return resource;
//    }
    @Input
    String sourceDirPath = getProject().getProjectDir().toString();

    @Input
    String destDirPath = getProject().getProjectDir().toString();

    @TaskAction
    void run() throws IOException {
        System.out.println("PropertiesToJson start");
//        Task t = p.getTasks().create("generateCode", GenerateCodeTask.class);
//        t.dependsOn(p.getTasks().getByPath("compileJava"));
        // Use the `uri` property
//        Project project = getProject();
//        Project targetProject = project.project(":web-core-angular");
//        System.out.println(project.getProjectDir());
//        System.out.println(targetProject.getProjectDir());

//        ResourceBundle b = ResourceBundle.getBundle("messages.message");
        File msgDir = new File(sourceDirPath);
        File[] files = msgDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File it = files[i];
            System.out.println(it);
            Properties props = new Properties();
            props.load(new FileInputStream(it));

            File destDirPathFile = new File(destDirPath);
            destDirPathFile.mkdirs();
//            Writer writer = new FileWriter(new File(destDirPathFile, FilenameUtils.removeExtension(it.getName()) + ".json"));
            Gson gsonObj = new Gson();
//            gsonObj.toJson(props, writer);
            String jsonStr = gsonObj.toJson(props);
//            System.out.println(jsonStr);
//            BufferedWriter bwriter = new BufferedWriter(writer);
//            bwriter.write(jsonStr);
//            writer.close();

            // fileWriter로 쓰면 버퍼떄문인지 안쪽 데이터 문제인지 모르겠지만 다 안써진다.
            // https://baejangho.com/entry/Java-save-String-to-file  자바 스트링 wirte  하는방법
            FileOutputStream outputStream = new FileOutputStream(new File(destDirPathFile, FilenameUtils.removeExtension(it.getName()) + ".json"));
            byte[] strToBytes = jsonStr.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
        }
//        String enPath = project.getProjectDir() + "/src/main/resources/messages/message_en_US.properties";
//        String koPath = project.getProjectDir() + "/src/main/resources/messages/message_ko_KR.properties";
//        InputStream koInputStream = new FileInputStream(koPath);
//
//        // 프로퍼티 객체 생성
//        Properties koProps = new Properties();
//        koProps.load(enInputStream);
//        Properties enProps = new Properties();
//        enProps.load(koInputStream);




//        Gson gsonObj = new Gson();
//        String koJson =  gsonObj.toJson(koProps);
//        String enJson =  gsonObj.toJson(enProps);
//
//
//        System.out.println(koJson);
//        System.out.println(enJson);
//        Set<File> ccp = project.getConfigurations().getByName("compileClasspath").getFiles();
//        System.out.println(ccp);
//        System.out.println(project.getProjectDir());
//        task.classpath = ccp;

//        ClassPathResource resource = new ClassPathResource("data/data.txt");

    }



    private static Map<String, String> resourceBundleToMap(final ResourceBundle bundle) {
        final Map<String, String> bundleMap = new HashMap<>();

        for (String key: bundle.keySet()) {
            final String value = bundle.getString(key);

            bundleMap.put(key, value);
        }

        return bundleMap;
    }
}
