import Vue from 'vue'
import axios from 'axios'

window.axios = axios
window.axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'

Vue.component('tasks', require('./components/Tasks.vue').default)

new Vue({
    el: '#app'
})
