// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// eslint-disable-next-line
import login from './components/login'
// eslint-disable-next-line
import home from './components/home'
import VueResource from 'vue-resource'
import ViewUI from 'view-design'
import 'view-design/dist/styles/iview.css'
import axios from 'axios'
// import into project
import VuejsDialog from 'vuejs-dialog'
// eslint-disable-next-line
import VuejsDialogMixin from 'vuejs-dialog/dist/vuejs-dialog-mixin.min.js'
import 'vuejs-dialog/dist/vuejs-dialog.min.css'

// Tell Vue to install the plugin.
Vue.use(VuejsDialog)
Vue.config.productionTip = false
Vue.use(VueResource)
Vue.use(ViewUI)
Vue.prototype.axios = axios
Vue.prototype.serverURL = 'http://127.0.0.1:8082'
// Vue.prototype.serverURL = 'http://116.205.171.68:8080/platform'
Vue.prototype.SUCCESS = 111

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  router: router,
  render: h => h(App)
})

export default function logStateCheck () {
  console.log(localStorage.getItem('jingbao_userid'))
  if (localStorage.getItem('jingbao_userid') !== null) {
    return true
  } else {
    return false
  }
}
