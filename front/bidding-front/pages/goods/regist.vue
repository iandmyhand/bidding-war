<template>
    <div>
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
        <b-button @click="registGoods">상품등록</b-button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            name: "",
            price: 0,
            category: "",
            content: ""
        }
    },

    created() {
        //
    },

    methods: {
        
        async registGoods() {
            
            const paramObj = {
                goods_name: this.name,
                goods_price: this.price,
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
        }
    }

}
</script>

<style>

</style>