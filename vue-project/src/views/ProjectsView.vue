<template>
  <div class="projects-view">
    <div class="page-header">
      <h1>项目展示</h1>
      <p>这里展示我们的精彩项目</p>
    </div>
    
    <div class="projects-grid">
      <div class="project-card" v-for="project in projects" :key="project.id">
        <div class="project-image">
          <img :src="project.image" :alt="project.title" />
        </div>
        <div class="project-content">
          <h3>{{ project.title }}</h3>
          <p>{{ project.description }}</p>
          <div class="project-tags">
            <span v-for="tag in project.tags" :key="tag" class="tag">{{ tag }}</span>
          </div>
          <div class="project-actions">
            <a-button type="primary" @click="viewProject(project)">查看详情</a-button>
            <a-button @click="openDemo(project)" v-if="project.demoUrl">在线演示</a-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

interface Project {
  id: number
  title: string
  description: string
  image: string
  tags: string[]
  demoUrl?: string
}

const projects = ref<Project[]>([
  {
    id: 1,
    title: 'Rubyy AI Code',
    description: '基于 Vue 3 + TypeScript + Ant Design Vue 的现代化前端项目',
    image: '/logo.png',
    tags: ['Vue 3', 'TypeScript', 'Ant Design Vue'],
    demoUrl: 'https://example.com'
  },
  {
    id: 2,
    title: '智能代码助手',
    description: 'AI 驱动的代码生成和优化工具',
    image: '/logo.png',
    tags: ['AI', 'Code Generation', 'Machine Learning'],
  },
  {
    id: 3,
    title: '响应式布局系统',
    description: '灵活的响应式布局组件库',
    image: '/logo.png',
    tags: ['CSS', 'Responsive', 'Component Library'],
  }
])

const viewProject = (project: Project) => {
  console.log('查看项目详情:', project.title)
  // 这里可以跳转到项目详情页面
}

const openDemo = (project: Project) => {
  if (project.demoUrl) {
    window.open(project.demoUrl, '_blank')
  }
}
</script>

<style scoped>
.projects-view {
  padding: 40px 0;
}

.page-header {
  text-align: center;
  margin-bottom: 48px;
}

.page-header h1 {
  font-size: 32px;
  color: #1890ff;
  margin-bottom: 16px;
}

.page-header p {
  font-size: 16px;
  color: #666;
}

.projects-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 24px;
  max-width: 1200px;
  margin: 0 auto;
}

.project-card {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.project-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
}

.project-image {
  height: 200px;
  background: #f5f5f5;
  display: flex;
  align-items: center;
  justify-content: center;
}

.project-image img {
  max-width: 80px;
  max-height: 80px;
  object-fit: contain;
}

.project-content {
  padding: 24px;
}

.project-content h3 {
  font-size: 18px;
  color: #333;
  margin-bottom: 12px;
}

.project-content p {
  color: #666;
  line-height: 1.6;
  margin-bottom: 16px;
}

.project-tags {
  margin-bottom: 20px;
}

.tag {
  display: inline-block;
  background: #f0f2f5;
  color: #666;
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 12px;
  margin-right: 8px;
  margin-bottom: 4px;
}

.project-actions {
  display: flex;
  gap: 12px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .projects-view {
    padding: 24px 0;
  }
  
  .page-header h1 {
    font-size: 24px;
  }
  
  .projects-grid {
    grid-template-columns: 1fr;
    gap: 16px;
  }
  
  .project-content {
    padding: 16px;
  }
  
  .project-actions {
    flex-direction: column;
  }
}
</style>