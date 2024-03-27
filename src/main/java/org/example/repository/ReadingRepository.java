package org.example.repository;

import org.example.model.Indications;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * The transaction repository
 */

public interface ReadingRepository extends JpaRepository<Indications, Long> {


    List<Indications> findAllByUserId(Long userId);

//    /**
//     * Find transaction by transaction identifier
//     *
//     * @param transactionIdentifier the transaction identifier
//     * @return optional of transaction
//     */
//    Optional<Transaction> findByTransactionIdentifier(UUID transactionIdentifier);
}
