package com.learnjava.libraraymanagementsystemv7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowingRulesTestCsvSource
{
    private BorrowingRules rules;
    @BeforeEach
    void setUp()
    {
        rules = new BorrowingRules();
    }
    @ParameterizedTest
    @CsvSource({"0,true","1,true","2,true","3,false","4,false"})
    void borrowingRules(int currentLoans,boolean expectedResult){
        assertEquals(expectedResult,rules.canBorrow(currentLoans));
    }
}
