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
            <span style="font-size: 25px; font-family: gaoyuan,serif;">京宝</span>
          </MenuItem>
          <MenuItem name="2" style="margin-left: 33%">
            <span style="font-size: 25px; font-family: gaoyuan,serif;" @click="clickHomePage">商城</span>
          </MenuItem>
          <MenuItem name="3" style="margin-left: 0">
            <span style="font-size: 25px; font-family: gaoyuan,serif;" @click="clickUserPage">个人</span>
          </MenuItem>
          <MenuItem name="4" style="margin-left: 0">
            <span style="font-size: 25px; font-family: gaoyuan,serif;" @click="clickMerPage">商家</span>
          </MenuItem>
          <div class="layout-nav" style="margin-right: 0">
            <Button type="text" @click="jumpLogin" ghost v-show="logined===false">
              <span style="font-size: 18px; font-family: heying,serif">登录</span>
            </Button>
            <Avatar :src=imgsrc v-show="logined===true && imgsrc !== null" />
            <Avatar style="color: #f56a00;background-color: #fde3cf; cursor: pointer" v-show="logined===true && imgsrc === null">U</Avatar>
            <span type="text" @click="clickUserPage" style="margin-left: 10px;cursor: pointer; font-size: 20px; color: #dcdee2;" v-show="logined===true">{{ username }}</span>
            <Button type="text" @click="logout" ghost v-show="logined===true">
              <span style="font-size: 10px; font-family: heying,serif">登出</span>
            </Button>
          </div>
        </Menu>
      </Header>
      <Layout :style="{padding: '0 50px'}">
<!--        商城主页面-->
        <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}" v-show="showHomePage===true">
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
                <p>
                  <Button type="dashed" shape="circle" icon="md-basket" style="margin-right: 30px; margin-top: 10px;width: 130px;margin-bottom: 20px">加入购物车</Button>
                  <Button type="default" shape="circle" icon="ios-card" style="margin-left: 30px; margin-top: 10px;width: 130px; margin-bottom: 20px">立即购买</Button>
                </p>
              </span>
            </Content>
          </Layout>
        </Content>
<!--        用户页面-->
        <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}" v-show="showUserPage===true">
          <Layout>
            <Sider hide-trigger :style="{background: '#fff'}">
              <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                <p style="font-family: heying;font-size: 11px" v-show="logined===true && imgsrc !== null">用户头像</p>
                <img :src=imgsrc style="width: 100px; margin-top: 5px"  v-show="logined===true && imgsrc !== null"/>
                <p style="font-family: heying;font-size: 11px" v-show="logined===true && imgsrc === null">用户暂未设置头像</p>
                <div></div>
                <p style="font-family: heying;font-size: 15px"><Icon type="md-body" />: {{username}}</p>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :format="['jpg','jpeg','png']"
                        :max-size="8192"
                        :before-upload="handleBeforeUpload"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        :onProgress="uploadUserImg"
                        action="//jsonplaceholder.typicode.com/posts/"
                        type="drag"
                        style="display: inline-block;width:58px;">
                  <span style="font-size: 8px; font-family: heying,serif; color: #2c3e50">更新头像</span>
                </Upload>
                <MenuItem name="1" style="margin-top: 30px">
                    <span style="font-size: 15px; font-family: Arial,serif; color: #2c3e50; margin-left: 0; font-weight: bold">购物车（21）</span>
                </MenuItem>
                <MenuItem name="2">
                  <span style="font-size: 15px; font-family: Arial,serif; color: #2c3e50; font-weight: bold">已购买（12）</span>
                </MenuItem>
                <MenuItem name="3">
                  <span style="font-size: 15px; font-family: Arial,serif; color: #2c3e50; margin-left: 0; font-weight: bold">猜您喜欢  </span>
                </MenuItem>
