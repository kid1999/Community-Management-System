<template>
    <el-row :gutter="20"  :xs="4" :sm="6" :md="8" :lg="9" :xl="11" >
          <el-col :span="8" :offset="6">
            <h1 style="text-align: center">会员信息</h1>
            <div class="grid-content bg-purple">
              <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

                <el-form-item label="学号" prop="memId">
                  <el-input v-model.number="ruleForm.memId"></el-input>
                </el-form-item>

                <el-form-item label="姓名" prop="memName">
                  <el-input v-model="ruleForm.memName"></el-input>
                </el-form-item>

                <el-form-item label="性别">
                  <el-select v-model="ruleForm.memGender" placeholder="性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                  </el-select>
                </el-form-item>

                <el-form-item label="学院">
                  <el-select v-model="ruleForm.memCollege" placeholder="请选择">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>

                <el-form-item label="班级">
                  <el-input v-model="ruleForm.memClass"></el-input>
                </el-form-item>

                <el-form-item label="手机号码">
                  <el-input v-model="ruleForm.memPhone"></el-input>
                </el-form-item>

                <el-form-item label="qq号">
                  <el-input v-model="ruleForm.memQq"></el-input>
                </el-form-item>

                <el-form-item label="会费是否支付">
                  <el-select v-model="ruleForm.memPayCheck" placeholder="会费是否支付">
                    <el-option label="是" value="1"></el-option>
                    <el-option label="否" value="0"></el-option>
                  </el-select>
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
      var checkmemId = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('学号不能为空'));
        }else{
          callback();
        }
      };
      var checkmemName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('姓名不能为空'));
        }else{
          callback();
        }
      };
      return {
          options: [{
              value: '电信学院',
              label: '电信学院'
          }, {
              value: '理学院',
              label: '理学院'
          }],
        ruleForm: {
            memId: '',
            memName:'',
            memGender: '',
            memPhone: '',
            memQq:'',
            memClass:'',
            memCollege:'',
            memPayCheck: ''
        },
        rules: {
            memId: [
            { validator: checkmemId, trigger: 'blur' }
          ],
            memName: [
            { validator: checkmemName, trigger: 'blur' }
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
            alert("请补全信息再试！");
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      to_register: function(formName) {
        let self = this;
        console.info(self.ruleForm);
        axios.post('/menber', self.ruleForm).then(function (response) {
          let res = response.data;
          if(res['s'] === 1){
            self.$router.push("/");
          }else{
            self.$message.success(res['info']);
          }
        }).catch(function (error) {
            self.$message.error(res['info']);
        });
      },
    },
      created() {
        function getmemColleges(){
            this.data().options = this.$axios.get("/colleges").then((response)=>{
                return response.data;
            })
        }
      }
  }
</script>

<style scoped>

</style>
