package com.krachilova.bookapp;

public class Datum {
    private String id;
    private String asin;
    private String title;
    private String image_url;
    private String image_url_hd;
    private String details_url;
    private String author;
    private String old_price;
    private String new_price;
    private String old_price_time;
    private String new_price_time;
    private String discount;
    private String info_text;
    private String expired;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
    }

    public String getImageUrlHd() {
        return image_url_hd;
    }

    public void setImageUrlHd(String imageUrlHd) {
        this.image_url_hd = imageUrlHd;
    }

    public String getDetailsUrl() {
        return details_url;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.details_url = detailsUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOldPrice() {
        return old_price;
    }

    public void setOldPrice(String oldPrice) {
        this.old_price = oldPrice;
    }

    public String getNewPrice() {
        return new_price;
    }

    public void setNewPrice(String newPrice) {
        this.new_price = newPrice;
    }

    public String getOldPriceTime() {
        return old_price_time;
    }

    public void setOldPriceTime(String oldPriceTime) {
        this.old_price_time = oldPriceTime;
    }

    public String getNewPriceTime() {
        return new_price_time;
    }

    public void setNewPriceTime(String newPriceTime) {
        this.new_price_time = newPriceTime;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getInfoText() {
        return info_text;
    }

    public void setInfoText(String infoText) {
        this.info_text = infoText;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

}
