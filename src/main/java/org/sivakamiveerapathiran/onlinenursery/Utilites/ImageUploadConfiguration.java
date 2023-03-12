package org.sivakamiveerapathiran.onlinenursery.Utilites;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the methods for mapping an external file location to the application. This application has functionalities
 * to upload an image and display them dynamically. Since the resources folder is static build, the images are not loaded dynamically.
 * Methods:
 * addResourceHandlers - user to map an external folder as resourcehandler to the application
 ***************************/
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
@Slf4j
@Configuration
public class ImageUploadConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        String dirName="src/main/upload";
        log.info("dirName: "+dirName);
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
        log.info("dirName After Change: "+dirName);
          registry.addResourceHandler("/upload/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
}
