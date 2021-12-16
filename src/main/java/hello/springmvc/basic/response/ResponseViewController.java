package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * HTTP 응답 - 정적 리소스, 뷰 템플릿
 *
 * 스프링(서버)에서 응답 데이터를 만드는 방법
 *  - 정적 리소스
 *   : 예) 웹 브라우저에 정적인 HTML, css, js 을 제공할 때는, 정적 리소스를 사용
 *  - 뷰 템플릿 사용
 *   : 예) 웹 브라우저에 동적인 HTML 을 제공할 때는 뷰 템플릿을 사용
 *  - HTTP 메시지 사용
 *   : HTTP API 를 제공하는 경우에는 HTML 이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON 같은 형식으로 데이터를 실어 보낸다.
 *
 * 정적 리소스
 *  - 스프링 부트는 클래스패스의 다음 디렉토리에 있는 정적 리소스를 제공
 *   ( /static , /public , /resources , /META-INF/resources )
 *  - src/main/resources : 리소스를 보관하는 곳이고, 클래스패스의 시작 경로
 *  - 정적 리소스는 해당 파일을 변경 없이 그대로 서비스하는 것
 *
 * 뷰 템플릿
 *  - 뷰 템플릿을 거쳐서 HTML 이 생성되고, 뷰가 응답을 만들어서 전달
 *  - 스프링 부트는 기본 뷰 템플릿 경로를 제공
 */
@Controller
public class ResponseViewController {

    /**
     * 뷰 템플릿을 호출
     *
     * @return
     */
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {

        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    /**
     * String 을 반환하는 경우 - View or HTTP 메시지
     *
     * @ResponseBody 가 없으면 response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 한다.
     * @ResponseBody 가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello 라는 문자가 입력된다.
     *
     * 뷰의 논리 이름인 response/hello 를 반환하면 다음 경로의 뷰 템플릿이 렌더링 되는 것을 확인 가능
     *
     * @param model
     * @return
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {

        model.addAttribute("data", "hello!");

        return "response/hello";
    }

    /**
     * @Controller 를 사용하고, HttpServletResponse , OutputStream(Writer) 같은 HTTP 메시지 바디를 처리하는 파라미터가 없으면
     * 요청 URL 을 참고해서 논리 뷰 이름으로 사용
     *
     * => 명시성이 너무 떨어지고 이렇게 딱 맞는 경우도 많이 없어서, 권장X
     *
     * @param model
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {

        model.addAttribute("data", "hello!");
    }
}
