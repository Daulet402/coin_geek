package techSolutions.api;

import techSolutions.dto.CoinType;

import java.io.IOException;

public interface CoingeckoParser extends Parser {

    String getCoinRateByCoinType(CoinType coinType) throws IOException;
}