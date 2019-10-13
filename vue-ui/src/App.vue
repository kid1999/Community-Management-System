<template>
  <div id="app">
    <el-container>

      <el-header>
        <Header></Header>
      </el-header>

      <el-main>
        <router-view/>
      </el-main>

      <el-footer>
        <Footer></Footer>
      </el-footer>

    </el-container>
  </div>
</template>

<style>

</style>

<script>
    import Header from "./views/Header";
    import Footer from "./views/Footer";
    export default {
        components: {Footer, Header},
        computed: {
            count () {
                return this.$store.state.count
            }
        }
    }
</script>
