package com.example.template.enums;

public enum TargetType {
    // 고립·은둔 청년
    ISOLATED_YOUTH("고립·은둔 청년", "Isolated Youth"),

    // 청년 일반
    GENERAL_YOUTH("청년 일반", "General Youth"),

    // 은둔형외톨이
    HIKIKOMORI("은둔형외톨이", "Hikikomori"),

    // 고립·은둔 지원자
    ISOLATION_APPLICANT("고립·은둔 지원자", "Isolation Applicant"),

    // 고립·은둔 청소년
    ISOLATED_TEENAGER("고립·은둔 청소년", "Isolated Teenager"),

    // 고립·은둔 중장년
    ISOLATED_MIDDLE_AGED("고립·은둔 중장년", "Isolated Middle-aged"),

    // 고립·은둔자 부모・가족
    ISOLATED_PARENTS_FAMILY("고립·은둔자 부모・가족", "Parents/Family of Isolated Individuals"),

    // 니트청년
    NEET_YOUTH("니트청년", "NEET Youth"),

    // 심리적 위기 청년
    YOUTH_IN_PSYCHOLOGICAL_CRISIS("심리적위기청년", "Youth in Psychological Crisis"),

    // 취약 청년
    VULNERABLE_YOUTH("취약 청년", "Vulnerable Youth"),

    // 학교 밖 청소년
    OUT_OF_SCHOOL_TEENAGER("학교밖청소년", "Out-of-School Teenager"),

    // 일반인
    GENERAL_PUBLIC("일반인", "General Public"),

    // 정신과 환자
    PSYCHIATRIC_PATIENT("정신과 환자", "Psychiatric Patient"),

    // 사업 담당자 및 기관
    PROGRAM_ADMIN_OR_ORGANIZATION("사업 담당자 및 기관", "Program Administrator or Organization"),

    // 서울시 고립·은둔청년지원사업 참여자
    SEOUL_ISOLATED_YOUTH_PARTICIPANT("서울시 고립·은둔청년지원사업 참여자", "Seoul Isolated Youth Program Participant");

    private final String koreanName;  // 한국어 이름
    private final String englishName; // 영어 이름

    // 생성자
    TargetType(String koreanName, String englishName) {
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

    // 한국어 이름으로 대상 찾기
    public static TargetType fromKoreanName(String koreanName) {
        for (TargetType target : TargetType.values()) {
            if (target.getKoreanName().equalsIgnoreCase(koreanName)) {
                return target;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + koreanName);
    }

    // 영어 이름으로 대상 찾기
    public static TargetType fromEnglishName(String englishName) {
        for (TargetType target : TargetType.values()) {
            if (target.getEnglishName().equalsIgnoreCase(englishName)) {
                return target;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + englishName);
    }
}
