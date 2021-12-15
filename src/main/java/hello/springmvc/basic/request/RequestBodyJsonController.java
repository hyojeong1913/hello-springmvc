package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTTP 요청 메시지 - JSON
 *
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * HttpServletRequest 를 사용해서 직접 HTTP 메시지 바디에서 데이터를 읽어와서, 문자로 변환
     *
     * 문자로 된 JSON 데이터를 Jackson 라이브러리인 objectMapper 를 사용해서 자바 객체로 변환
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("OK");
    }

    /**
     * @RequestBody 문자 변환
     *
     * @RequestBody 를 사용해서 HTTP 메시지에서 데이터를 꺼내고 messageBody 에 저장
     * 문자로 된 JSON 데이터인 messageBody 를 objectMapper 를 통해서 자바 객체로 변환
     *
     * @param messageBody
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "OK";
    }

    /**
     * @RequestBody 객체 변환
     *
     * @RequestBody 에 직접 만든 객체를 지정 가능
     *
     * HttpEntity , @RequestBody 를 사용하면 HTTP 메시지 컨버터가 HTTP 메시지 바디의 내용을 문자나 객체 등으로 변환 가능
     *
     * @param helloData
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "OK";
    }

    /**
     * HttpEntity 사용
     *
     * @param httpEntity
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {

        HelloData data = httpEntity.getBody();

        log.info("username = {}, age = {}", data.getUsername(), data.getAge());

        return "OK";
    }

    /**
     * 응답의 경우에도 @ResponseBody 를 사용하면 해당 객체를 HTTP 메시지 바디에 직접 넣어줄 수 있다.
     *
     * @RequestBody 요청
     *  : JSON 요청 -> HTTP 메시지 컨버터 -> 객체
     *
     * @ResponseBody 응답
     *  : 객체 -> HTTP 메시지 컨버터 -> JSON 응답
     * 
     * @param data
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {

        log.info("username = {}, age = {}", data.getUsername(), data.getAge());

        return data;
    }
}
