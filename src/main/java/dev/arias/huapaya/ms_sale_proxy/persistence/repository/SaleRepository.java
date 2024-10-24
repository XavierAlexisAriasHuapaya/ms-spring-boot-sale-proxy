package dev.arias.huapaya.ms_sale_proxy.persistence.repository;

import java.util.Map;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import dev.arias.huapaya.ms_sale_proxy.configuration.bean.LoadBalancerConfiguration;
import dev.arias.huapaya.ms_sale_proxy.model.Sale;

@FeignClient(name = "ms-sale")
@LoadBalancerClient(name = "ms-sale", configuration = LoadBalancerConfiguration.class)
public interface SaleRepository {

    @PostMapping(path = "/api/sale/sale")
    public Map<String, Object> save(Sale sale);

}
