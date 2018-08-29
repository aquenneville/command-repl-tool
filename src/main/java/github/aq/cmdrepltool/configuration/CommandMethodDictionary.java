package github.aq.cmdrepltool.configuration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import github.aq.cmdrepltool.model.TemplateCommand;
import github.aq.cmdrepltool.model.metadata.CommandMetaData;
import github.aq.cmdrepltool.model.metadata.MethodMetaData;

public class CommandMethodDictionary {

    
    /**
     * Scans all classes accessible from the context class loader which belong
     * to the given package and subpackages.
     * 
     * @param packageName
     *            The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws URISyntaxException 
     */
    public static Map<String, CommandMetaData> getCommandClasses(String packageName) throws ClassNotFoundException, IOException, URISyntaxException
    {
        Map<String, CommandMetaData> methodDictionary = new HashMap<String, CommandMetaData>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            URI uri = new URI(resource.toString());
            dirs.add(new File(uri.getPath()));
        }
        List<Class<? extends TemplateCommand>> classes = new ArrayList<Class<? extends TemplateCommand>>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        for (Class<? extends TemplateCommand> hardClass: classes) {
            for (Method method : hardClass.getMethods()) {
                if (method.isAnnotationPresent(MethodMetaData.class)) {
                    MethodMetaData mh = method.getAnnotation(MethodMetaData.class);
                    CommandMetaData cmdmd = new CommandMetaData();
                    cmdmd.setMethod(method);
                    cmdmd.setCommandClass(hardClass);                   
                    methodDictionary.put(mh.name(), cmdmd);
                }
            }
        }
        return methodDictionary;
    }
    
    /**
     * Recursive method used to find all classes in a given directory and
     * subdirs.
     * 
     * @param directory
     *            The base directory
     * @param packageName
     *            The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private static List<Class<? extends TemplateCommand>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<? extends TemplateCommand>> classes = new ArrayList<Class<? extends TemplateCommand>>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add((Class<? extends TemplateCommand>) Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }        
        return classes;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, IOException, URISyntaxException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        HashMap<String, CommandMetaData> methodDictionary = (HashMap<String, CommandMetaData>) CommandMethodDictionary.getCommandClasses("github.aq.cmdrepltool.commands");
        for (Map.Entry<String, CommandMetaData> entry: methodDictionary.entrySet()) {           
            CommandMetaData m = entry.getValue();
            MethodMetaData mh = m.getMethod().getAnnotation(MethodMetaData.class);
            System.out.println(entry.getKey() + ": " + mh.description());
            //m.getMethod().invoke(new CmdListServers());
        }
    }
}
