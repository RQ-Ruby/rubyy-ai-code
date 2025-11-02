<template>
  <a-layout-header class="header">
    <div class="header-content">
      <!-- 左侧 Logo 和标题 -->
      <div class="logo-section">
        <img src="/logo.png" alt="Logo" class="logo" />
        <span class="site-title">Rubyy AI Code</span>
      </div>

      <!-- 中间菜单 -->
      <a-menu
        v-model:selectedKeys="selectedKeys"
        mode="horizontal"
        class="header-menu"
        :items="menuItems"
        @click="handleMenuClick"
      />

      <!-- 右侧用户区域 -->
      <div class="user-section">
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.id">
            <a-dropdown>
              <a-space>
                <a-avatar :src="loginUserStore.loginUser.userAvatar" />
                {{ loginUserStore.loginUser.userName ?? '无名' }}
              </a-space>
              <template #overlay>
                <a-menu>
                  <a-menu-item @click="doLogout">
                    <LogoutOutlined />
                    退出登录
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>

      </div>
    </div>
  </a-layout-header>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'
import { type MenuProps, message } from 'ant-design-vue'
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { LogoutOutlined } from '@ant-design/icons-vue'
import { logout } from '@/api/userController.ts'

const loginUserStore = useLoginUserStore()


const router = useRouter()
const selectedKeys = ref<string[]>(['/'])


// 菜单配置项（使用路由路径作为 key，点击直接跳转）
const originItems = [
  {
    key: '/',
    label: '主页',
    title: '主页',
  },
  {
    key: '/about',
    label: '关于',
  },
  {
    key: '/projects',
    label: '项目',
  },
  {
    key: '/admin/UserManage',
    label: '用户管理',
    title: '用户管理',
  },
  {
    key: '/contact',
    label: '联系',
  },
]

// 过滤菜单项
const filterMenus = (menus = [] as MenuProps['items']) => {
  return menus?.filter((menu) => {
    const menuKey = menu?.key as string
    if (menuKey?.startsWith('/admin')) {
      const loginUser = loginUserStore.loginUser
      if (!loginUser || loginUser.userRole !== 'admin') {
        return false
      }
    }
    return true
  })
}

// 展示在菜单的路由数组
const menuItems = computed<MenuProps['items']>(() => filterMenus(originItems))


// 菜单点击处理（key 即为路由路径）
const handleMenuClick = ({ key }: { key: string }) => {
  selectedKeys.value = [key]
  router.push(key)
}




// 用户注销
const doLogout = async () => {
  const res = await logout()
  if (res.data.code === 0) {
    loginUserStore.setLoginUser({
      userName: '未登录',
    })
    message.success('退出登录成功')
    await router.push('/user/login')
  } else {
    message.error('退出登录失败，' + res.data.message)
  }
}

</script>

<style scoped>
.header {
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 0;
  position: sticky;
  top: 0;
  z-index: 1000;
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 64px;
  padding: 0 24px;
}

.logo-section {
  display: flex;
  align-items: center;
  gap: 12px;
  min-width: 200px;
}

.logo {
  height: 40px;
  width: 40px;
  object-fit: contain;
}

.site-title {
  font-size: 20px;
  font-weight: 600;
  color: #1890ff;
  white-space: nowrap;
}

.header-menu {
  flex: 1;
  border-bottom: none;
  background: transparent;
  justify-content: center;
  max-width: 600px;
}

.user-section {
  min-width: 100px;
  display: flex;
  justify-content: flex-end;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .header-content {
    padding: 0 16px;
  }

  .logo-section {
    min-width: 150px;
  }

  .site-title {
    font-size: 16px;
  }

  .header-menu {
    max-width: 300px;
  }

  .header-menu :deep(.ant-menu-item) {
    padding: 0 8px;
  }
}

@media (max-width: 576px) {
  .header-content {
    padding: 0 12px;
  }

  .logo-section {
    min-width: 120px;
  }

  .site-title {
    display: none;
  }

  .header-menu {
    max-width: 200px;
  }
}
</style>
