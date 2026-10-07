package com.learnjava.libraraymanagementsystemv7.policy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StandardLateFeePolicy implements LateFeePolicy
{
    @Override
    public BigDecimal calculateLateFee(long overdueDays) {
        return  BigDecimal.valueOf(overdueDays * 10);
    }
}
