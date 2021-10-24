<template>
    <div>
        <label for="input-user-id">ID:</label>
        <b-form-input for="input-user-id" v-model="userId"></b-form-input>
        <label for="input-user-name">사용자 이름:</label>
        <b-form-input for="input-user-name" v-model="userName"></b-form-input>
        <label for="input-user-nick">닉네임:</label>
        <b-form-input for="input-user-nick" v-model="userNick"></b-form-input>
        <label for="input-user-password">패스워드:</label>
        <b-form-input type="password" for="input-user-password" v-model="userPassword"></b-form-input>
        <b-button @click="signUp">SignUp</b-button>
    </div>
</template>

<script>
export default {
    
    data() {
        
        return {
            userId: "",
            userPassword: "",
            userName: "",
            userNick: ""
        }
    },

    created() {
        //
    },

    methods: {
        signUp() {
            const paramObj = {
                user_id: this.userId,
                user_nick: this.userNick,
                user_name: this.userName,
                user_password: this.userPassword
            }

            const data = JSON.stringify(paramObj);

            this.$axios.post("http://localhost:9090/v1/user/signup", data, {
                headers: { 'Content-Type': 'application/json;charset=UTF-8' }
            }).then(res => {
                console.log("jaeyoung result", resultData)
                
                const resultData = res.data
                alert(resultData.user_name + "님 회원가입이 완료되었습니다.")

                this.$router.push({ path: "/user/signin" })
            }).catch(error => {
                console.log(error)
                /**
                 * 에러코드별 alert 메세지 return 단, validation이 없어서 발생하는 케이스가 대부분으로
                 * 그냥 id 또는 패스워드 불일치 정도로만 나타냄
                 * 500 error 서버 에러를 제외하고는 위 메시지 처리
                 * --> 프론트 에러 메시지 const 정의는 @TODO 사항으로 // 저장되는 사항은 확인완료!
                 */
                if (error.code == 500) {
                    alert("서버 error")
                } else {
                    alert("아이디 또는 패스워드가 불일치합니다.")
                }                
            })
        }
    }
}
</script>

<style>

</style>