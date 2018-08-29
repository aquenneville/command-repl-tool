package github.aq.cmdrepltool.model;

import java.util.List;

public interface CommandI {

	public CommandResponse eval();
	public void setArguments(String[] args);
	public String getCommandName();
}
