package cz.upce.webalyt.plugin.core;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceController {

    @Autowired
    private ResourcePatternResolver resourceResolver;

    @Autowired
    private WebalytPluginConfig webalytPluginConfig;

    @RequestMapping(value = "/")
    public WebalytPluginConfig getPluginConfiguration() {
        return webalytPluginConfig;
    }

    @RequestMapping(value = "/files")
    public List<String> getAllFiles() {
        List<String> files = new ArrayList<>();
        try {
            for (Resource resource : resourceResolver.getResources("classpath:webalyt/*")) {
                files.add(resource.getFilename());
            }
            return files;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/files/{fileName:.+}")
    public String getFile(@PathVariable String fileName) {
        try {
            return readFileFromClassPath("/webalyt/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    static String readFileFromClassPath(String path) throws IOException {
        InputStream stream = null;
        String s;
        try {
            stream = new ClassPathResource(path).getInputStream();
            s = IOUtils.toString(stream);
        } finally {
            IOUtils.closeQuietly(stream);
        }
        return s;
    }
}
