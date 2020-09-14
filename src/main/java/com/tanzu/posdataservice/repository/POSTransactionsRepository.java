package com.tanzu.posdataservice.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import com.tanzu.posdataservice.model.POSTransaction;

@Profile("receiver")
public interface POSTransactionsRepository extends CrudRepository<POSTransaction, Integer> {

}
