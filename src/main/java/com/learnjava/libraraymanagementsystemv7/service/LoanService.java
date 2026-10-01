package com.learnjava.libraraymanagementsystemv7.service;

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
                        new RuntimeException(
                                "BookCopy with id "
                                        + request.getBookCopyId()
                                        + " not found"
                        ));

        if (bookCopy.getStatus() != BookCopyStatus.AVAILABLE) {
            throw new RuntimeException(
                    "BookCopy with id "
                            + request.getBookCopyId()
                            + " is not available"
            );
        }

        if (loanRepository.findByBookCopyIdAndStatus(
                bookCopy.getId(),
                LoanStatus.ACTIVE
        ).isPresent()) {

            throw new RuntimeException(
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
}