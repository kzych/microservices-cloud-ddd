package com.pillopl.acc.infrastructure.mongo;

import java.util.Optional;

public interface CreditCardMongoRepository { //extends MongoRepository<CreditCardDocument, Long> {

    Optional<CreditCardDocument> findByRefNo(String refNo);


}
