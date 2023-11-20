# 랜덤한 영어단어 제공 서비스
> 교육부가 지정한 초등, 중등, 고등 교육과정에 필수 암기 영단으롤 일부 추출하여 데이터 마이그레이션 하였으며,
> 사용자의 주관적 난이도 판단에 따라 계속적으로 등록되는 영단어 목록 랜덤 조회 서비스 제공

### Domain: `http://openapi.webservicetoday.com`

### 생성된 API
| **Title** | **Path** | **Method** |
| :--- | :--- | :---:|
| 영어단어 랜덤 조회 | `/api/open-api/random/word/eng` | GET |
| 영어단어 난이도별 랜덤 조회 | `/api/open-api/random/word/eng/:level` | GET |
| 영어단어 등록 | `/api/open-api/word/eng` | POST |

### API Description
- 영어단어 랜덤 조회
  - DB에 등록되어 있는 단어 중 랜덤한 하나의 단어 조회하여 반환
  - `curl -v http://openapi.webservicetoday.com/api/open-api/random/word/eng`
  - Response Json Example
    ```json
    {
        "vocabulary": "mouse",
        "mean": "쥐",
        "level": "ELEMENT",
        "lastUpdateDatetime": "2023.11.19 23:54",
        "lastUpdateUserNickName": "관리자"
    }
    ```
- 영어단어 난이도별 랜덤 조회
  - DB에 등록되어 있는 단어 중 PathVariable에 입력한 난이도에 따라 단어 조회
  - `curl -v http://openapi.webservicetoday.com/api/open-api/random/word/eng/:level`
  - PathVariable(string enum)
    - element -> 초등
    - middle -> 중등
    - high -> 고등
  - Response Json Example
    ```json
    {
        "vocabulary": "pharmacy",
        "mean": "약국",
        "level": "MIDDLE",
        "lastUpdateDatetime": "2023.11.20 10:32",
        "lastUpdateUserNickName": "약국 길찾기"
    }
    ```
- 영어단어 등록
  - 사용자의 필요에 따라 주관적인 난이도 선정으로 단어 사전에 데이터 등록
  - `curl -X POST -H "Content-Type: application/json" -d '{"vocabulary": "apple","meaning":"사과","level":"element","registerNickname":"사과돌이"}' http://openapi.webservicetoday.com/api/open-api/word/eng`
  - Response Body
    | **field name** | **type** | **nullable** | **description** |
    | :--- | :--- | :---: | :--- |
    | vocabulary | string | X | 영어 단어 |
    | meaning | string | X | 영어 단어 뜻 |
    | level | string enum | X | 영어 단어 난이도<br>- element: 초등<br>- middle: 중등<br>- high: 고등 |
    | registerNickname | string | X | 등록자 닉네임 |
    
