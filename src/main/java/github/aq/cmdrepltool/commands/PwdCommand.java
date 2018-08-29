package github.aq.cmdrepltool.commands;

import java.util.Arrays;

import github.aq.cmdrepltool.configuration.ConfigurationProperties;
import github.aq.cmdrepltool.model.TemplateCommand;
import github.aq.cmdrepltool.model.metadata.MethodMetaData;

public class PwdCommand extends TemplateCommand {

	@MethodMetaData(name="pwd", arguments="", description="print current working directory", usage="pwd")
	@Override
	public Object executeCommand() {
		String response = new String("pwd: " + ConfigurationProperties.getProperty("pwd"));
		return Arrays.asList(response);
	}

}
