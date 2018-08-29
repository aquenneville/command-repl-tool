package github.aq.cmdrepltool.configuration;

import java.util.ArrayList;
import java.util.List;

import github.aq.cmdrepltool.model.cloud.Server;



public class ServerPoolController {

    public static List<Server> servers = new ArrayList<Server>();
    
    public static void add(String serverElmt) {
        Server vm = new Server.Builder().create(serverElmt);
        if (!find(vm.getServerName())) {
            servers.add(new Server.Builder().create(serverElmt));
        }
    }

    public static boolean find(String serverName) {
        boolean result = false;
        if (serverName != null && serverName.length() > 0) {
            int i = 0;
            while (i < servers.size() && !serverName.equals(((Server)servers.get(i)).getServerName())) {
                i ++;
            }
            if (i < servers.size()) {
                result = true;
            }
        }
        return result;
    }
    
    public static void clear() {
        servers.clear();
    }

    public static List<Server> getServers() {
        return servers;
    }

    public static Server[] getServersArray() {
        return servers.toArray(new Server[servers.size()]);
    }

    public static String get(int pos) {
        return servers.get(pos).toString();
    }
}
