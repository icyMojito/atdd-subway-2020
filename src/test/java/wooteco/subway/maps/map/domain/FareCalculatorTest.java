package wooteco.subway.maps.map.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wooteco.subway.maps.line.domain.Line;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("지하철 요금 계산 단위 테스트")
public class FareCalculatorTest {
    private List<Line> pathLines = new ArrayList<>();

    @DisplayName("거리별 요금을 정확하게 계산한다.")
    @Test
    void calculateCorrectlyByDistanceOnly() {
        assertThat(FareCalculator.calculate(0, pathLines)).isEqualTo(0);
        assertThat(FareCalculator.calculate(10, pathLines)).isEqualTo(1_250);
        assertThat(FareCalculator.calculate(11, pathLines)).isEqualTo(1_350);
        assertThat(FareCalculator.calculate(25, pathLines)).isEqualTo(1_550);
        assertThat(FareCalculator.calculate(50, pathLines)).isEqualTo(2_050);
        assertThat(FareCalculator.calculate(51, pathLines)).isEqualTo(2_150);
        assertThat(FareCalculator.calculate(58, pathLines)).isEqualTo(2_250);
        assertThat(FareCalculator.calculate(90, pathLines)).isEqualTo(2_650);
    }

    @DisplayName("거리와 노선 추가금을 바탕으로 정확하게 계산한다.")
    @Test
    void calculateCorrectlyByDistanceAndLineExtraFare() {
        Line line1 = new Line("신분당선", "red lighten-1", LocalTime.now(), LocalTime.now(), 10, 0);
        Line line2 = new Line("2호선", "green lighten-1", LocalTime.now(), LocalTime.now(), 10, 500);
        Line line3 = new Line("3호선", "orange darken-1", LocalTime.now(), LocalTime.now(), 10, 900);

        this.pathLines.add(line1);
        this.pathLines.add(line2);
        this.pathLines.add(line3);

        assertThat(FareCalculator.calculate(0, pathLines)).isEqualTo(0);
        assertThat(FareCalculator.calculate(10, pathLines)).isEqualTo(2_650);
        assertThat(FareCalculator.calculate(11, pathLines)).isEqualTo(2_750);
        assertThat(FareCalculator.calculate(25, pathLines)).isEqualTo(2_950);
        assertThat(FareCalculator.calculate(50, pathLines)).isEqualTo(3_450);
        assertThat(FareCalculator.calculate(51, pathLines)).isEqualTo(3_550);
        assertThat(FareCalculator.calculate(58, pathLines)).isEqualTo(3_650);
        assertThat(FareCalculator.calculate(90, pathLines)).isEqualTo(4_050);
    }
}
