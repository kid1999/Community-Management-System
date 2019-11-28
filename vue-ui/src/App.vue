<template>
  <div id="app">

    <!--     侧边栏-->
    <a-layout id="components-layout-demo-side" style="min-height: 100vh">
      <a-layout-sider collapsible v-model="collapsed">
        <div class="logo" />
        <a-menu theme="dark" :defaultSelectedKeys="['1']" mode="inline">
          <a-menu-item key="/">
            <a-icon type="pie-chart" />
            <span>主页</span>
            <RouterLink to="/">主页</RouterLink>
          </a-menu-item>

          <a-menu-item key="2" >
            <a-icon type="desktop" />
            <span>会员信息</span>
            <RouterLink to="users">users</RouterLink>
          </a-menu-item>

          <a-sub-menu key="sub1">
            <span slot="title"><a-icon type="user" /><span>用户</span></span>
            <a-menu-item key="3"><RouterLink to="register">添加会员</RouterLink></a-menu-item>
            <a-menu-item key="login"><RouterLink to="login">登录</RouterLink></a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub2">
            <span slot="title"><a-icon type="team" /><span>超级管理员</span></span>
            <a-menu-item key="addAdmin"><RouterLink to="addAdmin">添加管理员</RouterLink></a-menu-item>
            <a-menu-item key="8">Team 2</a-menu-item>
          </a-sub-menu>
          <a-menu-item key="9">
            <a-icon type="file" />
            <span>File</span>
          </a-menu-item>
        </a-menu>
      </a-layout-sider>

<!--标题-->
      <a-layout>
        <a-layout-header style="background: #fff; padding: 0;text-align: center;font-size: x-large">社团管理系统</a-layout-header>
        <a-layout-content style="margin: 0 16px">
          <a-breadcrumb style="margin: 16px 0">

<!--            内容-->
          </a-breadcrumb>
          <div :style="{ padding: '24px', background: '#fff', minHeight: '720px' }">
            <router-view></router-view>
          </div>
        </a-layout-content>

        <a-layout-footer style="text-align: center">
          Ant Design ©2018 Created by Ant UED
        </a-layout-footer>

      </a-layout>

    </a-layout>

  </div>
</template>

<style>

</style>

<script>
    export default {
      data() {
        return {
          collapsed: false,
        }
      },
      computed: {
        count () {
          return this.$store.state.count
        }
      }
    }
</script>
