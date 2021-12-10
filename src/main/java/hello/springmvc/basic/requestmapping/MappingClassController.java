package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

/**
 * 회원 관리 API
 */
@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * 회원 목록 조회
     *
     * @return
     */
    @GetMapping
    public String user() {

        return "get users";
    }

    /**
     * 회원 등록
     *
     * @return
     */
    @PostMapping
    public String addUser() {

        return "post user";
    }

    /**
     * 회원 조회
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {

        return "get userId = " + userId;
    }

    /**
     * 회원 수정
     *
     * @param userId
     * @return
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {

        return "update userId = " + userId;
    }

    /**
     * 회원 삭제
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {

        return "delete userId = " + userId;
    }
}
