package techSolutions.api;

import techSolutions.dto.CoinType;
import techSolutions.dto.exceptions.CoinException;

public interface CoinService {

    String getCoinRateByCoinType(CoinType coinType) throws CoinException;

    Object getCache();
}