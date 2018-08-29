package github.aq.cmdrepltool.model.cloud;

public class Server {

    private String serverName;
    private String publicIpAddress;
    private String macAddress;
    private String username;
    private String password;
    private String privateKeyFile;
    
    private Server(Builder builder) {
        serverName = builder.newServerName;
        publicIpAddress = builder.newPublicIpAddress;
        macAddress = builder.newMacAddress;
        username = builder.newUsername;
        password = builder.newPassword;
        privateKeyFile = builder.newPrivateKeyFile;
    }
    
    public String getServerName() {
        return serverName;
    }

    public String getPublicIpAddress() {
        return publicIpAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPrivateKeyFile() {
        return privateKeyFile;
    }

    public static class Builder {

        private String newServerName;
        private String newPublicIpAddress;
        private String newMacAddress;
        private String newUsername;
        private String newPassword;
        private String newPrivateKeyFile;
        
        public Builder setNewServerName(String newServerName) {
            this.newServerName = newServerName;
            return this;
        }

        public Builder setNewPublicIpAddr(String newPublicIpAddress) {
            this.newPublicIpAddress = newPublicIpAddress;
            return this;
        }

        public Builder setNewMacAddress(String newMacAddress) {
            this.newMacAddress = newMacAddress;
            return this;
        }

        public Builder setNewUsername(String newUsername) {
            this.newUsername = newUsername;
            return this;
        }

        public Builder setNewPassword(String newPassword) {
            this.newPassword = newPassword;
            return this;
        }

        public Builder setNewPrivateKeyFile(String newPrivateKeyFile) {
            this.newPrivateKeyFile = newPrivateKeyFile;
            return this;
        }

        public Server create(String serverElmt) {
            return new Server(this);
        }

    }

}
