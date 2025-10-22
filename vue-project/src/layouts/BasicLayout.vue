<template>
  <a-layout class="basic-layout">
    <!-- 顶部导航栏 -->
    <GlobalHeader />
    
    <!-- 主要内容区域 -->
    <a-layout-content class="main-content">
      <div class="content-wrapper">
        <router-view />
      </div>
    </a-layout-content>
    
    <!-- 底部版权信息 -->
    <GlobalFooter />
  </a-layout>
</template>

<script setup lang="ts">
import GlobalHeader from '@/components/GlobalHeader.vue'
import GlobalFooter from '@/components/GlobalFooter.vue'
</script>

<style scoped>
.basic-layout {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.main-content {
  flex: 1;
  background: #fff;
  padding: 0;
  display: flex;
  flex-direction: column;
}

.content-wrapper {
  max-width: 1200px;
  margin: 0 auto;
  padding: 24px;
  width: 100%;
  flex: 1;
  display: flex;
  flex-direction: column;
}

/* 响应式设计 */
@media (max-width: 1200px) {
  .content-wrapper {
    max-width: 100%;
    padding: 24px 16px;
  }
}

@media (max-width: 768px) {
  .content-wrapper {
    padding: 16px 12px;
  }
}

@media (max-width: 576px) {
  .content-wrapper {
    padding: 12px 8px;
  }
}

/* 全局样式重置 */
:deep(.ant-layout) {
  background: #fff;
}

:deep(.ant-layout-content) {
  background: #fff;
}
</style>

<style>
/* 全局样式 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  height: 100%;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'PingFang SC', 'Hiragino Sans GB',
    'Microsoft YaHei', 'Helvetica Neue', Helvetica, Arial, sans-serif, 'Apple Color Emoji',
    'Segoe UI Emoji', 'Segoe UI Symbol';
  font-size: 14px;
  line-height: 1.5;
  color: #333;
  background-color: #fff;
}

#app {
  height: 100%;
}

/* 链接样式 */
a {
  color: #1890ff;
  text-decoration: none;
  transition: color 0.3s;
}

a:hover {
  color: #40a9ff;
}

/* 滚动条样式 */
::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 3px;
}

::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 3px;
}

::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}
</style>