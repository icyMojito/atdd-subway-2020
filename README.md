<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <img alt="license" src="https://img.shields.io/github/license/woowacourse/atdd-subway-2020">
</p>

<br>

# 레벨2 최종 미션 - 지하철 노선도 애플리케이션

#### 경로 조회 응답 결과에 요금 정보 추가하기

- 사용자가 조회한 경로를 찾는다.

- 찾은 경로와 요금 계산 기준에 따라 최종 요금을 계산한다.

- - 10km 이하: 1,250원
  - 10km 초과 ~ 50km 이하: 5km마다 100원
  - 50km 초과: 8km마다 100원
  - 추가 요금이 있는 노선들을 이용할 경우 가장 높은 추가 요금만 거리별 요금에 합산
  - 로그인 사용자가 13세 이상~19세 미만이면 350원을 뺀 금액의 80%로 책정
  - 로그인 사용자가 6세 이상~13세 미만이면 350원을 뺀 금액의 50%로 책정