<!--                <Submenu v-for="(label, i) in labelList" :key="i" :name="label.label1id">-->
<!--                  <template slot="title">-->
<!--                    <Icon type="ios-basket" style="font-size: 25px;" ></Icon>-->
<!--                    <span style="font-size: 18px;"> {{label.label1name}} </span>-->
<!--                  </template>-->
<!--                  <MenuItem v-for="(label2, j) in label.label2List" :key="j" :name="label2.label2id">-->
<!--                    <span style="font-size: 12px; margin-left: 15px" @click="getCommodity(label2.label2id)"> {{label2.label2name}} </span>-->
<!--                  </MenuItem>-->
<!--                </Submenu>-->
              </Menu>
            </Sider>
            <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
              <span v-for="(commodity, i) in commodityList" :key="i">
                <p style="margin-top: 10px; font-size: 20px; font-family:weifeng, serif">{{commodity.name}}</p>
                <img :src=commodity.pict1 style="height: 300px"/>
                <img :src=commodity.pict2 style="height: 300px"/>
                <img :src=commodity.pict3 style="height: 300px"/>
                <p style="margin-top: 10px; font-size: 20px; font-family:weifeng, serif">{{commodity.price}}￥</p>
                <p>
                  <Button type="dashed" shape="circle" icon="md-basket" style="margin-right: 30px; margin-top: 10px;width: 130px;margin-bottom: 20px">加入购物车</Button>
                  <Button type="default" shape="circle" icon="ios-card" style="margin-left: 30px; margin-top: 10px;width: 130px; margin-bottom: 20px">立即购买</Button>
                </p>
              </span>
            </Content>
          </Layout>
        </Content>
