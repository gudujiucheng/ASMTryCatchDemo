package com.addtrycatch;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;


public class AddTryCatchPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        boolean disablePlugin = false;
        Properties properties = new Properties();
        if (project.getRootProject().file("gradle.properties").exists()) {
            try {
                properties.load(new FileInputStream(project.getRootProject().file("gradle.properties")));
                disablePlugin = Boolean.parseBoolean(properties.getProperty("tryCatch.disablePlugin", "false"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("tryCatch  exception" + e.getMessage());
            }

        }
        Config.getInstance().extension = project.getExtensions().create("addTryCatch", AddTryCatchExtension.class);
        if (disablePlugin) {
            System.out.println("-------------->>>>>>>>>>>>>you open tryCatch plugin<<<<<<<<<<<<---------------------");
        } else {
            System.out.println("-------------->>>>>>>>>>>>>you close tryCatch plugin<<<<<<<<<<<<---------------------");
            return;
        }
        AppExtension appExtension = (AppExtension) project.getProperties().get("android");
        appExtension.registerTransform(new AddTryCatchTransform(project), Collections.EMPTY_LIST);
    }
}