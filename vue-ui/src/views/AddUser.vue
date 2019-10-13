<template>
    <div id="AddUser">



      <a-layout-content>
        <div :style="{ padding: '24px', background: '#fff', minHeight: '50px' }">
          <h2 >添加用户</h2>
        </div>
      </a-layout-content>


      <a-form :form="form">

        <a-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol" label="userId">
          <a-input v-decorator="['userId',{rules: [{ required: true, message: '请输入学号' }]}]"
            placeholder="请输入学号"/>
        </a-form-item>

        <a-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol" label="userName">
          <a-input v-decorator="['userName',{rules: [{ required: true, message: '请输入姓名' }]}]"
                   placeholder="请输入姓名"/>
        </a-form-item>

        <a-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol" label="userClass">
          <a-input v-decorator="['userClass',{rules: [{ required: true, message: '请输入班级' }]}]"
                   placeholder="请输入班级"/>
        </a-form-item>

        <a-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol" label="userQq">
          <a-input v-decorator="['userClass',{rules: [{ required: true, message: '请输入qq' }]}]"
                   placeholder="请输入qq"/>
        </a-form-item>

        <a-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol" label="userEmail">
          <a-input placeholder="Email"/>
        </a-form-item>

        <a-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol" label="userPhone">
          <a-input placeholder="Phone"  />
        </a-form-item>


        <a-form-item :label-col="formTailLayout.labelCol" :wrapper-col="formTailLayout.wrapperCol">
          <a-button type="primary" @click="check">Check</a-button>
        </a-form-item>
      </a-form>
    </div>
</template>

<script>
  const formItemLayout = {
    labelCol: { span: 4 },
    wrapperCol: { span: 8 },
  };
  const formTailLayout = {
    labelCol: { span: 4 },
    wrapperCol: { span: 8, offset: 4 },
  };
  export default {
    data () {
      return {
        checkNick: false,
        formItemLayout,
        formTailLayout,
        form: this.$form.createForm(this),
        user:{
          userId:'',
          userName:'',
          userEmail:'',
          userPhone:'',
          userQq:'',
          userClass:'',
        }
      };
    },
    methods: {
      check  () {
        this.form.validateFields(
          (err) => {
            if (!err) {
              console.info(this.user);
              console.info('success');
            }
          },
        );
      },
    },
  };
</script>

<style scoped>

</style>
