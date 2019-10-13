<template>
  <div class="hello">
    <el-container v-show="!isLogin">
      <h1 >{{ msg }}</h1>
      <el-button>登录</el-button>
      <el-button>注册</el-button>
    </el-container>

    <el-container v-show="isLogin">

    </el-container>


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
