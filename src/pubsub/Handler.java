package pubsub;
import publisher.PublisherImple;
import publisher.PublisherInterface;
import pubsubser.PubSubService;
import subscriber.SubscriberImple;
import subscriber.SubscriberInterface;
public class Handler {
    public static void main(String[] args) {

        //Init Publisher - Subscriber
        PublisherInterface animalPublisher = new PublisherImple();
        PublisherInterface humanPublisher = new PublisherImple();

        SubscriberInterface animalSubscriber = new SubscriberImple();
        SubscriberInterface bothSubscriber = new SubscriberImple();
        SubscriberInterface humanSubscriber = new SubscriberImple();

        PubSubService pubSubService = new PubSubService();

        //Declare Messages and Publish Messages to PubSubService
        Message animalMessage1 = new Message("Animal", "Tiger is animal");
        Message animalMessage2 = new Message("Animal", "Dogs is animal");


        animalPublisher.sendMessage(animalMessage1, pubSubService);
        animalPublisher.sendMessage(animalMessage2, pubSubService);


        Message humanMessage1 = new Message("Human", "My name is Phap");
        Message humanMessage2 = new Message("Human", "My nam is Lien");

        humanPublisher.sendMessage(humanMessage1, pubSubService);
        humanPublisher.sendMessage(humanMessage2, pubSubService);

        //Declare Subscribers
        animalSubscriber.addSubscriber("Animal",pubSubService);
        humanSubscriber.addSubscriber("Human",pubSubService);
        bothSubscriber.addSubscriber("Animal", pubSubService);
        bothSubscriber.addSubscriber("Human", pubSubService);

        //Trying unSubscribing a subscriber
//        humanSubscriber.unSubscribe("Human", pubSubService);

        //Broadcast message to all subscribers. After broadcast, messageQueue will be empty in PubSubService
        pubSubService.broadcast();

        //Print messages of each subscriber to see which messages they got
        System.out.println("Messages of Animal Subscriber are: ");
        animalSubscriber.printMessages();

        System.out.println("\nMessages of Human Subscriber are: ");
        humanSubscriber.printMessages();

        System.out.println("\nMessages of Both Subscriber are: ");
        bothSubscriber.printMessages();
    }
}
