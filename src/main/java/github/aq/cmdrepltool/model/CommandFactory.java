package github.aq.cmdrepltool.model;

import java.util.Map;

import github.aq.cmdrepltool.configuration.ConfigurationProperties;
import github.aq.cmdrepltool.model.metadata.CommandMetaData;

public class CommandFactory {
	
	public static CommandI create(InputTokens input) throws IllegalArgumentException {
		
		CommandI com = null;
		String commandName = input.getCommandName();
		for (Map.Entry<String, CommandMetaData> entry: ConfigurationProperties.getCommandMethodDictionary().entrySet()) {
			if (commandName.equals(entry.getKey())) {
				try {
					@SuppressWarnings("unchecked")
					Class<? extends TemplateCommand> commandHardClass = (Class<? extends TemplateCommand>) Class.forName(entry.getValue().getCommandClass().getName());
					com = (CommandI) commandHardClass.newInstance();
					if (input.hasArguments()) {
						com.setArguments(input.getArguments());
					}
				} catch(ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
					throw new IllegalStateException("");
				}
			}
		}
		if (com == null) {
			throw new IllegalArgumentException("Error: unknown command: " + input);
		}
		return com;
	}
}
