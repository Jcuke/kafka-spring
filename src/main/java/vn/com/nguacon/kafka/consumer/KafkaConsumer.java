package vn.com.nguacon.kafka.consumer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer implements Consumer {
	private Logger log = Logger.getLogger(KafkaConsumer.class);
	
	public PollableChannel pollableChannel;

	@Autowired
	public KafkaConsumer(@Qualifier("fromKafka") PollableChannel pollableChannel) {
		this.pollableChannel = pollableChannel;
	}
	
	@Override
	public void run() {
		while(true) {
			receive();
		}
	}

	@Override
	public void receive() {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("接收消息开始:");
        Message<?> message = pollableChannel.receive();
        System.out.println("接收消息!");
		log.info(String.format("consume: %s", message.getPayload()) + "   接收时间: " +System.nanoTime());
	}

}
