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
  mounted(){
      this.load()
  },
  created(){
      
  },
  methods:{
      async load(){
        //    const csrfToken = await this.getCsrfToken()

          await this.$axios.get("http://localhost:8080/bidding/v1/products", {
              params:{
                  page: 1,
                  size: 10
              },
              headers:{
                //   'X-CSRF-TOKEN': csrfToken
              }
          })
          .then(res=>{
              this.proudcts = res.data
          })
          .catch(error=>{
              console.log(error)
          })
      },
      async getCsrfToken(){
          let csrfToken
          await this.$axios.get("http://localhost:8081/pf-secure/v1/security/csrf-token")
          .then(res=>{
              csrfToken = res.data.csrf_token
          })
          .catch(error=>{
              console.log(error)
          })

          return csrfToken
      }


  }
}
</script>

<style>

</style>