<template>
  <div>
      <a :href="`/goods/regist`">상품등록</a>
      <b-table striped hover :items="goods">
        <template #cell(goods_id)="data">
            <a :href="`/goods/modify/${data.value}`">{{ data.value }}</a>
        </template>
      </b-table>
      <b-button @click="signOut">SignOut</b-button>
  </div>
</template>

<script>
export default {

    data() {

        return { 
            goods: {}
        }
    },

    created() {
        this.authCheck()
        this.load()
    },

    computed() {
        
    },

    methods:{
        async load() {

            await this.$axios.get("http://localhost:9090/goods/list", {
                params:{
                    nowPage: 1,
                    rows: 10,
                    searchName: "",
                    searchType: "A"
                }
            })
            .then(res=> {
                // 페이징 아직 미처리
                console.log(res.data)
                this.goods = res.data  
            })
            .catch(error=>{
                console.log(error)
            })
        },

        async authCheck() { // 원래는 middleware 인터셉터로 빼야할듯 싶지만.. 페이지별 정의하는것으로 마무리
            const authSession = this.$cookiz.get("authSession")
            console.log("authSession:", authSession)
            
            if (typeof authSession === "undefined") {
                alert("로그인이 필요합니다.")
                this.$router.push({ path: "/user/signin" })
            }
        },

        async signOut() {
            this.$cookiz.remove("authSession")
            this.$router.push({ path: "/user/signin" })
        }
    }
}
</script>

<style>

</style>