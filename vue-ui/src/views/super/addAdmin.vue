<template>
  <el-row :gutter="0">
    <el-col :span="6" :offset="6">
      <h1 style="text-align: center">编辑会员信息</h1>
      <div class="grid-content bg-purple">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

          <el-form-item label="学号" prop="userId">
            <el-input v-model="ruleForm.userId"></el-input>
          </el-form-item>

          <el-form-item label="姓名" prop="userName">
            <el-input v-model="ruleForm.userName"></el-input>
          </el-form-item>

          <el-form-item label="密码" prop="userPwd">
            <el-input type="password" v-model="ruleForm.userPwd"></el-input>
          </el-form-item>

          <el-form-item label="班级" prop="userClass">
            <el-input v-model="ruleForm.userClass"></el-input>
          </el-form-item>

          <el-form-item label="qq" prop="userQq">
            <el-input v-model="ruleForm.userQq"></el-input>
          </el-form-item>

          <el-form-item label="手机号码" prop="userPhone">
            <el-input v-model="ruleForm.userPhone"></el-input>
          </el-form-item>

          <el-form-item label="邮箱" prop="userEmail">
            <el-input v-model="ruleForm.userEmail"></el-input>
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
        name: "addAdmin",
        data() {
            //校验方法
            var checkId = (rule, value, callback) => {
                if (!value) {return callback(new Error('学号不能为空'));}
                else {callback();}
            };
            var checkName = (rule, value, callback) => {
                if (!value) {return callback(new Error('姓名不能为空'));}
                else {callback();}
            };
            var checkQQ = (rule, value, callback) => {
                if (!value) {return callback(new Error('QQ不能为空'));}
                else {callback();}
            };
            var checkClass = (rule, value, callback) => {
                if (!value) {return callback(new Error('班级不能为空'));}
                else {callback();}
            };
            var checkPwd = (rule, value, callback) => {
                if (!value) {return callback(new Error('密码不能为空'));}
                else {callback();}
            };
            return {
                ruleForm: {
                    userId:'',
                    userName:'',
                    userEmail:'',
                    userPhone:'',
                    userQq:'',
                    userClass:'',
                    userPwd:''
                },
                //规则
                rules: {
                    userId: [
                        {validator: checkId, trigger: 'blur'}
                    ],
                    userName: [
                        {validator: checkName, trigger: 'blur'}
                    ],
                    userQq: [
                        {validator: checkQQ, trigger: 'blur'}
                    ],
                    userClass: [
                        {validator: checkClass, trigger: 'blur'}
                    ],
                    userPwd: [
                        {validator: checkPwd, trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            //提交
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.to_login();
                    } else {
                        this.$message({
                            message: '请填写完信息,再试!!!',
                            type: 'warning'
                        });
                    }
                });
            },
            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            // 交互
            to_login: function(formName) {
                let self = this;
                axios.post('/admin', {
                    adminId:self.ruleForm.userId,
                    adminName:self.ruleForm.userName,
                    adminEmail:self.ruleForm.userEmail,
                    adminPhone:self.ruleForm.username,
                    adminQq:self.ruleForm.userPhone,
                    adminPwd:self.ruleForm.userPwd,
                }).then(function (response) {
                    var res = response.data;
                    console.info(res);
                    if(res['reslut'] === 1){
                        self.$router.push("/");
                        self.$message({
                            message: res['info'],
                            type: 'success'
                        });
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


