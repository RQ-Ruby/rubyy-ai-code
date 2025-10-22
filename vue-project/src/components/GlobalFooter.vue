<template>
  <a-layout-footer class="footer">
    <div class="footer-content">
      <p class="copyright">
        © {{ currentYear }} Rubyy原创项目
      </p>
    </div>
  </a-layout-footer>
</template>

<script setup lang="ts">
import { computed } from 'vue'

// 获取当前年份
const currentYear = computed(() => new Date().getFullYear())
</script>

<style scoped>
.footer {
  background: #f0f2f5;
  border-top: 1px solid #e8e8e8;
  padding: 24px 0;
  text-align: center;
  position: relative;
  bottom: 0;
  width: 100%;
  margin-top: auto;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}

.copyright {
  margin: 0;
  color: #666;
  font-size: 14px;
  line-height: 1.5;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .footer {
    padding: 16px 0;
  }
  
  .footer-content {
    padding: 0 16px;
  }
  
  .copyright {
    font-size: 12px;
  }
}
</style>