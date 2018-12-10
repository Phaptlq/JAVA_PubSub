package pubsubser;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import pubsub.Message;
import subscriber.SubscriberInterface;

public class PubSubService {
    //Keeps set of subscriber topic wise, using set to prevent duplicates
    Map<String, Set<SubscriberInterface>> subscribersTopicMap = new HashMap<String, Set<SubscriberInterface>>();
    //Holds messages published by publishers
    Queue<Message> messagesQueue = new LinkedList<Message>();
    //Adds message sent by publisher to queue
    public void addMessageToQueue(Message message){
        messagesQueue.add(message);
    }

    //Add a new Subscriber for a topic
    public void addSubscriber(String topic, SubscriberInterface subscriber){

        if(subscribersTopicMap.containsKey(topic)){
            Set<SubscriberInterface> subscribers = subscribersTopicMap.get(topic);
            subscribers.add(subscriber);
            subscribersTopicMap.put(topic, subscribers);
        }else{
            Set<SubscriberInterface> subscribers = new HashSet<SubscriberInterface>();
            subscribers.add(subscriber);
            subscribersTopicMap.put(topic, subscribers);
        }
    }

    //Remove an existing subscriber for a topic
    public void removeSubscriber(String topic, SubscriberInterface subscriber){
        if(subscribersTopicMap.containsKey(topic)){
            Set<SubscriberInterface> subscribers = subscribersTopicMap.get(topic);
            subscribers.remove(subscriber);
            subscribersTopicMap.put(topic, subscribers);
        }
    }
    //Broadcast new messages added in queue to All subscribers of the topic. messagesQueue will be empty after broadcasting
    public void broadcast(){
        if(messagesQueue.isEmpty()){
            System.out.println("No messages from publishers to display");
        }else{
            while(!messagesQueue.isEmpty()){
                Message message = messagesQueue.remove();
                String topic = message.getTopic();

                Set<SubscriberInterface> subscribersOfTopic = subscribersTopicMap.get(topic);

                for(SubscriberInterface subscriber : subscribersOfTopic){
                    //add broadcasted message to subscribers message queue
                    List<Message> subscriberMessages = subscriber.getSubscriberMessages();
                    subscriberMessages.add(message);
                    subscriber.setSubscriberMessages(subscriberMessages);
                }
            }
        }
    }

    //Sends messages about a topic for subscriber at any point
    public void getMessagesForSubscriberOfTopic(String topic, SubscriberInterface subscriber) {
        if(messagesQueue.isEmpty()){
            System.out.println("No messages from publishers to display");
        }else{
            while(!messagesQueue.isEmpty()){
                Message message = messagesQueue.remove();

                if(message.getTopic().equalsIgnoreCase(topic)){

                    Set<SubscriberInterface> subscribersOfTopic = subscribersTopicMap.get(topic);

                    for(SubscriberInterface _subscriber : subscribersOfTopic){
                        if(_subscriber.equals(subscriber)){
                            //add broadcasted message to subscriber message queue
                            List<Message> subscriberMessages = subscriber.getSubscriberMessages();
                            subscriberMessages.add(message);
                            subscriber.setSubscriberMessages(subscriberMessages);
                        }
                    }
                }
            }
        }
    }

}