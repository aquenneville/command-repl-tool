package github.aq.cmdrepltool.configuration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import github.aq.cmdrepltool.model.metadata.CommandMetaData;

public class ConfigurationProperties {
	private static Map<String, CommandMetaData> commandMethodDictionary;

	public ConfigurationProperties() {
	    try {
            commandMethodDictionary = CommandMethodDictionary.getCommandClasses("github.aq.cmdrepltool.commands");
        } catch (ClassNotFoundException | IOException | URISyntaxException e) {
            System.err.println("Error - cannot find any command classes");
        }
	}
	
	public static Map<String, CommandMetaData> getCommandMethodDictionary() {
		return commandMethodDictionary;
	}

	public static void setCommandMethodDictionary(Map<String, CommandMetaData> cmdMethodDictionary) {
		ConfigurationProperties.commandMethodDictionary = cmdMethodDictionary;
	}

	public static String getProperty(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
    
	
}
