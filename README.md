### 경매 사이트  (bidding-war) 만들기 with Kotlin Spring and React

#### 회원가입 화면 추가
- 회원가입 화면 추가
  ![](https://images.velog.io/images/sungjun-jin/post/f6c18831-6abb-4d43-bfcc-443ea4173d68/image.png)

#### 회원가입 시 중복회원명 검증 추가
- 프론트 코드
- 회원명으로 중복회원명 검증, 중복 회원시 window.alert()
```javascript
    const signUp = async () => {
        try {
            console.log('sign-up!')
            console.log(inputName.current.value)
            console.log(inputPassword.current.value)

            await signUpUser({
                'user_name': inputName.current.value,
                'password': inputPassword.current.value,
            })
        } catch (error) {
            window.alert("존재하는 ID 입니다.")
        }
    }
```

![](https://images.velog.io/images/sungjun-jin/post/f40d424d-548d-4fe4-b6df-6e3a47ded1d0/image.png)

- 코틀린 서비스 코드
```kotlin
@Service
@Transactional
class BiddingWarUserService(val repository: BiddingWarUserRepository) {
    fun signUP(user: User): Boolean {
        val signedUpUsers: MutableIterable<User> = getAll()

        for(signedUpUser in signedUpUsers) {
            if (signedUpUser.user_name == user.user_name) {
                return false
            }
        }

        repository.save(user)
        return true
    }

    fun getAll() = repository.findAll()
}
```
#### 회원가입 성공 시

![](https://images.velog.io/images/sungjun-jin/post/202ba35e-8e90-411a-a377-4e7935e6e91c/image.png)
- console.log
  ![](https://images.velog.io/images/sungjun-jin/post/d9622db8-9ca1-431a-a829-426a3b62baad/image.png)
- 인메모리 DB(H2 database)에 저장
  ![](https://images.velog.io/images/sungjun-jin/post/58714754-3fbe-47ef-a993-2b17910dd54e/image.png)


