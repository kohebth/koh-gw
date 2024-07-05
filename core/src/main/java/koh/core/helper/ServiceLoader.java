package koh.core.helper;

import koh.core.base.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.NotDirectoryException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ServiceLoader {
    @SuppressWarnings("unchecked")
    public static Set<Class<? extends Controller>> getServiceSubclasses(String packageName)
            throws NotDirectoryException, FileNotFoundException, URISyntaxException, ClassNotFoundException {
        Set<Class<? extends Controller>> serviceClasses = new HashSet<>();
        String path = packageName.replace('.', '/');

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // Get the resource URL for the package
        URL resource = requireResourceUrl(classLoader.getResource(path));
        File directory = requireExistedDirectory(resource.toURI());

        File[] files = requireFilesArray(directory.listFiles((dir, name) -> name.endsWith(".class")));

        for (File file : files) {
            String className = packageName + '.' + file.getName().replace(".class", "");
            Class<?> cls = Class.forName(className);
            if (Controller.class.isAssignableFrom(cls) && !cls.isInterface() && !Modifier.isAbstract(
                    cls.getModifiers())) {

                serviceClasses.add((Class<? extends Controller>) cls);
            }
        }
        return serviceClasses;
    }

    static URL requireResourceUrl(URL resourceUrl) {
        return Objects.requireNonNull(resourceUrl, "Unable to find the located package");
    }

    static File requireExistedDirectory(URI uri)
            throws FileNotFoundException, NotDirectoryException {
        File file = new File(uri);
        if (!file.exists()) {
            throw new FileNotFoundException(uri.toString());
        }
        if (!file.isDirectory()) {
            throw new NotDirectoryException(uri.toString());
        }
        return file;
    }

    static File[] requireFilesArray(File[] files) {
        return Objects.requireNonNull(files, "Unable to load classes at package");
    }

    private ServiceLoader() {
    }
}
