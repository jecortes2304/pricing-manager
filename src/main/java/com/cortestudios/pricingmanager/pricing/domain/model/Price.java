package com.cortestudios.pricingmanager.pricing.domain.model;


import java.time.LocalDateTime;
import java.util.Objects;

public class Price {

    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Long productId;
    private Integer priority;
    private Double price;
    private String currency;


    public Price() {
    }

    public Price(Long brandId, LocalDateTime startDate, LocalDateTime endDate, Long priceList, Long productId, Integer priority, Double price, String currency) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }


    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    @Override
    public String toString() {
        return "Price{" +
                "brandId=" + brandId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceList=" + priceList +
                ", productId=" + productId +
                ", priority=" + priority +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Objects.equals(brandId, price1.brandId) && Objects.equals(startDate, price1.startDate) && Objects.equals(endDate, price1.endDate) && Objects.equals(priceList, price1.priceList) && Objects.equals(productId, price1.productId) && Objects.equals(priority, price1.priority) && Objects.equals(price, price1.price) && Objects.equals(currency, price1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, startDate, endDate, priceList, productId, priority, price, currency);
    }


    public static final class PriceBuilder {
        private Long brandId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Long priceList;
        private Long productId;
        private Integer priority;
        private Double price;
        private String currency;

        private PriceBuilder() {
        }

        public static PriceBuilder aPrice() {
            return new PriceBuilder();
        }

        public PriceBuilder withBrandId(Long brandId) {
            this.brandId = brandId;
            return this;
        }

        public PriceBuilder withStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public PriceBuilder withEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public PriceBuilder withPriceList(Long priceList) {
            this.priceList = priceList;
            return this;
        }

        public PriceBuilder withProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public PriceBuilder withPriority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public PriceBuilder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public PriceBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Price build() {
            Price priceDto = new Price();
            priceDto.setBrandId(brandId);
            priceDto.setStartDate(startDate);
            priceDto.setEndDate(endDate);
            priceDto.setPriceList(priceList);
            priceDto.setProductId(productId);
            priceDto.setPriority(priority);
            priceDto.setPrice(price);
            priceDto.setCurrency(currency);
            return priceDto;
        }
    }
}
