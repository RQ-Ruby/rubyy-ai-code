<template>
  <div class="contact-view">
    <div class="page-header">
      <h1>联系我们</h1>
      <p>有任何问题或建议，欢迎与我们联系</p>
    </div>
    
    <div class="contact-content">
      <div class="contact-info">
        <h2>联系方式</h2>
        <div class="info-item">
          <a-icon type="mail" />
          <span>邮箱：contact@rubyy.ai</span>
        </div>
        <div class="info-item">
          <a-icon type="phone" />
          <span>电话：+86 138-0000-0000</span>
        </div>
        <div class="info-item">
          <a-icon type="environment" />
          <span>地址：北京市朝阳区科技园区</span>
        </div>
        <div class="info-item">
          <a-icon type="global" />
          <span>网站：https://rubyy.ai</span>
        </div>
      </div>
      
      <div class="contact-form">
        <h2>发送消息</h2>
        <a-form
          :model="form"
          :rules="rules"
          @finish="onSubmit"
          layout="vertical"
        >
          <a-form-item label="姓名" name="name">
            <a-input v-model:value="form.name" placeholder="请输入您的姓名" />
          </a-form-item>
          
          <a-form-item label="邮箱" name="email">
            <a-input v-model:value="form.email" placeholder="请输入您的邮箱" />
          </a-form-item>
          
          <a-form-item label="主题" name="subject">
            <a-input v-model:value="form.subject" placeholder="请输入消息主题" />
          </a-form-item>
          
          <a-form-item label="消息内容" name="message">
            <a-textarea 
              v-model:value="form.message" 
              placeholder="请输入您的消息内容"
              :rows="6"
            />
          </a-form-item>
          
          <a-form-item>
            <a-button type="primary" html-type="submit" size="large" block>
              发送消息
            </a-button>
          </a-form-item>
        </a-form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { message } from 'ant-design-vue'

const form = reactive({
  name: '',
  email: '',
  subject: '',
  message: ''
})

const rules = {
  name: [
    { required: true, message: '请输入您的姓名', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入您的邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
  ],
  subject: [
    { required: true, message: '请输入消息主题', trigger: 'blur' }
  ],
  message: [
    { required: true, message: '请输入消息内容', trigger: 'blur' },
    { min: 10, message: '消息内容至少10个字符', trigger: 'blur' }
  ]
}

const onSubmit = (values: any) => {
  console.log('提交表单:', values)
  message.success('消息发送成功！我们会尽快回复您。')
  
  // 重置表单
  Object.keys(form).forEach(key => {
    form[key as keyof typeof form] = ''
  })
}
</script>

<style scoped>
.contact-view {
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

.contact-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 48px;
  max-width: 1200px;
  margin: 0 auto;
}

.contact-info h2,
.contact-form h2 {
  font-size: 24px;
  color: #333;
  margin-bottom: 24px;
}

.info-item {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
  font-size: 16px;
  color: #666;
}

.info-item .anticon {
  margin-right: 12px;
  color: #1890ff;
  font-size: 18px;
}

.contact-form {
  background: #fafafa;
  padding: 32px;
  border-radius: 8px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .contact-view {
    padding: 24px 0;
  }
  
  .page-header h1 {
    font-size: 24px;
  }
  
  .contact-content {
    grid-template-columns: 1fr;
    gap: 32px;
  }
  
  .contact-form {
    padding: 24px;
  }
  
  .info-item {
    font-size: 14px;
  }
}
</style>