package github.aq.cmdrepltool.model.metadata;

import java.lang.reflect.Method;

import github.aq.cmdrepltool.model.TemplateCommand;

public class CommandMetaData {

	private Method method;
	private Class<? extends TemplateCommand> commandClass;
	private String args;
	
	public Class<? extends TemplateCommand> getCommandClass() {
		return commandClass;
	}

	public Method getMethod() {
		// TODO Auto-generated method stub
		return method;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public void setCommandClass(Class<? extends TemplateCommand> commandClass) {
		this.commandClass = commandClass;
	}

}
