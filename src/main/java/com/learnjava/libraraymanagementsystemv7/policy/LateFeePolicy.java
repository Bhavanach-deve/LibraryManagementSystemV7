package com.learnjava.libraraymanagementsystemv7.policy;

import java.math.BigDecimal;

public interface LateFeePolicy {
    BigDecimal calculateLateFee(long overdueDays);
}
