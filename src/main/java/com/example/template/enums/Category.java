package com.example.template.enums;

public enum Category {
    // TV 프로그램
    TV_PROGRAM("TV프로그램", "TV Program"),

    // 뉴스 기사
    NEWS_ARTICLE("뉴스 기사", "News Article"),

    // 가족/부모
    FAMILY_PARENT("가족/부모", "Family/Parent"),

    // 교육/연구
    EDUCATION_RESEARCH("교육/연구", "Education/Research"),

    // 지원가 지원
    SUPPORT_PROVISION("지원가 지원", "Support Provision"),

    // 고립·은둔 상담
    ISOLATION_COUNSELING("고립·은둔상담", "Isolation Counseling"),

    // 취업
    EMPLOYMENT("취업", "Employment"),

    // 서포터/봉사
    SUPPORT_VOLUNTEERING("서포터/봉사", "Support/Volunteering"),

    // 마음건강
    MENTAL_HEALTH("마음건강", "Mental Health"),

    // 청소년
    YOUTH("청소년", "Youth"),

    // 심리상담
    PSYCHOLOGICAL_COUNSELING("심리상담", "Psychological Counseling"),

    // 문화/예술
    CULTURE_ART("문화/예술", "Culture/Art"),

    // 커뮤니티
    COMMUNITY("커뮤니티", "Community"),

    // 여행/캠프
    TRAVEL_CAMP("여행/캠프", "Travel/Camp"),

    // 공모전
    CONTEST("공모전", "Contest"),

    // 테스트/검사
    TEST_ASSESSMENT("테스트/검사", "Test/Assessment"),

    // 세미나
    SEMINAR("세미나", "Seminar"),

    // 포럼/토론회
    FORUM_DISCUSSION("포럼/토론회", "Forum/Discussion"),

    // 당사자 자조모임
    SELF_HELP_GROUP("당사자 자조모임", "Self-Help Group"),

    // 모임공간
    MEETING_SPACE("모임공간", "Meeting Space"),

    // 취미/모임
    HOBBY_MEETING("취미/모임", "Hobby/Meeting"),

    // 종합청년지원
    COMPREHENSIVE_YOUTH_SUPPORT("종합청년지원", "Comprehensive Youth Support"),

    // 지원정보제공
    SUPPORT_INFORMATION("지원정보제공", "Support Information"),

    // 진로
    CAREER("진로", "Career"),

    // 주거
    HOUSING("주거", "Housing"),

    // 금융/경제
    FINANCE_ECONOMY("금융/경제", "Finance/Economy"),

    // 멘토링
    MENTORING("멘토링", "Mentoring"),

    // 공동생활
    SHARED_LIVING("공동생활", "Shared Living"),

    // 정책
    POLICY("정책", "Policy"),

    // 나눔
    SHARING("나눔", "Sharing"),

    // 대안학교
    ALTERNATIVE_SCHOOL("대안학교", "Alternative School"),

    // 여성/퀴어
    WOMEN_QUEER("여성/퀴어", "Women/Queer"),

    // 경제적 지원
    FINANCIAL_SUPPORT("경제적 지원", "Financial Support"),

    // 도전과제 프로그램
    CHALLENGE_PROGRAM("도전과제프로그램", "Challenge Program"),

    // 일경험지원
    WORK_EXPERIENCE_SUPPORT("일경험지원", "Work Experience Support"),

    // 채용
    RECRUITMENT("채용", "Recruitment");

    private final String koreanName;  // 한국어 이름
    private final String englishName; // 영어 이름

    // 생성자
    Category(String koreanName, String englishName) {
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
    public static Category fromKoreanName(String koreanName) {
        for (Category category : Category.values()) {
            if (category.getKoreanName().equalsIgnoreCase(koreanName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + koreanName);
    }

    // 영어 이름으로 카테고리 찾기
    public static Category fromEnglishName(String englishName) {
        for (Category category : Category.values()) {
            if (category.getEnglishName().equalsIgnoreCase(englishName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + englishName);
    }
}
