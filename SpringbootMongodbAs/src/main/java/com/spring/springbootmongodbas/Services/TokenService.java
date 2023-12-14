package com.spring.springbootmongodbas.Services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;


    @Service
    public class TokenService {

        public static  final String token_secret="dfgairnkk456830k";
        //one token is created on our own
        public String createToken(ObjectId userId)
        {
            try//block of code to be tested for errors while execution
            {
                //HMAC is basically to create the code through hashing to encrypt the data
                Algorithm algo=Algorithm.HMAC256(token_secret);
                String token= JWT.create().withClaim("userId",userId.toString()).
                        withClaim("createdAt",new Date()).
                        sign(algo);

                return token;

            }
            //if no errors generated by the code then it will execute
            catch(UnsupportedEncodingException | JWTCreationException e) {
                e.printStackTrace();
            }
            return null;
        }
        //decodiing the token

        public String getUserIdToken(String token) {
            try
            {
                Algorithm algo=Algorithm.HMAC256(token_secret);
                JWTVerifier jwtVerifier=JWT.require(algo).build();
                DecodedJWT decodedJWT=jwtVerifier.verify(token);
                return decodedJWT.getClaim("userId").asString();
            }
            catch (UnsupportedEncodingException |JWTCreationException e)
            {
                e.printStackTrace();//if we find any error in the code then it will simply
                //take u to the that code error
            }
            return null;

        }
        //token valid

        public boolean isTokenValid(String token) {
            String userId=this.getUserIdToken(token);
            return userId!=null;
        }

    }