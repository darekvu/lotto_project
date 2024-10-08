package org.darekvu.lottoproject.domain.numbergenerator;

import org.darekvu.lottoproject.domain.numbergenerator.dto.OneRandomNumberFetcherResponseDto;

interface OneRandomNumberFetcher {
   OneRandomNumberFetcherResponseDto retrieveOneRandomNumber(int lowerBand, int upperBand);
}
