package com.learnjava.libraraymanagementsystemv7.repository;

import com.learnjava.libraraymanagementsystemv7.entity.Loan;
import com.learnjava.libraraymanagementsystemv7.entity.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository
        extends JpaRepository<Loan, Integer> {

    Optional<Loan> findByBookCopyIdAndStatus(
            int bookCopyId,
            LoanStatus status
    );

    List<Loan> findByMemberIdAndStatus(
            int memberId,
            LoanStatus status
    );

    List<Loan> findByMemberId(int memberId);
}