package org.darekvu.lottoproject.domain.numbergenerator;

import org.darekvu.lottoproject.domain.numbergenerator.dto.OneRandomNumberFetcherResponseDto;

import java.security.SecureRandom;
import java.util.Random;

class SecureOneRandomNumberFetcher implements OneRandomNumberFetcher {
    @Override
    public OneRandomNumberFetcherResponseDto retrieveOneRandomNumber(int lowerBand, int upperBand) {
        Random random = new SecureRandom();
        return OneRandomNumberFetcherResponseDto.builder()
                .number(random.nextInt((upperBand - lowerBand) + 1))
                .build();
    }
}
