package chat.client;

import api.IUniversalRMIRegistry;
import interfaces.IChatService;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Scanner;

public class Main implements javax.jms.MessageListener{
    private javax.jms.Connection connect = null;
    private javax.jms.Session sendSession = null;
    private javax.jms.Session receiveSession = null;
    private javax.jms.MessageProducer sender = null;
    private javax.jms.Queue queue = null;
    InitialContext context = null;
    private IChatService service;
    private long token;

    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            env.put(Context.PROVIDER_URL, "rmi://localhost:1098/");
            Context ctx = new InitialContext(env);
            //retrieve the registry
            IUniversalRMIRegistry reg = (IUniversalRMIRegistry) Naming.lookup("rmi://localhost:1098/universalrmiregistry");
            IChatService service = (IChatService) reg.get("chat");
            Scanner a = new Scanner(System.in);
            System.out.print("Votre pseudonyme?");
            new Main(a.nextLine(),service);
        } catch (Exception e) {

        }
    }
    public Main(String pseudo, IChatService service){
        this.service = service;
        String queueName = "";
        try {
            token = service.login(pseudo);
            queueName = service.getQueue(token);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try
        {	// Create a connection
            // Si le producteur et le consommateur étaient codés séparément, ils auraient eu ce même bout de code

            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);

            javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();


            connect.start(); // on peut activer la connection.

            receiveSession = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("dynamicQueues/"+queueName);
            javax.jms.MessageConsumer qReceiver = receiveSession.createConsumer(queue);
            qReceiver.setMessageListener(this);
        } catch (javax.jms.JMSException jmse){
            jmse.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        runMessages();
    }

    private void runMessages(){
        Scanner a = new Scanner(System.in);
        new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.print(">");
                    String msg = a.nextLine();
                    if(msg.equals("quit"))
                        break;
                    try {
                        service.sendMessage(token,msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
