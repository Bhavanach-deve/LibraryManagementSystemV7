package com.learnjava.libraraymanagementsystemv7.policy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StandardLateFeePolicy implements LateFeePolicy {

    private static final BigDecimal FINE_PER_DAY =
            BigDecimal.valueOf(5);

    @Override
    public BigDecimal calculateLateFee(long overdueDays) {

        if (overdueDays <= 0) {
            return BigDecimal.ZERO;
        }

        return FINE_PER_DAY.multiply(BigDecimal.valueOf(overdueDays));
    }
}
