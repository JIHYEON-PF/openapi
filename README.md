# 랜덤한 영어단어 제공 서비스
> 교육부가 지정한 초등, 중등, 고등 교육과정에 필수 암기 영단으롤 일부 추출하여 데이터 마이그레이션 하였으며,
> 사용자의 주관적 난이도 판단에 따라 계속적으로 등록되는 영단어 목록 랜덤 조회 서비스 제공

### Domain: `http://openapi.webservicetoday.com`

### 생성된 API
| **Title** | **Path** | **Method** |
| :--- | :--- | :---:|
| 영어단어 난이도별 랜덤 조회 | `/api/open-api/random/word/eng` | GET |
| 영어단어 등록 | `/api/open-api/word/eng` | POST |
| 영어단어 수정 | `/api/open-api/word/eng/:vocabulary` | PATCH |

### API Description
- 영어단어 난이도별 랜덤 조회
  - DB에 등록되어 있는 단어 중 PathVariable에 입력한 난이도에 따라 단어 조회
  - `curl -v http://openapi.webservicetoday.com/api/open-api/random/word/eng?level=count=`
  - RequestParam
    | **field name** | **type** | **nullable** | **description** |
    | :--- | :--- | :---: | :--- |
    | level | string num | O | 조회 단어 레벨<br>- all: 전체 레벨 조회(Default Value)<br>- element: 초등<br>- middle: 중등<br>- high: 고등 |
    | count | int | O | 조회 단어 개수<br>- Default Value: 1<br>- 등록 단어보다 많이 요청할 경우 등록단어 전체 조회 |
  - Response Json Example
    ```json
    {
        "words": [
            {
                "vocabulary": "heavy",
                "mean": "무거운",
                "level": "ELEMENT",
                "lastUpdateDatetime": "2023.11.20 00:25",
                "lastUpdateUserNickName": "관리자"
            },
            {
                "vocabulary": "than",
                "mean": "~보다는",
                "level": "ELEMENT",
                "lastUpdateDatetime": "2023.11.20 00:25",
                "lastUpdateUserNickName": "관리자"
            }
        ]
    }
    ```
- 영어단어 등록
  - 사용자의 필요에 따라 주관적인 난이도 선정으로 단어 사전에 데이터 등록
  - `curl -X POST -H "Content-Type: application/json" -d '{"vocabulary": "apple","meaning":"사과","level":"element","registerNickname":"사과돌이"}' http://openapi.webservicetoday.com/api/open-api/word/eng`
  - Request Body
    | **field name** | **type** | **nullable** | **description** |
    | :--- | :--- | :---: | :--- |
    | vocabulary | string | X | 영어 단어 |
    | meaning | string | X | 영어 단어 뜻 |
    | level | string enum | X | 영어 단어 난이도<br>- element: 초등<br>- middle: 중등<br>- high: 고등 |
    | registerNickname | string | X | 등록자 닉네임 |
- 영어단어 수정
  - 등록되어 있는 단어를 사용자의 주관으로 의미, 난이도 등 정보 변경
  - `curl -X PATCH -H "Content-Type: application/json" -d '{"meaning": "황금사과", "level": "middle", "updateNickname": "아담"}' http://openapi.webservicetoday.com/api.open-api/word/eng/:vocabulary`
  - PathVariable
    - 기존에 등록되어 있는 단어
  - Request Body
    | **field name** | **type** | **nullable** | **description** |
    | :--- | :--- | :---: | :--- |
    | meaning | string | X | 영어 단어 뜻 |
    | level | string enum | X | 영어 단어 난이도<br>- element: 초등<br>- middle: 중등<br>- high: 고등 |
    | updateNickname | string | X | 수정자 닉네임 |
    
