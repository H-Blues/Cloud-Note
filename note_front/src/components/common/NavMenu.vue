<template>
  <div class="nav-bar">
    <el-menu 
      :default-active="'/home'" 
      class="el-menu-demo" mode="horizontal"
      router
      @select="handleSelect" 
      background-color="#000000" text-color="#F8F8FF" active-text-color="#FFD700">
      <el-menu-item v-for="(item, i) in navList" :index="item.url" :key="i">{{item.name}}</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
    data() {
      return {
        navList: [
          {name:'首页', url:'/home'},
          {name:'笔记本', url:'/bookshelf'}
        ],

      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>
.nav-bar{
  position: fixed;
  width: 100%;
  left:0px;
  top: 0px;
}
</style>