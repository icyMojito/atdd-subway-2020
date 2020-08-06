package wooteco.subway.maps.map.domain;

public class FareCalculator {
    private static final int DEFAULT_FARE_MAX_DISTANCE = 10;
    private static final int FIRST_ADDITIONAL_FARE_MAX_DISTANCE = 50;
    private static final int FIRST_ADDITIONAL_MAX_FARE = 2_050;

    public static int calculate(int distance) {
        if (distance == 0) {
            return 0;
        }

        int defaultFare = 1_250;
        int additionalFare = 0;

        if (DEFAULT_FARE_MAX_DISTANCE < distance && distance <= FIRST_ADDITIONAL_FARE_MAX_DISTANCE) {
            additionalFare = (int) ((Math.ceil((distance - 1) / 5) - 1) * 100);
        }
        if (FIRST_ADDITIONAL_FARE_MAX_DISTANCE < distance) {
            defaultFare = FIRST_ADDITIONAL_MAX_FARE;
            distance -= FIRST_ADDITIONAL_FARE_MAX_DISTANCE;
            additionalFare += (int) ((Math.ceil((distance / 8) + 1) * 100));
        }

        return defaultFare + additionalFare;
    }
}
