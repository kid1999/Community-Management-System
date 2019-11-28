<template>
  <el-row :gutter="0">
    <el-col :span="6" :offset="6">
      <h1 style="text-align: center">登录</h1>
      <div class="grid-content bg-purple">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="学号" prop="name">
            <el-input v-model.number="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  import axios from 'axios';
  import qs from 'qs';
  export default {
    name: "login",
    data() {
      var checkName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('学号不能为空'));
        } else {
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      return {
        ruleForm: {
          pass: '',
          name: ''
        },
        rules: {
          pass: [
            {validator: validatePass, trigger: 'blur'}
          ],
          name: [
            {validator: checkName, trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.to_login();
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      to_login: function(formName) {
        let self = this;
        let data = {
            "adminId": self.ruleForm.name + "",
            "adminPwd": self.ruleForm.pass,
        };
        axios.post('/login', qs.stringify(data), {headers:{'Content-Type':'application/x-www-form-urlencoded'}}).then(function (response) {
          let res = response.data;
          if(res['reslut'] === 1){
            self.$store.commit('Login');
            self.$router.push("/");
            self.$message.success({message: res['info']});
          }else{
            self.$message.error(res['info']);
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
    }
  }
</script>

<style scoped>

</style>
