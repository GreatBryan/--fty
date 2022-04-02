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
                <Submenu v-for="(label, i) in labelList" :key="i" :name="label.label1id">
                  <template slot="title">
                    <Icon type="ios-basket" style="font-size: 25px;" ></Icon>
                    <span style="font-size: 18px;"> {{label.label1name}} </span>
                  </template>
                    <MenuItem v-for="(label2, j) in label.label2List" :key="j" :name="label2.label2id">
                      <span style="font-size: 12px; margin-left: 15px" @click="getCommodity(label2.label2id)"> {{label2.label2name}} </span>
                    </MenuItem>
                </Submenu>
              </Menu>
            </Sider>
            <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
              <span v-for="(commodity, i) in commodityList" :key="i">
                <p style="margin-top: 10px; font-size: 20px; font-family:weifeng, serif">{{commodity.name}}</p>
                <img :src=commodity.pict1 style="height: 300px"/>
                <img :src=commodity.pict2 style="height: 300px"/>
                <img :src=commodity.pict3 style="height: 300px"/>
                <p style="margin-top: 10px; font-size: 20px; font-family:weifeng, serif">{{commodity.price}}￥</p>
              </span>
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
      imgsrc: null,
      labelList: [],
      commodityList: []
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
    // 获取标签
    let that = this
    let url = that.serverURL + '/commodity/getalllabel1'
    that.axios.get(url, {
      params: null,
      responseType: 'json'
    }).then(response => {
      console.log(response.data)
      that.labelList = response.data
    })
  },
  methods: {
    jumpLogin () {
      this.$router.replace('/login')
    },
    logout () {
      localStorage.clear()
      this.$Message.info('账号退出')
      this.logined = false
    },
    getCommodity (label2id) {
      let that = this
      let url = that.serverURL + '/commodity/getcombylabel2'
      let parameters = {label2id: label2id}
      that.axios.get(url, {
        params: parameters
      }).then(response => {
        that.commodityList = response.data
        for (let i = 0; i < that.commodityList.length; i++) {
          // 获取第一张图片
          that.axios.get(that.serverURL + '/commodity/getpict1byid', {
            params: {commodityid: that.commodityList[i].id},
            responseType: 'blob'
          }).then(response => {
            const reader = new FileReader()
            reader.onload = (e) => {
              that.commodityList[i].pict1 = e.target.result
            }
            reader.readAsDataURL(response.data)
          })
          // 获取第二张图片
          that.axios.get(that.serverURL + '/commodity/getpict2byid', {
            params: {commodityid: that.commodityList[i].id},
            responseType: 'blob'
          }).then(response => {
            const reader = new FileReader()
            reader.onload = (e) => {
              that.commodityList[i].pict2 = e.target.result
            }
            reader.readAsDataURL(response.data)
          })
          // 获取第三张图片
          that.axios.get(that.serverURL + '/commodity/getpict3byid', {
            params: {commodityid: that.commodityList[i].id},
            responseType: 'blob'
          }).then(response => {
            const reader = new FileReader()
            reader.onload = (e) => {
              that.commodityList[i].pict3 = e.target.result
            }
            reader.readAsDataURL(response.data)
          })
        }
        console.log(that.commodityList)
      })
    }
  }
}
</script>

<style>
</style>
