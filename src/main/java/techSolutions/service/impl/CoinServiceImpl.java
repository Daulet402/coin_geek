package techSolutions.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import techSolutions.api.CacheService;
import techSolutions.api.CoinService;
import techSolutions.api.CoingeckoParser;
import techSolutions.dto.CoinType;
import techSolutions.dto.exceptions.CoinException;

import java.io.IOException;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    private Logger log;

    @Autowired
    @Qualifier("objectLTBasedCache")
    private CacheService cacheService;

    @Autowired
    private CoingeckoParser coingeckoParser;


    @Override
    public String getCoinRateByCoinType(CoinType coinType) throws CoinException {
        log.info("getCoinRateByCoinType called with param " + coinType);
        String coinRate = (String) cacheService.getFromCache(String.valueOf(coinType));
        if (coinRate == null) {
            try {
                coinRate = coingeckoParser.getCoinRateByCoinType(coinType);
                log.info("web resource parsed");
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new CoinException(e.getMessage());
            }
            cacheService.addToCache(String.valueOf(coinType), coinRate);
        }
        return coinRate;
    }

    @Override
    public Object getCache() {
        return cacheService.getCache();
    }
}