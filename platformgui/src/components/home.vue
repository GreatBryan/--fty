<style scoped>
@font-face{
  font-family:'weifeng';
  src:url('../../static/font/weifeng.ttf');
}
@font-face{
  font-family:'gaoyuan';
  src:url('../../static/font/gaoyuan.ttf');
}
@font-face{
  font-family:'heying';
  src:url('../../static/font/heying.ttf');
}
.layout{
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.layout-logo{
  width: 100px;
  height: 30px;
  background: #5b6270;
  border-radius: 3px;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
}
.layout-nav{
  width: 420px;
  margin: 0 auto;
  margin-right: 20px;
}
.layout-footer-center{
  text-align: center;
}
</style>
<template>
  <div class="layout">
    <Layout>
      <Header>
        <Menu mode="horizontal" theme="dark" active-name="1">
          <MenuItem name="1">
            <Icon type="ios-boat-outline" style="font-size: 30px; margin-top: 20px; margin-left: 0" />
            <span style="font-size: 25px; font-family: gaoyuan,serif">京宝</span>
          </MenuItem>
          <div class="layout-nav" style="margin-right: 0">
            <Button type="text" @click="jumpLogin" ghost v-show="logined===false">
              <span style="font-size: 18px; font-family: heying,serif">登录</span>
            </Button>
            <Avatar :src=imgsrc style="cursor: pointer" v-show="logined===true && imgsrc !== null"/>
            <Avatar style="color: #f56a00;background-color: #fde3cf; cursor: pointer" v-show="logined===true && imgsrc === null">U</Avatar>
            <span type="text" style="margin-left: 10px; font-size: 20px; color: #dcdee2;" v-show="logined===true">{{ username }}</span>
            <Button type="text" @click="logout" ghost v-show="logined===true">
              <span style="font-size: 10px; font-family: heying,serif">登出</span>
            </Button>
          </div>
        </Menu>
      </Header>
      <Layout :style="{padding: '0 50px'}">
        <Breadcrumb :style="{margin: '8px 0'}">
        </Breadcrumb>
        <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}">
          <Layout>
            <Sider hide-trigger :style="{background: '#fff'}">
              <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                <Submenu name="1">
                  <template slot="title">
                    <Icon type="ios-navigate"></Icon>
                    Item 1
                  </template>
                  <MenuItem name="1-1">Option 1</MenuItem>
                  <MenuItem name="1-2">Option 2</MenuItem>
                  <MenuItem name="1-3">Option 3</MenuItem>
                </Submenu>
                <Submenu name="2">
                  <template slot="title">
                    <Icon type="ios-keypad"></Icon>
                    Item 2
                  </template>
                  <MenuItem name="2-1">Option 1</MenuItem>
                  <MenuItem name="2-2">Option 2</MenuItem>
                </Submenu>
                <Submenu name="3">
                  <template slot="title">
                    <Icon type="ios-analytics"></Icon>
                    Item 3
                  </template>
                  <MenuItem name="3-1">Option 1</MenuItem>
                  <MenuItem name="3-2">Option 2</MenuItem>
                </Submenu>
              </Menu>
            </Sider>
            <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
              Content
            </Content>
          </Layout>
        </Content>
      </Layout>
      <Footer class="layout-footer-center">2022.3-2022.4 &copy; FTY</Footer>
    </Layout>
  </div>
</template>
<script>
import logStateCheck from '../main'
export default {
  data () {
    return {
      logined: true,
      username: null,
      imgsrc: null
    }
  },
  created () {
    if (logStateCheck() === true) {
      let that = this
      let url = that.serverURL + '/userpage/user'
      let parameters = {account: localStorage.getItem('jingbao_userid')}
      that.axios.get(url, {
        params: parameters
      }).then(function (response) {
        console.log(response.data)
        if (response.data.length > 0 && response.data[0].id !== 0) {
          that.logined = true
          that.username = response.data[0].name
        } else {
          localStorage.clear()
          that.logined = false
        }
      })
    } else {
      this.logined = false
      this.$router.replace('/home')
    }
    if (this.logined === true) {
      let that = this
      let url = that.serverURL + '/userpage/getuserimg'
      let parameters = {account: localStorage.getItem('jingbao_userid')}
      that.axios.get(url, {
        params: parameters,
        responseType: 'blob'
      }).then(response => {
        console.log(typeof response.data)
        if (response.data.size <= 10) {
          that.imgsrc = null
        } else {
          const reader = new FileReader()
          reader.onload = (e) => {
            that.imgsrc = e.target.result
          }
          reader.readAsDataURL(response.data)
        }
        console.log(that.imgsrc)
      })
    }
  },
  methods: {
    jumpLogin () {
      this.$router.replace('/login')
    },
    logout () {
      localStorage.clear()
      this.$Message.info('账号退出')
      this.logined = false
    }
  }
}
</script>

<style>
</style>
