<template>
  <div class="wrap" id="wrap">
    <div class="logGet">
      <!-- 头部提示信息 -->
      <div class="logD logDtip">
        <p class="p1">京宝</p>
      </div>
      <!-- 输入框 -->
      <div class="lgD">
        <div>
          <Input prefix="ios-contact" placeholder="输入用户名" type="text" v-model="account" style="width: 85%; height: 100%; " />
        </div>
      </div>
      <div class="lgD">
        <Input prefix="ios-contact" placeholder="输入用户密码" type="text" v-model="password" style="width: 85%; height: 100%;" />
      </div>
      <div class="logC">
        <Button type="primary" @click="login" id = "login" style="margin-right: 10px">登 录</Button>
        <Button @click="register=true" id = "register" class = "btn" style="margin-left: 10px">注 册</Button>
        <Modal v-model="register" width="360">
          <p slot="header" style="color:#515a6e;text-align:center">
            <Icon type="md-body" />
            <span>账号注册</span>
          </p>
          <div slot="footer" style="display: flex; align-items: center;justify-content: center">
            <Button type="success" @click="registerCheck">注册</Button>
          </div>
        </Modal>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      account: '',
      password: '',
      register: false
    }
  },
  methods: {
    login () {
      let that = this
      let url = that.serverURL + '/userpage/check'
      let parameters = {account: that.account, password: that.password}
      that.axios.get(url, {
        params: parameters
      }).then(function (response) {
        console.log(response.data)
        if (response.data === that.SUCCESS) {
          that.$router.replace('/index')
        } else {
          that.$Message.info('账号或密码不正确')
        }
      })
    }
    // ok () {
    //   let that = this
    //   let url = that.serverURL + '/userpage/register'
    //   let parameters = {account: that.account, password: that.password, name: 'fty', role: 0}
    //   that.axios.get(url, {
    //     params: parameters
    //   }).then(function (response) {
    //     console.log(response.data)
    //     if (response.data === that.SUCCESS) {
    //       that.$router.replace('/index')
    //       this.$Message.info('注册成功')
    //     } else {
    //       that.$Message.info('账号已存在')
    //     }
    //   })
    // }
  }
}
</script>

<style>
  body {
    background-image: url(../../static/img/pic1.jpg);
    background-size: 100%;
    background-repeat: no-repeat;
    background-position: center center;
  }

  * {
    margin: 0;
    padding: 0;
  }

  #wrap {
    height: 600px;
    width: 100%;
    background-position: center center;
    position: relative;
  }
  #wrap .logGet {
    height: 330px;
    width: 270px;
    position: absolute;
    background-color: #FFFFFF;
    top: 10%;
    left: 15%;
  }
  #login {
    margin-left: 10%;
  }
  #register {
    margin-right: 10%;
  }
  .logGet .logD.logDtip .p1 {
    display: inline-block;
    font-family: "PingFang SC",serif;
    font-size: 40px;
    margin-top: 30px;
    width: 86%;
    font-weight:bold;
  }
  #wrap .logGet .logD.logDtip {
    width: 86%;
    border-bottom: 1px solid #ff9900;
    margin-bottom: 40px;
    margin-top: 0px;
    margin-right: auto;
    margin-left: auto;
  }
  #wrap .logGet .lgD {
    width: 86%;
    position: relative;
    margin-bottom: 30px;
    margin-top: 30px;
    margin-right: auto;
    margin-left: auto;
  }
  #wrap .logGet .logC {
    width: 86%;
    margin-top: 0px;
    margin-right: auto;
    margin-bottom: 0px;
    margin-left: auto;
  }

</style>
