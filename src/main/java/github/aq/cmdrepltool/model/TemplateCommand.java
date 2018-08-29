package github.aq.cmdrepltool.model;

import java.io.IOException;

public abstract class TemplateCommand implements CommandI {

	protected String commandName;
	protected String[] arguments;
	protected String externalProcessCommand;
	protected int exitValue;
	
	@Override
	public CommandResponse eval() {
		// TODO Auto-generated method stub
		CommandResponse response = new CommandResponse();
		long startExecution = System.currentTimeMillis();
		//CommandResponseData data = executeCommand();
		Object data = executeCommand();
		//response.setCommandResponseData(data);
		response.setData(data);
		long duration = System.currentTimeMillis() - startExecution;
		response.setCommandExecutionTimeInMs(duration);
		return response;
	}
	
	//public abstract CommandResponseData executeCommand();
	public abstract Object executeCommand();
	
	@Override
	public void setArguments(String[] args) {
		// TODO Auto-generated method stub
		arguments = args;
	}

	public String getExternalProcessCommand() {
		return externalProcessCommand;
	}

	public void setExternalProcessCommand(String externalProcessCommand) {
		this.externalProcessCommand = externalProcessCommand;
	}

	public int getExitValue() {
		return exitValue;
	}

	public void setExitValue(int exitValue) {
		this.exitValue = exitValue;
	}

	public String[] getArguments() {
		return arguments;
	}
	
	public void executeExternalProcess() {
		try {
			exitValue = Runtime.getRuntime().exec(externalProcessCommand).exitValue();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	public String getCommandName() {
		return commandName;
	}
	
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
}
