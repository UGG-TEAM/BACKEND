package com.example.template.enums;

public enum ProgramType {
    // 프로그램
    PROGRAM("프로그램", "Program"),

    // 사업
    PROJECT("사업", "Project"),

    // 단체/기관
    ORGANIZATION("단체/기관", "Organization"),

    // 플랫폼
    PLATFORM("플랫폼", "Platform"),

    // 행사
    EVENT("행사", "Event"),

    // 소모임
    SMALL_GROUP("소모임", "Small Group");

    private final String koreanName;  // 한국어 이름
    private final String englishName; // 영어 이름

    // 생성자
    ProgramType(String koreanName, String englishName) {
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

    // 한국어 이름으로 카테고리 찾기
    public static ProgramType fromKoreanName(String koreanName) {
        for (ProgramType category : ProgramType.values()) {
            if (category.getKoreanName().equalsIgnoreCase(koreanName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + koreanName);
    }

    // 영어 이름으로 카테고리 찾기
    public static ProgramType fromEnglishName(String englishName) {
        for (ProgramType category : ProgramType.values()) {
            if (category.getEnglishName().equalsIgnoreCase(englishName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + englishName);
    }
}
