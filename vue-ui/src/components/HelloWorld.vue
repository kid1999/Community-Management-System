<template>
  <div class="hello">

  <a-layout v-show="!isLogin">

  <a-layout-header style="background: #fff; padding: 0;text-align: center;"></a-layout-header>
  <a-layout-content style="margin: 0 0">
    <div :style="{ padding: '24px', background: '#fff', minHeight: '240px' }">
      <h1 >{{ msg }}</h1>
      <a-steps>
        <a-step status="finish" title="注册">
          <a-icon type="user" slot="icon"/>
        </a-step>
        <a-step status="finish" title="登录">
          <a-icon type="solution" slot="icon"/>
        </a-step>
        <a-step status="process" title="Go">
          <a-icon type="loading" slot="icon"/>
        </a-step>
        <a-step status="wait" title="Done">
          <a-icon type="smile-o" slot="icon"/>
        </a-step>
      </a-steps>
    </div>

    <div :style="{ padding: '24px', background: '#fff', minHeight: '120px'}">

      <el-button> <router-link to="login">登录</router-link></el-button>
      <el-button><router-link to="register">注册</router-link></el-button>
    </div>
  </a-layout-content>
  </a-layout>


    <a-layout v-show="isLogin">
      <div :style="{ padding: '24px', background: '#fff', minHeight: '120px'}">
        <h1>欢迎登录</h1>
      </div>
    </a-layout>






  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  computed:{
      isLogin(){
          return this.$store.state.isLogin;
      }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
