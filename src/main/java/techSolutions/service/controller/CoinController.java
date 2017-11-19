package techSolutions.service.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techSolutions.api.CoinService;
import techSolutions.dto.CoinType;
import techSolutions.dto.exceptions.CoinException;

@RestController
public class CoinController {

    @Autowired
    private Logger log;

    @Autowired
    private CoinService coinService;

    @RequestMapping("test/")
    public String testApp(String var) {
        log.info("testApp was called");
        return "App is runing";
    }

    @RequestMapping("/coin/{coinType}")
    public String getCoinRateByCoinType(@PathVariable CoinType coinType) throws CoinException {
        return coinService.getCoinRateByCoinType(coinType);
    }

    @RequestMapping("/cache")
    public Object getCache() {
        return coinService.getCache();
    }
}