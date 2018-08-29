package github.aq.cmdrepltool.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import github.aq.cmdrepltool.configuration.ConfigurationProperties;
import github.aq.cmdrepltool.model.TemplateCommand;
import github.aq.cmdrepltool.model.metadata.CommandMetaData;
import github.aq.cmdrepltool.model.metadata.MethodMetaData;

public class HelpCommand extends TemplateCommand {

    public List<String> help() {
        List<String> helpMessages = new ArrayList<String>();
        Map<String, CommandMetaData> commandMethodDictionary = ConfigurationProperties
                .getCommandMethodDictionary();
        for (Map.Entry<String, CommandMetaData> entry : commandMethodDictionary
                .entrySet()) {
            CommandMetaData m = entry.getValue();
            MethodMetaData mh = m.getMethod()
                    .getAnnotation(MethodMetaData.class);
            helpMessages.add(entry.getKey() + ": " + mh.description());
        }
        return helpMessages;
    }

    @Override
    @MethodMetaData(name = "help", arguments = "", description = "print the help", usage = "help")
    public Object executeCommand() {
        return help();
    }
}
