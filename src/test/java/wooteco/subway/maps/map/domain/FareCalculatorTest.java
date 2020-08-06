package wooteco.subway.maps.map.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("지하철 요금 계산 단위 테스트")
public class FareCalculatorTest {

    @DisplayName("거리별 요금을 정확하게 계산한다.")
    @Test
    void calculateCorrectly() {
        assertThat(FareCalculator.calculate(0)).isEqualTo(0);
        assertThat(FareCalculator.calculate(10)).isEqualTo(1_250);
        assertThat(FareCalculator.calculate(11)).isEqualTo(1_350);
        assertThat(FareCalculator.calculate(25)).isEqualTo(1_550);
        assertThat(FareCalculator.calculate(50)).isEqualTo(2_050);
        assertThat(FareCalculator.calculate(51)).isEqualTo(2_150);
        assertThat(FareCalculator.calculate(58)).isEqualTo(2_250);
        assertThat(FareCalculator.calculate(90)).isEqualTo(2_650);
    }
}
