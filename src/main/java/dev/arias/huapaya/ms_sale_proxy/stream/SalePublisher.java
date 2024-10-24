package dev.arias.huapaya.ms_sale_proxy.stream;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class SalePublisher {
    
    private final StreamBridge streamBridge;

    public void publisher(String sale){
        log.info("sale proxy stream");
        this.streamBridge.send("consumerStream", sale);
        this.streamBridge.send("consumerStream-in-0", sale);
        this.streamBridge.send("consumerStream-out-0", sale);
    }

    public void publisherCb(String sale){
        log.info("sale proxy stream");
        this.streamBridge.send("consumerSale", sale);
        this.streamBridge.send("consumerSale-in-0", sale);
        this.streamBridge.send("consumerSale-out-0", sale);
    }

}
