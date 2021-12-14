package hello.springmvc.basic.request;

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
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * HTTP 요청 메시지 - 단순 텍스트
 *
 * HTTP message body 에 데이터를 직접 담아서 요청
 *  - HTTP API 에서 주로 사용, JSON, XML, TEXT
 *  - 데이터 형식은 주로 JSON 사용
 *  - POST, PUT, PATCH
 */
@Slf4j
@Controller
public class RequestBodyStringController {

    /**
     * HTTP 메시지 바디의 데이터를 InputStream 을 사용해서 직접 읽을 수 있다.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        response.getWriter().write("OK");
    }

    /**
     * Input, Output 스트림, Reader
     *
     * @param inputStream HTTP 요청 메시지 바디의 내용을 직접 조회
     * @param responseWriter HTTP 응답 메시지의 바디에 직접 결과 출력
     * @throws IOException
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        responseWriter.write("OK");
    }

    /**
     * HttpEntity
     *
     * HTTP header, body 정보를 편리하게 조회
     *
     * 응답에서도 HttpEntity 사용 가능
     *
     * view 를 조회하지 않고 메시지 바디 정보 직접 반환
     *
     * RequestEntity
     *  : HttpMethod, url 정보가 추가, 요청에서 사용
     *
     * ResponseEntity
     *  - HTTP 상태 코드 설정 가능, 응답에서 사용
     *  - 예) return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED)
     *
     * 스프링 MVC 내부에서 HTTP 메시지 바디를 읽어서 문자나 객체로 변환해서 전달해주는데,
     * 이 때 HTTP 메시지 컨버터 (HttpMessageConverter) 라는 기능을 사용
     *
     * @param httpEntity
     * @return
     * @throws IOException
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();

        log.info("messageBody = {}", messageBody);

        return new HttpEntity<>("OK");
    }

    /**
     * @RequestBody
     *  - HTTP 메시지 바디 정보를 편리하게 조회 가능
     *  - 헤더 정보가 필요하다면 HttpEntity 를 사용하거나 @RequestHeader 를 사용하면 된다.
     *
     * 요청 파라미터 vs HTTP 메시지 바디
     *  - 요청 파라미터를 조회하는 기능 : @RequestParam , @ModelAttribute
     *  - HTTP 메시지 바디를 직접 조회하는 기능 : @RequestBody
     *
     * @ResponseBody
     *  - view 를 사용하지 않고 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달 가능
     *
     * @param messageBody
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody = {}", messageBody);

        return "OK";
    }
}
