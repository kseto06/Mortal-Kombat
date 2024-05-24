//This is where all "backend" stuff is handled:

public class Model {
    //Properties
    SuperSocketMaster ssm;
    String IPAddress = null;
    private final int PORT = 8080;

    //Methods

    //Hosting a new server
    public void hostServer() {
        ssm = new SuperSocketMaster(PORT, null);
        ssm.connect();
        System.out.println(ssm.connect());
        if (ssm.connect() == true) {
            IPAddress = ssm.getMyAddress();
            View.playerList[0] = "Host";
            System.out.println("Host connected successfully at port 8080");
        }        
    }

    public void clientServer() {
        ssm = new SuperSocketMaster(IPAddress, PORT, null);
        ssm.connect();
        System.out.println("Successful Client Connect? "+ssm.connect());
        if (ssm.connect() == true) { 
            View.playerList[1] = "Client";
            System.out.println("Client successfully connected");
        }
    }

    

    //Constructor
}