<!--        商家页面-->
        <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}" v-show="showMerPage===true">
          <Layout>
            <Sider hide-trigger :style="{background: '#fff'}">
              <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                <p style="font-family: heying;font-size: 11px" v-show="logined===true && imgsrc !== null">商家头像</p>
                <img :src=imgsrc style="width: 100px; margin-top: 5px"  v-show="logined===true && imgsrc !== null"/>
                <p style="font-family: heying;font-size: 11px" v-show="logined===true && imgsrc === null">商家暂未设置头像</p>
                <div></div>
                <p style="font-family: heying;font-size: 15px"><Icon type="md-body" />: {{username}}</p>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :format="['jpg','jpeg','png']"
                        :max-size="8192"
                        :before-upload="handleBeforeUpload"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        :onProgress="uploadUserImg"
                        action="//jsonplaceholder.typicode.com/posts/"
                        type="drag"
                        style="display: inline-block;width:58px;">
                  <span style="font-size: 8px; font-family: heying,serif; color: #2c3e50">更新头像</span>
                </Upload>
                <MenuItem name="1" style="margin-top: 30px">
                  <span @click="getMerPageComList" style="font-size: 15px; font-family: Arial,serif; color: #2c3e50; margin-left: 0; font-weight: bold">店铺商品</span>
                </MenuItem>
                <MenuItem name="2">
                  <span @click="drawerOfAddCom=true" style="font-size: 15px; font-family: Arial,serif; color: #2c3e50; font-weight: bold">添加商品</span>
                    <Drawer title="添加商品" width="280" :closable="true" v-model="drawerOfAddCom">
                      <Form :model="newComItem" :label-width="80">
                        <FormItem label="商品名字">
                          <Input v-model="newComItem.name" style="width: 170px" placeholder="长度不超过30"></Input>
                        </FormItem>
                        <FormItem label="商品价格">
                          <Input v-model="newComItem.price" style="width: 100px" placeholder="整数，单位为￥"></Input>￥
                        </FormItem>
                        <FormItem label="一级标签">
                          <Select v-model="newComItem.label1name" style="width:100px">
                            <Option v-for="(item, i) in labelList" :value="item.label1name" :key="i">{{ item.label1name }}</Option>
                          </Select>
                        </FormItem>
                        <FormItem label="二级标签">
                          <Select v-model="newComItem.label2name" style="width:100px">
                            <Option v-for="(item, i) in newComItem.label2List" :value="item.label2name" :key="i">{{ item.label2name }}</Option>
                          </Select>
                        </FormItem>
                        <FormItem label="商品图片1">
                          <Upload ref="upload"
                                  :show-upload-list="false"
                                  :format="['jpg','jpeg','png']"
                                  :max-size="8192"
                                  :before-upload="handleBeforeUploadCom1"
                                  :on-format-error="handleFormatError"
                                  :on-exceeded-size="handleMaxSize"
                                  action="//jsonplaceholder.typicode.com/posts/"
                                  type="drag"
                                  style="display: inline-block;width:100px;">
                            <span style="font-size: 8px; font-family: heying,serif; color: #2c3e50">上传图片</span>
                          </Upload>
                        </FormItem>
                        <FormItem label="商品图片2">
                          <Upload ref="upload"
                                  :show-upload-list="false"
                                  :format="['jpg','jpeg','png']"
                                  :max-size="8192"
                                  :before-upload="handleBeforeUploadCom2"
                                  :on-format-error="handleFormatError"
                                  :on-exceeded-size="handleMaxSize"
                                  action="//jsonplaceholder.typicode.com/posts/"
                                  type="drag"
                                  style="display: inline-block;width:100px;">
                            <span style="font-size: 8px; font-family: heying,serif; color: #2c3e50">上传图片</span>
                          </Upload>
                        </FormItem>
                        <FormItem label="商品图片3">
                          <Upload ref="upload"
                                  :show-upload-list="false"
                                  :format="['jpg','jpeg','png']"
                                  :max-size="8192"
                                  :before-upload="handleBeforeUploadCom3"
                                  :on-format-error="handleFormatError"
                                  :on-exceeded-size="handleMaxSize"
                                  action="//jsonplaceholder.typicode.com/posts/"
                                  type="drag"
                                  style="display: inline-block;width:100px;">
                            <span style="font-size: 8px; font-family: heying,serif; color: #2c3e50">上传图片</span>
                          </Upload>
                        </FormItem>
                      </Form>
                      <Button type="primary" @click="addNewCommodity">确认提交</Button>
                    </Drawer>
                </MenuItem>
              </Menu>
            </Sider>
            <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
              <span v-for="(commodity, i) in merPageComList" :key="i">
                <p style="margin-top: 10px; font-size: 20px; font-family:weifeng, serif">{{commodity.name}}</p>
                <img :src=commodity.pict1 style="height: 300px"/>
                <img :src=commodity.pict2 style="height: 300px"/>
                <img :src=commodity.pict3 style="height: 300px"/>
                <p style="margin-top: 10px; font-size: 20px; font-family:weifeng, serif">{{commodity.price}}￥</p>
                <p>
                  <Button @click="deleteComByCid(commodity.id)" type="warning" shape="circle" icon="ios-close-circle-outline" style="margin-right: 30px; margin-top: 10px;width: 120px;margin-bottom: 20px">删除该商品</Button></p>
              </span>
            </Content>
          </Layout>
        </Content>
      </Layout>
      <Footer class="layout-footer-center">2022.3-2022.4 毕业设计的网购平台开发中，使用过程中遇到bug、疑点可及时反馈 &copy; FTY</Footer>
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
      commodityList: [],
      showHomePage: true,
      showUserPage: false,
      showMerPage: false,
      updateUserInfo: false,
      imageFile: null,
      drawerOfAddCom: false,
      newComItem: {
        name: null,
        price: null,
        label1name: null,
        label2List: null,
        label2name: null,
        label2id: null,
        img1: null,
        img2: null,
        img3: null
      },
      merPageComList: []
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
    this.getCommodity(1)
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
    },
    clickHomePage () {
      this.showHomePage = true
      this.showUserPage = false
      this.showMerPage = false
    },
    clickUserPage () {
      if (logStateCheck() === false) {
        this.showHomePage = true
        this.showUserPage = false
        this.showMerPage = false
        this.$Message.info('为登录')
      } else {
        this.showHomePage = false
        this.showUserPage = true
        this.showMerPage = false
      }
    },
    clickMerPage () {
      let that = this
      let url = that.serverURL + '/userpage/user'
      let parameters = {account: localStorage.getItem('jingbao_userid')}
      that.axios.get(url, {
        params: parameters
      }).then(response => {
        console.log(response.data[0].role)
        if (response.data[0].role === 1) {
          this.showHomePage = false
          this.showUserPage = false
          this.showMerPage = true
          this.getMerPageComList()
        } else {
          this.showHomePage = true
          this.showUserPage = false
          this.showMerPage = false
          this.$Message.info('普通用户没有权限，可注册商家账号')
        }
      })
    },
    getMerPageComList () {
      let that = this
      let url = that.serverURL + '/merchantpage/getcommoditylistbymerchantuid'
      let parameters = {meraccount: localStorage.getItem('jingbao_userid')}
      that.axios.get(url, {params: parameters}).then(response => {
        that.merPageComList = response.data
        for (let i = 0; i < that.merPageComList.length; i++) {
          // 获取第一张图片
          that.axios.get(that.serverURL + '/commodity/getpict1byid', {
            params: {commodityid: that.merPageComList[i].id},
            responseType: 'blob'
          }).then(response => {
            const reader = new FileReader()
            reader.onload = (e) => {
              that.merPageComList[i].pict1 = e.target.result
            }
            reader.readAsDataURL(response.data)
          })
          // 获取第二张图片
          that.axios.get(that.serverURL + '/commodity/getpict2byid', {
            params: {commodityid: that.merPageComList[i].id},
            responseType: 'blob'
          }).then(response => {
            const reader = new FileReader()
            reader.onload = (e) => {
              that.merPageComList[i].pict2 = e.target.result
            }
            reader.readAsDataURL(response.data)
          })
          // 获取第三张图片
          that.axios.get(that.serverURL + '/commodity/getpict3byid', {
            params: {commodityid: that.merPageComList[i].id},
            responseType: 'blob'
          }).then(response => {
            const reader = new FileReader()
            reader.onload = (e) => {
              that.merPageComList[i].pict3 = e.target.result
            }
            reader.readAsDataURL(response.data)
          })
        }
      })
    },
    handleBeforeUpload (file) {
      this.imageFile = file
    },
    handleFormatError (file) {
      this.$Notice.warning({
        title: '文件格式不正确',
        desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg/png/jpeg 格式的图片'
      })
    },
    handleMaxSize (file) {
      this.$Notice.warning({
        title: '超出文件大小限制',
        desc: '文件 ' + file.name + ' 图片太大，不能超过 2M'
      })
    },
    uploadUserImg () {
      let that = this
      let url = that.serverURL + '/userpage/updateuserimg?account=' + localStorage.getItem('jingbao_userid')
      let fd = new FormData()
      console.log(that.imageFile.uid)
      fd.append('img', that.imageFile)
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data;boundary=' + that.imageFile.uid
        }
      }
      that.axios.post(url, fd, config).then(response => {
        if (response.data === 111) {
          that.$Message.success('更新成功, 请刷新页面查看')
        } else {
          that.$Message.error('更新失败，请稍后重试')
        }
      })
    },
    handleBeforeUploadCom1 (file) {
      this.newComItem.img1 = file
      this.$Message.info('图片1读取正常')
    },
    handleBeforeUploadCom2 (file) {
      this.newComItem.img2 = file
      this.$Message.info('图片2读取正常')
    },
    handleBeforeUploadCom3 (file) {
      this.newComItem.img3 = file
      this.$Message.info('图片3读取正常')
    },
    addNewCommodity () {
      if (this.newComItem.name === null) {
        this.$Message.warning('商品名字不能为空')
        return
      } else if (this.newComItem.price === null) {
        this.$Message.warning('商品价格不能为空')
        return
      } else if (this.newComItem.label1name === null) {
        this.$Message.warning('一级标签不能为空')
        return
      } else if (this.newComItem.label2name === null) {
        this.$Message.warning('二级标签不能为空')
        return
      } else if (this.newComItem.img1 === null) {
        this.$Message.warning('商品图片1未上传')
        return
      } else if (this.newComItem.img2 === null) {
        this.$Message.warning('商品图片3未上传')
        return
      } else if (this.newComItem.img3 === null) {
        this.$Message.warning('商品图片3未上传')
        return
      }
      // 得到label2id
      let that = this
      let url = that.serverURL + '/merchantpage/getlabel2idbylabel2name'
      let parameters = {label2name: that.newComItem.label2name}
      that.axios.get(url, {params: parameters}).then(response => {
        console.log(response.data)
        that.newComItem.label2id = response.data
        // 上传新商品
        url = that.serverURL + '/merchantpage/addcommodity?' +
          'name=' + that.newComItem.name + '&price=' + that.newComItem.price + '&label2id=' + that.newComItem.label2id +
          '&meraccount=' + localStorage.getItem('jingbao_userid')
        let fd = new FormData()
        fd.append('img1', that.newComItem.img1)
        fd.append('img2', that.newComItem.img2)
        fd.append('img3', that.newComItem.img3)
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
        that.axios.post(url, fd, config).then(response => {
          if (response.data === 111) {
            that.$Message.success('添加成功, 请刷新页面查看')
          } else {
            that.$Message.error('添加失败，请检查提交数据是否符合规范')
          }
        }).catch(response => {
          that.$Message.error('添加失败，请检查提交数据是否符合规范')
        })
      })
    },
    deleteComByCid (cid) {
      let that = this
      let url = that.serverURL + '/merchantpage/deletecombycid'
      let parameters = {commodityid: cid}
      that.axios.get(url, {params: parameters}).then(response => {
        if (response.data === 111) {
          that.$Message.success('删除成功，请刷新查看')
        } else {
          that.$Message.warning('删除失败')
        }
      })
    }
  },
  watch: {
    'newComItem.label1name' (newVal, oldVal) {
      let that = this
      let url = that.serverURL + '/merchantpage/getlabel2listbylabel1name'
      let parameters = {label1name: that.newComItem.label1name}
      that.axios.get(url, {params: parameters}).then(response => {
        console.log(response.data)
        that.newComItem.label2List = response.data
      })
    }
  }
}
</script>

<style>
</style>
