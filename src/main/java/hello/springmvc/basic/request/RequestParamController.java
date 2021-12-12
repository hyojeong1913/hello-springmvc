package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 요청 파라미터 - 쿼리 파라미터, HTML Form
     *
     * HttpServletRequest 의 request.getParameter() 를 사용하여 요청 파라미터를 조회
     *
     * 요청 파라미터 (request parameter) 조회
     * : GET 쿼리 파리미터 전송 방식이든, POST HTML Form 전송 방식이든 둘다 형식이 같으므로 구분없이 조회 가능
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // HttpServletRequest 가 제공하는 방식으로 요청 파라미터를 조회
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}", username);
        log.info("age = {}", age);

        response.getWriter().write("OK");
    }

    /**
     * HTTP 요청 파라미터 - @RequestParam
     *
     * 스프링이 제공하는 @RequestParam 을 사용하여 요청 파라미터를 매우 편리하게 사용 가능
     *
     * @RequestParam
     *  : 파라미터 이름으로 바인딩
     *
     * @ResponseBody
     *  : View 조회를 무시하고, HTTP message body 에 직접 해당 내용 입력
     *
     * @param memberName
     * @param memberAge
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {

        log.info("username = {}", memberName);
        log.info("age = {}", memberAge);

        return "OK";
    }

    /**
     * HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
     *
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {

        log.info("username = {}", username);
        log.info("age = {}", age);

        return "OK";
    }

    /**
     * String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
     *
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username = {}", username);
        log.info("age = {}", age);

        return "OK";
    }

    /**
     * 파라미터 필수 여부 - requestParamRequired
     *
     * @RequestParam.required
     *  : 파라미터 필수 여부
     *  : 기본값 = 파라미터 필수 (true)
     *
     * 파라미터 이름만 있고 값이 없는 경우 빈문자로 에러 X
     *  : 예) /request-param?username=
     *
     * null 을 int 에 입력하는 것은 불가능하므로 null 을 받을 수 있는 Integer 로 변경하거나 또는 defaultValue 사용
     *
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
    ) {

        log.info("username = {}", username);
        log.info("age = {}", age);

        return "OK";
    }

    /**
     * 기본 값 적용 - requestParamDefault
     *
     * 파라미터에 값이 없는 경우 defaultValue 를 사용하면 기본 값을 적용 가능
     *  => required 는 의미가 없다.
     *
     * defaultValue 는 빈 문자의 경우에도 설정한 기본 값이 적용
     *  : 예) /request-param?username=
     *
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {

        log.info("username = {}", username);
        log.info("age = {}", age);

        return "OK";
    }

    /**
     * 파라미터를 Map 으로 조회하기 - requestParamMap
     *
     * 파라미터를 Map, MultiValueMap 으로 조회 가능
     *
     * @RequestParam Map
     *  : Map(key=value)
     *
     * @RequestParam MultiValueMap
     *  : MultiValueMap(key=[value1, value2, ...]
     *  : 예) (key=userIds, value=[id1, id2])
     *
     * 파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용
     *
     * @param paramMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username = {}", paramMap.get("username"));
        log.info("age = {}", paramMap.get("age"));

        return "OK";
    }
}
