package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP 응답 - HTTP API, 메시지 바디에 직접 입력
 *
 * HTTP API 를 제공하는 경우에는 HTML 이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON 같은 형식으로 데이터를 실어 보낸다.
 *
 * @RestController 어노테이션
 *  : 해당 컨트롤러에 모두 @ResponseBody 가 적용
 *  : 뷰 템플릿을 사용하는 것이 아니라, HTTP 메시지 바디에 직접 데이터를 입력
 */
@Slf4j
@Controller
public class ResponseBodyController {

    /**
     * HttpServletResponse 객체를 통해서 HTTP 메시지 바디에 직접 OK 응답 메시지를 전달
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {

        response.getWriter().write("OK");
    }

    /**
     * ResponseEntity 엔티티는 HttpEntity 를 상속 받고 있어 HttpEntity 는 HTTP 메시지의 헤더, 바디 정보를 가지고 있다.
     *
     * ResponseEntity 는 여기에 더해서 HTTP 응답 코드를 설정 가능
     *
     * @return
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * @ResponseBody 를 사용하면 view 를 사용하지 않고, HTTP 메시지 컨버터를 통해서 HTTP 메시지를 직접 입력 가능
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {

        return "OK";
    }

    /**
     * ResponseEntity 를 반환
     *
     * HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환되어서 반환
     *
     * @return
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {

        HelloData helloData = new HelloData();

        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * @ResponseStatus(HttpStatus.OK) 애노테이션을 사용하면 응답 코드도 설정 가능
     *  => 애노테이션이기 때문에 응답 코드를 동적으로 변경 불가능
     * 
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {

        HelloData helloData = new HelloData();

        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }
}
