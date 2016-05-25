package chat.server;

import interfaces.IChatService;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * Created by user on 23/05/16.
 */
public class ChatService extends UnicastRemoteObject implements IChatService {

    private Map<Long, String> tokens;
    private Map<Long, String> queueNames;
    private Map<Long, MessageProducer> senders;
    private String channelName;
    private javax.jms.Connection connect = null;
    private javax.jms.Session sendSession = null;

    InitialContext context = null;

    public ChatService(String channel) throws RemoteException {
        channelName = channel;
        tokens = new Hashtable<>();
        senders = new Hashtable<>();
        queueNames = new Hashtable<>();
        //we need to connect here
        try
        {
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);
            // Create a connection.
            javax.jms.ConnectionFactory factory;
            factory = new ActiveMQConnectionFactory("user", "password", "tcp://localhost:61616");
            connect = factory.createConnection ("user", "password");
            connect.start(); // on peut activer la connection.
            sendSession = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

        } catch (javax.jms.JMSException jmse){
            jmse.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long login(String name)  throws RemoteException  {
        Random random = new Random();
        long token = random.nextLong();
        tokens.put(token,name);
        return token;
    }

    @Override
    public boolean sendMessage(long token, String message)  throws RemoteException  {
        String name = tokens.get(token);
        for(MessageProducer producer : senders.values()){
            TextMessage textMessage = null;
            try {
                textMessage = sendSession.createTextMessage();
                textMessage.setText("<"+name+">"+message);
                producer.send(textMessage);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String getQueue(long token)   throws RemoteException  {
        String queueName = null;
        if(queueNames.containsKey(token))
            return queueNames.get(token);
        try {
            queueName = "ChatService_" + channelName + "_" + token;
            queueNames.put(token, queueName);
            Queue queue = (Queue) context.lookup("dynamicQueues/" + queueName);
            MessageProducer sender = sendSession.createProducer(queue);
            senders.put(token, sender);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return queueName;
    }

}
