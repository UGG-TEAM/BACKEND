package com.example.template.enums;

public enum HostType {
    // 민간
    PRIVATE("민간", "Private"),

    // 지자체/공공서비스
    LOCAL_GOVERNMENT("지자체/공공서비스", "Local Government/Public Service"),

    // 국가사업
    NATIONAL_PROJECT("국가사업", "National Project"),

    // 개인/그룹
    INDIVIDUAL_OR_GROUP("개인/그룹", "Individual/Group");

    private final String koreanName;  // 한국어 이름
    private final String englishName; // 영어 이름

    // 생성자
    HostType(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }

    // 한국어 이름 반환
    public String getKoreanName() {
        return koreanName;
    }

    // 영어 이름 반환
    public String getEnglishName() {
        return englishName;
    }

    // 한국어 이름으로 주최측 유형 찾기
    public static HostType fromKoreanName(String koreanName) {
        for (HostType type : HostType.values()) {
            if (type.getKoreanName().equalsIgnoreCase(koreanName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + koreanName);
    }

    // 영어 이름으로 주최측 유형 찾기
    public static HostType fromEnglishName(String englishName) {
        for (HostType type : HostType.values()) {
            if (type.getEnglishName().equalsIgnoreCase(englishName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + englishName);
    }
}
