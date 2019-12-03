<template>
  <div id="Test">
    <el-button @click="postObj">按下post对象</el-button>
    <el-button @click="putObj">按下put对象</el-button>
    <el-button @click="deleteObj">按下delete对象</el-button>

    <el-button @click="getForm">按下get表单</el-button>
    <el-button @click="postForm">按下post表单</el-button>
    <el-button @click="putForm">按下put表单</el-button>
    <el-button @click="deleteForm">按下delete表单</el-button>
  </div>
</template>

<script>
    import qs from 'qs';
    export default {
        name: "Test",
        data(){
            return{
                data:{'adminId':'kid','adminPwd':'132456456'}
            }
        },
        methods:{
            postObj(){
                var self = this;
                this.$axios.post('/test', self.data).then(function (response) {
                    console.info(self.data);
                    console.log(response.data);
                }).catch(function (error) {
                    console.error("请求错误！");
                });
            },
            putObj(){
                var self = this;
                this.$axios.put('/test', self.data).then(function (response) {
                    console.info(self.data);
                    console.log(response.data);
                }).catch(function (error) {
                    console.error("请求错误！");
                });
            },
            getForm(){
                var self = this;
                this.$axios.get('/test', {params: self.data}).then(function (response) {
                    console.info(self.data);
                    console.log(response.data);
                }).catch(function (error) {
                    console.error("请求错误！");
                });
            },
            deleteObj(){
                var self = this;
                this.$axios.delete('/test', {data: self.data}).then(function (response) {
                    console.info(self.data);
                    console.log(response.data);
                }).catch(function (error) {
                    console.error("请求错误！");
                });
            },

            postForm(){
                var self = this;
                let data = new FormData();
                data.append('adminId','kid');
                data.append('adminPwd','132456456');
                this.$axios.post('/test2', data).then(function (response) {
                    console.info(self.data);
                    console.log(response.data);
                }).catch(function (error) {
                    console.error("请求错误！");
                });
            },
            putForm(){
                var self = this;
                this.$axios.put('/test2', qs.stringify(self.data)
                ).then(function (response) {
                    console.info(self.data);
                    console.log(response.data);
                }).catch(function (error) {
                    console.error("请求错误！");
                });
            },
            deleteForm(){
                var self = this;
                this.$axios.delete('/test2', {params: self.data}).then(function (response) {
                    console.info(self.data);
                    console.log(response.data);
                }).catch(function (error) {
                    console.error("请求错误！");
                });
            }
        }
    }
</script>

<style scoped>

</style>
