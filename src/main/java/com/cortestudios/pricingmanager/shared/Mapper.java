package com.cortestudios.pricingmanager.shared;

public interface Mapper<I, O> {
    O map(I input);
    I reverseMap(O output);
}
