package com.learnjava.libraraymanagementsystemv7;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class BorrowingRulesTestParamerterized
{
    private BorrowingRules rules;

    @BeforeEach
    void setUp()
    {
        rules = new BorrowingRules();
    }

    @ParameterizedTest
    @ValueSource(ints={0,1,2})
    void allowsBorrowingBelowLimit(int currentLoans) {
        assertTrue(rules.canBorrow(currentLoans));
    }


    @ParameterizedTest
    @ValueSource(ints={3,4,5})
    void rejectsBorrowingAtOrAboveLimit(int currentLoans) {
        assertFalse(rules.canBorrow(currentLoans));
    }
}
