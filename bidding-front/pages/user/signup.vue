<template>
  <div>
      <label for="input-user-name">UserName:</label>
      <b-form-input for="input-group" v-model="userName" placeholder="Enter user name"></b-form-input>
      <label for="input-user-password">UserPassword:</label>
      <b-form-input type="password" for="input-user-password" v-model="userPassword" placeholder="Enter user password"></b-form-input>
      <b-button @click="signUp">SignUp</b-button>
  </div>
</template>

<script>
export default {
    data(){
        return{
            userName:"",
            userPassword:""
        }
    },
    methods:{
        signUp(){
            const objData = {
                user_name:this.userName,
                user_password:this.userPassword
            }
            const data = JSON.stringify(objData);
            this.$axios.post("http://localhost:8081/bidding/v1/user/signup", data, {
                headers: { 'Content-Type': 'application/json;charset=UTF-8' },
                withCredentials: true
            }).then(res=>{
                this.userName = ""
                this.userPassword = ""
                const resultData = res.data
                alert("create user:\n"
                +resultData.user_id+", "+ resultData.user_name+", "+ resultData.created_at
                )
            }).catch(error=>{
                console.log(error)
            })
        }
    }
}
</script>

<style>

</style>
