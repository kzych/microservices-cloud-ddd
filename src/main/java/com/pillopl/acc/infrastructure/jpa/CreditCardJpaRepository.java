package com.pillopl.acc.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardJpaRepository extends JpaRepository<CreditCardEntity, Long> {

    Optional<CreditCardEntity> findByRefNo(String refNo);


}
