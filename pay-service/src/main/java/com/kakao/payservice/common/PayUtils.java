package com.kakao.payservice.common;

import org.apache.commons.lang3.StringUtils;

public class PayUtils {

    private static final String ONLY_NUMBER = "[^0-9.]";

    public static Long parseLimit (String limit) {
        Long numLimit = 0L;
        if (limit.contains("억원")) {
            numLimit = Long.parseLong(StringUtils.defaultIfEmpty(limit.replaceAll(ONLY_NUMBER, ""), "0")) * 10000;
        } else if (limit.contains("백만원"))  {
            numLimit = Long.parseLong(StringUtils.defaultIfEmpty(limit.replaceAll(ONLY_NUMBER, ""), "0")) * 100;
        }

        return numLimit;
    }

    public static Double parseRate (String rate) {
        Double numRate = 99.99;
        if (rate.contains("~")) {
            numRate = Double.parseDouble(StringUtils.defaultIfEmpty(rate.split("~")[0].replaceAll(ONLY_NUMBER, ""), "99.99"));
        } else {
            numRate = Double.parseDouble(StringUtils.defaultIfEmpty(rate.replaceAll(ONLY_NUMBER, ""), "99.99"));
        }

        return numRate;
    }
}
