package com.vhearu.feedback.plugins.product;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.vhearu.feedback.cassandra.Feedback;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.*;

@RestController
public class ProductFeedBackService {

    @Autowired
    RestTemplate restTemplate;


    private String url;

    private String username;

    private String password;

    public ProductFeedBackService(){}

    public ProductFeedBackService(String url, String username, String password)
    {
        this.url=url;
        this.username=username;
        this.password=password;
    }
    @RequestMapping(value = "/template/products")
    public  List<Feedback> getFeedbacks() {
        List<Feedback> feedbacks= new ArrayList<Feedback>();
        try{


            ObjectMapper mapper = new ObjectMapper();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(2000);
            restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(this.username, this.password));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity( JsonObject.class, headers);
            String result = restTemplate.getForObject(this.url, String.class);
            JSONObject jsonObject = new JSONObject(result);
            JSONObject hitsObject =  (JSONObject) jsonObject.get("hits");

            JSONArray jsonArray = (JSONArray) hitsObject.get("hits");
            for (int i=0;i < jsonArray.length() ; i++)
            {
                JSONObject feedbackjson =(JSONObject) jsonArray.get(i);
                JSONObject sourceJson = feedbackjson.getJSONObject("_source");
                Feedback feedback = new Feedback(feedbackjson.get("_id").toString(),sourceJson.get("source").toString(),sourceJson.get("fName").toString(),sourceJson.get("lname").toString(),sourceJson.get("email").toString(),sourceJson.get("company").toString(),sourceJson.get("contact").toString(),sourceJson.get("product").toString(),sourceJson.get("feedback").toString(), sourceJson.get("category").toString(),sourceJson.get("submittedDate").toString(),sourceJson.get("vmwcontactPerson").toString());
                feedbacks.add(feedback);
            }
            } catch (Exception e) { e.printStackTrace(); }
        return feedbacks;
    }
    public static void main(String[] args)
    {
        ProductFeedBackService ps = new ProductFeedBackService("http://vforecast-poc-a1:9200/partnerintegration.feedback/_search","elastic","changeme");
        //List<Feedback> feedbacks = ps.getFeedbacks("http://vforecast-poc-a1:9200/partnerintegration.feedback/_search",Feedback.class);
        List<Feedback> feedbacks = ps.getFeedbacks();
    }
}
