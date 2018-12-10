package publisher;
import pubsub.Message;
import pubsubser.PubSubService;

public class PublisherImple implements PublisherInterface {
    public void sendMessage(Message message, PubSubService pubSubService) {
        pubSubService.addMessageToQueue(message);
    }
}
