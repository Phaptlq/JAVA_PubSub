package publisher;
import pubsub.Message;
import pubsubser.PubSubService;

public interface PublisherInterface {
    //Send new message to PubSubService
    void sendMessage(Message message, PubSubService pubSubService);
}
