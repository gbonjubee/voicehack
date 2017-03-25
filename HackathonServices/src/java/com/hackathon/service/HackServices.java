/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackathon.service;

import com.hackathon.Bean.BalDataBean;
import com.hackathon.Bean.BalanceResponse;
import com.hackathon.Bean.HackBean;
import com.hackathon.Bean.TokenResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author AMUDA GBONJUBOLA
 */

@Path("hackService")
public class HackServices extends RXApplication {

    @POST
    @Path("/balance/{accNum}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBalance(@PathParam(value = "accNum") String accountNumber) {
        String availableBalance="";
        String tokenValue = getToken();
        String BAL_URL = "https://pwcstaging.herokuapp.com/account/validation?access_token="+tokenValue;
        try {
            HackBean balBean = new HackBean();
            balBean.setAccountnumber(accountNumber);
            balBean.setBankcode("044");
            Client build = ClientBuilder.newBuilder().build();
            WebTarget target = build.target(BAL_URL);

            Response post = target.request(MediaType.APPLICATION_JSON).post(Entity.json(balBean), Response.class);

            String readEntity = post.readEntity(String.class);

         //   System.out.println("readEntity = " + readEntity);
              BalanceResponse requestString = (BalanceResponse)new ServiceUtil().unpackBean(readEntity, "com.hackathon.Bean.BalanceResponse");
          if (requestString.getStatus().equalsIgnoreCase("success")){
              BalDataBean data=requestString.getData();
                      availableBalance=data.getAvailablebalance();
            System.out.println("Available Balance::"+data.getAccountname()+"your balance is"+data.getAvailablebalance());
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableBalance;
    }

    public String getToken() {
        String accessToken = "";
        String TOKEN_URL = "https://pwcstaging.herokuapp.com/oauth/token";
        try {
            Client build = ClientBuilder.newBuilder().build();
            WebTarget target = build.target(TOKEN_URL);
            Form form = new Form();
            form.param("grant_type", HackBean.grantType);
            form.param("client_id", HackBean.clientID);
            form.param("client_secret", HackBean.clientSecret);
            Response post = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
            String readEntity = post.readEntity(String.class);
            System.out.println("readEntity = " + readEntity);
           TokenResponse requestString = (TokenResponse)new ServiceUtil().unpackBean(readEntity, "com.hackathon.Bean.TokenResponse");
           accessToken=requestString.getAccess_token();
            System.out.println("accessToken::"+accessToken);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return accessToken;
    }

//    public static void main(String[] args) {
//        HackServices hack = new HackServices();
//      // hack.getToken();
//        hack.getBalance("0065172058");
//    }
}
