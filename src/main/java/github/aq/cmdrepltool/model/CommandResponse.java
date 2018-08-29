package github.aq.cmdrepltool.model;

public class CommandResponse {

	private String commandName;
	private long commandExecutionTimeInMs;
	private int status;
	private String message;
	//private CommandResponseDataI responseData;
	private Object data; 

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public long getCommandExecutionTimeInMs() {
		return commandExecutionTimeInMs;
	}

	public void setCommandExecutionTimeInMs(long commandExecutionTimeInMs) {
		this.commandExecutionTimeInMs = commandExecutionTimeInMs;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
	
}
