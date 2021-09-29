<template>
    <div>
        <h3>goods id: {{goods.goods_id}}</h3>
        <b-form-input hidden for="input-name" v-model="id" placeholder="Enter product name"></b-form-input>
        <label for="input-name">Name:</label>
        <b-form-input for="input-name" v-model="name" placeholder="Enter product name"></b-form-input>
        <label for="input-price">Price:</label>
        <b-form-input for="input-price" v-model="price" placeholder="Enter product price"></b-form-input>
        <select v-model="category">
            <option disabled value="">Please select one</option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
        </select>
        <label for="input-content">본문:</label>
        <b-form-input for="input-content" v-model="content" placeholder="Enter product desc"></b-form-input>
        <b-button @click="modifyGoods">상품수정</b-button>
    </div>
</template>

<script>
export default {
    data() {
        return{
            goods:{},
            name: "",
            price: 0,
            category: "",
            content: "",
            id: 0
        }
    },
    created(){
        this.load()
    },
    methods:{
        async load() {
            const id = this.$route.params.id
            
            await this.$axios.get("http://localhost:9090/goods/detail/" + id)
            .then(res=>{
                this.goods = res.data
                this.id = res.data.goods_id
                this.name = res.data.goods_name
                this.category = res.data.goods_category
                this.content = res.data.goods_content
                this.price = res.data.goods_price
            }).catch(error=>{
                console.log(error)
            })
        },

        async modifyGoods() {
            
            const paramObj = {
                goods_id: this.id,
                goods_name: this.name,
                goods_price: this.price,
                goods_category: this.category,
                goods_content: this.content
            };

            const data = JSON.stringify(paramObj);
            await this.$axios.put("http://localhost:9090/goods/modify", data,
                {
                    headers: { 'Content-Type': 'application/json;charset=UTF-8' }
                }
            ).then(res => {
                console.log(res.data)
                alert("success modify goods")
            }).catch(error => {
                console.log(error)
            })
        }
    }
}
</script>

<style>

</style>