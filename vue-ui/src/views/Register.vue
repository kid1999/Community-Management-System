<template>
  <el-row :gutter="20">

    <el-col :span="6" :offset="7">
      <h1 style="text-align: center">注册</h1>
      <div class="grid-content bg-purple">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="学号" prop="name">
            <el-input v-model.number="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="username">
            <el-input v-model.number="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="手机号码">
            <el-input v-model="this.ruleForm.admin_phone"></el-input>
          </el-form-item>
          <el-form-item label="qq号">
            <el-input v-model="this.ruleForm.admin_qq"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="this.ruleForm.admin_email"></el-input>
          </el-form-item>
          <el-form-item label="注册邀请码" prop="code">
            <el-input v-model.number="ruleForm.code"></el-input>
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
  export default {
    name: "register",
    data() {
      var checkName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('学号不能为空'));
        }else{
          callback();
        }
      };
      var checkUserName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('姓名不能为空'));
        }else{
          callback();
        }
      };
      var checkCode = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('邀请码不能为空'));
        }else{
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
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          name: '',
          username:'',
          pass: '',
          checkPass: '',
          admin_email:'',
          admin_phone:'',
          admin_qq:'',
          code:'',
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          name: [
            { validator: checkName, trigger: 'blur' }
          ],
          username: [
            { validator: checkUserName, trigger: 'blur' }
          ],
          code: [
            { validator: checkCode, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.to_register();
          } else {
            alert("请补全信息再试！")
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      to_register: function(formName) {
        let self = this;
        axios.post('/regsiter', {
          adminId: this.ruleForm.name,
          adminPwd: this.ruleForm.pass,
          adminName: this.ruleForm.username,
          adminEmail:this.ruleForm.admin_email,
          adminPhone:this.ruleForm.admin_phone,
          adminQq:this.ruleForm.admin_qq,
          invitationCode:this.ruleForm.code,
        }).then(function (response) {
          let res = response.data;
          if(res['reslut'] === 1){
            self.$router.push("/");
          }else{
            alert(res['info'])
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
