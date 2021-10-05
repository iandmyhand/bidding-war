<template>
  <div>
      <a :href="`/goods/regist`">상품등록</a>
      <b-table striped hover :items="goods">
        <template #cell(goods_id)="data">
            <a :href="`/goods/modify/${data.value}`">{{ data.value }}</a>
        </template>
      </b-table>
  </div>
</template>

<script>
export default {

    data() {

        return { 
            goods: {}
        }
    },

    computed() {
        //  여기에 쓰일 것은 아니지만..
        let user = this.$store.getters("session/getUserInfo")
        console.log("user:", user)

        if (!user) {
            console.log("로그인 하지 않은 유저입니다.")
        } else {
            console.log("로그인 완료!")
        }

        this.load()
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
        }
    }
}
</script>

<style>

</style>