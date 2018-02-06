package xyz.hnnknk.deneb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    TokenStore tokenStore;

    @RequestMapping(value = "/login")
    public String index() {
        List<String> tokenValues = new ArrayList<String>();
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("my-trusted-client");
        if (tokens!=null){
            for (OAuth2AccessToken token:tokens){
                System.out.println(token.getValue());
                tokenValues.add(token.getValue());
            }
        }
        return "login";
    }

}