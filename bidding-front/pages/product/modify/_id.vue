<template>
  <div>
      <li>product id: {{product.id}}</li>
      <li>product group: {{product.product_group}}</li>
      <li>product name: {{product.product_name}}</li>
      <li>product price: {{product.product_price}}</li>
      <li>product desc: {{product.product_desc}}</li>
  </div>
</template>

<script>
export default {
  data(){
    return{
      product:{}
    }
  },
  created(){
    this.load()
  },
  methods:{
    async load(){
      const id = this.$route.params.id
      
      await this.$axios.get("http://localhost:8080/bidding/v1/products/"+id)
      .then(res=>{
        this.product = res.data
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