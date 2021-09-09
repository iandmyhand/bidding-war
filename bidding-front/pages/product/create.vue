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
      <!-- <b-button @click="sendEncryptTest">test</b-button> -->
  </div>
</template>

<script>
const crypto = require('crypto');
export default {
    data(){
        return{
            rsaPublicKeys: {},
            keys: "",
            group: "",
            name: "",
            price: 0,
            desc: ""
        }
    },
    created(){
        this.getRsaPublicKey()
    },
    methods:{
        async getRsaPublicKey(){
            await this.$axios.get("https://dev-pf-security-support.s3.ap-northeast-2.amazonaws.com/public_key_cache/publicKeyCache.json")
            .then(res=>{
                this.rsaPublicKeys = res.data
                console.log(this.publicKeys)
            })
            .catch(error=>{
                console.log(error)
            })
        },
        async createProduct(){
            let publicKeys = this.getPublicKey()
            // const objData = {
            //     product_group: this.encMessage(publicKeys.publicKey, this.group),
            //     product_name: this.encMessage(publicKeys.publicKey, this.name),
            //     product_price: this.price,
            //     product_desc: this.encMessage(publicKeys.publicKey, this.desc)
            // };

            const objData = {
                product_group: this.group,
                product_name: this.name,
                product_price: this.price,
                product_desc: this.desc
            };
            let data = JSON.stringify(objData);
            console.log("data size : " + data.length)
            const secretKey = this.randomCode()
            const aseKey = this.encRsaMessage(publicKeys.publicKey, secretKey)
            data = this.encAesMessage(secretKey, data)
            
            await this.$axios.post("http://localhost:8080/bidding/v1/product", data,
                {
                    headers: { 
                        'Content-Type': 'application/json;charset=UTF-8',
                        'RSA-ENCRYPT-KEY-ID':publicKeys.keyId,
                        'AES-ENCRYPT-KEY': aseKey
                    }
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
        },
        getPublicKey(){
            console.log("test: " + this.rsaPublicKeys.next)
            let publicKey, keyId
            if(this.rsaPublicKeys!=undefined){
                if(this.rsaPublicKeys.next!=undefined){
                    publicKey = this.rsaPublicKeys.next.public_key
                    keyId = this.rsaPublicKeys.next.key_id
                    return { publicKey, keyId }
                }else{
                    publicKey = this.rsaPublicKeys.current.public_key
                    keyId = this.rsaPublicKeys.current.key_id
                    return { publicKey, keyId }
                }
            }
        },
        encRsaMessage(publicKey, message){
            // console.log("use : "+publicKey)
            // var encrypt = this.$jsencrypt
            publicKey = "-----BEGIN PUBLIC KEY-----\n" + publicKey + "\n-----END PUBLIC KEY-----\n"
            // const privateKeyText = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaTu8C6JfAAiRM9+ZUeHIyEonRgX74GLl4sTewPpHBor1JTvaBpzQwYTXd8qtd1M8c2pQ6XCiqOlZi3vlOtrNAZ8eG53hQox2swsxH0S+K8a7+1f5ay2c2+jHwMv2ofp3ZpxqXNLsqcC9gekdSkF9qTDgNkNOa4mczpqw/LqLzPGxge/63XDxEsIycUgSHKyqBZBshnUFmTdcyoeNYEWtoNakS6AsQnNihbyYMQxwwovCYmOc6eU4QXl8t/C0iaCitTwhbfhFUaIQFHvEcWbwahIxP1peYPqcGSDssS41i3+ULDMd7UnoLyB6VA4Tyb+jvFDwpQM7hLV2exIXmTtRFAgMBAAECggEATbVqkGBp63woo8pD6E4v1B+Z4DQCqRZqcOluTgd0h7tY784gPfLiSWrrIbbPrhU5qCI7e5hlsjRmxKvdiVJguxKIXNu8r381tobzMUebVbGYmyVRTpAYjos5Euna7Du3aqDIt1UK3LhaY4+w01d5daKkp9BDgEB8EHrjS35E+ZCu1gVu+qpqHXge2/G3Ew7umzWn2Ivu2m8BzxwgbdEggX+33PP3OsMXylxQg0TceTK3N04yrmex7bPSineQOa9j10r1kZP3IK4YTiiTu5E9/OAhccXZZCswxQxqh6h8Q7XYpT0cGCtxOHPtgbq53/qa5SeMbrmN3/6WWWWjPBe0EQKBgQDeyCHZIXl3vfRh69L0es+jfShlSq1RtAUggQoJgqbwYZC1bmRbmW5xIdgKNxwcjzBrID7SBh34ZWHZLGSDU3etgAdO/uz5G/wjTDbG8qdrCC7Y7QUe+Nj3ybyMBScULYOMHfXTaxAl9w9XcHvvPGi1f0cZMSh+SKH42AS/Yb1UTwKBgQCxURUDDVuz/XTNm/u8JYEmUjtgWCodoN0cmXh0pBpKO1tQr5+Lu5uZEUpDxFs33aUnSfTL+ingMMpSFMAhzzDJj2U5P7M/1hc7vJea6Yauaf/VEOnlQw7FmtN7ngNGklS2Ssa1WVSHva71VA978CMaPrq5vZBCP+XpMqDmLrnlKwKBgAb+DxnqlA5vFuGP7lIgHK5L+l2bh28eEF8hzbfYsvauiUU4jsvVOAMzBb3kVuyKjgF+xJoA+SXXwMd5Pjpk/0eh0hjnpXZ4K2TMOpfp+9k/K4FBhzyeoOi+Gz3l14EpoIxgUFvva1VVuNSMwkcTBVJVCV1ADr5P902BMjzzi2a/AoGBAKOnSTRa9ONnr5FBQRMCrnN2/BRM6voTiWAnCXTmLNmMdRhAL4nhKpgYzClFpkcmi5J6gLRufI3Nmj4tprLNrqKpdWxkLYVijGj3BBnXJRX/AT9eb/HIdW7OGhiC21UcI5Fn7IReIVVzLXKCFhR2q39CnEZn/igXH5SexMAOKkanAoGBAI7e7mclzIeD5XtG4mBRHQ9v9dA/YSOKdPjlecrFM+4sv+9x+skLjgPHToDSDs/SRbRlfI6F3iNWJJcli+AP5nzeTIsB89O1f3cZNyWuAmdZRdiGMpl4dozyYmrzo3QZ2xz6ixBWIw+oELC7VgRrBEg4wcJqRveziDRJWq4hDrSP"
            // const privateKey = "-----BEGIN PRIVATE KEY-----\n" + privateKeyText + "\n-----END PRIVATE KEY-----\n"

            // encrypt.setPublicKey(publicKey)
            // encrypt.setPrivateKey(privateKey)
            
            let encMsg = crypto.publicEncrypt({key:publicKey, padding: crypto.constants.RSA_PKCS1_PADDING},
                                                Buffer.from(message, 'utf8'))
            encMsg = encMsg.toString('base64')
            //get으로 parameter로 보낼경우 urlEncode해줘야하고 post body로 보낼경우 하지 말아야함
            // encMsg = encodeURIComponent(encMsg) 

            // encMsg = encrypt.encrypt(message)
            console.log(encMsg)
            // let decMsg = encrypt.decrypt(encMsg)
            // let decMsg = crypto.privateDecrypt(privateKey, Buffer.from(encMsg, 'base64'))
            // console.log(decMsg)
            
            return encMsg
        },
        encAesMessage(secretKey, message){
            console.log(secretKey)
            const iv = secretKey
            const cihper = crypto.createCipheriv(
             'aes-128-cbc',
             Buffer.from(secretKey),
             Buffer.from(iv)   
            )

            let encMsg = cihper.update(message, 'utf8', 'base64')
            encMsg+=cihper.final('base64')

            return encMsg
        },
        async sendEncryptTest(){
            let publicKeys = this.getPublicKey()

            // this.encMessage(publicKey, this.group)
            await this.$axios.get("http://localhost:8080/bidding/v1/product?"+"msg="+this.encMessage(publicKeys.publicKey, this.group),{
                headers:{
                    'RSA-ENCRYPT-KEY-ID':publicKeys.keyId
                }
            })
            .then(res=>{
                console.log(res.data)
            })
            .catch(error=>{
                console.log(error)
            })
        },
        randomCode(){
            const chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz'
            const stringLength = 16
            let randomstring = ''
            for (let i = 0; i < stringLength; i++) {
            const rnum = Math.floor(Math.random() * chars.length)
            randomstring += chars.substring(rnum, rnum + 1)
            }
            return randomstring
        }
    }
}
</script>

<style>

</style>