package org.darekvu.lottoproject.numbergenerator;

import org.darekvu.lottoproject.numbergenerator.dto.OneRandomNumberFetcherResponseDto;

interface OneRandomNumberFetcher {
   OneRandomNumberFetcherResponseDto retrieveOneRandomNumber(int lowerBand, int upperBand);
}
