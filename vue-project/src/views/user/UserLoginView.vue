<template>
  <div id="userLoginPage" class="login-page">
    <div class="container">
      <div class="header">
        <h2 class="title">Ruby代码生成 用户登录</h2>
        <p class="subtitle">智能高效，一键生成你的应用体验</p>
      </div>

      <a-card class="login-card" :bordered="false">
        <a-form
          layout="vertical"
          :model="formState"
          name="user-login"
          autocomplete="off"
          @finish="handleSubmit"
        >
          <a-form-item
            label="账号"
            name="userAccount"
            :rules="[{ required: true, message: '请输入账号' }]"
          >
            <a-input
              v-model:value="formState.userAccount"
              placeholder="请输入账号"
              size="large"
            />
          </a-form-item>

          <a-form-item
            label="密码"
            name="userPassword"
            :rules="[
              { required: true, message: '请输入密码' },
              { min: 8, message: '密码不能小于 8 位' },
            ]"
          >
            <a-input-password
              v-model:value="formState.userPassword"
              placeholder="请输入密码"
              size="large"
            />
          </a-form-item>

          <div class="tips">
            没有账号？
            <RouterLink to="/user/register">去注册</RouterLink>
          </div>

          <a-form-item>
            <a-button type="primary" html-type="submit" size="large" block>
              登录
            </a-button>
          </a-form-item>
        </a-form>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { message } from 'ant-design-vue'
import { useRouter } from 'vue-router'
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { login as userLogin } from '@/api/userController.ts'

interface LoginForm {
  userAccount: string
  userPassword: string
}

const formState = reactive<API.UserLoginRequest>({
  userAccount: '',
  userPassword: '',
})

const router = useRouter()
const loginUserStore = useLoginUserStore()

const handleSubmit = async (values: any) => {
  const res = await userLogin(values)
  // 登录成功后将登录状态保存到全局状态
  if (res.data.code === 0 && res.data.data) {
    await loginUserStore.fetchLoginUser()
    message.success('登录成功')
    router.push({
      path: '/',
      replace: true,
    })
  } else {
    message.error('登录失败，' + res.data.message)
  }
}
</script>

<style scoped>
.login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #f0f5ff 0%, #ffffff 100%);
  padding: 24px;
}

.container {
  width: 100%;
  max-width: 420px;
}

.header {
  text-align: center;
  margin-bottom: 24px;
}

.title {
  margin: 0;
  font-size: 24px;
  font-weight: 600;
  color: #1d2129;
}

.subtitle {
  margin-top: 8px;
  font-size: 14px;
  color: #86909c;
}

.login-card {
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
  border-radius: 12px;
}

:deep(.ant-card-body) {
  padding: 24px;
}

.tips {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 6px;
  color: #86909c;
  margin: 6px 0 8px;
}

.tips a {
  color: #1890ff;
}

/* 响应式优化 */
@media (max-width: 576px) {
  .container {
    max-width: 100%;
  }

  .title {
    font-size: 20px;
  }

  :deep(.ant-card-body) {
    padding: 16px;
  }
}
</style>
