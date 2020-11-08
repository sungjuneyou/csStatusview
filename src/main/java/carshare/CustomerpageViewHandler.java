package carshare;

import carshare.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerpageViewHandler {


    @Autowired
    private CustomerpageRepository customerpageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_CREATE_1 (@Payload Paid paid) {
        try {
            if (paid.isMe()) {
                // view 객체 생성
                System.out.println("###################################### listener Updatestatus : " + paid.toJson());
                Customerpage customerpage = new Customerpage();
                // view 객체에 이벤트의 Value 를 set 함
                customerpage.setOrderId(paid.getOrderId());
                customerpage.setPaymentId(paid.getId());
                customerpage.setProductId(paid.getProductId());
                customerpage.setQty(paid.getQty());
                customerpage.setStatus(paid.getStatus());
                // view 레파지 토리에 save
                customerpageRepository.save(customerpage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenShipped_then_UPDATE_1(@Payload Shipped shipped) {
        try {
            if (shipped.isMe()) {
                // view 객체 조회
                System.out.println("###################################### listener Updatestatus : " + shipped.toJson());
                List<Customerpage> customerpageList = customerpageRepository.findByOrderId(shipped.getOrderId());
                for(Customerpage customerpage : customerpageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customerpage.setDeliveryId(shipped.getId());
                    customerpage.setStatus(shipped.getStatus());
                    // view 레파지 토리에 save
                    customerpageRepository.save(customerpage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryCanceled_then_UPDATE_2(@Payload DeliveryCanceled deliveryCanceled) {
        try {
            if (deliveryCanceled.isMe()) {
                // view 객체 조회
                System.out.println("###################################### listener Updatestatus : " + deliveryCanceled.toJson());
                List<Customerpage> customerpageList = customerpageRepository.findByOrderId(deliveryCanceled.getOrderId());
                for(Customerpage customerpage : customerpageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customerpage.setStatus(deliveryCanceled.getStatus());
                    // view 레파지 토리에 save
                    customerpageRepository.save(customerpage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
/*    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_3(@Payload Paid paid) {
        try {
            if (paid.isMe()) {
                // view 객체 조회
                System.out.println("###################################### listener Updatestatus : " + paid.toJson());
                List<Customerpage> customerpageList = customerpageRepository.findByOrderId(paid.getOrderId());
                for(Customerpage customerpage : customerpageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customerpage.setPaymentId(paid.getId());
                    customerpage.setStatus(paid.getStatus());
                    // view 레파지 토리에 save
                    customerpageRepository.save(customerpage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCancelled_then_UPDATE_3(@Payload PayCancelled payCancelled) {
        try {
            if (payCancelled.isMe()) {
                // view 객체 조회
                System.out.println("###################################### listener Updatestatus : " + payCancelled.toJson());
                List<Customerpage> customerpageList = customerpageRepository.findByOrderId(payCancelled.getOrderId());
                for(Customerpage customerpage : customerpageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customerpage.setStatus(payCancelled.getStatus());
                    // view 레파지 토리에 save
                    customerpageRepository.save(customerpage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOffered_then_UPDATE_4(@Payload Offered offered) {
        try {
            if (offered.isMe()) {
                // view 객체 조회
                System.out.println("###################################### listener Updatestatus : " + offered.toJson());
                List<Customerpage> customerpageList = customerpageRepository.findByOrderId(offered.getOrderId());
                for(Customerpage customerpage : customerpageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customerpage.setPointId(offered.getId());
                    customerpage.setPoint(offered.getPoint());
                    customerpage.setStatus(offered.getStatus());
                    // view 레파지 토리에 save
                    customerpageRepository.save(customerpage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPointCanceled_then_UPDATE_5(@Payload PointCanceled pointCanceled) {
        try {
            if (pointCanceled.isMe()) {
                // view 객체 조회
                System.out.println("###################################### listener Updatestatus : " + pointCanceled.toJson());
                List<Customerpage> customerpageList = customerpageRepository.findByOrderId(pointCanceled.getOrderId());
                for(Customerpage customerpage : customerpageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customerpage.setStatus(pointCanceled.getStatus());
                    // view 레파지 토리에 save
                    customerpageRepository.save(customerpage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}