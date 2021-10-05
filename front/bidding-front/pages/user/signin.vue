<template>
  <div>
      <label for="input-auth-id">ID:</label>
      <b-form-input for="input-auth-id" v-model="authId"></b-form-input>
      <label for="input-auth-password">패스워드:</label>
      <b-form-input type="password" for="input-auth-password" v-model="authPassword"></b-form-input>
      <b-button @click="signIn">SignIn</b-button>
      <b-button href="/user/signup">SignUp</b-button>
      {{ getJaeyoung }}
  </div>
</template>

<script>
export default {
    
    data() {
    
        return {
            authId: "",
            authPassword: ""
        }
    },

    created() {
        //
    }, 

    mounted() {

    },

    methods: {

        signIn() {
            let data = new FormData()
            data.append('authId', this.authId)
            data.append('authPassword', this.authPassword)

            this.$axios.post("http://localhost:9090/v1/user/signin", data, {
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }).then(res => {
                const resultData = res.data
                console.log("jaeyoung log :", resultData)

                this.$store.commit("session/setUserInfo", resultData)
            }).catch(error => {
                console.log(error)
            })
        }
    },

    computed: {
        getJaeyoung() {
            return this.$store.getters.getUserInfo
        }    
    }

}
</script>

<style>
</style>