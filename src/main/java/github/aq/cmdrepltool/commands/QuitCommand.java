package github.aq.cmdrepltool.commands;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import github.aq.cmdrepltool.model.TemplateCommand;
import github.aq.cmdrepltool.model.metadata.MethodMetaData;

public class QuitCommand extends TemplateCommand {

    @Override
    @MethodMetaData(name="quit", arguments="", description="quit the application", usage="quit")
    public Object executeCommand() {
        ScheduledExecutorService scheduler = Executors
                .newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            public void run() {
                System.exit(0);
            }
        };

        int delay = 200;
        scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);
        scheduler.shutdown();
        return "Byebye!";
    }

}
