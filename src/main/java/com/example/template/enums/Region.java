package com.example.template.enums;

public enum Region {
    // 서울
    SEOUL("서울", "Seoul"),
    // 부산
    BUSAN("부산", "Busan"),
    // 대구
    DAEGU("대구", "Daegu"),

    // 인천
    INCHEON("인천", "Incheon"),

    // 광주
    GWANGJU("광주", "Gwangju"),

    // 대전
    DAEJEON("대전", "Daejeon"),

    // 울산
    ULSAN("울산", "Ulsan"),

    // 경기
    GYEONGGI("경기", "Gyeonggi"),

    // 강원
    GANGWON("강원", "Gangwon"),

    // 충남
    CHUNGNAM("충남", "Chungnam"),

    // 충북
    CHUNGBUK("충북", "Chungbuk"),

    // 전남
    JEONNAM("전남", "Jeonnam"),

    // 전북
    JEONBUK("전북", "Jeonbuk"),

    // 경남
    GYEONGNAM("경남", "Gyeongnam"),

    // 경북
    GYEONGBUK("경북", "Gyeongbuk"),

    // 제주
    JEJU("제주", "Jeju");

    private final String koreanName;  // 한국어 지역 이름
    private final String englishName; // 영어 지역 이름

    // 생성자
    Region(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }

    // 한국어 지역 이름 반환
    public String getKoreanName() {
        return koreanName;
    }

    // 영어 지역 이름 반환
    public String getEnglishName() {
        return englishName;
    }

    // 코드로 지역 찾기
    public static Region fromKoreanName(String koreanName) {
        for (Region region : Region.values()) {
            if (region.getKoreanName().equalsIgnoreCase(koreanName)) {
                return region;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + koreanName);
    }

    // 영어 이름으로 지역 찾기
    public static Region fromEnglishName(String englishName) {
        for (Region region : Region.values()) {
            if (region.getEnglishName().equalsIgnoreCase(englishName)) {
                return region;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + englishName);
    }
}
