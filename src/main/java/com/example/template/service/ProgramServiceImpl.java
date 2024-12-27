package com.example.template.service;

import com.example.template.common.exception.handler.GeneralHandler;
import com.example.template.common.response.status.ErrorCode;
import com.example.template.dto.HomeProgramDTO;
import com.example.template.dto.RecommendProgramDTO;
import com.example.template.dto.RecommendProgramRequestDTO;
import com.example.template.dto.RecommendProgramResponseDTO;
import com.example.template.entity.Program;
import com.example.template.repository.ProgramRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final RestTemplate restTemplate;

    public List<HomeProgramDTO> getHomePrograms(int page) {

        Pageable pageable = PageRequest.of(page, 5);

        return toHomeProgramDTO(programRepository.findAllByOrderByPostDateDesc(pageable).getContent());
    }

    public List<RecommendProgramDTO> getRecommendPrograms(String category) {
        Pageable pageable = PageRequest.of(0, 5);

        return toRecommendProgramDTO(programRepository.findAllByCategoryContains(category, pageable).getContent());
    }

    public List<String> getComment(String type){

        List<String> commentList = new ArrayList<>();

        return switch (type) {
            case "은둔형 고립군" -> {
                commentList.add("당신은 오랜 시간 혼자만의 공간에서 많은 상처를 안고 살아왔군요.");
                commentList.add( "당신은 혼자 있는 시간이 많아지고, 타인과의 관계에서 어려움을 느끼며 살아왔을지도 몰라요.  이전부터 혼자 지내는 시간이 많았던 만큼, 마음에 쌓인 이야기들도 많을 것 같아요.  천천히 마음의 짐을 내려놓고, 편안함을 느낄 수 있는 시간을 가질 수 있기를 바랍니다.");
                yield commentList; // `yield`를 사용하여 값을 반환
            }
            case "좌절형 고립군" -> {
                commentList.add("당신은 삶의 이행기에서 큰 어려움과 좌절을 겪었군요.");
                commentList.add("삶에서 중요한 시기에 어려운 경험들을 겪으셨을 것 같아요. 사회에서의 도전이 힘들어질 때 누구나 지칠 수 있는 법이에요. 지금은 잠시 쉬어가며, 스스로를 돌볼 수 있는 시간을 가질 수 있으면 좋겠어요.");
                yield commentList;
            }
            case "관계단절형 고립군" -> {
                commentList.add("당신은 사람들과의 단절로 인해 깊은 외로움을 느끼고 있군요.");
                commentList.add( "대인관계가 어려워지면서 혼자가 되는 시간이 길어졌을 것 같아요. 사람들과의 관계를 다시 시작하는 건 누구에게나 용기가 필요한 일이죠. 작은 연결부터 시작해 조금씩 따뜻한 관계를 만들어갈 수 있기를 응원합니다.");
                yield commentList;
            }
            case "의존형 고립군" -> {
                commentList.add("당신은 무기력과 나아가기 어려운 현실 속에서 힘들어하고 있군요.");
                commentList.add( "일상에서 무기력함을 느끼며 집 밖으로 나가는 일이 힘들었을 수도 있어요. 그럴 때일수록 스스로를 몰아세우기보다, 충분히 쉬며 에너지를 채우는 게 중요하답니다. 조금씩 작은 변화부터 시도하며 자신만의 속도로 나아갈 수 있기를 바랍니다.");
                yield commentList;
            }
            default -> throw new GeneralHandler(ErrorCode._BAD_REQUEST);
        };
    }

    private List<HomeProgramDTO> toHomeProgramDTO(List<Program> programs) {

        log.info("program 데이터베이스에서 조회 : ", programs.size());

        return programs.stream()
                .map(program -> HomeProgramDTO.builder()
                        .title(program.getTitle())
                        .region(program.getRegion())
                        .category(parse(program.getCategory()))
                        .target(parse(program.getTargetType()))
                        .type(parse(program.getProgramType()))
                        .content(program.getContent())
                        .build())
                .toList();
    }

    private List<RecommendProgramDTO> toRecommendProgramDTO(List<Program> programs) {

        log.info("추천 program 데이터베이스에서 조회 : ", programs.size());

        return programs.stream()
                .map(program -> RecommendProgramDTO.builder()
                        .title(program.getTitle())
                        .content(program.getContent())
                        .build())
                .toList();
    }

    public RecommendProgramResponseDTO getRecommendPrograms(RecommendProgramRequestDTO recommendProgramRequestDTO) {
        String url = "https://2cc8-34-86-34-40.ngrok-free.app/predict";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RecommendProgramRequestDTO> entity = new HttpEntity<>(recommendProgramRequestDTO, headers);

        // 서버 응답을 String[]로 받아옴
        String[] data = restTemplate.postForObject(url, entity, String[].class);

        RecommendProgramResponseDTO recommendProgramResponseDTO = new RecommendProgramResponseDTO();

        // 응답 데이터로 RecommendProgramDTO 리스트를 설정
        recommendProgramResponseDTO.setRecommendProgramDTOList(getRecommendPrograms(data[0]));
        recommendProgramResponseDTO.setComment(getComment(data[1]));

        log.info("Response: {}", recommendProgramResponseDTO); // 로그 출력 개선

        return recommendProgramResponseDTO;
    }

    private String parse(String input) {
        if (input == null || input.isEmpty()) {
            return ""; // 빈 문자열이나 null 처리
        }
        int commaIndex = input.indexOf(',');
        // 쉼표가 없으면 전체 문자열을 반환
        if (commaIndex == -1) {
            return input;
        }
        // 쉼표 이전까지의 부분 문자열을 반환
        return input.substring(0, commaIndex);
    }
}
