<template>
  <div id="Users">
    <!--    新建 -->
    <a-button class="editable-add-btn" @click="handleAdd">Add</a-button>

    <!--  列表  -->
    <a-table bordered :dataSource="dataSource" :columns="columns">
      <!--   修改   -->
      <template slot="userName" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'userName', $event)"/>
      </template>

      <template slot="userId" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'userId', $event)"/>
      </template>

      <template slot="userPhone" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'userPhone', $event)"/>
      </template>

      <template slot="userClass" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'userClass', $event)"/>
      </template>

      <template slot="userQq" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'userQq', $event)"/>
      </template>

      <template slot="userEmail" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'userEmail', $event)"/>
      </template>

      <!--   删除   -->

      <template slot="operation" slot-scope="text, record">
        <a-popconfirm
          v-if="dataSource.length"
          title="Sure to delete?"
          @confirm="() => onDelete(record.key)"
        >
          <a href="javascript:;">Delete</a>
        </a-popconfirm>
      </template>

    </a-table>
  </div>
</template>
<script>
    import axios from 'axios';
    import EditableCell from '../../components/EditableCell';
    export default {
        components: {
            EditableCell,
        },
        data ()  {
            return {
                dataSource: [{
                    key:'123154542123',
                    userId: '01234541345',
                    userName: 'Edward King 0',
                    userClass: '32',
                    userQq: '456125411',
                    userPhone:'12345641',
                    userCreateTime:'2019-10-13T00:00:00.000+0000',
                    userEmail:'dada@qq.com',
                }],
                count: 2,
                columns: [{
                    title: '姓名',
                    dataIndex: 'userName',
                    scopedSlots: { customRender: 'userName' },
                }, {
                    title: '学号',
                    dataIndex: 'userId',
                    scopedSlots: { customRender: 'userId' },
                }, {
                    title: '班级',
                    dataIndex: 'userClass',
                    scopedSlots: { customRender: 'userClass' },
                },
                    {
                        title: '手机号',
                        dataIndex: 'userPhone',
                        scopedSlots: { customRender: 'userPhone' },
                    },
                    {
                        title: 'QQ',
                        dataIndex: 'userQq',
                        scopedSlots: { customRender: 'userQq' },
                    },
                    {
                        title: 'Email',
                        dataIndex: 'userEmail',
                        scopedSlots: { customRender: 'userEmail' },
                    },
                    {
                        title: '创建时间',
                        dataIndex: 'userCreateTime',
                        scopedSlots: { customRender: 'userCreateTime' },
                    },
                    {
                        title: '操作',
                        dataIndex: 'operation',
                        scopedSlots: { customRender: 'operation' },
                    }],
            }
        },
        mounted(){
            this.to_getList(1,5);
        },
        methods: {
            // 通过 target 构建一个userJson
            to_buildOne(target){
                var data = {
                    userId:target.userId,
                    userName:target.userName,
                    userEmail:target.userEmail,
                    userPhone:target.userPhone,
                    userQq:target.userQq,
                    userClass:target.userClass,
            };
                return data;
            },
            // 改变值时
            onCellChange (key, dataIndex, value) {
                const dataSource = [...this.dataSource];
                const target = dataSource.find(item => item.key === key);
                console.info(target);
                if (target) {
                    target[dataIndex] = value;
                    this.dataSource = dataSource
                }
                var data = this.to_buildOne(target);
                console.info(data);
                this.to_update(data);
            },
            //删除
            onDelete (key) {
                const dataSource = [...this.dataSource];
                this.dataSource = dataSource.filter(item => item.key !== key);
                this.count -= 1;
            },
            //增加

            handleAdd() {
                const { count, dataSource } = this;
                const newData = {
                    key:this.count+1,
                    memId: '必填',
                    memName: '必填',
                    memGender: '必填',
                    memPhone: '必填',
                    memQq:'',
                    memClass:'',
                    memCollege:'',
                    memJoinTime:'',
                    memPayCheck:'',
                };
                this.dataSource = [...dataSource, newData];
                this.count = count + 1;
            },


            // 更新一个数据
            to_update(data) {
                let self = this;
                axios.patch('/userUpdate', data).then(function (response) {
                    let res = response.data;
                    if(res['reslut'] === 1){
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

            // 初始化 获取全部列表
            to_getList(pageNo,pageSize){
                let self = this;
                axios.get('/userList?'+'pageNo='+pageNo+'&pageSize='+pageSize).then(function(response){
                    self.dataSource =  response.data.records;
                    for(let i = 0;i < self.dataSource.length; i++) {
                        self.dataSource[i]['key'] = self.dataSource[i]['userId'];
                    }
                })
            },
            // 删除 一行数据
            to_deleteOne:function(target){
                let self = this;
                axios.delete('/userDelete',this.to_buildOne(target)).then(function(response){
                    self.$message({
                        message: response.data['info'],
                        type: 'success'
                    });
                })
            },


        },
    }
</script>
<style>
  .editable-cell {
    position: relative;
  }

  .editable-cell-input-wrapper,
  .editable-cell-text-wrapper {
    padding-right: 24px;
  }

  .editable-cell-text-wrapper {
    padding: 5px 24px 5px 5px;
  }

  .editable-cell-icon,
  .editable-cell-icon-check {
    position: absolute;
    right: 0;
    width: 20px;
    cursor: pointer;
  }

  .editable-cell-icon {
    line-height: 18px;
    display: none;
  }

  .editable-cell-icon-check {
    line-height: 28px;
  }

  .editable-cell:hover .editable-cell-icon {
    display: inline-block;
  }

  .editable-cell-icon:hover,
  .editable-cell-icon-check:hover {
    color: #108ee9;
  }

  .editable-add-btn {
    margin-bottom: 8px;
  }
</style>
