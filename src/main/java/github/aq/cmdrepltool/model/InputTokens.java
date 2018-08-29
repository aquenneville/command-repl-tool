package github.aq.cmdrepltool.model;

public class InputTokens {

    private String commandName;
    private String[] arguments;
    
    public InputTokens(String input) {
        int splitCommandPosition = input.indexOf(" ");
        String args = "";
        if (splitCommandPosition > 0) {
            commandName = input.substring(0, splitCommandPosition);
            args = input.substring(splitCommandPosition, input.length());
            arguments = args.split(" ");
        } else {
            commandName = input.trim();
        }
    }

    public String getCommandName() {
        return commandName;
    }

    public boolean hasArguments() {
        return arguments != null;
    }
    
    public String[] getArguments() {
        return arguments;
    }
    
    
}
