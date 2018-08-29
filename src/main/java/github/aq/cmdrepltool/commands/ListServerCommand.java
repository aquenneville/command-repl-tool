package github.aq.cmdrepltool.commands;

import github.aq.cmdrepltool.configuration.ServerPoolController;
import github.aq.cmdrepltool.model.TemplateCommand;
import github.aq.cmdrepltool.model.cloud.Server;
import github.aq.cmdrepltool.model.metadata.MethodMetaData;

public class ListServerCommand extends TemplateCommand {

    @MethodMetaData(name="ls-sv", arguments="", description="list the servers", usage="list server")
    @Override
    public Object executeCommand() {
        int cpt = 0;
        for (Server server : ServerPoolController.getServers()) {
            System.out.println("|" + cpt + "|" + server.getPublicIpAddress() + " " + server.getMacAddress());
        }
        return null;
    }

}
