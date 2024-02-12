package com.ticket.app.repository.elasticsearch;

import com.ticket.app.entity.elasticsearch.TicketElasticModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TicketElasticRepository extends ElasticsearchRepository<TicketElasticModel, String> {
}
