package wooteco.subway.maps.map.domain;

import wooteco.subway.maps.line.domain.Line;

import java.util.List;

public class FareCalculator {
    private static final int DEFAULT_FARE_MAX_DISTANCE = 10;
    private static final int FIRST_EXTRA_FARE_MAX_DISTANCE = 50;
    private static final int FIRST_EXTRA_MAX_DISTANCE_FARE = 800;

    public static int calculate(int distance, List<Line> pathLines) {
        if (distance == 0) {
            return 0;
        }

        int defaultFare = 1_250;
        int additionalFare = 0;

        additionalFare += calculateAdditionalFareByDistance(distance);
        additionalFare += calculateAdditionalFareByLineExtraFare(pathLines);

        return defaultFare + additionalFare;
    }

    private static int calculateAdditionalFareByDistance(int distance) {
        int additionalFareByDistance = 0;
        if (DEFAULT_FARE_MAX_DISTANCE < distance && distance <= FIRST_EXTRA_FARE_MAX_DISTANCE) {
            return (int) ((Math.ceil((distance - 1) / 5) - 1) * 100);
        }
        if (FIRST_EXTRA_FARE_MAX_DISTANCE < distance) {
            additionalFareByDistance = FIRST_EXTRA_MAX_DISTANCE_FARE;
            int distanceOverFirstAdditionalFareMaxDistance = distance - FIRST_EXTRA_FARE_MAX_DISTANCE;
            additionalFareByDistance += (int) ((Math.ceil((distanceOverFirstAdditionalFareMaxDistance / 8) + 1) * 100));
        }
        return additionalFareByDistance;
    }

    private static int calculateAdditionalFareByLineExtraFare(List<Line> pathLines) {
        return pathLines.stream()
            .mapToInt(Line::getExtraFare)
            .sum();
    }
}
