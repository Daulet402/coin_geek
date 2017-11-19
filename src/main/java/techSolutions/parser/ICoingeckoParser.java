package techSolutions.parser;

import techSolutions.dto.CoinType;

import java.io.IOException;

public interface ICoingeckoParser extends IParser {

    String getCoinRateByCoinType(CoinType coinType) throws IOException;
}