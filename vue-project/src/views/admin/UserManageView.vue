<template>
  <div id="userManagePage">
    <!-- 搜索表单 -->
    <a-form layout="inline" :model="searchForm" @finish="doSearch">
      <a-form-item label="账号">
        <a-input v-model:value="searchForm.userAccount" placeholder="输入账号" />
      </a-form-item>
      <a-form-item label="用户名">
        <a-input v-model:value="searchForm.userName" placeholder="输入用户名" />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit">搜索</a-button>
      </a-form-item>
    </a-form>
    <a-divider />
    <!-- 表格 -->
  </div>

  <a-table :columns="columns" :data-source="data" :pagination="pagination" @change="doTableChange">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'name'">
        <span>
          <smile-outlined />
          Name
        </span>
      </template>
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'userAvatar'">
        <a-image :src="record.userAvatar" :width="120" />
      </template>
      <template v-else-if="column.dataIndex === 'userRole'">
        <div v-if="record.userRole==='user'">
          普通用户
        </div>
        <div v-else-if="record.userRole==='admin'">
          管理员
        </div>
      </template>
      <template v-else-if="column.dataIndex === 'createTime'">
        {{ dayjs(record.createTime).format('YYYY-MM-DD HH:mm:ss') }}
      </template>

      <template v-else-if="column.key === 'action'">
        <a-button danger @click="doDelete(record.id)">删除</a-button>
      </template>
    </template>
  </a-table>
</template>
<script lang="ts" setup>
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
import { computed, onMounted, reactive, ref } from 'vue'
import { deleteUser, listUserVoByPage } from '@/api/userController.ts'
import { message } from 'ant-design-vue'
import dayjs from 'dayjs'
const columns = [
  {
    title: 'id',
    dataIndex: 'id',
  },
  {
    title: '账号',
    dataIndex: 'userAccount',
  },
  {
    title: '用户名',
    dataIndex: 'userName',
  },
  {
    title: '头像',
    dataIndex: 'userAvatar',
  },
  {
    title: '简介',
    dataIndex: 'userProfile',
  },
  {
    title: '用户角色',
    dataIndex: 'userRole',
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
  },
  {
    title: '操作',
    key: 'action',
  },
]
// 分页参数
const pagination = computed(() => {
  return {
    current: searchForm.pageNum ?? 1,
    pageSize: searchForm.pageSize ?? 10,
    total: total.value,
    showSizeChanger: true,
    showTotal: (total: number) => `共 ${total} 条`,
  }
})
// 表格变化处理
const doTableChange = (page: any) => {
  searchForm.pageNum = page.current
  searchForm.pageSize = page.pageSize
  getUserList()
}


// 获取数据
const doSearch = () => {
  // 重置页码
  searchForm.pageNum = 1
  // 获取用户列表
  getUserList()
}




//数据
const data=ref<API.UserVO[]>([])
const total=ref(0);
//搜索条件
const searchForm=reactive<API.UserQueryRequest>({
  pageNum:1,
  pageSize:10,
})
//获取用户列表
const getUserList=async()=>{
  const res=await listUserVoByPage({
    ...searchForm
  })
if(res.data.data) {
data.value = res.data.data.records??[]
total.value = res.data.data.totalRow??0
}
else{
message.error("获取数据失败"+res.data.message)
}
}

// 删除数据
const doDelete = async (id: string) => {
  if (!id) {
    return
  }
  const res = await deleteUser({ id })
  if (res.data.code === 0) {
    message.success('删除成功')
    // 刷新数据
    getUserList()
  } else {
    message.error('删除失败')
  }
}



//页面加载时获取用户列表
onMounted(()=>{
  getUserList()
})
</script>
