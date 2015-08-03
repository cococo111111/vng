package banking.gatewayweb.server;

public class BankingDaemon {

    public static void main(String[] args) {

        try {
            BankingServer webserver = new BankingServer();
            webserver.start();
        } catch (Exception e) {
            System.out.println("Ex: " + e);
        }
        System.exit(3);
    }

}
