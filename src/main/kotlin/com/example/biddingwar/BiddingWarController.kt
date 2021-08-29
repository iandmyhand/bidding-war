package com.example.biddingwar

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/bidding-war")
class BiddingWarController(val service: BiddingWarService) {

    @GetMapping
    fun products() = ResponseEntity.ok(service.getAll())

    @GetMapping("/{id}")
    fun product(@PathVariable id: Long) = service.get(id)

    @PostMapping
    fun create(@RequestBody product: Product, request: HttpServletRequest) : ResponseEntity<String> {
        val result = service.save(product, request)

        return if (result) {
            ResponseEntity.ok(
                "PID: ${product.id} (${product.name})이 등록 되었습니다."
            )
        } else {
            ResponseEntity.status(404).body("상품 등록에 실패했습니다.")
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = if (service.delete(id)) {
        ResponseEntity.ok("PID: ${id}가 삭제 되었습니다.")
    } else {
        ResponseEntity.status(404).body("존재하지 않는 PID: ${id} 입니다.")
    }
}

@RestController
@RequestMapping("/bidding-war/users")
class BiddingWarUserController(val service: BiddingWarUserService) {

    @GetMapping
    fun users() = ResponseEntity.ok(service.getAll())

    @PostMapping
    fun signUp(@RequestBody user: User) : ResponseEntity<String> {
        val result = service.signUP(user)

        if (!result) {
            return ResponseEntity.status(400).body("중복 ID 입니다.")
        }

        return ResponseEntity.ok(
                "회원가입 성공."
        )
    }

    @PostMapping("/login")
    fun signIn(@RequestBody user: User, request: HttpServletRequest): ResponseEntity<String> {

        val user = service.signIn(user, request)

        return if (user != null) {
            ResponseEntity.ok("로그인 성공")
        } else {
            ResponseEntity.status(401).body("로그인 실패")
        }

    }
}