package com.cortestudios.pricingmanager.pricing.domain.exception;

public class PriceNotFoundException extends RuntimeException {
  public PriceNotFoundException(String message) {
    super(message);
  }
}
