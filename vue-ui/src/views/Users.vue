<template>
  <div id="Users">
    <!--    新建 -->
    <a-button class="editable-add-btn" @click="handleAdd">Add</a-button>

    <!--  列表  -->
    <a-table bordered :dataSource="dataSource" :columns="columns">
    <!--   修改   -->
      <template slot="name" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'name', $event)"/>
      </template>

      <template slot="id" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'id', $event)"/>
      </template>

      <template slot="phone" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'phone', $event)"/>
      </template>

      <template slot="class" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'class', $event)"/>
      </template>

      <template slot="qq" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'qq', $event)"/>
      </template>

      <template slot="email" slot-scope="text, record">
        <editable-cell :text="text" @change="onCellChange(record.key, 'email', $event)"/>
      </template>

    <!--   删除   -->
      <template slot="operation" slot-scope="text, record">
        <a-popconfirm
          v-if="dataSource.length"
          title="Sure to delete?"
          @confirm="() => onDelete(record.key)">
          <a href="javascript:;">Delete</a>
        </a-popconfirm>
      </template>

    </a-table>
  </div>
</template>
<script>
  import axios from 'axios';
  import EditableCell from '../components/EditableCell';
  export default {
    components: {
      EditableCell,
    },
    data () {
      return {
        dataSource: [{
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
          scopedSlots: { customRender: 'name' },
        }, {
          title: '学号',
          dataIndex: 'userId',
          scopedSlots: { customRender: 'id' },
        }, {
          title: '班级',
          dataIndex: 'userClass',
          scopedSlots: { customRender: 'class' },
        },
          {
            title: '手机号',
            dataIndex: 'userPhone',
            scopedSlots: { customRender: 'phone' },
          },
          {
            title: 'QQ',
            dataIndex: 'userQq',
            scopedSlots: { customRender: 'qq' },
          },
          {
            title: 'Email',
            dataIndex: 'userEmail',
            scopedSlots: { customRender: 'email' },
          },
          {
            title: '创建时间',
            dataIndex: 'userCreateTime',
            scopedSlots: { customRender: 'time' },
          },
          {
          title: '操作',
          dataIndex: 'operation',
          scopedSlots: { customRender: 'operation' },
        }],
      }
    },
    methods: {
      onCellChange (key, dataIndex, value) {
        const dataSource = [...this.dataSource]
        const target = dataSource.find(item => item.key === key)
        console.info(target);
        console.info(target['userCreateTime'])
        if (target) {
          target[dataIndex] = value
          this.dataSource = dataSource
        }
      },
      //删除
      onDelete (key) {
        const dataSource = [...this.dataSource]
        this.dataSource = dataSource.filter(item => item.key !== key)
        this.count -= 1;
      },
      //增加
      handleAdd () {
        const { count, dataSource } = this
        const newData = {
          userId: '必填',
          userName: '必填',
          userClass: '必填',
          userQq: '必填',
          userPhone:'',
          userCreateTime:'',
          userEmail:'',
        }
        this.dataSource = [...dataSource, newData]
        this.count = count + 1;
      },
      to_update: function(target) {
        let self = this;
        axios.post('/userUpdate', {
          userId:target['id'],
          userName:target['name'],
          userEmail:target['email'],
          userPhone:target['phone'],
          userQq:target['qq'],
          userClass:target['class'],
        }).then(function (response) {
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
