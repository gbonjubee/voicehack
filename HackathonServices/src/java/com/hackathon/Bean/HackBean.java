/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackathon.Bean;

/**
 *
 * @author AMUDA GBONJUBOLA
 */
public class HackBean {

    public static String clientID = "58d557dc4637e61000b651e6";
    public static String clientSecret = "lfv78LRsCLPFybYE4zoDx5y3xZpuoYj4usgzONzNAz7N9sGCp3uzfg6632SXMWHgn4JeFOw1SLILbIncZmdNrYaDPZmlCaQWsM6i";
    public static String grantType = "client_credentials";

     private String bankcode;
    private String accountnumber;

    /**
     * @return the bankcode
     */
    public String getBankcode() {
        return bankcode;
    }

    /**
     * @param bankcode the bankcode to set
     */
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    /**
     * @return the accountnumber
     */
    public String getAccountnumber() {
        return accountnumber;
    }

    /**
     * @param accountnumber the accountnumber to set
     */
    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

   
    
}
