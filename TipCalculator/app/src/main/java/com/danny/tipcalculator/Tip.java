package com.danny.tipcalculator;

import java.util.Currency;
import java.util.Locale;

/**
 * Represents a Tip
 *  - uses Locale to parse and format currency.
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class Tip {

    private Currency currency;
    private double total;

    /**
     * Create a tip.
     * @param totalStr The total in String format. May include currency symbol (Ex: '$')
     * @throws TipException
     */
    public Tip(String totalStr) throws TipException {

        // strip the currency symbol using the currency symbol of the default locale
        currency = Currency.getInstance(Locale.getDefault());
        totalStr = totalStr.replace(currency.getSymbol(), "");

        try {
            total = Double.parseDouble(totalStr);
        }
        catch (NumberFormatException e) {
            throw new TipException(e);
        }
        if(total < 0)
            throw new TipException("Total must be a positive number");

    }

    /**
     * Calculate the tip for the given percent.
     * @param percent the tip percent as decimal (ex: 0.15)
     * @return the amount of tip for the given percent
     */
    public String getTip(double percent) {
        return format(total * percent);
    }

    /**
     * Calculate the total including tip for the given percent
     * @param percent the tip percent as decimal (ex: 0.15)
     * @return
     */
    public String getTotalWithTip(double percent) {
        return format(total * (1.0 + percent));
    }

    /**
     * Get the total before tip.
     * @return the total before tip
     */
    public String getTotal() {
        return format(total);
    }

    /**
     * Format as currency using locale's standard decimal spaces (Ex: Euro = 2, Yen = 0, ...).
     * @param amt the currency amount
     * @return the formatted currency
     */
    private String format(double amt) {
        String currencyFormat = "%s %." + currency.getDefaultFractionDigits() + "f";
        return String.format(currencyFormat, currency.getSymbol(), amt);
    }

}
