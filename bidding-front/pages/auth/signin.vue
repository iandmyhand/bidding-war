<template>
  <div>
      <label for="input-user-name">UserName:</label>
      <b-form-input for="input-group" v-model="userName" placeholder="Enter user name"></b-form-input>
      <label for="input-user-password">UserPassword:</label>
      <b-form-input type="password" for="input-user-password" v-model="password" placeholder="Enter user password"></b-form-input>
      <b-button @click="signin">SignIn</b-button>
      <b-button href="/user/signup">SignUp</b-button>
  </div>
</template>

<script>
export default {
    data(){
        return{
            userName:"",
            password:""
        }
    },
    methods:{
        signin(){
            let data = new FormData()
            data.append('user_name',this.userName)
            data.append('password',this.password)

            // const data = JSON.stringify(objData);
            this.$axios.post("http://localhost:8080/bidding/v1/auth/signin", data, {
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }).then(res=>{
                const resultData = res.data
                alert("signin user:\n"
                +resultData.user_id+", "+ resultData.latest_login_date
                )
                this.moveProduct()
            }).catch(error=>{
                console.log(error)
            })
        },
        moveProduct(){
            const hash = this.$route.hash;
            const path = '/product'
            this.$router.push({ path: path, hash: hash });
        },
    }
}
</script>

<style>

</style>