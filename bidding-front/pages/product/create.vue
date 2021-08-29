<template>
  <div>
      <label for="input-group">Group:</label>
      <b-form-input for="input-group" v-model="group" placeholder="Enter product group"></b-form-input>
      <label for="input-name">Name:</label>
      <b-form-input for="input-name" v-model="name" placeholder="Enter product name"></b-form-input>
      <label for="input-price">Price:</label>
      <b-form-input for="input-price" v-model="price" placeholder="Enter product price"></b-form-input>
      <label for="input-desc">Desc:</label>
      <b-form-input for="input-desc" v-model="desc" placeholder="Enter product desc"></b-form-input>
      <b-button @click="createProduct">create</b-button>
  </div>
</template>

<script>
export default {
    data(){
        return{
            group: "",
            name: "",
            price: 0,
            desc: ""
        }
    },
    methods:{
        async createProduct(){
            const objData = {
                product_group: this.group,
                product_name: this.name,
                product_price: this.price,
                product_desc: this.desc
            };
            const data = JSON.stringify(objData);
            await this.$axios.post("http://localhost:8080/bidding/v1/product", data,
                {
                    headers: { 'Content-Type': 'application/json;charset=UTF-8' }
                }
            )
            .then(res=>{
                this.group = ""
                this.name = ""
                this.price = 0
                this.desc = ""
                alert("created product")
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