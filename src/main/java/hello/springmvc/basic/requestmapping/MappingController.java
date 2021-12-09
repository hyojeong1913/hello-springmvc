package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 요청 매핑
 */
@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 요청
     *
     * @RequestMapping 에 method 속성으로 HTTP 메서드를 지정하지 않으면 HTTP 메서드와 무관하게 호출된다.
     * HTTP 메서드 모두 허용 (GET, HEAD, POST, PUT, PATCH, DELETE)
     *
     * 대부분의 속성을 배열[] 로 제공하므로 다중 설정이 가능
     *  - 예) {"/hello-basic", "/hello-go"}
     *
     * @return
     */
    @RequestMapping("/hello-basic")
    public String helloBasic() {

        log.info("helloBasic");

        return "OK";
    }

    /**
     * method 특정 HTTP 메서드 요청만 허용
     *
     * @return
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {

        log.info("mappingGetV1");

        return "OK";
    }

    /**
     * HTTP 메서드 매핑 축약
     *
     * 종류
     *  - @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping
     *
     * 장점
     *  - HTTP 메서드를 축약한 애노테이션을 사용하는 것이 더 직관적
     *
     * @return
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {

        log.info("mappingGetV2");

        return "OK";
    }

    /**
     * PathVariable (경로 변수) 사용
     *
     * 변수명이 같으면 생략 가능
     *  - 예) @PathVariable("userId") String userId -> @PathVariable userId
     *
     * @param data
     * @return
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {

        log.info("mappingPath userId = {}", data);

        return "OK";
    }

    /**
     * PathVariable 사용 - 다중
     *
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("/mapping/user/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {

        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);

        return "OK";
    }

    /**
     * 특정 파라미터 조건 매핑
     *
     * 특정 파라미터가 있거나 없는 조건을 추가 가능
     *  - 예)
     *      params="mode"
     *      params="!mode"
     *      params="mode=debug"
     *      params="mode!=debug" (! = )
     *      params = {"mode=debug","data=good"}
     *
     * @return
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {

        log.info("mappingParam");

        return "OK";
    }

    /**
     * 특정 헤더 조건 매핑
     *
     * 파라미터 매핑과 비슷하지만, HTTP 헤더를 사용
     *  - 예)
     *      headers="mode"
     *      headers="!mode"
     *      headers="mode=debug"
     *      headers="mode!=debug" (! = )
     *
     * @return
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {

        log.info("mappingHeader");

        return "OK";
    }

    /**
     * 미디어 타입 조건 매핑 - HTTP 요청 Content-Type, consume
     *
     * HTTP 요청의 Content-Type 헤더를 기반으로 미디어 타입으로 매핑
     *  - 예)
     *      consumes="application/json"
     *      consumes="!application/json"
     *      consumes="application/*"
     *      consumes="*\/*"
     *      MediaType.APPLICATION_JSON_VALUE
     *
     * 만약 맞지 않으면 HTTP 415 상태코드 (Unsupported Media Type) 을 반환
     *
     * @return
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {

        log.info("mappingConsumes");

        return "OK";
    }

    /**
     * 미디어 타입 조건 매핑 - HTTP 요청 Accept, produce
     *
     * HTTP 요청의 Accept 헤더를 기반으로 미디어 타입으로 매핑
     *  - 예)
     *      produces = "text/html"
     *      produces = "!text/html"
     *      produces = "text/*"
     *      produces = "*\/*"
     *
     * 만약 맞지 않으면 HTTP 406 상태코드 (Not Acceptable) 을 반환
     *
     * @return
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {

        log.info("mappingProduces");

        return "OK";
    }
}
