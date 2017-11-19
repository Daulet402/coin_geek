import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TestApp {

    public static void main(String[] args)  throws Exception{
        LocalDateTime cacheUpdatedTime = LocalDateTime.now();

        Thread.sleep(3000);

        long diff = cacheUpdatedTime.until(LocalDateTime.now(), ChronoUnit.SECONDS);
        System.out.println(diff);
    }
}