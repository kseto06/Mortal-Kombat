package network;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.locks.ReentrantLock;

/**Class to connect client to host, through Multicast Connection*/
public class MulticastClient {
    private String ip;
    private int port;
    private String messageText;
    private ReentrantLock messageTextLock = new ReentrantLock();
    private MulticastSocket socket;
    private InetSocketAddress groupAddress;
    private NetworkInterface networkInterface;
    private SocketRunnable socketRunnable;
    private Thread socketThread;
    transient ActionListener actionListener;

    public String readText() {
        if (socketRunnable != null) {
            return messageText;
        } else {
            return null;
        }
    }

    private synchronized void addActionListener(ActionListener listener) {
        actionListener = AWTEventMulticaster.add(actionListener, listener);
    }

    private void postActionEvent() {
        ActionListener listener = actionListener;
        if (listener != null) {
            listener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Network Message"));
        }
    }

    public boolean connect() {
        try {
            socketRunnable = new SocketRunnable();
            socketThread = new Thread(socketRunnable);    
            socketThread.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean disconnect() {
        try {
            socket.leaveGroup(groupAddress, networkInterface);
            socket.close();
            socketThread.interrupt();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MulticastClient(String ip, int port, ActionListener listener) {
        this.addActionListener(listener);
        this.ip = ip;
        this.port = port;
    }

    private class SocketRunnable implements Runnable {
        private void receiveMulticast(String ip, int port) throws IOException {
            socket = new MulticastSocket(port);
            InetAddress group = InetAddress.getByName(ip);
            groupAddress = new InetSocketAddress(group, port);
            networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            socket.joinGroup(groupAddress, networkInterface);
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    socket.receive(packet);
                } catch (SocketException e) {
                    return;
                }
                String received = new String(packet.getData(), packet.getOffset(), packet.getLength());
                messageTextLock.lock();
                messageText = received;
                postActionEvent();
                messageTextLock.unlock();
            }
            socket.leaveGroup(groupAddress, networkInterface);
            socket.close();
        }

        @Override
        public void run() {
            try {
                receiveMulticast(ip, port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
