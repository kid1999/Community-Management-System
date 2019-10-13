<template>
    <div id="header">
      <el-container>
          <el-menu  router  class="el-menu-demo" mode="horizontal" >
            <el-menu-item index="/">北部湾大学社团管理系统</el-menu-item>
            <el-menu-item index="5">各种社团活动</el-menu-item>

            <el-submenu index="2" v-show="isLogin">
              <template slot="title">root</template>
              <el-menu-item index="info">个人信息</el-menu-item>
              <el-menu-item index="news"><el-badge is-dot class="item">消息中心</el-badge></el-menu-item>
              <el-menu-item index="/" @click="$store.commit('Logout')">退出登录</el-menu-item>
            </el-submenu>

            <el-submenu index="3" v-show="!isLogin">
              <template slot="title">新用户</template>
              <el-menu-item index="login" >登录</el-menu-item>
              <el-menu-item index="register">注册</el-menu-item>
            </el-submenu>

          </el-menu>

      </el-container>
    </div>
</template>

<script>
    export default {
        name: "header",
        computed:{
            isLogin(){
                return this.$store.state.isLogin;
            }
        }
    }
</script>

<style scoped>

</style>
