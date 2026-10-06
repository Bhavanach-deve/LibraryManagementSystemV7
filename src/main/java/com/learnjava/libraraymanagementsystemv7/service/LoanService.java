package com.learnjava.libraraymanagementsystemv7.service;
import com.learnjava.libraraymanagementsystemv7.exception.*;
import com.learnjava.libraraymanagementsystemv7.dto.BorrowRequest;
import com.learnjava.libraraymanagementsystemv7.dto.LoanResponse;
import com.learnjava.libraraymanagementsystemv7.entity.BookCopy;
import com.learnjava.libraraymanagementsystemv7.entity.BookCopyStatus;
import com.learnjava.libraraymanagementsystemv7.entity.Loan;
import com.learnjava.libraraymanagementsystemv7.entity.LoanStatus;
import com.learnjava.libraraymanagementsystemv7.entity.Member;
import com.learnjava.libraraymanagementsystemv7.repository.BookCopyRepository;
import com.learnjava.libraraymanagementsystemv7.repository.LoanRepository;
import com.learnjava.libraraymanagementsystemv7.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import com.learnjava.libraraymanagementsystemv7.exception.LoanNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final BookCopyRepository bookCopyRepository;

    public LoanService(
            LoanRepository loanRepository,
            MemberRepository memberRepository,
            BookCopyRepository bookCopyRepository) {

        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    @Transactional
    public LoanResponse borrowBook(BorrowRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Member with id "
                                        + request.getMemberId()
                                        + " not found"
                        ));

        BookCopy bookCopy = bookCopyRepository
                .findById(request.getBookCopyId())
                .orElseThrow(() ->
                        new BookCopyNotFoundException(
                                "BookCopy with id "
                                        + request.getBookCopyId()
                                        + " not found"
                        ));

        if (bookCopy.getStatus() != BookCopyStatus.AVAILABLE) {
            throw new BookCopyNotAvailableException(
                    "BookCopy with id "
                            + request.getBookCopyId()
                            + " is not available"
            );
        }

        if (loanRepository.findByBookCopyIdAndStatus(
                bookCopy.getId(),
                LoanStatus.ACTIVE
        ).isPresent()) {

            throw new ActiveLoanAlreadyExistsException(
                    "BookCopy with id "
                            + bookCopy.getId()
                            + " already has an active loan"
            );
        }

        LocalDateTime borrowedAt = LocalDateTime.now();

        Loan loan = new Loan();

        loan.setMember(member);
        loan.setBookCopy(bookCopy);
        loan.setBorrowedAt(borrowedAt);
        loan.setDueDate(borrowedAt.plusDays(14));
        loan.setReturnedAt(null);
        loan.setStatus(LoanStatus.ACTIVE);

        bookCopy.setStatus(BookCopyStatus.BORROWED);

        Loan savedLoan = loanRepository.save(loan);

        return toLoanResponse(savedLoan);
    }

    private LoanResponse toLoanResponse(Loan loan) {

        LoanResponse response = new LoanResponse();

        response.setId(loan.getId());
        response.setMemberId(loan.getMember().getId());
        response.setBookCopyId(loan.getBookCopy().getId());
        response.setBorrowedAt(loan.getBorrowedAt());
        response.setDueDate(loan.getDueDate());
        response.setReturnedAt(loan.getReturnedAt());
        response.setStatus(loan.getStatus().name());

        return response;
    }
    @Transactional
    public LoanResponse returnBook(int loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan with id " + loanId + " not found"
                        ));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new LoanAlreadyReturnedException(
                    "Loan with id " + loanId + " has already been returned"
            );
        }

        loan.setReturnedAt(LocalDateTime.now());
        loan.setStatus(LoanStatus.RETURNED);

        BookCopy bookCopy = loan.getBookCopy();
        bookCopy.setStatus(BookCopyStatus.AVAILABLE);

        Loan savedLoan = loanRepository.save(loan);

        return toLoanResponse(savedLoan);
    }
    public LoanResponse getLoanById(int loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan with id " + loanId + " not found"
                        ));

        return toLoanResponse(loan);
    }
    public List<LoanResponse> getLoansByMemberId(int memberId) {

        memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new MemberNotFoundException(
                                "Member with id " + memberId + " not found"
                        ));

        List<Loan> loans = loanRepository.findByMemberId(memberId);

        List<LoanResponse> responses = new ArrayList<>();

        for (Loan loan : loans) {

            LoanResponse response = toLoanResponse(loan);

            responses.add(response);
        }

        return responses;
    }
    public List<LoanResponse> getActiveLoansByMemberId(int memberId) {

        memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new MemberNotFoundException(
                                "Member with id " + memberId + " not found"
                        ));

        List<Loan> loans =
                loanRepository.findByMemberIdAndStatus(
                        memberId,
                        LoanStatus.ACTIVE
                );

        List<LoanResponse> responses = new ArrayList<>();

        for (Loan loan : loans) {

            LoanResponse response = toLoanResponse(loan);

            responses.add(response);
        }

        return responses;
    }
    public List<LoanResponse> getOverdueLoans() {

        List<Loan> loans =
                loanRepository.findByStatusAndDueDateBefore(
                        LoanStatus.ACTIVE,
                        LocalDateTime.now()
                );

        List<LoanResponse> responses = new ArrayList<>();

        for (Loan loan : loans) {

            LoanResponse response = toLoanResponse(loan);

            responses.add(response);
        }

        return responses;
    }
}