<template>
  <div>
      <a :href="`/product/create`">제품생성</a>
      <b-table striped hover :items="proudcts">
        <template #cell(id)="data">
            <!-- `data.value` is the value after formatted by the Formatter -->
            <a :href="`/product/modify/${data.value}`">{{ data.value }}</a>
        </template>
      </b-table>
  </div>
</template>

<script>
export default {
    data(){
        return{
            proudcts:{}
        }
  },
  created(){
      this.load()
  },
  methods:{
      async load(){
          await this.$axios.get("http://localhost:8080/bidding/v1/products", {
              params:{
                  page: 1,
                  size: 10
              }
          })
          .then(res=>{
              this.proudcts = res.data
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