package dev.arias.huapaya.ms_sale_proxy.service.implementation;

import java.util.Map;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.arias.huapaya.ms_sale_proxy.model.Sale;
import dev.arias.huapaya.ms_sale_proxy.persistence.repository.FallbackRepository;
import dev.arias.huapaya.ms_sale_proxy.persistence.repository.SaleRepository;
import dev.arias.huapaya.ms_sale_proxy.service.interfaces.SaleService;
import dev.arias.huapaya.ms_sale_proxy.stream.SalePublisher;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SaleServiceImplementation implements SaleService {

    private final FallbackRepository fallbackRepository;

    private final SaleRepository saleRepository;

    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    private final SalePublisher salePublisher;

    private final ObjectMapper objectMapper;

    @Override
    public Map<String, Object> save(Sale sale) {
        var circuitBreaker = this.circuitBreakerFactory.create("sale-circuitbreaker");
        this.salePublisher.publisher(this.buildEventMsg(sale));
        return circuitBreaker.run(() -> this.saleRepository.save(sale),
                throwable -> this.fallbackRepository.getFallback());
    }

    private String buildEventMsg(Sale sale) {
        try {
            return this.objectMapper.writeValueAsString(sale);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
