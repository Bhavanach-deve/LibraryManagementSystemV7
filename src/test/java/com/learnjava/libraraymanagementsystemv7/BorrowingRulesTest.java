package com.learnjava.libraraymanagementsystemv7;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowingRulesTest
{
    private final BorrowingRules borrowingRules = new BorrowingRules();

    @Test
    void allowsBorrowingWhenMemberHasTwoLoan(){
        assertTrue(borrowingRules.canBorrow(2));
    }
    @Test
    void rejectBorrowingWhenMemberHasThreeLoan(){
        assertFalse(borrowingRules.canBorrow(3));
    }
}
