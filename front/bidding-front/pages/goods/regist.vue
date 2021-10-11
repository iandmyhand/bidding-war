<template>
    <div>
        <label for="input-name">Name:</label>
        <b-form-input for="input-name" v-model="name" placeholder="Enter product name"></b-form-input>
        <label for="input-price">Price:</label>
        <b-form-input for="input-bid-price" v-model="bidPrice" placeholder="Enter product bid price"></b-form-input>
        <label for="input-price">Min-Price:</label>
        <b-form-input for="input-buy-price" v-model="buyPrice" placeholder="Enter product buy price"></b-form-input>
        <select v-model="category">
            <option disabled value="">Please select one</option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
        </select>
        <label for="input-content">본문:</label>
        <b-form-input for="input-content" v-model="content" placeholder="Enter product desc"></b-form-input>
        <b-button @click="registGoods">상품등록</b-button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            name: "",
            buyPrice: 0,
            bidPrice: 0,
            category: "",
            content: ""
        }
    },

    created() {
        //
        this.authCheck()
    },

    methods: {
        
        async registGoods() {
            
            const paramObj = {
                goods_name: this.name,
                goods_bid_price: this.bidPrice,
                goods_buy_price: this.buyPrice,
                goods_category: this.category,
                goods_content: this.content
            };

            const data = JSON.stringify(paramObj);
            await this.$axios.post("http://localhost:9090/goods/regist", data,
                {
                    headers: { 'Content-Type': 'application/json;charset=UTF-8' }
                }
            ).then(res => {
                console.log(res.data)
                alert("success regist goods")
            }).catch(error => {
                console.log(error)
            })
        },

        async authCheck() { // 원래는 middleware 인터셉터로 빼야할듯 싶지만.. 페이지별 정의하는것으로 마무리
            const authSession = this.$cookiz.get("authSession")
            
            if (typeof authSession === "undefined") {
                alert("로그인이 필요합니다.")
                this.$router.push({ path: "/user/signin" })
            }
        }
    }

}
</script>

<style>

</style>