<template>
  <div id="test">

    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="姓名" prop="memName"></el-table-column>
      <el-table-column label="学号" prop="memId"></el-table-column>
      <el-table-column label="性别" prop="memGender"></el-table-column>
      <el-table-column label="学院" prop="memCollege"></el-table-column>
      <el-table-column label="班级" prop="memClass"></el-table-column>
      <el-table-column label="手机号码" prop="memPhone"></el-table-column>
      <el-table-column label="QQ" prop="memQq"></el-table-column>
      <el-table-column label="会费是否支付" prop="memPayCheck"></el-table-column>
      <el-table-column align="right">
        <template slot="header" size="mini" slot-scope="scope">
          <el-input v-model="search" size="mini" placeholder="输入姓名|学号搜索" @blur="to_search()"/>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div align="center">
      <el-button @click="dialogFormVisible = true" icon="el-icon-add" >会员添加</el-button>
    </div>

    <div class="block" style="margin-top:15px;">
      <el-pagination align='center' @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pageSize" layout="total, prev, pager, next" :total="total"></el-pagination>
    </div>



    <!-- 删除信息弹出 -->
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <span>你确定要删除此纪录吗？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="to_delete()">确 定</el-button>
  </span>
    </el-dialog>


    <!--  会员信息弹出  -->
    <el-dialog title="会员信息" :visible.sync="dialogFormVisible">

      <el-form :model="form" status-icon :rules="rules" ref="ruleForm">

        <el-form-item label="学号" prop="memId" :label-width="formLabelWidth">
          <el-input v-model.number="form.memId"></el-input>
        </el-form-item>

        <el-form-item label="姓名" prop="memName" :label-width="formLabelWidth">
          <el-input v-model="form.memName"></el-input>
        </el-form-item>

        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-select v-model="form.memGender" placeholder="性别" >
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="学院" :label-width="formLabelWidth">
          <el-select v-model="form.memCollege" placeholder="请选择" >
            <el-option
              v-for="item in colleges"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="班级" :label-width="formLabelWidth">
          <el-input v-model="form.memClass"></el-input>
        </el-form-item>

        <el-form-item label="手机号码" :label-width="formLabelWidth">
          <el-input v-model="form.memPhone"></el-input>
        </el-form-item>

        <el-form-item label="qq号" :label-width="formLabelWidth">
          <el-input v-model="form.memQq"></el-input>
        </el-form-item>

        <el-form-item label="会费是否支付" :label-width="formLabelWidth">
          <el-select v-model="form.memPayCheck" placeholder="会费是否支付">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary"  @click="submitForm('form')">确 定</el-button>
      </div>


    </el-dialog>
  </div>
</template>

<script>

    export default {
        name: "members",
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
                tableData:[],
                dialogFormVisible: false,
                dialogVisible:false,
                formLabelWidth: '120px',
                changeIndex:-1, //修改列
                deleteIndex:-1, //删除列
                currentPage: 1, // 当前页码
                total: 0,  // 总条数
                pageSize: 10,   // 每页的数据条数
                search:'',
                colleges: [],
                form: {},
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
            // 表单核对
            submitForm() {
                this.$refs.ruleForm.validate((valid) => {
                    if (valid) {
                        this.to_save();
                        this.dialogFormVisible = false;
                        if(this.changeIndex === -1){
                            this.tableData.push(this.form);
                        }else{
                            this.tableData[this.changeIndex] = this.form;
                        }
                    } else {
                        this.$message.error("请补全信息再试！");
                        return false;
                    }
                    this.form = {};
                    this.changeIndex = -1;
                });
            },

            // 修改信息
            handleEdit(index, row) {
                this.form = row;
                this.changeIndex = index;
                this.dialogFormVisible = true;
            },

            // 删除信息
            handleDelete(index, row) {
                this.dialogVisible = true;
                this.deleteIndex = index;
                console.info(this.deleteIndex);
                this.form = row;
            },

            // axios delete()
            to_delete(){
                this.dialogVisible = false;
                var _self = this;
                var deleteIndex = this.deleteIndex;
                var param = _self.form;
                this.$axios.delete('/member',{data: param}).then(function (response) {
                    var res = response.data;
                    if(res['statusCode'] === 1){
                        _self.tableData.splice(deleteIndex,1);
                        _self.$message.success(res['info']);
                    }else{
                        _self.$message.error(res['info']);
                    }
                }).catch(function (error) {
                    _self.$message.error("请求错误！");
                });
                this.form = {};
                this.deleteIndex = -1;
            },

            // axios save()
            to_save() {
                var self = this;
                this.$axios.post('/member', self.form).then(function (response) {
                    var res = response.data;
                    if(res['statusCode'] === 1){    // 后端返回成功
                        self.$message.success(res['info']);
                    }else{
                        self.$message.error(res['info']);
                    }
                }).catch(function (error) {
                    self.$message.error("请求错误！");
                });
            },

            // axios get
            to_search(){
                var self = this;
                this.$axios.get("/searchMember?search=" + self.search).then((response)=>{
                    console.info(self.search);
                    let data = response.data;
                    console.info(data);
                    self.tableData = data;
                    return response.data;
                })
            },

            // get page
            to_getPage(){
                var self = this;
                this.$axios.get("/member?currentPage=" + self.currentPage + "&pageSize=" + self.pageSize).then((response)=>{
                    let data = response.data;
                    console.info(data);
                    self.tableData = data.content;
                    self.total = data.totalElements;
                });
            },

            handleCurrentChange(val) {
                var self = this;
                this.currentPage = val;
                self.to_getPage();
            },
        },
        // 初始化得到 学院信息
        created:function getmemColleges(){
            var self = this;
            this.$axios.get("/colleges").then((response)=>{
                let data = response.data;
                for (var i=0;i<data.length;i++){
                    self.colleges.push({value: data[i].collegeName,label: data[i].collegeName});
                }
                return response.data;
            })
        },

        mounted() {
            var self = this;
            this.$axios.get("/member?currentPage=" + self.currentPage + "&pageSize=" + self.pageSize).then((response)=>{
                let data = response.data;
                self.tableData = data.content;
                self.total = data.totalElements;
            });
        }

    }
</script>

<style scoped>

</style>
