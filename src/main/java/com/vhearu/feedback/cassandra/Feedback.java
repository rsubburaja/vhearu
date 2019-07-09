package com.vhearu.feedback.cassandra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Time;
import java.util.Date;

@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feedback {

    @PrimaryKey
    private String id;
    private String source;
    private String customerFName;
    private String customerLName;
    private String customerEmail;
    private String company;
    private String contact;
    private String product;
    private String feedBack;
    private String category;
    private String submittedDate;
    private String vmwcontactPerson;


    public Feedback(final String id, String source, String customerFName, String customerLName, String customerEmail, String company, String contact,String product, String feedBack, String category, String submittedDate,String vmwcontactPerson) {
        this.id = id;
        this.source = source;
        this.customerFName = customerFName;
        this.customerLName = customerLName;
        this.customerEmail = customerEmail;
        this.company = company;
        this.product = product;
        this.contact = contact;
        this.feedBack=feedBack;
        this.category = category;
        this.submittedDate = submittedDate;
        this.vmwcontactPerson = vmwcontactPerson;


    }

    public String getId() {
        return id;
    }

    public void setId(String key) {
        this.id = id;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCustomerFName() {
        return customerFName;
    }

    public void setCustomerFName(String customerFName) {
        this.customerFName = customerFName;
    }

    public String getCustomerLName() {
        return customerLName;
    }

    public void setCustomerLName(String customerLName) {
        this.customerLName = customerLName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getVmwcontactPerson() {
        return vmwcontactPerson;
    }

    public void setVmwcontactPerson(String vmwcontactPerson) {
        this.vmwcontactPerson = vmwcontactPerson;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getFeedBack() {
        return feedBack;
    }

    @Override
    public String toString() {
        return "Id:" + this.id + ",Source:" + this.source;
    }
}

