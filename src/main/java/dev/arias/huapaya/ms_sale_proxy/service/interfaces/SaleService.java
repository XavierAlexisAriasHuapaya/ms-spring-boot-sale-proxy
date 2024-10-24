package dev.arias.huapaya.ms_sale_proxy.service.interfaces;

import java.util.Map;

import dev.arias.huapaya.ms_sale_proxy.model.Sale;

public interface SaleService {

    public Map<String, Object> save(Sale sale);

}
