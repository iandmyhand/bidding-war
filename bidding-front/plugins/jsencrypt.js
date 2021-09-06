import Vue from 'vue'
import jsencrypt from 'jsencrypt'

Vue.prototype.$jsencrypt = new jsencrypt({default_key_size:2048})