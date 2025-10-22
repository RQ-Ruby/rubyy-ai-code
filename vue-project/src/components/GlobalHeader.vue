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
        <a-button type="primary" @click="handleLogin">
          登录
        </a-button>
      </div>
    </div>
  </a-layout-header>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import type { MenuProps } from 'ant-design-vue'

const router = useRouter()
const selectedKeys = ref<string[]>(['home'])

// 菜单配置
const menuItems: MenuProps['items'] = [
  {
    key: 'home',
    label: '首页',
  },
  {
    key: 'about',
    label: '关于',
  },
  {
    key: 'projects',
    label: '项目',
  },
  {
    key: 'contact',
    label: '联系',
  },
]

// 菜单点击处理
const handleMenuClick = ({ key }: { key: string }) => {
  selectedKeys.value = [key]
  // 根据菜单项进行路由跳转
  switch (key) {
    case 'home':
      router.push('/')
      break
    case 'about':
      router.push('/about')
      break
    case 'projects':
       router.push('/projects')
      break
    case 'contact':
      router.push('/contact')
      break
  }
}

// 登录处理
const handleLogin = () => {
  console.log('登录功能待实现')
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
