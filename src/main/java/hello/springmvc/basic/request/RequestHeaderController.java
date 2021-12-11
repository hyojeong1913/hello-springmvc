package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * HTTP 요청 - 기본, 헤더 조회
 *
 * 애노테이션 기반의 스프링 컨트롤러는 다양한 파라미터를 지원
 */
@Slf4j
@RestController
public class RequestHeaderController {

    /**
     * HttpMethod
     * - HTTP 메서드를 조회
     *
     * Locale
     * - Locale 정보를 조회
     *
     * @RequestHeader MultiValueMap<String, String>
     * - 모든 HTTP 헤더를 MultiValueMap 형식으로 조회
     *
     * @RequestHeader("host")
     * - 특정 HTTP 헤더를 조회
     *
     * @CookieValue(value = "myCookie", required = false)
     * - 특정 쿠키를 조회 (required 옵션 : 필수 값 여부)
     *
     * MultiValueMap
     * - MAP 과 유사한데, 하나의 키에 여러 값을 받을 수 있음.
     * - HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용
     *   (keyA=value1&keyA=value2)
     *
     * @param request
     * @param response
     * @param httpMethod
     * @param locale
     * @param headerMap
     * @param host
     * @param cookie
     * @return
     */
    @RequestMapping("/headers")
    public String headers(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale,
            @RequestHeader MultiValueMap<String, String> headerMap,
            @RequestHeader("host") String host,
            @CookieValue(value = "myCookie", required = false) String cookie
            ) {

        log.info("request = {}", request);
        log.info("response = {}", response);
        log.info("httpMethod = {}", httpMethod);
        log.info("locale = {}", locale);
        log.info("headerMap = {}", headerMap);
        log.info("host = {}", host);
        log.info("cookie = {}", cookie);

        return "OK";
    }
}
